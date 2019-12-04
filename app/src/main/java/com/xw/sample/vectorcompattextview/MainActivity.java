package com.xw.sample.vectorcompattextview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.xw.repo.VectorCompatTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final VectorCompatTextView checkableTv1 = findViewById(R.id.checkable_text_view1);
        checkableTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkableTv1.toggle();
                checkableTv1.setText(checkableTv1.isChecked() ? "SELECTED TAB" : "UNSELECTED TAB");
            }
        });
        final VectorCompatTextView checkableTv2 = findViewById(R.id.checkable_text_view2);
        checkableTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkableTv2.toggle();
                checkableTv2.setText(checkableTv2.isChecked() ? "Night Mode" : "Day Mode");
            }
        });

        final VectorCompatTextView demoTv = findViewById(R.id.demo_tv);
        ((RadioGroup) findViewById(R.id.radio_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String txt = "";
                if (checkedId == R.id.boy_rb) {
                    demoTv.setHideDrawable(false);
                    new VectorCompatTextView.CompoundDrawableConfigBuilder(demoTv)
                            .setDrawableEnd(ContextCompat.getDrawable(MainActivity.this, R.drawable.svg_ic_boy))
                            .build();
                    txt = "Jack";
                } else if (checkedId == R.id.girl_rb) {
                    demoTv.setHideDrawable(false);
                    new VectorCompatTextView.CompoundDrawableConfigBuilder(demoTv)
                            .setDrawableEnd(R.drawable.svg_ic_girl)
                            .build();
                    txt = "Mary";
                } else if (checkedId == R.id.reset_rb) {
                    demoTv.setHideDrawable(true);
                    txt = "Name";

                }
                demoTv.setText(txt);
            }
        });
    }
}
