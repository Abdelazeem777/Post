package com.ahk.post;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buNewPost,buViewPosts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buNewPost=(Button)findViewById(R.id.buNewPost);
        buViewPosts=(Button)findViewById(R.id.buViewPosts);
        buNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,newpostactivity.class);
                startActivity(intent1);
            }
        });
        buViewPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this,viewpostsactivity.class);
                startActivity(intent2);
            }
        });
    }
}
