package com.mall.bodolandmall.Adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mall.bodolandmall.Activities.CategoryActivity;
import com.mall.bodolandmall.Models.CategoryModel;
import com.mall.bodolandmall.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<CategoryModel>categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder viewHolder, int i) {

        String icon=categoryModelList.get(i).getCategoryIconLink();
        String name=categoryModelList.get(i).getCategoryName();
        viewHolder.setCategory(name,i);

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryIcon=itemView.findViewById(R.id.category_icon);
            categoryName=itemView.findViewById(R.id.category_title);
        }


        private void setCategoryIcon(){

        }

        private void setCategory(final String name, final int position){
            categoryName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position!=0){
                        Intent categoryIntent=new Intent(itemView.getContext(), CategoryActivity.class);
                        categoryIntent.putExtra("CategoryName",name);
                        itemView.getContext().startActivity(categoryIntent);
                    }

                }
            });
        }
    }
}
