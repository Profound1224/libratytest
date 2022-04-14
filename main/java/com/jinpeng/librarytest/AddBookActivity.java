package com.jinpeng.librarytest;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AddBookActivity extends Activity {

    private MyDataBaseHelper dbhelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);
        dbhelper = new MyDataBaseHelper(this,"Bookstore.db",null,4);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        EditText bookname = (EditText) findViewById(R.id.bookname);
        EditText author = (EditText) findViewById(R.id.author);
        EditText price = (EditText) findViewById(R.id.price);
        EditText pages = (EditText) findViewById(R.id.pages);
        EditText code = (EditText) findViewById(R.id.category_code);

        try{


        Button add = (Button) findViewById(R.id.addbutton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookname1 = bookname.getText().toString();
                String author1 = author.getText().toString();
                String price1 = price.getText().toString();
                String pages1 = pages.getText().toString();
                String id = code.getText().toString();
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                if (bookname1 != null && author1 != null && price1 != null && pages1 != null && id != null) {
                    //组装数据
                    values.put("name", bookname1);
                    values.put("author", author1);
                    values.put("pages", pages1);
                    values.put("price", price1);
                    values.put("category_id", id);
                    db.insert("Book", null, values);
                    Toast.makeText(AddBookActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddBookActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
