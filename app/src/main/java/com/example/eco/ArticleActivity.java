package com.example.eco;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends AppCompatActivity {
    RecyclerView recyclerViewArticle;
    Adapter adapterArticle;
    String articles;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_view);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Articles");
        databaseReference.keepSynced(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewArticle = (RecyclerView) findViewById(R.id.recyclerViewArticle);

        recyclerViewArticle.setLayoutManager(layoutManager);
        articles = getIntent().getExtras().getString("article");

        TextView articleTextView = (TextView) findViewById(R.id.articleTextView);
        articleTextView.setText(articles);


    }



}
