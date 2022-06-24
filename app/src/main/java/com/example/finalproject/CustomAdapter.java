package com.example.finalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList student_id, student_name, student_family_name, student_age;

    Animation translate_anim;

    CustomAdapter(Activity activity, Context context,
                  ArrayList student_id, ArrayList student_name, ArrayList student_family_name,
                  ArrayList student_age) {
        this.activity = activity;
        this.context = context;
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_family_name = student_family_name;
        this.student_age = student_age;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent, false);

        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.student_id_txt.setText(String.valueOf(student_id.get(position)));
        holder.student_name_txt.setText(String.valueOf(student_name.get(position)));
        holder.student_family_name_txt.setText(String.valueOf(student_family_name.get(position)));
        holder.student_age_txt.setText(String.valueOf(student_age.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(student_id.get(position)));
                intent.putExtra("title", String.valueOf(student_name.get(position)));
                intent.putExtra("author", String.valueOf(student_family_name.get(position)));
                intent.putExtra("pages", String.valueOf(student_age.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return student_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView student_id_txt, student_name_txt, student_family_name_txt, student_age_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id_txt = itemView.findViewById(R.id.student_id_txt);
            student_name_txt = itemView.findViewById(R.id.student_name_txt);
            student_family_name_txt = itemView.findViewById(R.id.student_family_name_txt);
            student_age_txt = itemView.findViewById(R.id.student_age_txt);
            mainLayout = itemView.findViewById(R.id.main_layout);
            //to animate recycler view
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

        }
    }
}
