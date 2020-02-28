package com.trilogy.bootcampspot.data.datasource.db

import androidx.room.*
import com.trilogy.bootcampspot.data.EnrollmentInfo
import io.reactivex.Single

@Dao
interface EnrollmentInfoDAO {

    @Query("SELECT * from EnrollmentInfo")
    fun getAll(): Single<List<EnrollmentInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(enrollmentInfo: EnrollmentInfo)

    @Delete
    fun delete(enrollmentInfo: EnrollmentInfo)

    @Query("DELETE from EnrollmentInfo")
    fun deleteAll()

}