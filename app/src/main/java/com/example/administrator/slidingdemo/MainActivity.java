package com.example.administrator.slidingdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CardHelperCallback.OnItemTouchCallbackListener
        ,CardAdapter.CardItemCallback{

    private RecyclerView recyclerView;
    private List<CardBean> mCardBeanList;
    private CardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }


    //加载更多
    public void loadMore(){
        List<CardBean> cardBeenList = new ArrayList<>();

        CardBean bean0 = new CardBean();
        bean0.setName("拉塞尔-韦斯特布鲁克");
        bean0.setBallYear("球龄：11年");
        bean0.setTeam("球队：俄克拉荷马雷霆");
        bean0.setPic(R.drawable.west01);

        CardBean bean1 = new CardBean();
        bean1.setName("科比-布莱恩特");
        bean1.setBallYear("球龄：20年");
        bean1.setTeam("球队：洛杉矶湖人");
        bean1.setPic(R.drawable.kobe);

        CardBean bean2 = new CardBean();
        bean2.setName("勒布朗-詹姆斯");
        bean2.setBallYear("球龄：15年");
        bean2.setTeam("球队：洛杉矶湖人");
        bean2.setPic(R.drawable.lebron01);

        CardBean bean3 = new CardBean();
        bean3.setName("保罗-乔治");
        bean3.setBallYear("球龄：9年");
        bean3.setTeam("球队：俄克拉荷马雷霆");
        bean3.setPic(R.drawable.paul01);

        CardBean bean4 = new CardBean();
        bean4.setName("迈克尔-乔丹");
        bean4.setBallYear("球龄：16年");
        bean4.setTeam("球队：芝加哥公牛");
        bean4.setPic(R.drawable.jordan);

        CardBean bean5 = new CardBean();
        bean5.setName("德维恩-韦德");
        bean5.setBallYear("球龄：15年");
        bean5.setTeam("球队：迈阿密热火");
        bean5.setPic(R.drawable.wade);

        CardBean bean6 = new CardBean();
        bean6.setName("德克-诺维斯基");
        bean6.setBallYear("球龄：21年");
        bean6.setTeam("球队：达拉斯小牛");
        bean6.setPic(R.drawable.derk);

        CardBean bean7 = new CardBean();
        bean7.setName("达米安-利拉德");
        bean7.setBallYear("球龄：7年");
        bean7.setTeam("球队：波特兰开拓者");
        bean7.setPic(R.drawable.lilade);

        CardBean bean8 = new CardBean();
        bean8.setName("史蒂芬-库里");
        bean8.setBallYear("球龄：10年");
        bean8.setTeam("球队：金州勇士");
        bean8.setPic(R.drawable.curry01);

        CardBean bean9 = new CardBean();
        bean9.setName("凯里-欧文");
        bean9.setBallYear("球龄：7年");
        bean9.setTeam("球队：波士顿凯尔特人");
        bean9.setPic(R.drawable.irving01);
        cardBeenList.add(bean0);
        cardBeenList.add(bean1);
        cardBeenList.add(bean2);
        cardBeenList.add(bean3);
        cardBeenList.add(bean4);
        cardBeenList.add(bean5);
        cardBeenList.add(bean6);
        cardBeenList.add(bean7);
        cardBeenList.add(bean8);
        cardBeenList.add(bean9);

        Collections.reverse(cardBeenList);
        mCardBeanList.addAll(0,cardBeenList);
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        mCardBeanList = new ArrayList<>();
        loadMore();
        CardManager manager = new CardManager(this);
        recyclerView.setLayoutManager(manager);
        mCardAdapter = new CardAdapter(mCardBeanList);
        mCardAdapter.setCardItemCallback(this);
        recyclerView.setAdapter(mCardAdapter);
        initTouch();
    }

    public void initTouch(){
        CardHelperCallback itemTouchHelpCallback = new CardHelperCallback();
        itemTouchHelpCallback.setListener(this);
        ItemTouchHelper helper = new ItemTouchHelper(itemTouchHelpCallback);
        //将ItemTouchHelper和RecyclerView进行绑定
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onSwiped(int position,int direction) {
        if(direction==CardManager.MAX_COUNT){//左滑
            //Toast.makeText(this,"left",Toast.LENGTH_SHORT).show();
        }else {//右滑
            //Toast.makeText(this,"right",Toast.LENGTH_SHORT).show();
        }
        if(mCardBeanList!=null){
            for (int i = 0;i<recyclerView.getChildCount();i++){
                View view = recyclerView.getChildAt(i);
                view.setAlpha(1);
            }
            mCardBeanList.remove(position);
            //加载更多
            if(mCardBeanList.size()<CardManager.MAX_COUNT){
                loadMore();
            }
            mCardAdapter.notifyDataSetChanged();
        }
    }

    //点击事件
    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,mCardBeanList.get(position).getName(),Toast.LENGTH_SHORT).show();
        Log.i("zs",mCardBeanList.get(position).getName());
    }

}

