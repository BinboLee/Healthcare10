package com.example.healthcare10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    //建立页面和菜单栏的连接
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.control,menu);
        return true;
    }
    //菜单项功能设置
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.weight){
            Intent config= new Intent(this, MainActivity.class );
            startActivity(config);
        }
        if(item.getItemId()==R.id.memo){
            Intent config= new Intent(this, Main2Activity.class );
            startActivity(config);
        }
        return  super.onOptionsItemSelected(item);
    }
}
