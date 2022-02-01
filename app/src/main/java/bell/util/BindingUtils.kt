package bell.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("setImageUrl")
fun loadImage(view: ImageView, url: Int) {
    Glide
        .with(view.context)
        .load(url)
        .fitCenter()
        .into(view)
}

@BindingAdapter("setImageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide
        .with(view.context)
        .load(url)
        .fitCenter()
        .into(view)
}



