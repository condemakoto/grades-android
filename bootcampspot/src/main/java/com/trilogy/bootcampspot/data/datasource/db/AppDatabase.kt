package com.trilogy.bootcampspot.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trilogy.bootcampspot.data.EnrollmentInfo

@Database(entities = arrayOf(EnrollmentInfo::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun enrollmentInfoDao(): EnrollmentInfoDAO
}
