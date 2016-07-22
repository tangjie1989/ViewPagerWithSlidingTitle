package com.tj.viewpagerwithslidingtitle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.tj.library.indicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;

    private int mCurrViewPagerIndex = 0;

    private static final String[] title = new String[]{"星球大战", "老炮儿", "万万没想到",
            "夏洛特烦恼", "寻龙诀", "煎饼侠", "港囧", "侏罗纪世界"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(adapter);

        TabPageIndicator tabTitleView = (TabPageIndicator) findViewById(R.id.tab_title_view);

        tabTitleView.setViewPager(mViewPager);
        tabTitleView.setOnPageChangeListener(new PageChangeListener());
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            int index = mViewPager.getCurrentItem();
            if (arg0 == 0 && mCurrViewPagerIndex != index) { //滑到对应页面时，才更新数据
                mCurrViewPagerIndex = index;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
        }
    }

    private class TabPageIndicatorAdapter extends FragmentPagerAdapter {

        private TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString(ContentFragment.FRAGMENT_TITLE, title[position]);
            return ContentFragment.newInstance(bundle);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            if (manager != null) {
                FragmentTransaction trans = manager.beginTransaction();
                trans.remove((Fragment) object);
                trans.commit();
            }
        }
    }

}
