package com.solutions404.trotrolive.viewModel

import androidx.lifecycle.ViewModel
import com.solutions404.trotrolive.database.trotro.Trotro
import com.solutions404.trotrolive.database.trotro.TrotroDao
import kotlinx.coroutines.flow.Flow

class TrotroListViewModel(private val trotroDao: TrotroDao): ViewModel() {

    fun fullTrotro(): Flow<List<Trotro>> = trotroDao.getAll()

    fun trotroForStopName(name: String): Flow<List<Trotro>> = trotroDao.getByStopName(name)

    fun trotroForStationName(name: String): Flow<List<Trotro>> = trotroDao.getStationName(name)

}
