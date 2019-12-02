package com.xw.repo;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewTreeObserver;

import com.xw.repo.vectorcompattextview.R;

/**
 * Compat VectorDrawable resources, which is able to use in CompoundDrawables of TextView.
 * <p>
 * Created by woxingxiao on 2017-03-19.
 */
public class VectorCompatTextView extends AppCompatCheckedTextView {

    private boolean isTintDrawableInTextColor;
    private int mDrawableCompatColor;
    private boolean isDrawableAdjustTextWidth;
    private boolean isDrawableAdjustTextHeight;
    private boolean isDrawableAdjustViewWidth;
    private boolean isDrawableAdjustViewHeight;
    private int mDrawableWidth;
    private int mDrawableHeight;

    private Drawable mDrawableStart;
    private Drawable mDrawableTop;
    private Drawable mDrawableEnd;
    private Drawable mDrawableBottom;
    boolean hideDrawable = false;
    private InvisibleDrawable mInvisibleDrawableStart;
    private InvisibleDrawable mInvisibleDrawableTop;
    private InvisibleDrawable mInvisibleDrawableEnd;
    private InvisibleDrawable mInvisibleDrawableBottom;

    public VectorCompatTextView(Context context) {
        this(context, null);
    }

    public VectorCompatTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VectorCompatTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VectorCompatTextView);

            Drawable ds = null;
            Drawable dt = null;
            Drawable de = null;
            Drawable db = null;

            Drawable dl = null;
            Drawable dr = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ds = a.getDrawable(R.styleable.VectorCompatTextView_drawableStartCompat);
                dt = a.getDrawable(R.styleable.VectorCompatTextView_drawableTopCompat);
                de = a.getDrawable(R.styleable.VectorCompatTextView_drawableEndCompat);
                db = a.getDrawable(R.styleable.VectorCompatTextView_drawableBottomCompat);

                dl = a.getDrawable(R.styleable.VectorCompatTextView_drawableLeftCompat);
                dr = a.getDrawable(R.styleable.VectorCompatTextView_drawableRightCompat);
            } else {
                int dsId = a.getResourceId(R.styleable.VectorCompatTextView_drawableStartCompat, -1);
                int dtId = a.getResourceId(R.styleable.VectorCompatTextView_drawableTopCompat, -1);
                int deId = a.getResourceId(R.styleable.VectorCompatTextView_drawableEndCompat, -1);
                int dbId = a.getResourceId(R.styleable.VectorCompatTextView_drawableBottomCompat, -1);

                int dlId = a.getResourceId(R.styleable.VectorCompatTextView_drawableLeftCompat, -1);
                int drId = a.getResourceId(R.styleable.VectorCompatTextView_drawableRightCompat, -1);

                if (dsId != -1) ds = AppCompatResources.getDrawable(context, dsId);
                if (dtId != -1) dt = AppCompatResources.getDrawable(context, dtId);
                if (deId != -1) de = AppCompatResources.getDrawable(context, deId);
                if (dbId != -1) db = AppCompatResources.getDrawable(context, dbId);

                if (dlId != -1) dl = AppCompatResources.getDrawable(context, dlId);
                if (drId != -1) dr = AppCompatResources.getDrawable(context, drId);
            }

            isTintDrawableInTextColor = a.getBoolean(R.styleable.VectorCompatTextView_tintDrawableInTextColor, false);
            mDrawableCompatColor = a.getColor(R.styleable.VectorCompatTextView_drawableCompatColor, 0);
            isDrawableAdjustTextWidth = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustTextWidth, false);
            isDrawableAdjustTextHeight = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustTextHeight, false);
            isDrawableAdjustViewWidth = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustViewWidth, false);
            isDrawableAdjustViewHeight = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustViewHeight, false);
            mDrawableWidth = a.getDimensionPixelSize(R.styleable.VectorCompatTextView_drawableWidth, 0);
            mDrawableHeight = a.getDimensionPixelSize(R.styleable.VectorCompatTextView_drawableHeight, 0);
            hideDrawable = a.getBoolean(R.styleable.VectorCompatTextView_hideDrawable, false);
            a.recycle();

            if (mDrawableWidth < 0)
                mDrawableWidth = 0;
            if (mDrawableHeight < 0)
                mDrawableHeight = 0;
            if (isDrawableAdjustTextWidth)
                isDrawableAdjustViewWidth = false;
            if (isDrawableAdjustTextHeight)
                isDrawableAdjustViewHeight = false;

            mDrawableStart = ds;
            mDrawableTop = dt;
            mDrawableEnd = de;
            mDrawableBottom = db;
            if (ds == null)
                mDrawableStart = dl;
            if (de == null)
                mDrawableEnd = dr;

            behaveDrawables();
        }
    }

    private void tintDrawable(Drawable drawable) {
        if (drawable != null) {
            if (isTintDrawableInTextColor) {
                DrawableCompat.setTint(drawable.mutate(), getCurrentTextColor());
            } else if (mDrawableCompatColor != 0) {
                DrawableCompat.setTint(drawable.mutate(), mDrawableCompatColor);
            }
        }
    }

    private void behaveDrawables(Drawable... drawables) {
        if (drawables != null && drawables.length == 4) {
            mDrawableStart = drawables[0];
            mDrawableTop = drawables[1];
            mDrawableEnd = drawables[2];
            mDrawableBottom = drawables[3];
        }
        if (drawables != null) {
            for (Drawable drawable : drawables) {
                tintDrawable(drawable);
            }
        }

        if (!isDrawableAdjustTextWidth && !isDrawableAdjustTextHeight && !isDrawableAdjustViewWidth &&
                !isDrawableAdjustViewHeight && mDrawableWidth == 0 && mDrawableHeight == 0) {
            setCompoundDrawablesInCompatibility();
            return;
        }

        if (isDrawableAdjustTextWidth || isDrawableAdjustTextHeight || isDrawableAdjustViewWidth || isDrawableAdjustViewHeight) {
            boolean invalid = ((isDrawableAdjustTextWidth || isDrawableAdjustViewWidth) && (mDrawableStart != null || mDrawableEnd != null))
                    || ((isDrawableAdjustTextHeight || isDrawableAdjustViewHeight) && (mDrawableTop != null || mDrawableBottom != null));
            if (invalid) {
                if (mDrawableWidth > 0 || mDrawableHeight > 0) {
                    resizeCompoundDrawablesInCompatibility();
                } else {
                    setCompoundDrawablesInCompatibility();
                }
            } else {
                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT < 16) {
                            getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }

                        adjustCompoundDrawablesInCompatibility();
                    }
                });
            }
            return;
        }

        if (mDrawableWidth > 0 || mDrawableHeight > 0) {
            resizeCompoundDrawablesInCompatibility();
        }
    }

    private void setCompoundDrawablesInCompatibility() {
        if (hideDrawable) {
            mInvisibleDrawableStart = createInvisibleDrawable(mDrawableStart);
            mInvisibleDrawableTop = createInvisibleDrawable(mDrawableTop);
            mInvisibleDrawableEnd = createInvisibleDrawable(mDrawableEnd);
            mInvisibleDrawableBottom = createInvisibleDrawable(mDrawableBottom);

            if (Build.VERSION.SDK_INT < 17) {
                setCompoundDrawablesWithIntrinsicBounds(
                        mInvisibleDrawableStart, mInvisibleDrawableTop, mInvisibleDrawableEnd, mInvisibleDrawableBottom);
            } else {
                setCompoundDrawablesRelativeWithIntrinsicBounds(
                        mInvisibleDrawableStart, mInvisibleDrawableTop, mInvisibleDrawableEnd, mInvisibleDrawableBottom);
            }
        } else {
            if (Build.VERSION.SDK_INT < 17) {
                setCompoundDrawablesWithIntrinsicBounds(
                        mDrawableStart, mDrawableTop, mDrawableEnd, mDrawableBottom);
            } else {
                setCompoundDrawablesRelativeWithIntrinsicBounds(
                        mDrawableStart, mDrawableTop, mDrawableEnd, mDrawableBottom);
            }
        }
    }

    private void resizeCompoundDrawablesInCompatibility() {
        Drawable[] drawables = new Drawable[]{mDrawableStart, mDrawableTop, mDrawableEnd, mDrawableBottom};
        for (Drawable drawable : drawables) {
            if (drawable == null) {
                continue;
            }

            if (mDrawableWidth > 0 && mDrawableHeight > 0) {
                drawable.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
            } else if (mDrawableWidth > 0) {
                int h = mDrawableWidth * drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth();
                drawable.setBounds(0, 0, mDrawableWidth, h);
            } else {
                int w = mDrawableHeight * drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
                drawable.setBounds(0, 0, w, mDrawableHeight);
            }
        }

        if (hideDrawable) {
            mInvisibleDrawableStart = createInvisibleDrawable(mDrawableStart);
            mInvisibleDrawableTop = createInvisibleDrawable(mDrawableTop);
            mInvisibleDrawableEnd = createInvisibleDrawable(mDrawableEnd);
            mInvisibleDrawableBottom = createInvisibleDrawable(mDrawableBottom);

            if (Build.VERSION.SDK_INT < 17) {
                setCompoundDrawables(
                        mInvisibleDrawableStart, mInvisibleDrawableTop, mInvisibleDrawableEnd, mInvisibleDrawableBottom);
            } else {
                setCompoundDrawablesRelative(
                        mInvisibleDrawableStart, mInvisibleDrawableTop, mInvisibleDrawableEnd, mInvisibleDrawableBottom);
            }
        } else {
            if (Build.VERSION.SDK_INT < 17) {
                setCompoundDrawables(mDrawableStart, mDrawableTop, mDrawableEnd, mDrawableBottom);
            } else {
                setCompoundDrawablesRelative(mDrawableStart, mDrawableTop, mDrawableEnd, mDrawableBottom);
            }
        }
    }

    private void adjustCompoundDrawablesInCompatibility() {
        int width = 0;
        int height = 0;

        if (isDrawableAdjustTextWidth) {
            Paint paint = new Paint();
            paint.setTextSize(getTextSize());
            CharSequence text = getText();
            Rect rect = new Rect();
            paint.getTextBounds(text.toString(), 0, text.length(), rect);

            width = rect.width();
        } else if (isDrawableAdjustViewWidth) {
            width = getMeasuredWidth();
        }
        if (isDrawableAdjustTextHeight) {
            Paint paint = new Paint();
            paint.setTextSize(getTextSize());
            CharSequence text = getText();
            Rect rect = new Rect();
            paint.getTextBounds(text.toString(), 0, text.length(), rect);

            height = rect.height();
        } else if (isDrawableAdjustViewHeight) {
            height = getMeasuredHeight();
        }

        int h = mDrawableHeight;
        int w = mDrawableWidth;

        if (mDrawableTop != null) {
            if (h == 0)
                h = width * mDrawableTop.getIntrinsicHeight() / mDrawableTop.getIntrinsicWidth();
            mDrawableTop.setBounds(0, 0, width, h);
        }
        if (mDrawableBottom != null) {
            if (h == 0)
                h = width * mDrawableBottom.getIntrinsicHeight() / mDrawableBottom.getIntrinsicWidth();
            mDrawableBottom.setBounds(0, 0, width, h);
        }

        if (mDrawableStart != null) {
            if (w == 0)
                w = height * mDrawableStart.getIntrinsicWidth() / mDrawableStart.getIntrinsicHeight();
            mDrawableStart.setBounds(0, 0, w, height);
        }
        if (mDrawableEnd != null) {
            if (w == 0)
                w = height * mDrawableEnd.getIntrinsicWidth() / mDrawableEnd.getIntrinsicHeight();
            mDrawableEnd.setBounds(0, 0, w, height);
        }

        if (hideDrawable) {
            mInvisibleDrawableStart = createInvisibleDrawable(mDrawableStart);
            mInvisibleDrawableTop = createInvisibleDrawable(mDrawableTop);
            mInvisibleDrawableEnd = createInvisibleDrawable(mDrawableEnd);
            mInvisibleDrawableBottom = createInvisibleDrawable(mDrawableBottom);

            if (Build.VERSION.SDK_INT < 17) {
                setCompoundDrawables(
                        mInvisibleDrawableStart, mInvisibleDrawableTop, mInvisibleDrawableEnd, mInvisibleDrawableBottom);
            } else {
                setCompoundDrawablesRelative(
                        mInvisibleDrawableStart, mInvisibleDrawableTop, mInvisibleDrawableEnd, mInvisibleDrawableBottom);
            }
        } else {
            if (Build.VERSION.SDK_INT < 17) {
                setCompoundDrawables(mDrawableStart, mDrawableTop, mDrawableEnd, mDrawableBottom);
            } else {
                setCompoundDrawablesRelative(mDrawableStart, mDrawableTop, mDrawableEnd, mDrawableBottom);
            }
        }
    }

    private InvisibleDrawable createInvisibleDrawable(Drawable drawable) {
        if (drawable != null) {
            InvisibleDrawable invisibleDrawable = new InvisibleDrawable();
            invisibleDrawable.updateBounds(drawable.getBounds());
            return invisibleDrawable;
        }
        return null;
    }

    private Drawable[] getCompoundDrawablesInCompatibility() {
        Drawable[] drawables;
        if (Build.VERSION.SDK_INT < 17) {
            drawables = getCompoundDrawables();
        } else {
            drawables = getCompoundDrawablesRelative();
        }
        mDrawableStart = drawables[0];
        mDrawableTop = drawables[1];
        mDrawableEnd = drawables[2];
        mDrawableBottom = drawables[3];

        return drawables;
    }

    private void tintCompoundDrawables() {
        Drawable[] drawables = getCompoundDrawablesInCompatibility();
        for (Drawable drawable : drawables) {
            tintDrawable(drawable);
        }

        setCompoundDrawablesInCompatibility();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

        if (isDrawableAdjustTextWidth || isDrawableAdjustTextHeight) {
            Drawable[] drawables = getCompoundDrawablesInCompatibility();
            if (drawables[0] == null && drawables[1] == null && drawables[2] == null && drawables[3] == null)
                return;

            adjustCompoundDrawablesInCompatibility();
        }
    }

    @Override
    public void setTextColor(@ColorInt int color) {
        super.setTextColor(color);

        tintCompoundDrawables();
    }

    public boolean isTintDrawableInTextColor() {
        return isTintDrawableInTextColor;
    }

    public void setTintDrawableInTextColor(boolean tintDrawableInTextColor) {
        if (isTintDrawableInTextColor == tintDrawableInTextColor)
            return;

        isTintDrawableInTextColor = tintDrawableInTextColor;
        tintCompoundDrawables();
    }

    public int getDrawableCompatColor() {
        return mDrawableCompatColor;
    }

    public void setDrawableCompatColor(@ColorInt int drawableCompatColor) {
        if (mDrawableCompatColor == drawableCompatColor)
            return;

        mDrawableCompatColor = drawableCompatColor;
        tintCompoundDrawables();
    }

    public boolean isHideDrawable() {
        return hideDrawable;
    }

    public void setHideDrawable(boolean hideDrawable) {
        if (this.hideDrawable == hideDrawable)
            return;

        this.hideDrawable = hideDrawable;
        setCompoundDrawablesInCompatibility();
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);

        behaveDrawables(getCompoundDrawablesInCompatibility());
    }

    @Override
    public void toggle() {
        super.toggle();

        behaveDrawables(getCompoundDrawablesInCompatibility());
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (isTintDrawableInTextColor) {
            Drawable[] drawables = getCompoundDrawablesInCompatibility();

            boolean needRefresh = false;
            for (Drawable drawable : drawables) {
                if (drawable != null) {
                    needRefresh = true;
                    break;
                }
            }

            if (needRefresh) {
                tintCompoundDrawables();
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private static class InvisibleDrawable extends Drawable {

        private Rect mRect = new Rect();

        void updateBounds(Rect rect) {
            if (rect != null) {
                mRect.set(rect);
            }
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
        }

        @Override
        public void setAlpha(int alpha) {
        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSPARENT;
        }

        @Override
        public int getIntrinsicWidth() {
            return mRect.width();
        }

        @Override
        public int getIntrinsicHeight() {
            return mRect.height();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static class CompoundDrawableConfigBuilder {

        private VectorCompatTextView mTextView;

        private Drawable mDrawableLeft_, mDrawableTop_, mDrawableRight_, mDrawableBottom_;
        private boolean isTintDrawableInTextColor_;
        private int mDrawableColor_;
        private boolean isDrawableAdjustTextWidth_;
        private boolean isDrawableAdjustTextHeight_;
        private boolean isDrawableAdjustViewWidth_;
        private boolean isDrawableAdjustViewHeight_;
        private int mDrawableWidth_;
        private int mDrawableHeight_;

        public CompoundDrawableConfigBuilder(@NonNull VectorCompatTextView textView) {
            mTextView = textView;
        }

        public CompoundDrawableConfigBuilder setCompoundDrawables(Drawable l, Drawable t, Drawable r, Drawable b) {
            mDrawableLeft_ = l;
            mDrawableTop_ = t;
            mDrawableRight_ = r;
            mDrawableBottom_ = b;
            return this;
        }

        public CompoundDrawableConfigBuilder tintDrawableInTextColor() {
            isTintDrawableInTextColor_ = true;
            return this;
        }

        public CompoundDrawableConfigBuilder setDrawableColor(@ColorInt int color) {
            mDrawableColor_ = color;
            return this;
        }

        public CompoundDrawableConfigBuilder drawableWidthAdjustTextWidth() {
            isDrawableAdjustTextWidth_ = true;
            return this;
        }

        public CompoundDrawableConfigBuilder drawableHeightAdjustTextHeight() {
            isDrawableAdjustTextHeight_ = true;
            return this;
        }

        public CompoundDrawableConfigBuilder drawableWidthAdjustViewWidth() {
            isDrawableAdjustViewWidth_ = true;
            return this;
        }

        public CompoundDrawableConfigBuilder drawableHeightAdjustViewHeight() {
            isDrawableAdjustViewHeight_ = true;
            return this;
        }

        public CompoundDrawableConfigBuilder setDrawableWidth(int widthInDp) {
            mDrawableWidth_ = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthInDp,
                    Resources.getSystem().getDisplayMetrics());
            return this;
        }

        public CompoundDrawableConfigBuilder setDrawableHeight(int heightInDp) {
            mDrawableHeight_ = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightInDp,
                    Resources.getSystem().getDisplayMetrics());
            return this;
        }

        public void build() {
            mTextView.isTintDrawableInTextColor = isTintDrawableInTextColor_;
            mTextView.mDrawableCompatColor = mDrawableColor_;
            mTextView.isDrawableAdjustTextWidth = isDrawableAdjustTextWidth_;
            mTextView.isDrawableAdjustTextHeight = isDrawableAdjustTextHeight_;
            mTextView.isDrawableAdjustViewWidth = isDrawableAdjustViewWidth_;
            mTextView.isDrawableAdjustViewHeight = isDrawableAdjustViewHeight_;
            mTextView.mDrawableWidth = mDrawableWidth_;
            mTextView.mDrawableHeight = mDrawableHeight_;
            mTextView.behaveDrawables(mDrawableLeft_, mDrawableTop_, mDrawableRight_, mDrawableBottom_);
        }
    }
}