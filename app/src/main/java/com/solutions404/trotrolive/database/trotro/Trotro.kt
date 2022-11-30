package com.solutions404.trotrolive.database.trotro

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a single table in the database. Each row is a separate instance of the Schedule class.
 * Each property corresponds to a column. Additionally, an ID is needed as a unique identifier for
 * each row in the database.
 */

@Entity
data class Trotro (
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "station_name") val stationName: String,
    @NonNull @ColumnInfo(name = "stop_name") val stopName: String,
    @NonNull @ColumnInfo(name = "fare_ghs") val fareGhs: Int

        )
