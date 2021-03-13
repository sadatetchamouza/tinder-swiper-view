import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.android.tinderswipeview.Profile
import com.example.android.tinderswipeview.R
import com.example.android.tinderswipeview.Utils
import com.mindorks.placeholderview.SwipeDirection
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.*
import com.mindorks.placeholderview.annotations.swipe.*
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


@Layout(R.layout.tinder_card_view)
class TinderCard(private val context: Context,
                 private val profile: Profile,
                 private val swipeView: SwipePlaceHolderView) {

    @View(R.id.profileImageView)
    lateinit var profileImageView: ImageView

    @View(R.id.nameAgeTxt)
    lateinit var nameAgeTxt: TextView

    @View(R.id.locationNameTxt)
    lateinit var locationNameTxt: TextView

    @Resolve
    fun onResolved() {
        Glide.with(context).load(profile.imageUrl).into(profileImageView)
        nameAgeTxt.text = profile.name.toString() + ", " + profile.age
        locationNameTxt.text = profile.location
    }

    @SwipeOut
    fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
        swipeView.addView(this)
    }

    @SwipeCancelState
    fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
    }

    @SwipeInState
    fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")
    }

    @SwipeOutState
    fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }

}