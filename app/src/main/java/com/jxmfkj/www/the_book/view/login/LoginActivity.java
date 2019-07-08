package com.jxmfkj.www.the_book.view.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jxmfkj.www.the_book.MainActivity;
import com.jxmfkj.www.the_book.R;
import com.jxmfkj.www.the_book.db.DataHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private DataHelper dataHelper;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.tv_forgot)
    TextView tv_forgot;
    @BindView(R.id.tv_register)
    TextView tv_register;

    @OnClick({R.id.tv_login, R.id.tv_forgot, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                if (!TextUtils.isEmpty(edt_phone.toString()) && !TextUtils.isEmpty(edt_password.toString())) {
                    //点击登录
                    Login_click();
                }
                break;
            case R.id.tv_register:
                //点击注册
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forgot:
                //点击忘记密码
                Toast.makeText(this, "本功能暂未上线", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    private void Login_click() {
        final ProgressDialog dialog = ProgressDialog.show(this, "提示", "正在登录中");
        String name = edt_phone.getText().toString();
        String password = edt_password.getText().toString();
        if (Login(name, password)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 800);

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "登录失败，账号密码错误", Toast.LENGTH_LONG).show();
                }
            }, 800);

        }

    }

    private boolean Login(String name, String password) {
        SQLiteDatabase db = dataHelper.getWritableDatabase();
        String sql = "Select * from userData where name = ? and password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{name, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else {

            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dataHelper = new DataHelper(this, "UserStore.db", null, 1);
        ButterKnife.bind(this);
    }
}
