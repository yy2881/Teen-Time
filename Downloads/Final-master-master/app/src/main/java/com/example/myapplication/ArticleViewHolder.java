package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public TextView textView_title;
    public ImageView imageView_number;
    public boolean saveArticle;
    public boolean isViewed;
    public TextView textView_content;
    public TextView textView_date;
    public ImageView imageView_user;
    public Button tag1_text;



    public ArticleViewHolder (View itemView, final Context context){
        super(itemView);
        cardView=(CardView)itemView.findViewById(R.id.card_view);
        textView_title=(TextView)itemView.findViewById(R.id.article_title);
        imageView_number=(ImageView)itemView.findViewById(R.id.article_image);
        textView_content=itemView.findViewById(R.id.article_content);
        textView_date=itemView.findViewById(R.id.released_date);
        tag1_text = itemView.findViewById(R.id.tag1);



    }




}
