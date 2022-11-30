package com.solutions404.trotrolive

import android.app.Application
import com.solutions404.trotrolive.database.AppDatabase

class TrotroFareApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(
        this
    ) }
}