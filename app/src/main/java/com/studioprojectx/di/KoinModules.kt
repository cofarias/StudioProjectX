package com.studioprojectx.di

import androidx.room.Room
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.studioprojectx.AppViewModel
import com.studioprojectx.database.SPXDatabase
import com.studioprojectx.domainlayer.authentication.FirebaseAuthRepository
import com.studioprojectx.domainlayer.tasks.TasksRepository
import com.studioprojectx.features.auth.signin.SignInViewModel
import com.studioprojectx.features.auth.signup.SignUpViewModel
import com.studioprojectx.features.tasks.form.TaskFormViewModel
import com.studioprojectx.features.tasks.list.TaskListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::TaskFormViewModel)
    viewModelOf(::TaskListViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::AppViewModel)
}

val storageModule = module {
    singleOf(::TasksRepository)
    singleOf(::FirebaseAuthRepository)

    single {
        Room.databaseBuilder(
            androidContext(),
            SPXDatabase::class.java, "studio-project-x.db"
        ).build()
    }
    single { get<SPXDatabase>().taskDao() }
}

val firebaseModule = module {
    single {
        Firebase.auth
    }
}