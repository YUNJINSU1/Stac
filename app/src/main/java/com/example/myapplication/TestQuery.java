package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.strapi.MyHttpClient;
import com.example.strapi.StrContext;

public class TestQuery extends AppCompatActivity {
    EditText id, mail, name, pass;
    StrContext vertex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view);

        // Intent로부터 StrContext 객체를 받음
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if (b != null) {
            vertex = (StrContext) b.getSerializable("context");
        }

        id = findViewById(R.id.sub_id);
        mail = findViewById(R.id.sub_email);
        name = findViewById(R.id.sub_name);
        pass = findViewById(R.id.sub_ps);
    }

    @SuppressLint("NonConstantResourceId")
    public void mOnClick(View v) {
        int id1 = v.getId();
        if (id1 == R.id.sub_btn) {
            // MyHttpClient 객체를 설정
            MyHttpClient client = new MyHttpClient();
            vertex.setClient(client);

            vertex.MyHttpClient_Format(mail.getText().toString(), id.getText().toString(), pass.getText().toString(), name.getText().toString());
            vertex.sendPostRequest();
        }
    }
}
