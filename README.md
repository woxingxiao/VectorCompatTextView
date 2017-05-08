[![](https://jitpack.io/v/woxingxiao/VectorCompatTextView.svg)](https://jitpack.io/#woxingxiao/VectorCompatTextView)
[![API](https://img.shields.io/badge/API-9%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=9)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()
# VectorCompatTextView
Compatible with vector drawable(svg) usage in TextView.  
TextView的CompoundDrawable对vector drawable（svg）矢量图使用的适配。
# Screenshot
![demo1](https://github.com/woxingxiao/VectorCompatTextView/blob/master/screenshot/demo1.jpg)
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
     // e.g. compile 'com.github.woxingxiao:VectorCompatTextView:1.3'
     compile 'com.github.woxingxiao:VectorCompatTextView:${LATEST_VERSION}'
  }
```
## Usage
```xml
<com.xw.repo.VectorCompatTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:drawablePadding="4dp"
    android:gravity="center_vertical"
    android:text="GitHub"
    android:textSize="16sp"
    app:drawableLeftCompat="@drawable/svg_ic_github"/>
```
```xml
<com.xw.repo.VectorCompatTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/color_gray_light"
    android:gravity="center_vertical"
    android:padding="16dp"
    android:text="Next"
    android:textSize="16sp"
    app:drawableRightCompat="@drawable/svg_ic_arrow_right"
    app:tintDrawableInTextColor="true"/> <!--tint-->
```
```xml
<com.xw.repo.VectorCompatTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/color_gray_light"
    android:gravity="center_vertical"
    android:padding="16dp"
    android:text="Next"
    android:textSize="16sp"
    app:drawableCompatColor="@color/color_red"
    app:drawableRightCompat="@drawable/svg_ic_arrow_right"/>
```
# LICENSE
[MIT](https://github.com/woxingxiao/VectorCompatTextView/blob/master/LICENSE)
