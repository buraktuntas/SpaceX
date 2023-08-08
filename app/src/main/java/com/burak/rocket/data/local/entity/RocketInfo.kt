package com.burak.rocket.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "rocket_info")
data class RocketInfo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "sliderListUrl")
    val sliderListUrl: List<String>,
    @ColumnInfo(name = "description")
    val description: String
): Parcelable