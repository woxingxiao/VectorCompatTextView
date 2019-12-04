[![](https://jitpack.io/v/woxingxiao/VectorCompatTextView.svg)](https://jitpack.io/#woxingxiao/VectorCompatTextView)
[![API](https://img.shields.io/badge/API-9%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=9)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()
# VectorCompatTextView

With this multifunctional TextView, the VectorDrawable resources can be set as CompoundDrawables, furthermore, the majority of scenarios to manipulate CompoundDrawables have been supported, such as checked state, color tinting/setting, custom size setting, visibility, RTL, etc. AndroidX version is available.

这个炒鸡强大的库可以让你轻松将VectorDrawable资源设置为CompoundDrawable，并且支持大多数操控CompoundDrawable的情景，如：checked状态，颜色设置或着色，自定义宽高，隐藏或显示，RTL布局等等（当然常规资源如png/jpg，Drawable的子类等更不在话下）。有AndroidX版本。

## Screenshot
![demo5](https://github.com/woxingxiao/VectorCompatTextView/blob/master/screenshot/demo5.jpg) ![demo6](https://github.com/woxingxiao/VectorCompatTextView/blob/master/screenshot/demo6.gif)
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
  android {
      ...
      defaultConfig {
          ...
          vectorDrawables.useSupportLibrary = true  // essential
      }
  ...

  dependencies {
     // Support
     // e.g. implementation 'com.github.woxingxiao:VectorCompatTextView:2.7'
     implementation 'com.github.woxingxiao:VectorCompatTextView:${LATEST_VERSION}'

     // AndroidX
     // e.g. implementation 'com.github.woxingxiao:VectorCompatTextView:2.7-androidx'
     implementation 'com.github.woxingxiao:VectorCompatTextView:${LATEST_VERSION}-androidx'
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
    app:drawableAdjustTextHeight="true" <!-- text bounds -->
    app:drawableLeftCompat="@drawable/shape_rect_solid_red"
    app:drawableWidth="4dp"/>

<com.xw.repo.VectorCompatTextView
    android:layout_width="120dp"
    android:layout_height="wrap_content"
    android:checked="true"
    android:gravity="center"
    android:text="SELECTED TAB"
    android:textColor="@drawable/selector_text_color_tab"
    app:drawableAdjustViewWidth="true" <!-- view bounds -->
    app:drawableBottomCompat="@drawable/selector_drawable_tab"
    app:drawableHeight="2dp"/>
```
[**Check the Demo for complete usage.**](https://github.com/woxingxiao/VectorCompatTextView/blob/master/app/src/main/java/com/xw/sample/vectorcompattextview/MainActivity.java)
# LICENSE
[MIT](https://github.com/woxingxiao/VectorCompatTextView/blob/master/LICENSE)
