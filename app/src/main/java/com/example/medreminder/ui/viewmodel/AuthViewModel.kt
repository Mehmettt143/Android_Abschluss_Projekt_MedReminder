package com.example.medreminder.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.data.service.FirebaseAuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.text.isBlank

class AuthViewModel(
//    private val authService: FirebaseAuthService // dependency injection mit koin - empfohlen
) : ViewModel() {

    // auth
    // nicht empofhlen - ohne dependency injection, mit eigenem singleton
    private val authService: FirebaseAuthService = FirebaseAuthService.getInstance()

    // Ausgangssituation: Flow<User?>
    // Was wir haben möchten: Boolean
    val isLoggedIn = authService.authState
        .map{ nullableUser ->
            nullableUser != null
            // kürzere schreibweise, gleiche logik
//            if (nullableUser != null)
//                true
//            else
//                false
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )




    // ui states
    private val _showRegister = MutableStateFlow(false)
    val showRegister = _showRegister.asStateFlow()

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


    fun toggleShowRegister() {
        _showRegister.value = !_showRegister.value
    }

    fun onEmailInputChange(value: String) {
        _emailInput.value = value
    }

    fun onPasswordInputChange(value: String) {
        _passwordInput.value = value
    }

    fun onPasswordRepeatInputChange(value: String) {
        _passwordRepeatInput.value = value
    }

    fun onUsernameInputChange(value: String) {
        _usernameInput.value = value
    }

    fun loginOrRegister() {
        val email = _emailInput.value
        val password = _passwordInput.value
        val passwordRepeat = _passwordRepeatInput.value
        val username = _usernameInput.value

        if (_showRegister.value) {
            // register
            // TODO input validation
            // TODO error handling
            authService.register(email, password)
        } else {
            // login
            authService.login(email, password)
        }
    }

    private fun validateRegisterInputs(
        email: String,
        password: String,
        passwordRepeat: String,
        username: String
    ): Boolean {

        resetShowHintStates()

        var isValid = true

        // wenn email leer ist oder kein gültiges email pattern hat dann zeigen wir dem nutzer einen hinweis an
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _showEmailHint.value = true
            isValid = false
        }
        // wenn das password leer ist oder kürzer als 6 zeichen zeigen wir dem nutzer einen hinweis an
        if (password.isBlank() || password.length < 6) {
            _showPasswordHint.value = true
            isValid = false
        }

        // wenn das wiederholte password nicht gleich ist dann zeigen wir dem nutzer einen hinweis an
        if (passwordRepeat != password) {
            _showPasswordRepeatHint.value = true
            isValid = false
        }

        // TODO validate username

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
    }


}