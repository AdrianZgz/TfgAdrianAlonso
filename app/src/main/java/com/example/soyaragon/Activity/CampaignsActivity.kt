package com.example.soyaragon.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soyaragon.BottomNavigationBar
import com.example.soyaragon.CampaignItem
import com.example.soyaragon.Domain.CampaignItemModel
import com.example.soyaragon.R
import com.example.soyaragon.ViewModel.MainViewModel

// Actividad que muestra la pantalla de campañas
class CampaignsActivity : BaseActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Establecemos el contenido de la actividad utilizando Jetpack Compose
        setContent {
            // Muestra la pantalla de campañas y define qué hacer al pulsar sobre una
            CampaignScreen ( onItemClick = {item->
        val intent= Intent(this,DetailCampaignActivity::class.java)
            intent.putExtra("object", item)
            startActivity(intent)
})
        }
    }
}

@Preview
@Composable
// Composable principal que muestra toda la interfaz de la pantalla de campañas
fun CampaignScreen(onItemClick: (CampaignItemModel) -> Unit = {}) {

    Scaffold(
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.redBackground),
                                colorResource(R.color.redBackground)
                            )
                        ),
                        shape = CircleShape
                    )
                    .padding(3.dp)
            ) {
                val context = LocalContext.current

                FloatingActionButton(
                    onClick = { (context as? Activity)?.finish()
                        Toast.makeText(context, "Cupones", Toast.LENGTH_SHORT).show()
                        context.startActivity(Intent(context, CouponsActivity::class.java)) },
                    backgroundColor = colorResource(id = R.color.black3),
                    modifier = Modifier.size(58.dp),
                    contentColor = Color.White,
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.cupones128),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                )


            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = colorResource(R.color.blackBackground)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(color = colorResource(R.color.blackBackground))
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            CampaignContent(onItemClick)
        }
    }
}

@Composable
fun CampaignContent(onItemClick: (CampaignItemModel) -> Unit) {

    val viewModel = MainViewModel()
    val campaigns = remember { mutableStateListOf<CampaignItemModel>() }

    var showCampaignsLoading by remember { mutableStateOf(true) }

    // Efecto que se lanza al cargar la pantalla
    LaunchedEffect(Unit) {
        viewModel.loadCampaigns().observeForever {
            campaigns.clear() // Limpiamos campañas anteriores
            campaigns.addAll(it) // Añadimos nuevas campañas
            showCampaignsLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.redBackground))
            .padding(top = 60.dp, bottom = 0.dp)//100.dp)
    ) {
        // Título de la sección
        Text(
            text = "Campañas",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        Spacer(Modifier.height(15.dp))
        // Caja donde se muestra el contenido de las campañas
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(R.color.gray1Background),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                )
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
        ) {

        if (showCampaignsLoading) {
            // Indicador de carga mientras se obtienen los datos
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Lista de campañas en forma de LazyColumn (un scroll infinito)
            LazyColumn(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(campaigns) { item ->
                    CampaignItem(item, onItemClick)
                }
            }
        }

    }}
}