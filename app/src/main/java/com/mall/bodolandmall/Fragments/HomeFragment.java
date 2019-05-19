package com.mall.bodolandmall.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mall.bodolandmall.Adapters.CategoryAdapter;
import com.mall.bodolandmall.Adapters.GridProductLayoutAdapter;
import com.mall.bodolandmall.Adapters.HomePagerAdapter;
import com.mall.bodolandmall.Adapters.HorizontalProductScrollAdapter;
import com.mall.bodolandmall.Adapters.SliderAdapter;
import com.mall.bodolandmall.Models.CategoryModel;
import com.mall.bodolandmall.Models.HomePageModel;
import com.mall.bodolandmall.Models.HorizontalProductScrollModel;
import com.mall.bodolandmall.Models.SliderModel;
import com.mall.bodolandmall.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
    }


    private RecyclerView categoryRecyclerview;

    /* banner slider*/
    private ViewPager bannerSliderViewPager;
    private SliderAdapter sliderAdapter;
    List<SliderModel> sliderModelList;

    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    /* banner slider*/

    /* strip ads image*/
    private ImageView strip_image;
    private ConstraintLayout strip_container;

    /* strip ads image*/

    /* horizontal scroll view*/

    private TextView horizontallayoutTitle;
    private Button horizontalviewAllBtn;
    private RecyclerView horizontalRecyclerview;

    /* horizontal scroll view*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerview = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerview.setLayoutManager(layoutManager);
        List<CategoryModel> categoryModelList = new ArrayList<>();

        categoryModelList.add(new CategoryModel("Link", "Men"));
        categoryModelList.add(new CategoryModel("Link", "Mobiles"));
        categoryModelList.add(new CategoryModel("Link", "Tv"));
        categoryModelList.add(new CategoryModel("Link", "Grocery"));
        categoryModelList.add(new CategoryModel("Link", "Electonic"));
        categoryModelList.add(new CategoryModel("Link", "Bazzar"));
        categoryModelList.add(new CategoryModel("Link", "Women"));
        categoryModelList.add(new CategoryModel("Link", "Home"));
        categoryModelList.add(new CategoryModel("Link", "Laptops"));
        categoryModelList.add(new CategoryModel("Link", "Baby"));
        categoryModelList.add(new CategoryModel("Link", "Books"));
        categoryModelList.add(new CategoryModel("Link", "Health"));
        categoryModelList.add(new CategoryModel("Link", "Beauty"));
        categoryModelList.add(new CategoryModel("Link", "Toys"));
        categoryModelList.add(new CategoryModel("Link", "Automobile"));
        categoryModelList.add(new CategoryModel("Link", "Games"));

        CategoryAdapter adapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /* banner slider*/

        bannerSliderViewPager = view.findViewById(R.id.banner_slide_view_pager);

        sliderModelList = new ArrayList<SliderModel>();
//
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
//

        sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setPageMargin(20);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setAdapter(sliderAdapter);

        bannerSliderViewPager.setCurrentItem(currentPage);


        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE) {
                    pageLooper();
                }

            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();
        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startBannerSlideShow();
                }
                return false;
            }
        });

        /* banner slider*/

        /* strip ads*/

        strip_image = view.findViewById(R.id.strip_ads_image);
        strip_container = view.findViewById(R.id.strip_ads_container);
        strip_image.setImageResource(R.drawable.slider1);
        strip_container.setBackgroundColor(Color.parseColor("#000000"));


        /* strip ads*/


        /*horizontal scroll view*/
        horizontallayoutTitle = view.findViewById(R.id.horizontal_scroll_title);
        horizontalviewAllBtn = view.findViewById(R.id.horizontal_scroll_viewAll);
        horizontalRecyclerview = view.findViewById(R.id.horizontal_scroll_recyclerview);

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


        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerview.setLayoutManager(layoutManager1);
        horizontalRecyclerview.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

        /*horizontal scroll view*/

        /* grid Product layout*/
        TextView grid_layoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button grid_layout_viewAll = view.findViewById(R.id.grid_product_layout_viewallBtn);
        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);
        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));

        /* grid Product layout*/



        /* oriigainak recyclerview */

        RecyclerView recyclerView= view.findViewById(R.id.main_recy);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
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



        return view;
    }


    public void pageLooper() {

        if (currentPage == sliderModelList.size() - 1) {
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1) {
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }

    }

    private void startBannerSlideShow() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {

                if (currentPage >= sliderModelList.size()) {
                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++, false);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);

            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSlideShow() {
        timer.cancel();
    }
}
