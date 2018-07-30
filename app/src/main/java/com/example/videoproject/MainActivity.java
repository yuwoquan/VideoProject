package com.example.videoproject;

import android.animation.ArgbEvaluator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;

public class MainActivity extends AppCompatActivity implements DiscreteScrollView.ScrollListener<DscrollAdapter.ViewHolder>, DiscreteScrollView.OnItemChangedListener<DscrollAdapter.ViewHolder>{

    private RecyclerView recyclerView;
    private DiscreteScrollView discreteScrollView;
    private List<HomeDateBean> homeItems;
    private InfiniteScrollAdapter dscrollAdapter;
    private int currentOverlayColor;
    private int overlayColor;
    private ArgbEvaluator evaluator;
    private DscrollAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recyclerView=findViewById(R.id.recyclerview);
        discreteScrollView=findViewById(R.id.recyclerview);
        evaluator = new ArgbEvaluator();
        currentOverlayColor = ContextCompat.getColor(getApplicationContext(), R.color.galleryCurrentItemOverlay);
        overlayColor = ContextCompat.getColor(getApplicationContext(), R.color.galleryItemOverlay);
        homeItems=new ArrayList<>();
        homeItems.add(new HomeDateBean( "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=93920&resourceType=video&editionType=default&source=aliyun"	,"「美国队长 3：内战」是一部于 2016 年上映的美国超级英雄电影，由罗素兄弟共同执导，克里斯多福·马克斯与史蒂芬·麦菲利编剧，漫威影业制作，华特迪士尼影业发行。本片改编自漫威漫画旗下的角色美国队长和马克·米勒的 2006 年漫画「内战」的故事情节，为 2014 年电影「美国队长 2：酷寒战士」的续集和漫威电影宇宙的第十三部作品。","http://img.kaiyanapp.com/4b7b21f4384c3ca806b918beef4c5869.jpeg?imageMogr2/quality/60/format/jpg"));
        homeItems.add(new HomeDateBean( "https://qiniu-xpc5.vmoviercdn.com/5b2b48babfe33.mp4","诚意献礼重庆直辖21年纪录混剪《FOREVER 21》","https://cs.vmovier.com/Uploads/avatar/2018/06/21/2.png@960w_540h_1e_1c.jpg"));
        homeItems.add(new HomeDateBean( "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=41951&resourceType=video&editionType=default&source=aliyun","	你知道自己从哪里来，但你不会知道自己将走到哪里去，就像 LeBron James 的成就之路，路上充满各种声音，但自己要有笃定的信念。这是 NIKE 2016 年（LeBron James 拿下生涯骑士队首座总冠军的一年）拍摄的广告 Come out of Nowhere 。","http://img.kaiyanapp.com/e4e9e3aa340334f739d16f8f31d0bbc3.jpeg?imageMogr2/quality/60/format/jpg"));
        homeItems.add(new HomeDateBean( "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=6170&resourceType=video&editionType=default&source=aliyun","如果一个朋友连续三年愚人节拿同样一套把戏骗你，你应该会很不屑吧。嘿，不要想得那么简单哦！如果你遇到一个朋友像这部短片主角这么拼的话，你能不能接住招还不一定呢~ From nigahiga	","http://img.kaiyanapp.com/b65ced4f0f559817e35922b6b8413856.jpeg?imageMogr2/quality/100"));
        homeItems.add(new HomeDateBean( "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=2896&resourceType=video&editionType=default&source=aliyun","那群爱作死的喜欢爬到各个摩天楼顶上的年轻人，在 2014 年来到了上海，把中国第一高楼、世界第二高楼上海中心大厦（Shanghai Tower）定为了目标。From on the roofs	","http://img.kaiyanapp.com/f061ac06b3f8e6d913c170d53e1e9303.jpeg?imageMogr2/quality/60/format/jpg"));
        homeItems.add(new HomeDateBean( "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=114592&resourceType=video&editionType=default&source=aliyun	","科学表明，性爱能改善心情，减少焦虑，但是你若被爱爱占据了生活大部分时间可能就有点过头了。那么，什么频率的啪啪啪是理想的呢？The Infographics Show 告诉你答案。	","http://img.kaiyanapp.com/1841303e19d951d35a57e228caaa667c.png?imageMogr2/quality/60/format/jpg"));


//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        adapter= new DscrollAdapter(homeItems);
//        recyclerView.setAdapter(adapter);

        /**
         * When I use RecyclerView, I will return to the item when I return to the video,
         * but using your DiscreteScrollView only has a voice and can not return to the control playback.
         * My level is limited, and I still can't handle this mistake for two days, but I really want to know why it causes this problem.
         * thanks
         */

        ((DefaultItemAnimator)discreteScrollView.getItemAnimator()).setSupportsChangeAnimations(false);
        discreteScrollView.setAdapter(new DscrollAdapter(homeItems));
        discreteScrollView.addScrollListener(this);
        discreteScrollView.addOnItemChangedListener(this);
        discreteScrollView.scrollToPosition(0);
        discreteScrollView.setItemTransitionTimeMillis(180);
    }

    @Override
    public void onCurrentItemChanged(@Nullable DscrollAdapter.ViewHolder viewHolder, int adapterPosition) {
        if (viewHolder != null) {
            viewHolder.setOverlayColor(currentOverlayColor);
        }
    }

    @Override
    public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable DscrollAdapter.ViewHolder currentHolder, @Nullable DscrollAdapter.ViewHolder newCurrent) {
        if (currentHolder != null && newCurrent != null) {
            float position = Math.abs(currentPosition);
            currentHolder.setOverlayColor(interpolate(position, currentOverlayColor, overlayColor));
            newCurrent.setOverlayColor(interpolate(position, overlayColor, currentOverlayColor));
        }
    }
    private int interpolate(float fraction, int c1, int c2) {
        return (int) evaluator.evaluate(fraction, c1, c2);
    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
