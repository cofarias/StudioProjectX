package com.studioprojectx.features.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.studioprojectx.features.home.model.HomeEvent
import com.studioprojectx.features.home.model.HomeUIState
import com.studioprojectx.ui.theme.StudioProjectXTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    uiState: HomeUIState,
    onNewTaskClick: () -> Unit = {},
    onTaskListClick: () -> Unit = {},
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        topBar = {},
        content = { paddingValues ->
            HomeContent(
                paddingValues = paddingValues,
                uiState = uiState,
                onNewTaskClick = onNewTaskClick,
                onTaskListClick = onTaskListClick,
                onEvent = onEvent
            )
        }
    )
}

@Composable
fun HomeContent(
    uiState: HomeUIState,
    onEvent: (HomeEvent) -> Unit,
    onNewTaskClick: () -> Unit = {},
    onTaskListClick: () -> Unit = {},
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {

        val showRegisterProduct = remember { mutableStateOf(false) }

        ListOptionsFeatures(
            uiState = uiState,
            onNewTaskClick = onNewTaskClick,
            onTaskListClick = onTaskListClick,
            onShowRegisterProductClick = {
                showRegisterProduct.value = !showRegisterProduct.value
            },
            onEvent = onEvent
        )

        if (showRegisterProduct.value) {
            CreateProduct(
                uiState = uiState,
                onEvent = onEvent
            )
        }
    }
}

@Composable
fun ListOptionsFeatures(
    uiState: HomeUIState,
    onEvent: (HomeEvent) -> Unit,
    onNewTaskClick: () -> Unit = {},
    onTaskListClick: () -> Unit = {},
    onShowRegisterProductClick: () -> Unit,
) {
    val showProducts = remember { mutableStateOf(false) }
    Spacer(modifier = Modifier.size(30.dp))
    val images: List<String> = listOf(
        "https://t3.ftcdn.net/jpg/05/71/06/76/360_F_571067620_JS5T5TkDtu3gf8Wqm78KoJRF1vobPvo6.jpg",
        "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_640.jpg",
        "https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/1015f/MainBefore.jpg",
        "https://images.pexels.com/photos/1525041/pexels-photo-1525041.jpeg?cs=srgb&dl=pexels-francesco-ungaro-1525041.jpg&fm=jpg"
    )
    val image = images.random()

    ElevatedCard(
        colors = CardColors(
            contentColor = Color.White,
            containerColor = Color.Black,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.LightGray
        ),
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = image,
                contentDescription = "Initial Image",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Olá, seja bem vindo",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(4.dp),
            )
        }
    }

    Spacer(modifier = Modifier.size(30.dp))

    ExtendedFloatingActionButton(
        onClick = onNewTaskClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Criar tarefa"
            )
        }
    }

    Spacer(modifier = Modifier.size(30.dp))

    ExtendedFloatingActionButton(
        onClick = onTaskListClick,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Listagem das tarefas"
            )
        }
    }

    Spacer(modifier = Modifier.size(30.dp))

    ExtendedFloatingActionButton(
        onClick = onShowRegisterProductClick,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Cadastrar produto no Firebase"
            )
        }
    }

    Spacer(modifier = Modifier.size(30.dp))

    ExtendedFloatingActionButton(
        onClick = {
            showProducts.value = !showProducts.value
            onEvent(HomeEvent.GetProducts)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Lista de produtos - Firebase"
            )
        }
    }

    Spacer(modifier = Modifier.padding(16.dp))

    ListProducts(showProducts, uiState)
}

@Composable
private fun ListProducts(
    showProducts: MutableState<Boolean>,
    uiState: HomeUIState
) {
    if (showProducts.value) {
        Text(
            text = "Itens que estão no banco de dados",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (uiState.products.isEmpty()) {
            Text(
                text = "Nenhum produto cadastrado",
                textAlign = TextAlign.Center
            )
        } else {
            uiState.products.forEach { product ->
                Text(
                    text = product["name_product"] as? String ?: "N/A",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}

@Composable
fun CreateProduct(
    uiState: HomeUIState,
    onEvent: (HomeEvent) -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                brush = Brush.radialGradient(
                    colors = listOf(Color.Blue, Color.Gray, Color.Transparent, Color.LightGray),
                    radius = 50f
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Text(
            modifier = Modifier.fillMaxSize(),
            fontWeight = FontWeight.Bold,
            text = "Cadastrar Produto",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome do Produto") },
            singleLine = true,
            placeholder = { Text(text = "Nome do Produto") },
            value = uiState.nameProduct,
            onValueChange = uiState.nameProductChange,
        )

        Spacer(modifier = Modifier.padding(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Descrição do Produto") },
            singleLine = true,
            placeholder = { Text(text = "Descrição do Produto") },
            value = uiState.descriptionProduct,
            onValueChange = uiState.descriptionProductChange,
        )

        Spacer(modifier = Modifier.padding(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Observações") },
            singleLine = true,
            placeholder = { Text(text = "Observações") },
            value = uiState.observationProduct,
            onValueChange = uiState.observationProductChange,
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonColors(
                containerColor = Color(0xFFFF00FF),
                contentColor = Color.Black,
                disabledContainerColor = Color(0xFF888888),
                disabledContentColor = Color(0xFF888888)
            ),
            content = {
                Text(
                    text = "Cadastrar produto",
                    fontWeight = FontWeight.Bold,
                )
            },
            onClick = {
                onEvent(HomeEvent.AddProduct)
                focusManager.clearFocus()
                scope.launch {
                    snackBarHostState.showSnackbar("Produto cadastrado com sucesso")
                }
            }
        )

        Spacer(modifier = Modifier.padding(8.dp))

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.Start)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    StudioProjectXTheme {
        HomeScreen(
            HomeUIState(),
            onEvent = {}
        )
    }
}