package com.jinpeng.librarytest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    private MyDataBaseHelper dbhelper;
    private final ArrayList<String> arrlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addbook = (Button) findViewById(R.id.addBook);
        dbhelper = new MyDataBaseHelper(this, "Bookstore.db", null, 4);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        arrlist.add("书名"+"   "+"类型号"+" "+"价格");
        //查询book表中的数据
        Cursor cursor = db.query("Book",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                //遍历Cursor对象，取出数据并打印
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range")String author = cursor.getString(cursor.getColumnIndex("author"));
                @SuppressLint("Range")int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                @SuppressLint("Range")double price = cursor.getDouble(cursor.getColumnIndex("price"));
                @SuppressLint("Range")int category_id = cursor.getInt(cursor.getColumnIndex("category_id"));
                arrlist.add(name+"  "+category_id+"       "+price);
            }while (cursor.moveToNext());
        }
        cursor.close();

//        create.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dbhelper.getWritableDatabase();
//            }
//        });

//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SQLiteDatabase db = dbhelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                //组装数据
//
//                values.put("category_name","历史文学");
//                values.put("category_code",1);
//                db.insert("Category",null,values);
//                values.clear();
//            }
//        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,arrlist);
        ListView listview = (ListView)findViewById(R.id.booklist);
        listview.setAdapter(adapter);

        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddBookActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        query.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SQLiteDatabase db = dbhelper.getWritableDatabase();
//                //查询book表中的数据
//                Cursor cursor = db.query("Book",null,null,null,null,null,null);
//                if(cursor.moveToFirst()){
//                    do{
//                        //遍历Cursor对象，取出数据并打印
//                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
//                        @SuppressLint("Range")String author = cursor.getString(cursor.getColumnIndex("author"));
//                        @SuppressLint("Range")int pages = cursor.getInt(cursor.getColumnIndex("pages"));
//                        @SuppressLint("Range")double price = cursor.getDouble(cursor.getColumnIndex("price"));
//                        @SuppressLint("Range")int category_id = cursor.getInt(cursor.getColumnIndex("category_id"));
//                        Log.d("MainActivity","book name is"+name);
//                        Log.d("MainActivity","author name is"+author);
//                        Log.d("MainActivity","pages have"+pages);
//                        Log.d("MainActivity","price is"+price);
//                        Log.d("MainActivity","category_id is "+category_id);
//                        arrlist.add(name+category_id+price);
//                    }while (cursor.moveToNext());
//                }
//                cursor.close();
//            }
//        });
    }
}