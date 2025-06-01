package com.example.soyaragon.Activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.soyaragon.Domain.CampaignItemModel
import com.example.soyaragon.R

// Actividad que muestra los detalles de una campaña
class DetailCampaignActivity : BaseActivity() {
    private lateinit var campaignItem: CampaignItemModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se obtiene el objeto de la campaña desde el intent
        campaignItem = (intent.getSerializableExtra("object") as CampaignItemModel)

        // Se establece el contenido Compose de la pantalla
        setContent {
            DetailCampaignScreen(campaignItem, onBackClick = { finish() })
        }
    }
}

@Composable
fun DetailCampaignScreen(film: CampaignItemModel, onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()

    val isLoading = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.blackBackground))
    ) {
        if (isLoading.value) {
            // Muestra un spinner si está cargando
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // Encabezado con imagen de campaña, botón de retroceso y fondo decorativo
                Box(
                    modifier = Modifier.height(400.dp)
                ) {
                    // Botón para volver atrás
                    Image(
                        contentDescription = "",
                        painter = painterResource(R.drawable.back),
                        modifier = Modifier
                            .padding(start = 16.dp, top = 48.dp)
                            .clickable { onBackClick() }
                    )
                    // Icono de favorito (decorativo POR AHORA)
                    Image(
                        contentDescription = "",
                        painter = painterResource(R.drawable.fav),
                        modifier = Modifier
                            .padding(end = 16.dp, top = 48.dp)
                            .align(Alignment.TopEnd)
                    )
                    // Imagen de fondo desenfocada
                    AsyncImage(
                        model = film.Poster,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alpha = 0.1f
                    )
                    // Imagen destacada de la campaña
                    AsyncImage(
                        model = film.Poster,
                        contentDescription = null,
                        modifier = Modifier
                            .size(500.dp, 300.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .align(Alignment.BottomCenter),
                        contentScale = ContentScale.Crop
                    )
                    // Degradado para mejorar legibilidad del texto en la parte inferior
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        colorResource(R.color.black2),
                                        colorResource(R.color.black1)
                                    ), start = Offset(0f, 0f),
                                    end = Offset(0f, Float.POSITIVE_INFINITY)
                                )
                            )
                    )
                    // Título de la campaña
                    Text(
                        text = film.Title,
                        style = TextStyle(color = Color.White, fontSize = 27.sp),
                        modifier = Modifier
                            .padding(end = 16.dp, top = 48.dp)
                            .align(Alignment.BottomCenter)

                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Sección con el contenido de la campaña
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.cal),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding()
                        )
                        Text(text = " ${film.Month}", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        "¿Cómo funciona?",
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        film.Description,
                        style = TextStyle(color = Color.White, fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        "Tiendas que participan",
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Lista horizontal de tiendas participantes
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(film.Shops) { shop ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                shop.PicUrl?.let {
                                    AsyncImage(
                                        model = it,
                                        contentDescription = shop.NameShop,
                                        modifier = Modifier
                                            .height(120.dp)
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                shop.NameShop?.let {
                                    Text(
                                        text = it,
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )
                                }
                                shop.AddressShop?.let {
                                    Text(
                                        text = it,
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }


                }
            }
        }
    }
}
