package com.xw.sample.vectorcompattextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xw.repo.VectorCompatTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final VectorCompatTextView textView = (VectorCompatTextView) findViewById(R.id.checkable_text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.toggle();
                textView.setText(textView.isChecked() ? "SELECTED TAB" : "UNSELECTED TAB");
            }
        });
    }
}
