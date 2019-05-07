package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;



public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private List<Article> articles;
    private Context context;








    public ArticleAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_card_view, parent, false);
        return new ArticleViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(final ArticleViewHolder holder, final int position) {

        final Article article = articles.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference articleRef = FirebaseDatabase.getInstance().getReference("articles");
                articleRef.child(String.valueOf(position)).child("isViewed").setValue(true);
                Intent intent=new Intent(context,Article1.class);
                intent.putExtra("position",position);
                intent.putExtra("title",article.title);
                intent.putExtra("content",article.content);
                intent.putExtra("resource",article.resource);
                intent.putExtra("releasedDate",article.releasedDate);
                intent.putExtra("articleId",article.articleId);
                context.startActivity(intent);

            }
        });
        holder.textView_title.setText(article.title);
        holder.imageView_number.setImageResource(article.articleId);
        holder.textView_content.setText(String.valueOf(article.content));
        holder.textView_date.setText(article.releasedDate.toString());


        if(article.isRelationship){
            holder.tag1_text.setText(R.string.Relationship);
        }else if(article.isSexualBehavior){
            holder.tag1_text.setText(R.string.SexualBehavior);
        }else {
            holder.tag1_text.setText(R.string.SexualHealth);
        }




    }


    @Override
    public int getItemCount() {
        return articles.size();
    }



}





