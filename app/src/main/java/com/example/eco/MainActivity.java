package com.example.eco;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   RecyclerView recyclerView;
    Adapter adapter;
    List <Model> models;
    List< CheckboxItem> DailyAct;
    Button button2;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;
    private DatabaseReference databaseReference;
    //private CollectionReference collectionReference;
    public static final String TAG = "ListViewExample";
    //private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNavigationViewListener();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.setSingletonInstance(new Picasso.Builder(this).build());

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Articles");
        databaseReference.keepSynced(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(layoutManager);

        snapHelper.attachToRecyclerView(recyclerView);

        models = new ArrayList<>();
        adapter = new Adapter(models);
        recyclerView.setAdapter(adapter);
        button2 = findViewById(R.id.button2);
        //viewPager.setPadding(-10, 0, 550, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapActivity.class);
               v.getContext().startActivity(intent);
            }
        });

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();



        updateList();


    }

    private void updateList() {

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                models.add(dataSnapshot.getValue(Model.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Model model = dataSnapshot.getValue(Model.class);

                int index = getItemIndex(model);
                models.set(index, model);
                adapter.notifyItemChanged(index);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Model model = dataSnapshot.getValue(Model.class);

                int index = getItemIndex(model);
                models.remove(index);
                adapter.notifyItemRemoved(index);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private int getItemIndex(Model model) {
        int index = 1;

        for(int i =0; i < models.size(); i++) {
            if (models.get(i).Key.equals(model.Key)) {
                index = i;
                break;
            }
        }
        return index;

    }

    LinearSnapHelper snapHelper = new LinearSnapHelper() {

        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int x, int y) {
            View centerView = findSnapView(layoutManager);
            if(centerView == null)
                return  RecyclerView.NO_POSITION;
            int position = layoutManager.getPosition(centerView);
            int targetPosition =-1;
            if(layoutManager.canScrollHorizontally()) {
                if (x < 0) {
                    targetPosition = position - 1;
                } else {
                    targetPosition = position + 1;
                }
            }

            if(layoutManager.canScrollHorizontally()) {
                if(y < 0) {
                    targetPosition = position - 1;
                }else  {
                    targetPosition = position + 1;
                }
            }

            final int firstItem = 0;
            final  int lastItem = layoutManager.getItemCount() - 1;
            targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
            return targetPosition;

        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(mToggle.onOptionsItemSelected(item) ) {

            return true;
        }
        switch (item.getItemId()) {
            case R.id.nav_account:
                Intent intent = new Intent(MainActivity.this, LoginScreenActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.settings:
                Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show();
                break;

            case R.id.help:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
