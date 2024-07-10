package com.example.myapplication;

// RegisterActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 회원가입 버튼 클릭 시 처리
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 여기에 회원가입 버튼을 클릭했을 때 처리할 내용을 작성
                // 예를 들어, 회원가입 화면으로 이동하는 코드를 추가할 수 있습니다.
                // 여기서는 간단히 MainActivity로 이동하는 예시를 보여줍니다.
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            }
        });
    }
}
