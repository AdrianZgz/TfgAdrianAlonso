package com.example.soyaragon.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.soyaragon.Domain.ShopItemModel
import com.example.soyaragon.R

// Actividad que muestra los detalles de un establecimiento
class DetailMovieActivity : BaseActivity() {
    private lateinit var filmItem: ShopItemModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se obtiene el objeto del comercio desde el intent
        filmItem = intent.getSerializableExtra("object") as ShopItemModel

        // Se establece el contenido Compose de la pantalla
        setContent {
            DetailScreen(filmItem, onBackClick = { finish() })
        }
    }
}

@Composable
fun DetailScreen(film: ShopItemModel, onBackClick: () -> Unit) {
    val isLoading = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.blackBackground))
    ) {
        if (isLoading.value) {
            // Muestra un spinner si está cargando
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    // Cabecera con imagen de fondo y póster
                    Box(
                        modifier = Modifier.height(400.dp)
                    ) {
                        Image(
                            contentDescription = "",
                            painter = painterResource(R.drawable.back),
                            modifier = Modifier
                                .padding(start = 16.dp, top = 48.dp)
                                .clickable { onBackClick() }
                        )
                        Image(
                            contentDescription = "",
                            painter = painterResource(R.drawable.fav),
                            modifier = Modifier
                                .padding(end = 16.dp, top = 48.dp)
                                .align(Alignment.TopEnd)
                        )
                        AsyncImage(
                            model = film.Poster,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            alpha = 0.1f
                        )
                        AsyncImage(
                            model = film.Poster,
                            contentDescription = null,
                            modifier = Modifier
                                .size(210.dp, 300.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .align(Alignment.BottomCenter),
                            contentScale = ContentScale.Crop
                        )
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
                                        ),
                                        start = Offset(0f, 0f),
                                        end = Offset(0f, Float.POSITIVE_INFINITY)
                                    )
                                )
                        )
                        Text(
                            text = film.Title,
                            style = TextStyle(color = Color.White, fontSize = 27.sp),
                            modifier = Modifier
                                .padding(end = 16.dp, top = 48.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.star),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(text = " ${film.Imdb}", color = Color.White)

                            Text(text = "|", color = Color.White)

                            Icon(
                                painter = painterResource(R.drawable.time),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(text = " ${film.Time}", color = Color.White)
                            Text(text = "|", color = Color.White)
                            Icon(
                                painter = painterResource(R.drawable.cal),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(text = " ${film.Year}", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            film.Description,
                            style = TextStyle(color = Color.White, fontSize = 14.sp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                    }
                }



                itemsIndexed(film.Casts) { _, cast ->
                    cast.Actor?.let { actorName ->
                        Button(
                            onClick = {
                                cast.PicUrl?.let { url ->
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD10000)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                // Imagen alineada a la izquierda
                                Row(
                                    modifier = Modifier.align(Alignment.CenterStart),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = cast.PicUrl,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(RoundedCornerShape(20.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                }

                                // Texto centrado encima del botón
                                Text(
                                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                                    text = actorName,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}
