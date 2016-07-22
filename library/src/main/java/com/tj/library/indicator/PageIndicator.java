package com.tj.library.indicator;

import android.support.v4.view.ViewPager;

public interface PageIndicator extends ViewPager.OnPageChangeListener {

	void setViewPager(ViewPager view);

	void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);
}
