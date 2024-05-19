package com.studioprojectx.authentication

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(
    private val firebaseAuth: FirebaseAuth
) {
    suspend fun signUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(
            email, password
        ).await()
    }

    suspend fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(
            email, password
        ).await()
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}