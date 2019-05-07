package com.example.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.Date;

public class Article1 extends AppCompatActivity {
    private TextView title_view;
    private TextView resource_view;
    private TextView date_view;
    private TextView content_view;
    private ImageView image_view;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article1);

        setTitle("Article");


        title_view = findViewById(R.id.article_title);
        resource_view = findViewById(R.id.article_resource);
        date_view = findViewById(R.id.article_date);
        content_view = findViewById(R.id.article_content);
        image_view = findViewById(R.id.article_image);
        Toolbar toolbar1 = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar1);

        Intent intent = getIntent();
        final int index = intent.getIntExtra("position", 0);
        String title = intent.getStringExtra("title");
        String resource = intent.getStringExtra("resource");
        String content = intent.getStringExtra("content");
        int id = intent.getIntExtra("articleId",0);


        title_view.setText(title);
        resource_view.setText(resource);
        content_view.setText(content);
        image_view.setImageResource(id);

        



        toolbar1.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.toolbar_save:
                        DatabaseReference articleRef = FirebaseDatabase.getInstance().getReference("articles");
                        articleRef.child(String.valueOf(index)).child("articleIsSaved").setValue(true);
                        Toast.makeText(Article1.this, "Saved Successfully! ", Toast.LENGTH_SHORT).show();

                        break;
                }
                return false;
            }


        });


    }


}
