package com.solutions404.trotrolive.database.trotro

import androidx.room.Dao
import androidx.room.Query
import java.util.concurrent.Flow

/**
 * Provides access to read/write operations on the schedule table.
 * Used by the view models to format the query results for use in the UI.
 */
@Dao
interface TrotroDao{

    @Query("SELECT * FROM trotro ORDER BY fare_ghs ASC")
    fun getAll(): kotlinx.coroutines.flow.Flow<List<Trotro>>

    @Query("SELECT * FROM trotro WHERE station_name = :stopName ORDER BY fare_ghs ASC")
    fun getByStopName(stopName: String): kotlinx.coroutines.flow.Flow<List<Trotro>>


    @Query("SELECT * FROM trotro WHERE station_name = :staionName ORDER BY fare_ghs ASC")
    fun getStationName(staionName: String): kotlinx.coroutines.flow.Flow<List<Trotro>>

}