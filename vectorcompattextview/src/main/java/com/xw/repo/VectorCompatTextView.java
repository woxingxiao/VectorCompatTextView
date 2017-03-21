package com.xw.repo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.xw.repo.vectorcompattextview.R;

/**
 * Compat VectorDrawable resources, which is able to use in CompoundDrawables of TextView.
 * <><p/>
 * Created by woxingxiao on 2017-03-19.
 */
public class VectorCompatTextView extends AppCompatTextView {

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
                    dl = ContextCompat.getDrawable(context, dlId);
                if (dtId != -1)
                    dt = ContextCompat.getDrawable(context, dtId);
                if (drId != -1)
                    dr = ContextCompat.getDrawable(context, drId);
                if (dbId != -1)
                    db = ContextCompat.getDrawable(context, dbId);
            }

            boolean isTint = a.getBoolean(R.styleable.VectorCompatTextView_tintDrawableInTextColor, false);
            if (isTint && dl != null)
                DrawableCompat.setTint(dl, getCurrentTextColor());
            if (isTint && dt != null)
                DrawableCompat.setTint(dt, getCurrentTextColor());
            if (isTint && dr != null)
                DrawableCompat.setTint(dr, getCurrentTextColor());
            if (isTint && db != null)
                DrawableCompat.setTint(db, getCurrentTextColor());

            setCompoundDrawablesWithIntrinsicBounds(dl, dt, dr, db);

            a.recycle();
        }
    }

}