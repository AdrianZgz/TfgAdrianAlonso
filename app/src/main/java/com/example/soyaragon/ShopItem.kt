package com.example.soyaragon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.soyaragon.Activity.city
import com.example.soyaragon.Domain.ShopItemModel

@Composable
fun ShopItem(item:ShopItemModel, onItemClick:(ShopItemModel)-> Unit) {
    Column (
        modifier = Modifier
            .padding(8.dp)
            .width(170.dp)
            .clickable { onItemClick(item) },
            //.background(color = Color(android.graphics.Color.parseColor("#2f2f39")))




    ){
        AsyncImage(
            model = item.Poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))

        )

        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
            Text(
                text = item.Title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = item.Imdb.toString(),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )}
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Categoria",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Precio",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

            }
            Text(
                text = city,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}


        /*Column(modifier = Modifier.padding(8.dp)) {
        Text( text = item.Title,
            modifier = Modifier.padding(start = 4.dp),
            style = TextStyle(color = colorResource(R.color.black3), fontSize = 14.sp, fontWeight = FontWeight.Bold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xffffc107)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = item.Imdb.toString(), style = TextStyle(color = Color.White, fontSize = 12.sp))

        }
    }}
}*/