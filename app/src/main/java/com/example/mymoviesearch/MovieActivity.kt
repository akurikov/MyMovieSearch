package com.example.mymoviesearch

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val tv = findViewById<TextView>(R.id.movie_title)
        tv.text = intent.getStringExtra("movie_title")

        showImageByTitle(tv.text.toString())

     }


    private fun showImageByTitle(title: String) {

        val iv = findViewById<ImageView>(R.id.movie_image)
        val tv = findViewById<TextView>(R.id.movie_description)

        when (title) {
            getString(R.string.title1) -> {
                iv.background = getDrawable(R.color.color1)
                tv.text = getString(R.string.decription1)
            }
            getString(R.string.title2) -> {
                iv.background = getDrawable(R.color.color2)
                tv.text = getString(R.string.decription2)
            }
            getString(R.string.title3) -> {
                iv.background = getDrawable(R.color.color3)
                tv.text = getString(R.string.decription3)
            }
            else -> {
                iv.background = getDrawable(R.color.colorPrimary)
                tv.text = getString(R.string.decription)
            }
        }

    }

    fun onCheckBoxClicked(view: View) {}

}
