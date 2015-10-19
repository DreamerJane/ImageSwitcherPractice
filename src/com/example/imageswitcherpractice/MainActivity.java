package com.example.imageswitcherpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements ViewFactory {

	private ImageSwitcher imageSwitcher;
	
	int countNumber = 0;
	
	private static final int COUNT_TIME = 5000;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        imageSwitcher = (ImageSwitcher) findViewById(R.id.image_switcher);
        //系统将自动分配ImageView用来展示图片，下面一句话很重要
        imageSwitcher.setFactory(this);
        new MyTimer(COUNT_TIME, 1000).start();
    }

    
    
    class MyTimer extends CountDownTimer {
    	
		public MyTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			switch (countNumber) {
			case 0:
				imageSwitcher.setImageResource(R.drawable.xiaoye0);
				break;
			case 1:
				imageSwitcher.setImageResource(R.drawable.xiaoye1);
				break;
			case 2:
				imageSwitcher.setImageResource(R.drawable.xiaoye2);
				break;
			case 3:
				imageSwitcher.setImageResource(R.drawable.xiaoye3);
				break;
			}
			countNumber++;
		}

		@Override
		public void onFinish() {
			startActivity(new Intent(MainActivity.this, HomeActivity.class));
			finish();
		}
    	
    }

	@Override
	public View makeView() {
		//重写方法，设置展示图片格式
		final ImageView imageView = new ImageView(this);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		return imageView;
	}
	
}
