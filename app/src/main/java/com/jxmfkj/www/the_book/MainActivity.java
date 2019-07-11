package com.jxmfkj.www.the_book;

import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.jxmfkj.www.the_book.adapter.MyAdapter;
import com.jxmfkj.www.the_book.view.BookRackFragment;
import com.jxmfkj.www.the_book.view.MyFragment;
import com.jxmfkj.www.the_book.view.StackRoomFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * time:2019/07/08
 * name:向死由生
 * 主页
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.viewPage)
    ViewPager viewPage;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private long clickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();

    }
//点击退出


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (SystemClock.uptimeMillis() - clickTime <= 1500) {
                //如果两次的时间差＜1s，就不执行操作

            } else {
                //当前系统时间的毫秒值
                clickTime = SystemClock.uptimeMillis();
                Toast.makeText(MainActivity.this, "再次点击退出", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initViews() {
        ArrayList<String> titles = new ArrayList<>();
        titles.add("书架");
        titles.add("书库");
        titles.add("我的");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BookRackFragment());
        fragments.add(new StackRoomFragment());
        fragments.add(new MyFragment());
        ArrayList<Integer> Icons = new ArrayList<>();
        Icons.add(R.drawable.vector_drawable_shuku);
        Icons.add(R.drawable.vector_drawable_bookcase);
        Icons.add(R.drawable.vector_drawable_my);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), titles, fragments, Icons, this);
        viewPage.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPage);
        for (int i = 0; i < titles.size(); i++) {
            tabLayout.getTabAt(i).setText(titles.get(i));
        }
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }


    }
}
