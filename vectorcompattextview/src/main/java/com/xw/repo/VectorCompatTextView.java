package com.xw.repo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;
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

            Drawable dl = null;
            Drawable dt = null;
            Drawable dr = null;
            Drawable db = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dl = a.getDrawable(R.styleable.VectorCompatTextView_drawableLeftCompat);
                dt = a.getDrawable(R.styleable.VectorCompatTextView_drawableTopCompat);
                dr = a.getDrawable(R.styleable.VectorCompatTextView_drawableRightCompat);
                db = a.getDrawable(R.styleable.VectorCompatTextView_drawableBottomCompat);
            } else {
                int dlId = a.getResourceId(R.styleable.VectorCompatTextView_drawableLeftCompat, -1);
                int dtId = a.getResourceId(R.styleable.VectorCompatTextView_drawableTopCompat, -1);
                int drId = a.getResourceId(R.styleable.VectorCompatTextView_drawableRightCompat, -1);
                int dbId = a.getResourceId(R.styleable.VectorCompatTextView_drawableBottomCompat, -1);

                if (dlId != -1)
                    dl = AppCompatResources.getDrawable(context, dlId);
                if (dtId != -1)
                    dt = AppCompatResources.getDrawable(context, dtId);
                if (drId != -1)
                    dr = AppCompatResources.getDrawable(context, drId);
                if (dbId != -1)
                    db = AppCompatResources.getDrawable(context, dbId);
            }

            isTintDrawableInTextColor = a.getBoolean(R.styleable.VectorCompatTextView_tintDrawableInTextColor, false);
            mDrawableCompatColor = a.getColor(R.styleable.VectorCompatTextView_drawableCompatColor, 0);
            isDrawableAdjustTextWidth = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustTextWidth, false);
            isDrawableAdjustTextHeight = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustTextHeight, false);
            isDrawableAdjustViewWidth = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustViewWidth, false);
            isDrawableAdjustViewHeight = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustViewHeight, false);
            mDrawableWidth = a.getDimensionPixelSize(R.styleable.VectorCompatTextView_drawableWidth, 0);
            mDrawableHeight = a.getDimensionPixelSize(R.styleable.VectorCompatTextView_drawableHeight, 0);
            a.recycle();

            if (mDrawableWidth < 0)
                mDrawableWidth = 0;
            if (mDrawableHeight < 0)
                mDrawableHeight = 0;
            if (isDrawableAdjustTextWidth)
                isDrawableAdjustViewWidth = false;
            if (isDrawableAdjustTextHeight)
                isDrawableAdjustViewHeight = false;

            initDrawables(dl, dt, dr, db);
        }
    }

    private void initDrawables(final Drawable... drawables) {
        for (Drawable drawable : drawables) {
            tintDrawable(drawable);
        }

        if (!isDrawableAdjustTextWidth && !isDrawableAdjustTextHeight && !isDrawableAdjustViewWidth &&
                !isDrawableAdjustViewHeight && mDrawableWidth == 0 && mDrawableHeight == 0) {
            setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
        } else {
            if (isDrawableAdjustTextWidth || isDrawableAdjustTextHeight || isDrawableAdjustViewWidth ||
                    isDrawableAdjustViewHeight) {
                boolean invalid = (
                        (isDrawableAdjustTextWidth || isDrawableAdjustViewWidth) &&
                                (drawables[0] != null || drawables[2] != null))
                        ||
                        ((isDrawableAdjustTextHeight || isDrawableAdjustViewHeight)
                                && (drawables[1] != null || drawables[3] != null));
                if (invalid) {
                    if (mDrawableWidth > 0 || mDrawableHeight > 0) {
                        resizeDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
                    } else {
                        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
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

                            adjustDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
                        }
                    });
                }
            } else if (mDrawableWidth > 0 || mDrawableHeight > 0) {
                resizeDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
            }
        }
    }

    private void tintDrawable(Drawable drawable) {
        if (drawable != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                drawable = DrawableCompat.wrap(drawable).mutate();
            }
            if (isTintDrawableInTextColor) {
                DrawableCompat.setTint(drawable, getCurrentTextColor());
            } else if (mDrawableCompatColor != 0) {
                DrawableCompat.setTint(drawable, mDrawableCompatColor);
            }
        }
    }

    private void resizeDrawables(Drawable... drawables) {
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

        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

        if (isDrawableAdjustTextWidth || isDrawableAdjustTextHeight) {
            Drawable[] drawables = getCompoundDrawables();
            if (drawables[0] == null && drawables[1] == null && drawables[2] == null && drawables[3] == null)
                return;

            adjustDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
        }
    }

    private void adjustDrawables(Drawable dl, Drawable dt, Drawable dr, Drawable db) {
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

        if (dt != null) {
            if (h == 0) h = width * dt.getIntrinsicHeight() / dt.getIntrinsicWidth();
            dt.setBounds(0, 0, width, h);
        }
        if (db != null) {
            if (h == 0) h = width * db.getIntrinsicHeight() / db.getIntrinsicWidth();
            db.setBounds(0, 0, width, h);
        }

        if (dl != null) {
            if (w == 0) w = height * dl.getIntrinsicWidth() / dl.getIntrinsicHeight();
            dl.setBounds(0, 0, w, height);
        }
        if (dr != null) {
            if (w == 0) w = height * dr.getIntrinsicWidth() / dr.getIntrinsicHeight();
            dr.setBounds(0, 0, w, height);
        }

        setCompoundDrawables(dl, dt, dr, db);
    }

    @Override
    public void setTextColor(@ColorInt int color) {
        super.setTextColor(color);

        refreshCompoundDrawables();
    }

    private void refreshCompoundDrawables() {
        Drawable[] drawables = getCompoundDrawables();
        for (Drawable drawable : drawables) {
            tintDrawable(drawable);
        }

        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public boolean isTintDrawableInTextColor() {
        return isTintDrawableInTextColor;
    }

    public void setTintDrawableInTextColor(boolean tintDrawableInTextColor) {
        if (isTintDrawableInTextColor == tintDrawableInTextColor)
            return;

        isTintDrawableInTextColor = tintDrawableInTextColor;
        refreshCompoundDrawables();
    }

    public int getDrawableCompatColor() {
        return mDrawableCompatColor;
    }

    public void setDrawableCompatColor(@ColorInt int drawableCompatColor) {
        if (mDrawableCompatColor == drawableCompatColor)
            return;

        mDrawableCompatColor = drawableCompatColor;
        refreshCompoundDrawables();
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);

        Drawable[] drawables = getCompoundDrawables();
        initDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    @Override
    public void toggle() {
        super.toggle();

        Drawable[] drawables = getCompoundDrawables();
        initDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (isTintDrawableInTextColor) {
            Drawable[] drawables = getCompoundDrawables();

            boolean needRefresh = false;
            for (Drawable drawable : drawables) {
                if (drawable != null) {
                    needRefresh = true;
                    break;
                }
            }

            if (needRefresh) {
                refreshCompoundDrawables();
            }
        }
    }
}