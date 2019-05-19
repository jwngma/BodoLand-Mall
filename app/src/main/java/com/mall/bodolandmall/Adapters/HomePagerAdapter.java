package com.mall.bodolandmall.Adapters;

import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
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

import com.mall.bodolandmall.Models.HomePageModel;
import com.mall.bodolandmall.Models.HorizontalProductScrollModel;
import com.mall.bodolandmall.Models.SliderModel;
import com.mall.bodolandmall.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePagerAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModelList;

    public HomePagerAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
    }


    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.STRIP_ADS_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {

        switch (viewtype) {
            case HomePageModel.BANNER_SLIDER:
                View bannerSliderView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sliding_ads_layout, viewGroup, false);
                return new BannerSliderViewHolder(bannerSliderView);
            case HomePageModel.STRIP_ADS_BANNER:
                View stripAdView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.strip_ads_layout, viewGroup, false);
                return new StripAdBannerViewHolder(stripAdView);
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_layout, viewGroup, false);
                return new HorizontalProductViewViewHolder(horizontalProductView);
            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_product_layout, viewGroup, false);
                return new GridProductViewViewHolder(gridProductView);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        switch (homePageModelList.get(i).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(i).getSliderModelList();
                ((BannerSliderViewHolder) viewHolder).setBannerSliderViewPager(sliderModelList);
                break;

            case HomePageModel.STRIP_ADS_BANNER:
                int resource = homePageModelList.get(i).getResources();
                String color = homePageModelList.get(i).getBackgroundColor();
                ((StripAdBannerViewHolder) viewHolder).setStripAd(resource, color);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horizontal_product_layout_title = homePageModelList.get(i).getTitle();
                List<HorizontalProductScrollModel> horizontalProductScrollModelList = homePageModelList.get(i).getHorizontalProductScrollModelList();
                ((HorizontalProductViewViewHolder) viewHolder).setHorizontalProductlayout(horizontalProductScrollModelList, horizontal_product_layout_title);
                break;
            case HomePageModel.GRID_PRODUCT_VIEW:
                String grid_product_layout_title = homePageModelList.get(i).getTitle();
                List<HorizontalProductScrollModel> gridProductScrollModelList = homePageModelList.get(i).getHorizontalProductScrollModelList();
                ((GridProductViewViewHolder) viewHolder).setGridProductlayout(gridProductScrollModelList, grid_product_layout_title);
                break;

            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {


        private ViewPager bannerSliderViewPager;
        private int currentPage = 2;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;

        //BannerSliderViewHolder
        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slide_view_pager);


        }

        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList) {

            SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
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
                        pageLooper(sliderModelList);
                    }

                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(sliderModelList);
            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(sliderModelList);
                    stopBannerSlideShow();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(sliderModelList);
                    }
                    return false;
                }
            });
        }

        public void pageLooper(List<SliderModel> sliderModelList) {

            if (currentPage == sliderModelList.size() - 1) {
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size() - 3;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }

        }

        private void startBannerSlideShow(final List<SliderModel> sliderModelList) {
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


    //StripAdBannerViewHolder
    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder {

        private ImageView strip_image;
        private ConstraintLayout strip_container;

        public StripAdBannerViewHolder(@NonNull View itemView) {
            super(itemView);

            strip_image = itemView.findViewById(R.id.strip_ads_image);
            strip_container = itemView.findViewById(R.id.strip_ads_container);

        }

        private void setStripAd(int resource, String color) {
            strip_image.setImageResource(resource);
            strip_container.setBackgroundColor(Color.parseColor(color));

        }
    }

    //HorizontalProductView
    public class HorizontalProductViewViewHolder extends RecyclerView.ViewHolder {
        private TextView horizontallayoutTitle;
        private Button horizontalviewAllBtn;
        private RecyclerView horizontalRecyclerview;

        public HorizontalProductViewViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontallayoutTitle = itemView.findViewById(R.id.horizontal_scroll_title);
            horizontalviewAllBtn = itemView.findViewById(R.id.horizontal_scroll_viewAll);
            horizontalRecyclerview = itemView.findViewById(R.id.horizontal_scroll_recyclerview);
        }

        private void setHorizontalProductlayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title) {
            horizontallayoutTitle.setText(title);
            if (horizontalProductScrollModelList.size() > 8) {
                horizontalviewAllBtn.setVisibility(View.VISIBLE);
            } else {
                horizontalviewAllBtn.setVisibility(View.INVISIBLE);
            }
            HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
            LinearLayoutManager layoutManager1 = new LinearLayoutManager(itemView.getContext());
            layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecyclerview.setLayoutManager(layoutManager1);
            horizontalRecyclerview.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();

        }
    }


    public class GridProductViewViewHolder extends RecyclerView.ViewHolder {

        TextView grid_layoutTitle;
        Button grid_layout_viewAll;
        GridView gridView;

        public GridProductViewViewHolder(@NonNull View itemView) {
            super(itemView);
            grid_layoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
            grid_layout_viewAll = itemView.findViewById(R.id.grid_product_layout_viewallBtn);
            gridView = itemView.findViewById(R.id.grid_product_layout_gridview);
        }

        private void setGridProductlayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title) {
            grid_layoutTitle.setText(title);
            gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));
        }
    }
}
