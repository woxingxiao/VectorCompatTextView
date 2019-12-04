package com.xw.sample.vectorcompattextview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.xw.repo.VectorCompatTextView.CompoundDrawableConfigBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        checkable_text_view1.setOnClickListener {
            checkable_text_view1.toggle()
            checkable_text_view1.text =
                if (checkable_text_view1.isChecked) "SELECTED TAB" else "UNSELECTED TAB"
        }
        checkable_text_view2.setOnClickListener {
            checkable_text_view2.toggle()
            checkable_text_view2.text =
                if (checkable_text_view2.isChecked) "Night Mode" else "Day Mode"
        }

        radio_group.setOnCheckedChangeListener { _, checkedId ->
            var txt = ""
            when (checkedId) {
                R.id.boy_rb -> {
                    demo_tv.isHideDrawable = false
                    CompoundDrawableConfigBuilder(demo_tv)
                        .setDrawableEnd(
                            ContextCompat.getDrawable(this, R.drawable.svg_ic_boy)
                        )
                        .build()
                    txt = "Jack"
                }
                R.id.girl_rb -> {
                    demo_tv.isHideDrawable = false
                    CompoundDrawableConfigBuilder(demo_tv)
                        .setDrawableEnd(R.drawable.svg_ic_girl)
                        .build()
                    txt = "Mary"
                }
                R.id.reset_rb -> {
                    demo_tv.isHideDrawable = true
                    txt = "Name"
                }
            }
            demo_tv.text = txt
        }
    }
}