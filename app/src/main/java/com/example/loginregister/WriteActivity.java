package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WriteActivity extends AppCompatActivity implements PostViewListener{
    public static ArrayList<Post> postArr = new ArrayList<Post>();
    private PostAdapter postAdapter = null;
    //public int i = 0;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    Spinner menuSpinner;
    Spinner locationSpinner;
    Spinner peopleNumSpinner;
    EditText postTitle;
    EditText postEndTime;
    EditText postContents;
    Button writeDoneButton;
    Button writeCancelButton;

    final private String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        selectSpinner();

        writeDoneButton = findViewById(R.id.write_done_button);
        writeCancelButton = findViewById(R.id.write_cancel_button);

        writeDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uplodePost();
                if (isWriteDone() == true) {
                    Intent intent = new Intent(WriteActivity.this, RecyclerviewActivity.class);
                    startActivity(intent);
                }
            }
        });

        writeCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(WriteActivity.this, RecyclerviewActivity.class);
                startActivity(intent2);

            }
        });
    }

    public void selectSpinner(){
        menuSpinner = (Spinner)findViewById(R.id.menuSpinner);
        locationSpinner = (Spinner)findViewById(R.id.locationSpinner);
        peopleNumSpinner = (Spinner)findViewById(R.id.peopleNumSpinner);

        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this, R.array.menu, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> wAdapter = ArrayAdapter.createFromResource(this, R.array.where, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(this, R.array.number_of_people, android.R.layout.simple_spinner_dropdown_item);

        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        menuSpinner.setAdapter(mAdapter);

        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        locationSpinner.setAdapter(wAdapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        peopleNumSpinner.setAdapter(nAdapter);
        peopleNumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onPostClick(int position, View view) {

    }


    public boolean isWriteDone(){
        postTitle = (EditText) findViewById(R.id.postTitle);
        postEndTime = (EditText) findViewById(R.id.postEndTime);
        postContents = (EditText) findViewById(R.id.postContents);
        menuSpinner = (Spinner) findViewById(R.id.menuSpinner);
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        peopleNumSpinner = (Spinner) findViewById(R.id.peopleNumSpinner);

        if(postTitle.getText().toString().length() == 0 || postEndTime.getText().toString().length() == 0
                || postContents.getText().toString().length() == 0 || menuSpinner.getSelectedItem().toString() == "????????? ???????????????."
                || locationSpinner.getSelectedItem().toString() == "??? ?????? ???????????????." || peopleNumSpinner.getSelectedItem().toString() == "?????? ?????? ???????????????.") {
            return false;
        }
        else{
            return true;
        }
    }

    public void uplodePost(){
        postTitle = (EditText) findViewById(R.id.postTitle);
        postEndTime = (EditText) findViewById(R.id.postEndTime);
        postContents = (EditText) findViewById(R.id.postContents);
        menuSpinner = (Spinner) findViewById(R.id.menuSpinner);
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        peopleNumSpinner = (Spinner) findViewById(R.id.peopleNumSpinner);

        if (isWriteDone() == false){
            Toast.makeText(this,"?????? ????????? ???????????? ???????????????.",Toast.LENGTH_SHORT).show();
            return;
        }

        else{
            Post post = new Post();

            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String getTime = simpledate.format(date);

            post.setPostId("post_" + postArr.size());
            post.setPostTitle(postTitle.getText().toString());
            post.setPostMenu(menuSpinner.getSelectedItem().toString());
            post.setPostEatLocation(locationSpinner.getSelectedItem().toString());
            post.setPostPeopleNum(peopleNumSpinner.getSelectedItem().toString());
            post.setPostEndTime(postEndTime.getText().toString());
            post.setPostContents(postContents.getText().toString());
            post.setPostWrittenDate(getTime);

            boolean test = postArr.add(post);
            if(test == true){
                Toast.makeText(this,"????????????????????? ??????????????? ?????????????????????." + postArr.size(),Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(this,"????????????????????? ???????????? ???????????????." + postArr.size(),Toast.LENGTH_SHORT).show();
            }

            databaseReference.child("?????????").push().setValue(post);
        }
    }

    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(WriteActivity.this, RecyclerviewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
