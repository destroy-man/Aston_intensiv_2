package ru.korobeynikov.astonintensiv2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DrumViewModel:ViewModel() {

    private var liveData: MutableLiveData<Array<Any>>? = null

    fun getData(): LiveData<Array<Any>>? {
        if (liveData == null)
            liveData = MutableLiveData<Array<Any>>()
        return liveData
    }

    fun loadData(data: Array<Any>) {
        liveData?.value = data
    }
}