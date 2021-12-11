package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerviewActivity extends AppCompatActivity implements PostViewListener, View.OnClickListener{
    PostAdapter postAdapter = null;
    ArrayList<Post>postArrayList = null;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    Button writeButton, searchButton, mypageButton;
    private long bckPressedTime = 0;
    public static Context recyclerContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerContext = this;

        writeButton = (Button)findViewById(R.id.writeBtn);
        searchButton = (Button)findViewById(R.id.searchBtn);
        mypageButton = (Button)findViewById(R.id.mypageBtn);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent2);
            }
        });

        mypageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent3);
            }
        });

        init();
        initView();
    }

    protected void onStart(){
        super.onStart();
        addChildEvent();
    }

    private void init(){
        postArrayList = new ArrayList<>();
    }
    @Override
    public void onPostClick(int position, View view) {

    }

    @Override
    public void onClick(View v) {

    }


    private void initView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.postView);
        postAdapter = new PostAdapter(this, postArrayList, this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(postAdapter);
    }

    private void addChildEvent() {
        databaseReference.child("게시물").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Post post = snapshot.getValue(Post.class);

                postArrayList.add(post);

                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onBackPressed(){
        //super.onBackPressed();
        if(System.currentTimeMillis() > bckPressedTime + 2000){
            bckPressedTime = System.currentTimeMillis();
            Toast.makeText(this,"뒤로가기 버튼을 2초 안에 한번 더 누르시면 앱이 종료됩니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        if(System.currentTimeMillis() <= bckPressedTime + 2000){
            finish();
        }
    }

}