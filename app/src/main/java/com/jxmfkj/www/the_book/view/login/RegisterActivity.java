package com.jxmfkj.www.the_book.view.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jxmfkj.www.the_book.R;
import com.jxmfkj.www.the_book.db.DataHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    private DataHelper dataHelper;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.tv_click)
    TextView tv_click;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.left_image)
    ImageView left_image;

    @OnClick({R.id.left_image, R.id.tv_click})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_image:
                //返回
                finish();
                break;
            case R.id.tv_click:
                //点击注册
                register();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dataHelper = new DataHelper(this, "UserStore.db", null, 1);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        tv_title.setText("注册");
    }

    private void register() {
        String name = edt_phone.getText().toString();
        String password = edt_password.getText().toString();
        if (chaxun(name)) {
            Toast.makeText(RegisterActivity.this, "这个账号已经被注册，请重新输入", Toast.LENGTH_LONG).show();
        } else {
            if (register_login(name, password)) {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
    //插入数据
    private boolean register_login(String name, String password) {
        SQLiteDatabase db = dataHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("name", name);
        value.put("password", password);
        db.insert("userData", null, value);
        db.close();
        return true;

    }

    private boolean chaxun(String name) {
        SQLiteDatabase db = dataHelper.getWritableDatabase();
        String Query = "Select * from userData where name = ?";
        Cursor cursor = db.rawQuery(Query, new String[]{name});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }


    }

}
