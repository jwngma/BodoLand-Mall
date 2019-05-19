package com.mall.bodolandmall.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.mall.bodolandmall.Adapters.HomePagerAdapter;
import com.mall.bodolandmall.Adapters.SliderAdapter;
import com.mall.bodolandmall.Models.HomePageModel;
import com.mall.bodolandmall.Models.HorizontalProductScrollModel;
import com.mall.bodolandmall.Models.SliderModel;
import com.mall.bodolandmall.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //

        /* banner slider*/

        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.drawable.slider9, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider10, "#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.slider1, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider2, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider3, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider4, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider8, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider9, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider10, "#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.slider1, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider2, "#077AE4"));

        /* banner slider*/

        /* horizontal*/
        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.account, "Redmi", "very useful Mobile", "Rs 599987/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.back, "Redmi1", "very useful Mobile", "Rs 7568/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.boy_default_profile, "Redmi2", "very useful Mobile", "Rs 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.fb, "Redmi3", "very useful Mobile", "Rs 856/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.girl_default_profile, "Redmi4", "very useful Mobile", "Rs 6355/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.love_icon, "Redmi5", "very useful Mobile", "Rs 353/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.slider1, "Redmi6", "very useful Mobile", "Rs 3573/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.slider2, "Redmi7", "very useful Mobile", "Rs 2346/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.slider3, "Redmi8", "very useful Mobile", "Rs 3457/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.boy_default_profile, "Redmi9", "very useful Mobile", "Rs 23456/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.boy_default_profile, "Redmi10", "very useful Mobile", "Rs 52999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.boy_default_profile, "Redmi11", "very useful Mobile", "Rs 9986/-"));


        /* horizontal*/

        /* oriigainak recyclerview */

        RecyclerView recyclerView=findViewById(R.id.category_recy);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        List<HomePageModel>homePageModelList= new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.slider1,"#077AE4"));
        homePageModelList.add(new HomePageModel(2,"Deals of the Day!!",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Trending Products!!",horizontalProductScrollModelList));

        HomePagerAdapter adapter1= new HomePagerAdapter(homePageModelList);
        recyclerView.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        /* oriigainak recyclerview */
        //


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_menu:
                Toast.makeText(this, item.getTitle() + " has been clicked", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
