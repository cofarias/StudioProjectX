package com.studioprojectx.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG
import com.studioprojectx.domainlayer.authentication.FirebaseAuthRepository
import com.studioprojectx.features.home.model.HomeEvent
import com.studioprojectx.features.home.model.HomeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    val db = Firebase.firestore

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.AddProduct -> addProductToFirestore()
        }
    }

    init {
        _uiState.update { currentState ->
            currentState.copy(
                nameProductChange = { name ->
                    _uiState.update { it.copy(nameProduct = name) }
                },
                descriptionProductChange = { description ->
                    _uiState.update { it.copy(descriptionProduct = description) }
                },
                observationProductChange = { observation ->
                    _uiState.update { it.copy(observationProduct = observation) }
                }
            )
        }
    }


    private fun getProductMap(): Map<String, String> {
        return hashMapOf(
            "name_product" to _uiState.value.nameProduct,
            "description_product" to _uiState.value.descriptionProduct,
            "observation_product" to _uiState.value.observationProduct,
        )
    }

    fun addProductToFirestore() {
        val product = getProductMap()
        db.collection("products")
            .add(product)
            .addOnSuccessListener { task ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${task.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

}