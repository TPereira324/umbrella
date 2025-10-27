package pt.iade.ei.bestumbrella1.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.bestumbrella1.data.Repository
import pt.iade.ei.bestumbrella1.network.WeatherResponse

class WeatherViewModel(private val repository: Repository) : ViewModel() {
    
    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> = _weatherData
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    
    fun getWeatherForecast(latitude: Double, longitude: Double) {
        _isLoading.value = true
        viewModelScope.launch {
            repository.getWeatherForecast(latitude, longitude)
                .onSuccess { response ->
                    _weatherData.value = response
                    _isLoading.value = false
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Erro desconhecido"
                    _isLoading.value = false
                }
        }
    }
}