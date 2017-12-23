[![](https://jitpack.io/v/woxingxiao/VectorCompatTextView.svg)](https://jitpack.io/#woxingxiao/VectorCompatTextView)
[![API](https://img.shields.io/badge/API-9%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=9)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()
# VectorCompatTextView
- Compatible VectorDrawable(svg)
- flexible size setting for CompoundDrawable
- tint drawables with color
- support StateListDrawable(mostly checked_state)

---
- 适配VectorDrawable矢量图（svg）
- 灵活地设置CompoundDrawable的尺寸大小
- 为CompoundDrawable着色
- 支持StateListDrawable（主要是checked_state）

# Screenshot
![demo5](https://github.com/woxingxiao/VectorCompatTextView/blob/master/screenshot/demo5.jpg)
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
          vectorDrawables.useSupportLibrary = true  // necessarily
      }
  ...

  dependencies {
     // e.g. compile 'com.github.woxingxiao:VectorCompatTextView:2.3'
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
**Check the Demo for complete usage.**

# LICENSE
[MIT](https://github.com/woxingxiao/VectorCompatTextView/blob/master/LICENSE)
