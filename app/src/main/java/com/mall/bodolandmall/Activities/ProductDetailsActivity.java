package com.mall.bodolandmall.Activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mall.bodolandmall.Adapters.ProductDetailsAdapter;
import com.mall.bodolandmall.Adapters.ProductImagesAdapter;
import com.mall.bodolandmall.R;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ProductDetailsActivity";
    private ViewPager productImagesViewPager;
    private TabLayout viewPagerIndicator;

    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTablayout;

    private FloatingActionButton addToWishListBtn;
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;

    /* rating layout*/

    private LinearLayout rateNowContainer;

    /* rating layout*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Log.d(TAG, "onCreate: Product details activity has been started");

        Toolbar toolbar = (Toolbar) findViewById(R.id.product_details_toolbar);
        setSupportActionBar(toolbar);
        // String title = getIntent().getStringExtra("CategoryName");
        // getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initProductImage();
        initProductDescription();
        initrateNowConatiner();
    }

    /* rating star*/

    private void initrateNowConatiner() {
        rateNowContainer=findViewById(R.id.star_rating_container);
        for (int x=0;x<rateNowContainer.getChildCount();x++){
            final  int startPosition=x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(startPosition);
                }
            });
        }
    }

    private void setRating(int startPosition) {
        for (int x=0;x<rateNowContainer.getChildCount();x++){
            ImageView startBtn=(ImageView)rateNowContainer.getChildAt(x);
            startBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if (x<=startPosition){
                startBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }

    }
    /* rating star*/


    private void initProductImage() {

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewPager_indicator);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.boy_default_profile);
        productImages.add(R.drawable.boy_default_profile);
        productImages.add(R.drawable.boy_default_profile);
        productImages.add(R.drawable.boy_default_profile);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);
        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);
        initAddToWishList();
    }

    private void initAddToWishList() {
        addToWishListBtn = findViewById(R.id.add_to_wish_listBtn);
        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#fff")));
                } else {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimaryDark));

                }
            }
        });
    }

    private void initProductDescription() {
        productDetailsViewPager = findViewById(R.id.product_details_viewpager);
        productDetailsTablayout = findViewById(R.id.product_details_tablayout);

        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTablayout.getTabCount()));
        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTablayout));

        productDetailsTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_and_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.product_detail_card:
                Toast.makeText(this, item.getTitle() + " has been clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.product_detail_search:
                Toast.makeText(this, item.getTitle() + " has been clicked", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
