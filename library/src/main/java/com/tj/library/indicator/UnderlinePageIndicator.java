package com.tj.library.indicator;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.tj.library.R;
import com.tj.library.util.TabTitleScreenInfoUtil;

class UnderlinePageIndicator extends View{

    private static final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int mMaxSegments = 5; //将整个屏幕切成5段
    private int mScreenWidth;
    
    public UnderlinePageIndicator(Context context) {
        this(context, null);
    }

    public UnderlinePageIndicator(Context context, AttributeSet attrs) {
    	
        super(context, attrs);
        
        if (isInEditMode()) return;

        final Resources res = getResources();

        //Load defaults from resources
        final int defaultSelectedColor = res.getColor(R.color.view_pager_with_tab_line_under_line_color);
        
        setSelectedColor(defaultSelectedColor);
        
        initScreenSizeInfo(context);
    }

    private void initScreenSizeInfo(Context context){
    	mScreenWidth = TabTitleScreenInfoUtil.getScreenWidth(context);
    }

    private void setSelectedColor(int selectedColor) {
        mPaint.setColor(selectedColor);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        
//        final float pageWidth = (getWidth() - paddingLeft - paddingRight) / (1f * maxScrollSegments);
        
        final float left = paddingLeft + leftScrollDistance; //paddingLeft + pageWidth * (mCurrentPage + mPositionOffset);
        final float right = left + pageWidth + paddingRight;
        final float top = getPaddingTop();
        final float bottom = getHeight() - getPaddingBottom();
        
        canvas.drawRect(left, top, right, bottom, mPaint);
    }

    int getLineMaxScrollSegments(){
    	return mMaxSegments;
    }

    void setLineScrollSegments(int pageCount){
    	if(pageCount < mMaxSegments){
            mMaxSegments = pageCount;
    	}
    	pageWidth = mScreenWidth / mMaxSegments;
    }
    
    private float leftScrollDistance;//移动距离
    private float pageWidth ;//line宽度根据title宽度自动变化
    
    void updateLeftScrollDistance(float leftScrollDistance, float pageWidth){
    	this.leftScrollDistance = leftScrollDistance;
    	this.pageWidth = pageWidth;
    	invalidate();
    }

}