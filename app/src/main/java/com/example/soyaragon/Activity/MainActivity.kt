package com.example.soyaragon.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.ui.platform.LocalContext

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soyaragon.BottomNavigationBar
import com.example.soyaragon.Domain.ShopItemModel
import com.example.soyaragon.ShopItem
import com.example.soyaragon.R
import com.example.soyaragon.ViewModel.MainViewModel



var city = "Zaragoza"

class MainActivity : BaseActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
        MainScreen ( onItemClick = {item->
        val intent= Intent(this,DetailMovieActivity::class.java)
            intent.putExtra("object", item)
            startActivity(intent)
})
        }
    }
}

@Preview
@Composable
fun MainScreen(onItemClick: (ShopItemModel) -> Unit = {}) {

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
            when (city) {
                "Zaragoza" -> MainContentZaragoza(onItemClick)
                "Huesca" -> MainContentHuesca(onItemClick)
                "Teruel" -> MainContentTeruel(onItemClick)
            }
        }
    }
}

@Composable
fun MainContentZaragoza(onItemClick: (ShopItemModel) -> Unit) {

    // Se instancia el ViewModel que gestiona la carga de datos
    val viewModel = MainViewModel()

    //////////////ZARAGOZA////////////////////////
    // Se crean listas para guardar los datos cargados
    val restaurantesDestZgz = remember { mutableStateListOf<ShopItemModel>() }
    val comerciosDestZgz = remember { mutableStateListOf<ShopItemModel>() }
    val alojamientosDestZgz = remember { mutableStateListOf<ShopItemModel>() }
    val turismoDestZgz = remember { mutableStateListOf<ShopItemModel>() }
    val serviciosDestZgz = remember { mutableStateListOf<ShopItemModel>() }

    // Variables para mostrar u ocultar el indicador de carga
    var showRestaurantesDestZgzLoading by remember { mutableStateOf(true) }
    var showComerciosDestZgzLoading by remember { mutableStateOf(true) }
    var showAlojamientosDestZgzLoading by remember { mutableStateOf(true) }
    var showTurismoDestZgzLoading by remember { mutableStateOf(true) }
    var showServiciosDestZgzLoading by remember { mutableStateOf(true) }

    // Se lanzan los efectos para cargar los datos de forma asíncrona cuando se compone la pantalla
    LaunchedEffect(Unit) {
        viewModel.loadRestaurantesDestZgz().observeForever {
                restaurantesDestZgz.clear()
                restaurantesDestZgz.addAll(it)
                showRestaurantesDestZgzLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadComerciosDestZgz().observeForever {
            comerciosDestZgz.clear()
            comerciosDestZgz.addAll(it)
            showComerciosDestZgzLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadAlojamientosDestZgz().observeForever {
            alojamientosDestZgz.clear()
            alojamientosDestZgz.addAll(it)
            showAlojamientosDestZgzLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadTurismoDestZgz().observeForever {
            turismoDestZgz.clear()
            turismoDestZgz.addAll(it)
            showTurismoDestZgzLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadServiciosDestZgz().observeForever {
            serviciosDestZgz.clear()
            serviciosDestZgz.addAll(it)
            showServiciosDestZgzLoading = false
        }
    }



    // Contenedor principal de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.redBackground))
            .padding(top = 60.dp, bottom = 0.dp)//100.dp)
    ) {
        // Título principal "SoyAragón"
        Text(
            text = "SoyAragón",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        // Botones para seleccionar la ciudad
        CityButtons()
        Spacer(Modifier.height(15.dp))

        // Contenedor con fondo gris claro y bordes redondeados en la parte superior
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(R.color.gray1Background),
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp,top = 22.dp, bottom = 30.dp) // Espaciado superior e inferior dentro del contenedor
                .verticalScroll(rememberScrollState()),verticalArrangement = Arrangement.spacedBy(0.dp) // Espacio entre elementos
            ) {
        SectionTitle("Restaurantes en ".plus(city))

        if (showRestaurantesDestZgzLoading) {
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
                items(restaurantesDestZgz) { item ->
                    ShopItem(item, onItemClick)
                }
            }
        }

        SectionTitle("Comercios en ".plus(city))


        if (showComerciosDestZgzLoading) {
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
                items(comerciosDestZgz) { item ->
                    ShopItem(item, onItemClick)
                }
            }
        }

                SectionTitle("Alojamientos en ".plus(city))


                if (showAlojamientosDestZgzLoading) {
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
                        items(alojamientosDestZgz) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }

                SectionTitle("Turismo en ".plus(city))


                if (showTurismoDestZgzLoading) {
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
                        items(turismoDestZgz) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }

                SectionTitle("Servicios en ".plus(city))


                if (showServiciosDestZgzLoading) {
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
                        items(serviciosDestZgz) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }
    }}
}}

@Composable
fun MainContentHuesca(onItemClick: (ShopItemModel) -> Unit) {

    val viewModel = MainViewModel()

    val restaurantesDestHue = remember { mutableStateListOf<ShopItemModel>() }
    val comerciosDestHue = remember { mutableStateListOf<ShopItemModel>() }
    val alojamientosDestHue = remember { mutableStateListOf<ShopItemModel>() }
    val turismoDestHue = remember { mutableStateListOf<ShopItemModel>() }
    val serviciosDestHue = remember { mutableStateListOf<ShopItemModel>() }


    var showRestaurantesDestHueLoading by remember { mutableStateOf(true) }
    var showComerciosDestHueLoading by remember { mutableStateOf(true) }
    var showAlojamientosDestHueLoading by remember { mutableStateOf(true) }
    var showTurismoDestHueLoading by remember { mutableStateOf(true) }
    var showServiciosDestHueLoading by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        viewModel.loadRestaurantesDestHue().observeForever {
            restaurantesDestHue.clear()
            restaurantesDestHue.addAll(it)
            showRestaurantesDestHueLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadComerciosDestHue().observeForever {
            comerciosDestHue.clear()
            comerciosDestHue.addAll(it)
            showComerciosDestHueLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadAlojamientosDestHue().observeForever {
            alojamientosDestHue.clear()
            alojamientosDestHue.addAll(it)
            showAlojamientosDestHueLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadTurismoDestHue().observeForever {
            turismoDestHue.clear()
            turismoDestHue.addAll(it)
            showTurismoDestHueLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadServiciosDestHue().observeForever {
            serviciosDestHue.clear()
            serviciosDestHue.addAll(it)
            showServiciosDestHueLoading = false
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.redBackground))
            .padding(top = 60.dp, bottom = 0.dp)//100.dp)
    ) {
        Text(
            text = "SoyAragón",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        CityButtons()
        Spacer(Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(R.color.gray1Background),
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp,top = 22.dp, bottom = 30.dp) // Espaciado superior e inferior dentro del contenedor
                    .verticalScroll(rememberScrollState()),verticalArrangement = Arrangement.spacedBy(0.dp) // Espacio entre elementos
            ) {
                SectionTitle("Restaurantes en ".plus(city))

                if (showRestaurantesDestHueLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        //contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(restaurantesDestHue) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }

                SectionTitle("Comercios en ".plus(city))


                if (showComerciosDestHueLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(comerciosDestHue) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }

                SectionTitle("Alojamientos en ".plus(city))


                if (showAlojamientosDestHueLoading) {
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
                        items(alojamientosDestHue) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }

                SectionTitle("Turismo en ".plus(city))


                if (showTurismoDestHueLoading) {
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
                        items(turismoDestHue) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }

                SectionTitle("Servicios en ".plus(city))


                if (showServiciosDestHueLoading) {
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
                        items(serviciosDestHue) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }
            }}
    }}

@Composable
fun MainContentTeruel(onItemClick: (ShopItemModel) -> Unit) {

    val viewModel = MainViewModel()

    val restaurantesDestTer = remember { mutableStateListOf<ShopItemModel>() }
    val comerciosDestTer = remember { mutableStateListOf<ShopItemModel>() }
    val alojamientosDestTer = remember { mutableStateListOf<ShopItemModel>() }
    val turismoDestTer = remember { mutableStateListOf<ShopItemModel>() }
    val serviciosDestTer = remember { mutableStateListOf<ShopItemModel>() }


    var showRestaurantesDestTerLoading by remember { mutableStateOf(true) }
    var showComerciosDestTerLoading by remember { mutableStateOf(true) }
    var showAlojamientosDestTerLoading by remember { mutableStateOf(true) }
    var showTurismoDestTerLoading by remember { mutableStateOf(true) }
    var showServiciosDestTerLoading by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        viewModel.loadRestaurantesDestTer().observeForever {
            restaurantesDestTer.clear()
            restaurantesDestTer.addAll(it)
            showRestaurantesDestTerLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadComerciosDestTer().observeForever {
            comerciosDestTer.clear()
            comerciosDestTer.addAll(it)
            showComerciosDestTerLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadAlojamientosDestTer().observeForever {
            alojamientosDestTer.clear()
            alojamientosDestTer.addAll(it)
            showAlojamientosDestTerLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadTurismoDestTer().observeForever {
            turismoDestTer.clear()
            turismoDestTer.addAll(it)
            showTurismoDestTerLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadServiciosDestTer().observeForever {
            serviciosDestTer.clear()
            serviciosDestTer.addAll(it)
            showServiciosDestTerLoading = false
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.redBackground))
            .padding(top = 60.dp, bottom = 0.dp)//100.dp)
    ) {
        Text(
            text = "SoyAragón",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        CityButtons()
        Spacer(Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(R.color.gray1Background),
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp,top = 22.dp, bottom = 30.dp) // Espaciado superior e inferior dentro del contenedor
                    .verticalScroll(rememberScrollState()),verticalArrangement = Arrangement.spacedBy(0.dp) // Espacio entre elementos
            ) {
                SectionTitle("Restaurantes en ".plus(city))

                if (showRestaurantesDestTerLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(restaurantesDestTer) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }

                SectionTitle("Comercios en ".plus(city))


                if (showComerciosDestTerLoading) {
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
                        items(comerciosDestTer) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }

                SectionTitle("Alojamientos en ".plus(city))


                if (showAlojamientosDestTerLoading) {
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
                        items(alojamientosDestTer) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }

                SectionTitle("Turismo en ".plus(city))


                if (showTurismoDestTerLoading) {
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
                        items(turismoDestTer) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }

                SectionTitle("Servicios en ".plus(city))


                if (showServiciosDestTerLoading) {
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
                        items(serviciosDestTer) { item ->
                            ShopItem(item, onItemClick)
                        }
                    }
                }
            }}
    }}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = TextStyle(color = colorResource(R.color.black3), fontSize = 18.sp),
        modifier = Modifier.padding(start = 16.dp, top = 10.dp),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CityButtons() {
    val context = LocalContext.current

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {
        GradientButtonCities(
            text = "Zaragoza",
            onClick = {
                Toast.makeText(context, "Zaragoza", Toast.LENGTH_SHORT).show()
                city = "Zaragoza"
                //recargo la actividad
                val intent = Intent(context, MainActivity::class.java)
                (context as? Activity)?.finish() // Cierra la actividad actual
                context.startActivity(intent)
                      },
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
        )
        Spacer(Modifier.width(5.dp))
        GradientButtonCities(
            text = "Huesca",
            onClick = {
                Toast.makeText(context, "Huesca", Toast.LENGTH_SHORT).show()
                city = "Huesca"
                //recargo la actividad
                val intent = Intent(context, MainActivity::class.java)
                (context as? Activity)?.finish() // Cierra la actividad actual
                context.startActivity(intent)
            },
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
        )
        Spacer(Modifier.width(5.dp))
        GradientButtonCities(
            text = "Teruel",
            onClick = {
                Toast.makeText(context, "Teruel", Toast.LENGTH_SHORT).show()
                city = "Teruel"
                //recargo la actividad
                val intent = Intent(context, MainActivity::class.java)
                (context as? Activity)?.finish() // Cierra la actividad actual
                context.startActivity(intent)
            },
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
        )
    }
}

@Composable
fun GradientButtonCities(
    text: String,
    onClick:()->Unit,
    modifier: Modifier=Modifier
){
    Button(onClick = onClick,
        modifier=modifier,
        shape = RoundedCornerShape(60.dp),
        border = BorderStroke(
            width = 2.dp, brush = Brush.linearGradient(
                colors = listOf(colorResource(R.color.white), colorResource(R.color.white) )
            )
        ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent,
            contentColor = Color.White)
    ){
        androidx.compose.material.Text(
            text= text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White)
    }
}