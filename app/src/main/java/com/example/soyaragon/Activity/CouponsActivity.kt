////////////////////////////////////
// ACTIVIDAD EN DESARROLLO /////////
// NO TENER EN CUENTA ESTE CODIGO///
// /////////////////////////////////

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.soyaragon.Domain.ShopItemModel
import com.example.soyaragon.ShopItem
import com.example.soyaragon.R
import com.example.soyaragon.ViewModel.MainViewModel

class CouponsActivity : BaseActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CouponsScreen ( onItemClick = {item->
        val intent= Intent(this,DetailMovieActivity::class.java)
            intent.putExtra("object", item)
            startActivity(intent)
})
        }
    }
}

@Preview
@Composable
fun CouponsScreen(onItemClick: (ShopItemModel) -> Unit = {}) {

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
            CouponsContent(onItemClick)
        }
    }
}

@Composable
fun CouponsContent(onItemClick: (ShopItemModel) -> Unit) {

    val viewModel = MainViewModel()
    val restaurantesZaragoza = remember { mutableStateListOf<ShopItemModel>() }
    val comerciosZaragoza = remember { mutableStateListOf<ShopItemModel>() }

    var showRestaurantesZaragoza by remember { mutableStateOf(true) }
    var showComerciosZaragoza by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadRestaurantesDestZgz().observeForever {
            restaurantesZaragoza.clear()
            restaurantesZaragoza.addAll(it)
            showRestaurantesZaragoza = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadComerciosDestZgz().observeForever {
            comerciosZaragoza.clear()
            comerciosZaragoza.addAll(it)
            showComerciosZaragoza = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.redBackground))
            .padding(top = 60.dp, bottom = 0.dp)
    ) {
        Text(
            text = "Cupones",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        Spacer(Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(R.color.gray1Background),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                )
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp, bottom = 30.dp) // Espaciado superior e inferior dentro del contenedor
                .verticalScroll(rememberScrollState()),verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre elementos
            ) {
        SectionTitle("Restaurantes en Zaragoza")

        if (showRestaurantesZaragoza) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(restaurantesZaragoza) { item ->
                    ShopItem(item, onItemClick)
                }
            }
        }

        SectionTitle("Comercios en Zaragoza")


        if (showComerciosZaragoza) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(comerciosZaragoza) { item ->
                    ShopItem(item, onItemClick)
                }
            }
        }

                SectionTitle("Comercios en Zaragoza")


                if (showComerciosZaragoza) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(comerciosZaragoza) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }
    }}
}}

