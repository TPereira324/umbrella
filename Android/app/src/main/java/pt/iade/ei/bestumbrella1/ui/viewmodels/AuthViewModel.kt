package pt.iade.ei.bestumbrella1.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.bestumbrella1.data.Repository
import pt.iade.ei.bestumbrella1.models.UserResponse
import pt.iade.ei.bestumbrella1.network.UserProfileResponse

class AuthViewModel(private val repository: Repository) : ViewModel() {
    
    private val _loginResult = MutableLiveData<UserResponse>()
    val loginResult: LiveData<UserResponse> = _loginResult
    
    private val _registerResult = MutableLiveData<UserResponse>()
    val registerResult: LiveData<UserResponse> = _registerResult
    
    private val _userProfile = MutableLiveData<UserProfileResponse>()
    val userProfile: LiveData<UserProfileResponse> = _userProfile
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    
    fun login(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            repository.loginUser(email, password)
                .onSuccess { response ->
                    _loginResult.value = response
                    _isLoading.value = false
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Erro desconhecido"
                    _isLoading.value = false
                }
        }
    }
    
    fun register(name: String, email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            repository.registerUser(name, email, password)
                .onSuccess { response ->
                    _registerResult.value = response
                    _isLoading.value = false
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Erro desconhecido"
                    _isLoading.value = false
                }
        }
    }
    
    fun getUserProfile() {
        _isLoading.value = true
        viewModelScope.launch {
            repository.getUserProfile()
                .onSuccess { response ->
                    _userProfile.value = response
                    _isLoading.value = false
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Erro desconhecido"
                    _isLoading.value = false
                }
        }
    }
}