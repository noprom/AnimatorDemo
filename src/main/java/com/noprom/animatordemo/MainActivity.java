package com.noprom.animatordemo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    // 存放所有imageView的ID
    private int[] res = {R.id.imageView_a, R.id.imageView_b, R.id.imageView_c, R.id.imageView_d, R.id.imageView_e, R.id.imageView_f, R.id.imageView_g, R.id.imageView_h};

    // 存储所有的ImageView
    private List<ImageView> imageViewList = new ArrayList<ImageView>();

    // 标识菜单当前处于展开还是关闭
    public boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 找到所有ImageView并添加点击事件
        for (int i = 0; i < res.length; i++) {
            ImageView imageView = (ImageView) findViewById(res[i]);
            // 添加点击事件
            imageView.setOnClickListener(this);
            // 存储到imageViewList里面
            imageViewList.add(imageView);
        }
    }

    // 处理所有ImageView的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_a:
                if (!flag) {
                    startAnim();// 展开其他图标
                    flag = true;
                } else {
                    closeAnim();// 关闭其他图标
                    flag = false;
                }
                break;
            default:
                Toast.makeText(MainActivity.this,"点击了"+v.getId()+"这个ImageView",Toast.LENGTH_LONG).show();
                break;
        }

    }

    /**
     * 关闭菜单
     */
    private void closeAnim() {
        // 遍历除了imageView_a的其他imageView
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i),
                    "translationY", i * 100, 0F);
            // 设置持续时间时间
            animator.setDuration(500);
            // 设置自由落体的变化
            animator.setInterpolator(new BounceInterpolator());
            // 设置延迟时间
            animator.setStartDelay(i * 300);
            animator.start();
        }
    }

    /**
     * 展开其他的图标
     */
    private void startAnim() {
        // 遍历除了imageView_a的其他imageView
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i),
                    "translationY", 0F, i * 100);
            // 设置延迟时间
            animator.setDuration(500);
            // 设置自由落体的变化
            animator.setInterpolator(new BounceInterpolator());
            // 设置延迟时间
            animator.setStartDelay(i * 300);
            animator.start();
        }

    }
}
