[![](https://jitpack.io/v/woxingxiao/VectorCompatTextView.svg)](https://jitpack.io/#woxingxiao/VectorCompatTextView)
[![API](https://img.shields.io/badge/API-9%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=9)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()
# VectorCompatTextView
Compatible vector drawable(svg), flexible size setting and tinting color for compound drawables of TextView.
Compound drawables支持vector drawable（svg）矢量图适配，灵活的尺寸设置以及着色等功能的TextView。
# Screenshot
![demo3](https://github.com/woxingxiao/VectorCompatTextView/blob/master/screenshot/demo3.png)
## Download
root project:`build.gradle`
```groovy
  allprojects {
	 repositories {
		...
		maven { url "https://jitpack.io" }
	 }
  }
```
app:`build.gradle`
```groovy
  dependencies {
     // e.g. compile 'com.github.woxingxiao:VectorCompatTextView:1.6'
     compile 'com.github.woxingxiao:VectorCompatTextView:${LATEST_VERSION}'
  }
```
## Usage
```xml
<com.xw.repo.VectorCompatTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="GitHub"
    app:drawableLeftCompat="@drawable/svg_ic_github"/>

<com.xw.repo.VectorCompatTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Next"
    app:drawableRightCompat="@drawable/svg_ic_arrow_right"
    app:tintDrawableInTextColor="true"/>

<com.xw.repo.VectorCompatTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="ON"
    app:drawableCompatColor="#f44336"
    app:drawableBottomCompat="@drawable/svg_ic_line"/>

<com.xw.repo.VectorCompatTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="GitHub"
    app:drawableAdjustTextWidth="true"
    app:drawableTopCompat="@drawable/svg_ic_github"/>

<com.xw.repo.VectorCompatTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="GitHub"
    app:drawableWidth="24dp"
    app:drawableHeight="32dp"
    app:drawableLeftCompat="@mipmap/ic_launcher"/>

<com.xw.repo.VectorCompatTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="评论"
    app:drawableAdjustTextHeight="true"
    app:drawableLeftCompat="@drawable/shape_rect_solid_red"
    app:drawableWidth="4dp"/>
```
**Check the Demo for complete usage.**

# LICENSE
[MIT](https://github.com/woxingxiao/VectorCompatTextView/blob/master/LICENSE)
