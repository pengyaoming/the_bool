package com.jxmfkj.www.the_book.db;

import android.content.Context;
import android.content.SharedPreferences;

public class MySp {
    //创建一个存储库
    public static SharedPreferences share(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Date", Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static boolean getType(Context context) {
        return share(context).getBoolean("type", false);
    }

    public static boolean setType(boolean type, Context context) {
        SharedPreferences.Editor editor = share(context).edit();
        editor.putBoolean("type", type);
        Boolean bool = editor.commit();
        return bool;
    }

}
