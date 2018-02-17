package com.rohit.swipeimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyDataBase dataBase;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    int total = 0;
    ImageView imageView;
    int current=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= findViewById(R.id.imageView);
        imageView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                Toast.makeText(MainActivity.this,"Right",Toast.LENGTH_LONG).show();
                current++;
                current=current%(total+1);
                Bitmap bitmap=getBitmap(current);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onSwipeLeft() {
                if(current==1){
                    current=total;
                }else {
                    current--;
                }

                Bitmap bitmap=getBitmap(current);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onSwipeTop() {

            }

            @Override
            public void onSwipeBottom() {

            }
        });
        dataBase = new MyDataBase(this);
        loadImageToDb();
    }

    public Bitmap getBitmap(int id) {
        return dataBase.getImageById(id);
    }

    private void loadImageToDb() {
        List<Integer> ids = dataBase.getAllImageIds();
        if (ids.isEmpty()) {
            Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.p1);
            dataBase.insertImage(image1);

            image1 = BitmapFactory.decodeResource(getResources(), R.drawable.p2);
            dataBase.insertImage(image1);
            image1 = BitmapFactory.decodeResource(getResources(), R.drawable.p3);
            dataBase.insertImage(image1);
            image1 = BitmapFactory.decodeResource(getResources(), R.drawable.p4);
            dataBase.insertImage(image1);
            image1 = BitmapFactory.decodeResource(getResources(), R.drawable.p5);
            dataBase.insertImage(image1);
            ids = dataBase.getAllImageIds();
        }
        total = ids.size();
        imageView.setImageBitmap(getBitmap(1));
    }

}
