package com.starapps.kotlinmvvm.repository.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "ImagesTable")
@Parcelize
data class ImageListResponseItem(
    @SerializedName("author")
    val author: String,
    @SerializedName("download_url")
    val downloadUrl: String,
    @SerializedName("height")
    val height: Int,
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
) : Parcelable