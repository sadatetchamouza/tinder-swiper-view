package com.example.android.tinderswipeview

import TinderCard
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android.tinderswipeview.Utils.loadProfiles
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var mSwipeView: SwipePlaceHolderView
    private var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSwipeView = findViewById<View>(R.id.swipeView) as SwipePlaceHolderView
        mContext = applicationContext
        mSwipeView.getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
                .setDisplayViewCount(3)
                .setSwipeDecor(SwipeDecor()
                        .setPaddingTop(30)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view))
        for (profile in loadProfiles(this.applicationContext)!!) {
            mSwipeView.addView(TinderCard(this, profile, mSwipeView))
        }
        findViewById<View>(R.id.rejectBtn).setOnClickListener { mSwipeView.doSwipe(false) }
        findViewById<View>(R.id.acceptBtn).setOnClickListener { mSwipeView.doSwipe(true) }
    }
}