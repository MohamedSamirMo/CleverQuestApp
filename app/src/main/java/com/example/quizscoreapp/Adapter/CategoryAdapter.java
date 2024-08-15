package com.example.quizscoreapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizscoreapp.Models.CategoryModel;
import com.example.quizscoreapp.R;
import com.example.quizscoreapp.SetsActivity;
import com.example.quizscoreapp.databinding.ItemCategoryBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<CategoryModel> list;
    public CategoryAdapter(Context context, ArrayList<CategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel model = list.get(position);
        holder.binding.nameCategory.setText(model.getCategoryName());
        Picasso.get()
                .load(model.getCategoryImage()).placeholder(R.drawable.profile)
                .into(holder.binding.categoryProfileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SetsActivity.class);
                intent.putExtra("category",model.getCategoryName());
                intent.putExtra("sets",model.getSetNumber());
                intent.putExtra("key",model.getKey());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = ItemCategoryBinding.bind(itemView);
        }

    }
}
