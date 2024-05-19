package com.studioprojectx.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.studioprojectx.authentication.FirebaseAuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storageModule = module {
    singleOf(::FirebaseAuthRepository)
}

val firebaseModule = module {
    single {
        Firebase.auth
    }
}