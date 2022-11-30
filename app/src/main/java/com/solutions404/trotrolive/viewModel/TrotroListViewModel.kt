package com.solutions404.trotrolive.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.solutions404.trotrolive.database.trotro.Trotro
import com.solutions404.trotrolive.database.trotro.TrotroDao
import kotlinx.coroutines.flow.Flow

class TrotroFareViewModel(private val trotroDao: TrotroDao): ViewModel() {

    fun fullTrotro(): Flow<List<Trotro>> = trotroDao.getAll()

    fun trotroForStopName(name: String): Flow<List<Trotro>> = trotroDao.getByStopName(name)

    fun trotroForStationName(name: String): Flow<List<Trotro>> = trotroDao.getStationName(name)

}


class TrotroViewModelFactory(
    private val trotroDao: TrotroDao
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrotroFareViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrotroFareViewModel(trotroDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}