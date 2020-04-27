package com.example.mymoviesearch

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(val title: String, val decription: String = "", val poster: String = "") : Parcelable
