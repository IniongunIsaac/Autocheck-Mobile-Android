package com.iniongungroup.mobile.droid.autocheckmobile.common.utils.bindings

import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.formatted

@BindingAdapter("app:image")
fun setImage(imageView: AppCompatImageView, @DrawableRes imageRes: Int) {
    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, imageRes))
}

@BindingAdapter("app:image")
fun setImage(imageView: SimpleDraweeView, imageUrl: String) {
    imageView.setImageURI(imageUrl)
}

@BindingAdapter("app:text")
fun setText(textView: AppCompatTextView, @StringRes textId: Int) {
    textView.text = textView.context.getString(textId)
}

@BindingAdapter("app:text")
fun setText(textView: AppCompatTextView, text: String) {
    textView.text = text
}

@BindingAdapter("app:text")
fun setText(textView: TextView, value: Int) {
    textView.text = "$value"
}

@BindingAdapter("app:text")
fun setText(textView: TextView, value: Double?) {
    textView.text = "${value?.formatted() ?: 0.0}"
}

@BindingAdapter("viewVisibility")
fun setViewVisibility(view : View, visible : Boolean) {
    view.visibility = if (visible) VISIBLE else INVISIBLE
}

@BindingAdapter("textDrawable", "drawablePosition")
fun TextView.setImageDrawable(icon: Drawable, position: Int) {
    when (position) {
        0 -> setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)
        1 -> setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null)
        2 -> setCompoundDrawablesWithIntrinsicBounds(null, icon, null, null)
        3 -> setCompoundDrawablesWithIntrinsicBounds(null, null, null, icon)
    }
}

@BindingAdapter("strikeThroughText")
fun TextView.setStrikeThroughText(textString: String) {
    text = textString
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

@BindingAdapter("strikeThrough")
fun TextView.setStrikeThrough(strikeThrough: Boolean) {
    if (strikeThrough)
        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}