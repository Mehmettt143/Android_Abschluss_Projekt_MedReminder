package com.example.medreminder.ui.viewmodel

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.R
import com.example.medreminder.data.remote.firebase.FirebaseAuthService
import com.example.medreminder.data.repository.ApiDrugRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.text.isBlank

class AuthViewModel(
    private val authService: FirebaseAuthService,
    private val drugRepository: ApiDrugRepository
) : ViewModel() {

    //Benutzer-Login-Status überwachen
    val isLoggedIn = authService.authState
        .map { nullableUser -> nullableUser != null }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

    // UI-Status für Registrierungs- oder Login-Ansicht
    private val _showRegister = MutableStateFlow(false)
    val showRegister = _showRegister.asStateFlow()

    //E-Mail Eingabe verwalten
    private val _emailInput = MutableStateFlow("")
    val emailInput = _emailInput.asStateFlow()

    private val _passwordInput = MutableStateFlow("")
    val passwordInput = _passwordInput.asStateFlow()

    private val _passwordRepeatInput = MutableStateFlow("")
    val passwordRepeatInput = _passwordRepeatInput.asStateFlow()

    private val _usernameInput = MutableStateFlow("")
    val usernameInput = _usernameInput.asStateFlow()

    private val _showEmailHint = MutableStateFlow(false)
    val showEmailHint = _showEmailHint.asStateFlow()

    private val _showPasswordHint = MutableStateFlow(false)
    val showPasswordHint = _showPasswordHint.asStateFlow()

    private val _showPasswordRepeatHint = MutableStateFlow(false)
    val showPasswordRepeatHint = _showPasswordRepeatHint.asStateFlow()

    //Fehler anzeigen, wenn Eingaben ungültig sind
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    init {
        // Authentifizierungsstatus überwachen und Benutzernamen abrufen
        viewModelScope.launch {
            authService.authState.collect { user ->
                if (user != null) {
                    // wenn User eingellogt ist wird der Username geholt
                    fetchUsername()
                } else {
                    // Username wird auf leerer String gesetzt, wenn er/sie nicht eingeloggt ist
                    _username.value = ""
                }
            }
        }
    }

    //Registrierungs- oder Login-Ansicht umschalten
    fun toggleShowRegister() {
        _showRegister.value = !_showRegister.value
        resetShowHintStates()
        _errorMessage.value = null
    }

    //E-Mail eingabe aktualisieren
    fun onEmailInputChange(value: String) {
        _emailInput.value = value
        _showEmailHint.value = value.isBlank()
    }

    fun onPasswordInputChange(value: String) {
        _passwordInput.value = value
        _showPasswordHint.value = value.isBlank()
    }

    fun onPasswordRepeatInputChange(value: String) {
        _passwordRepeatInput.value = value
        _showPasswordRepeatHint.value = value.isBlank()
    }

    fun onUsernameInputChange(value: String) {
        _usernameInput.value = value
    }

    //Login- oder Registrierung durchführen
    fun loginOrRegister(context: Context) {
        viewModelScope.launch {
            val email = _emailInput.value
            val password = _passwordInput.value
            val passwordRepeat = _passwordRepeatInput.value
            val username = _usernameInput.value

            if (!validateRegisterInputs(email, password, passwordRepeat, username, context)) {
                return@launch
            }

            val result = if (_showRegister.value) {
                authService.register(email, password, username)
            } else {
                authService.login(email, password)
            }

            result.onSuccess {
                // Başarılı login/register sonrası inputs'u temizle
                resetInputs()
                _errorMessage.value = null
                // Username otomatik olarak auth state değişimi ile güncellenecek
            }.onFailure { e ->
                _errorMessage.value =
                    e.message ?: context.getString(R.string.error_operation_failed)
            }
        }
    }

    private fun validateRegisterInputs(
        email: String,
        password: String,
        passwordRepeat: String,
        username: String,
        context: Context
    ): Boolean {
        resetShowHintStates()
        _errorMessage.value = null

        var isValid = true

        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _showEmailHint.value = true
            _errorMessage.value = context.getString(R.string.error_invalid_email)
            isValid = false
        }
        if (password.isBlank() || password.length < 6) {
            _showPasswordHint.value = true
            _errorMessage.value = context.getString(R.string.error_password_length)
            isValid = false
        }
        if (_showRegister.value && passwordRepeat != password) {
            _showPasswordRepeatHint.value = true
            _errorMessage.value = context.getString(R.string.error_password_mismatch)
            isValid = false
        }

        return isValid
    }

    private fun resetShowHintStates() {
        _showEmailHint.value = false
        _showPasswordHint.value = false
        _showPasswordRepeatHint.value = false
    }

    private fun resetInputs() {
        _emailInput.value = ""
        _passwordInput.value = ""
        _passwordRepeatInput.value = ""
        _usernameInput.value = ""
    }

    fun logout() {
        viewModelScope.launch {
            try {
                authService.logout()
                resetInputs()
                resetShowHintStates()
                _errorMessage.value = null
                _username.value = ""
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "" //R.string.logout_failed.toString()
            }
        }
    }

    private fun fetchUsername() {
        viewModelScope.launch {
            try {
                val result = drugRepository.getUsername()
                if (result.isSuccess) {
                    _username.value = result.getOrNull() ?: ""
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message
                        ?: "" //R.string.username_load_failed.toString()
                    _username.value = ""
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "" // R.string.username_load_error.toString()
                _username.value = ""
            }
        }
    }
}