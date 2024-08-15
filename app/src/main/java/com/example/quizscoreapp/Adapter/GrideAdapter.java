package com.example.quizscoreapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.quizscoreapp.QuestionActivity;
import com.example.quizscoreapp.R;

public class GrideAdapter extends BaseAdapter {
    public int sets=0;
    private String category;

    public GrideAdapter(int sets, String category) {
        this.sets = sets;
        this.category = category;
    }

    @Override
    public int getCount() {
        return sets+1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1;
        if (convertView==null){
            view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sets,parent,false);
        }else {
            view1=convertView;
        }
        if (position==0){
            ((CardView)view1.findViewById(R.id.setCard)).setVisibility(View.GONE);
        }else {
            ((TextView)view1.findViewById(R.id.setName)).setText(String.valueOf(position));
        }

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent=new Intent(parent.getContext(), QuestionActivity.class);
                    intent.putExtra("setNumber",position);
                    intent.putExtra("categoryName",category);
                    parent.getContext().startActivity(intent);

            }
        });
        return view1;
    }

}
