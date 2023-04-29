package com.example.medicapp.presentation.base_profile.analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.net.models.CatalogCloudItem
import com.example.medicapp.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnalysisViewModel(
    private val repository : Repository
) : ViewModel() {
    private val _catalogLiveData = MutableLiveData<NetworkResult<List<CatalogCloudItem>>>()
    val catalogLiveData : LiveData<NetworkResult<List<CatalogCloudItem>>> = _catalogLiveData

    fun getCatalog(){
        viewModelScope.launch(Dispatchers.IO) {
            _catalogLiveData.postValue(NetworkResult.Loading())
            val result = repository.getCatalog()
            withContext(Dispatchers.Main){
                _catalogLiveData.value = result
            }
        }
    }

}