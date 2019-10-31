package com.example.healthcare10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG="wow";
    private ListView lt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Manager manager=new Manager(this);
        List<Item1> testlist = manager.listAll1();
        ArrayList<Map<String,String>> data=new ArrayList<>();
        int j=1;
        for (Item1 i : testlist){
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("memo",i.getMemo());
            hashMap.put("id",""+j);
            data.add(hashMap);
           j++;
        }
        SimpleAdapter listAdpter = new SimpleAdapter(
                this,
                data,
                R.layout.user,
                new String[] {"memo","id"},
                new int[] {R.id.textView3,R.id.textView5}

        );
        lt=findViewById(R.id.list);
        lt.setAdapter(listAdpter);
        lt.setOnItemClickListener(this);


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
    public void add1(View v){

        EditText et=findViewById(R.id.editText2);
        Manager manager=new Manager(this);
        manager.add1(new Item1(et.getText().toString()));
        Intent config= new Intent(this, Main2Activity.class );
        startActivity(config);
    }
    

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Manager manager=new Manager(this);
        String text=(String)((TextView)view.findViewById(R.id.textView3)).getText();
        manager.delete1(text);
        Intent config= new Intent(this, Main2Activity.class );
        startActivity(config);
    }
}
