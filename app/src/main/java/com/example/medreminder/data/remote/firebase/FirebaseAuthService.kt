package com.example.medreminder.data.remote.firebase

import android.util.Log
import com.example.medreminder.data.remote.firebase.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class FirebaseAuthService {

    private val TAG = "FirebaseAuthService"

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _authState = MutableStateFlow(firebaseAuth.currentUser)
    val authState: StateFlow<FirebaseUser?> = _authState

    private val authStateListener = FirebaseAuth.AuthStateListener { auth ->
        _authState.value = auth.currentUser
    }

    init {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    fun register(email: String, password: String, username: String): Result<Unit> {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { authResult ->
                    // user document in firestore anlegen, um den username zu speichern
                    val docRef = firestore.collection("users").document(authResult.user!!.uid)
                    docRef.set(
                        User(
                            id = authResult.user!!.uid,
                            email = email,
                            username = username
                        )
                    )
                }
            return Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "register: failed", e)
            return Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "login: failed", e)
            Result.failure(e)
        }
    }

    fun logout() {
        try {
            firebaseAuth.signOut()
        } catch (e: Exception) {
            Log.e(TAG, "logout: failed", e)
        }
    }
}