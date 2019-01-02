package com.example.administrator.slidingdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/12/24.
 */

public class CardManager extends RecyclerView.LayoutManager {

    //最大显示个数
    public static int MAX_COUNT = 4;
    //缩放系数
    public static float SCALE_RATIO = 0.03f;
    //平移系数
    public static float TRANS_RATIO = 5f;


    public CardManager(Context context) {
        //初始化平移系数
        TRANS_RATIO = (int) (8f * context.getResources().getDisplayMetrics().density);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return  new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        //如果RecyclerView中只有一个item或者没有，什么都不做
        if(itemCount<1){
            return;
        }
        //最底部item的角标
        int bottomPosition;
        if(itemCount<MAX_COUNT){
            bottomPosition = 0;
        }else {
            bottomPosition = itemCount - MAX_COUNT;
        }
        //从最底层的item开始摆放
        for(int i =bottomPosition;i<itemCount;i++){
            //从缓冲池中获取到itemView
            View view = recycler.getViewForPosition(i);
            //将itemView添加到RecyclerView中
            addView(view);
            //测量itemView
            measureChildWithMargins(view,0,0);
            //recyclerView宽度-itemView宽度
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
            //将itemView水平居中
            layoutDecoratedWithMargins(view,widthSpace/2,heightSpace/2
                    ,widthSpace/2+getDecoratedMeasuredWidth(view)
                    ,heightSpace/2+getDecoratedMeasuredHeight(view));
            //改变View的大小跟位置
            int level = Math.abs(i-itemCount+1);
            //如果是最底层的两个itemView
            if(level>MAX_COUNT-3){
                //进行缩放
                view.setScaleX(1-SCALE_RATIO*(MAX_COUNT-2));
                //进行平移
                view.setTranslationY(TRANS_RATIO*(MAX_COUNT-2));
            }else if(level>0){
                view.setScaleX(1-SCALE_RATIO*level);
                view.setTranslationY(TRANS_RATIO*level);
            }
        }
    }
}
