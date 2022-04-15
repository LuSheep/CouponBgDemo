# Test
# CouponBgDemo
Android可定制边缘类型的优惠券背景

博客讲解地址：http://blog.csdn.net/shakespeare001/article/details/51873783

效果图如下：<br/>
![](https://raw.githubusercontent.com/LuSheep/CouponBgDemo/master/pics/1CAC8C9EAB1745E69F60231550CA5E37.jpg)

#使用方法：<br />
直接在布局中使用自定义的View即可，这个自定义View是继承自LinearLayout，里面的布局方式可以按照LinearLayout来摆放。通过指定不同的边缘属性来定制不同的边缘风格，如下：<br/>
```java  
<com.scu.lly.couponbgdemo.view.CouponBgView
    android:layout_width="match_parent"
    android:layout_height="200dp"
    android:layout_marginLeft="20dp"
    android:layout_marginRight="20dp"
    android:orientation="horizontal"
    android:background="#47BDBD"
    android:gravity="center_vertical"
    coupon:horizontal_style="circle"
    coupon:vertical_style="triangle">
    <LinearLayout
      android:layout_width="0dp"
      android:layout_weight="1"
      android:layout_height="match_parent"
      android:padding="30dp"
      android:orientation="vertical">
        <TextView
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:textColor="@color/white"
          android:textSize="18sp"
          android:text="顺旺基优惠券"/>
        <TextView
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:textColor="#FC4A36"
          android:layout_marginTop="15dp"
          android:textSize="16sp"
          android:text="全场五折优惠"/>
        <TextView
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:textColor="@color/white"
          android:layout_marginTop="15dp"
          android:textSize="15sp"
          android:text="券编号：2016070920160720"/>
        <TextView
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:textColor="@color/white"
          android:layout_marginTop="15dp"
          android:textSize="15sp"
          android:text="有效期：2016-07-09至2016-07-20"/>
    </LinearLayout>
    <ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginRight="20dp"
    android:src="@mipmap/iv_coupon"
    android:layout_gravity="center"/>
</com.scu.lly.couponbgdemo.view.CouponBgView>
```
