package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity
{
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증 처리
    private DatabaseReference mDatebaseRef; //실시간 데이터베이스!
    private EditText mEtName, mEtHak; //회원가입 입력필드

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); //한번에 한 xml만

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatebaseRef = FirebaseDatabase.getInstance().getReference("sig-gather");

        mEtName = findViewById(R.id.et_Name);
        mEtHak = findViewById(R.id.et_Hak);

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //로그인 요청
                String strName = mEtName.getText().toString(); //입력받은 문자열을 strName에 할당
                String strHak = mEtHak.getText().toString(); //입력받은 문자열을 strHak에 할당

                mFirebaseAuth.signInWithEmailAndPassword(strName, strHak).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful()) {
                            //로그인 성공!!
                            Intent intent = new Intent(LogInActivity.this, RecyclerviewActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(LogInActivity.this, "로그인 실패!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //회원가입 화면으로 이동]
                Intent intent = new Intent(LogInActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });
    }
}
