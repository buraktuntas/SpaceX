package com.burak.rocket.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.burak.rocket.data.local.entity.RocketInfo
import com.burak.rocket.util.StringListConverter

@Database(entities = [RocketInfo::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun rocketDao(): RocketInfoDao

}