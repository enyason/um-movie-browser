package com.android.emovie.utils

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadImage(image: Any?, placeholder: Int = 0, circular: Boolean = false) {
    Glide
        .with(context)
        .load(image)
        .placeholder(placeholder)
        .apply(
            if (circular) {
                RequestOptions.circleCropTransform()
            } else {
                RequestOptions.noTransformation()
            }
        )
        .into(this)
}

fun Fragment.toast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, length).show()
}


fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>


fun Fragment.getErrorString(data: Any): String {
    return when (data) {
        is Int -> getString(data)
        is String -> data
        else -> ""
    }

}
