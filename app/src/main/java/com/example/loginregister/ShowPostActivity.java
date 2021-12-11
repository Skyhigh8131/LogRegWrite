package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowPostActivity extends AppCompatActivity {
    private Post post;

    TextView showPosTitle;
    TextView showPosEndT;
    TextView showPosCont;
    TextView showPosM;
    TextView showPosL;
    TextView showPosNP;
    TextView showPosWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);

        Intent intent = getIntent();
        post = (Post) intent.getSerializableExtra("post");

        showView();
    }

    public void showView(){
        showPosTitle = (TextView) findViewById(R.id.showPostTitle);
        showPosEndT = (TextView) findViewById(R.id.showPostEndTime);
        showPosCont = (TextView) findViewById(R.id.showPostContents);
        showPosM = (TextView) findViewById(R.id.showPostMenu);
        showPosL = (TextView) findViewById(R.id.showPostLocation);
        showPosNP = (TextView) findViewById(R.id.showPostPeopleNum);
        showPosWD = (TextView) findViewById(R.id.showPostWrittenDate);

        showPosTitle.setText(post.getPostTitle().toString());
        showPosEndT.setText(post.getPostEndTime().toString());
        showPosCont.setText(post.getPostContents().toString());
        showPosM.setText(post.getPostMenu().toString());
        showPosL.setText(post.getPostEatLocation().toString());
        showPosNP.setText(post.getPostPeopleNum().toString());
        showPosWD.setText(post.getPostWrittenDate().toString());
    }

    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(ShowPostActivity.this, RecyclerviewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
