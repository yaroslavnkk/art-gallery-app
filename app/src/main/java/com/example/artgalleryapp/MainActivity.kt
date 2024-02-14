package com.example.artgalleryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgalleryapp.ui.theme.ArtGalleryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtLayout()
                }
            }
        }
    }
}
@Composable
fun ArtLayout(modifier: Modifier = Modifier){
    var state by remember {
        mutableIntStateOf(1)
    }
    val smth =  when(state) {
        1 -> ArtImageAndText(
            drawableResourceId = R.drawable.bird_sparrow_pen_2023,
            contentDescriptionId = R.string.sparrow_name,
            authorNameId = R.string.sparrow_author,
            authorYearId = R.string.sparrow_year
        )

        2 -> ArtImageAndText(
            drawableResourceId = R.drawable.flowers_garden_spring_2020,
            contentDescriptionId = R.string.flowers_garden_spring,
            authorNameId = R.string.flowers_author,
            authorYearId = R.string.flowers_year
        )

        3 -> ArtImageAndText(
            drawableResourceId = R.drawable.horse_animal_2019,
            contentDescriptionId = R.string.horse_animal,
            authorNameId = R.string.horse_author,
            authorYearId = R.string.horse_year
        )

        else -> ArtImageAndText(
            drawableResourceId = R.drawable.woman_terraces_2021,
            contentDescriptionId = R.string.woman_terraces,
            authorNameId = R.string.woman_author,
            authorYearId = R.string.woman_year
        )
    }

    Box (
        modifier = modifier
    ){
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
            ) {
                Button(
                    onClick = {
                        smth
                        state--
                        if(state < 1){
                            state = 4
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF99CCFF)),
                    modifier = modifier
                        .height(40.dp)
                        .width(150.dp)
                ) {
                    Text(text = "Previous")
                }
                Spacer(
                    modifier = Modifier
                        .width(36.dp)
                )
                Button(
                    onClick = {
                        smth
                        state++
                        if(state > 4){
                            state = 1
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF99CCFF)),
                    modifier = modifier
                        .height(40.dp)
                        .width(150.dp)
                ) {
                    Text(text = "Next")
                }
            }
        }
    }

}

@Composable
fun ArtImageAndText(modifier: Modifier = Modifier, drawableResourceId : Int, contentDescriptionId : Int, authorNameId : Int, authorYearId : Int) {
    val imageModifier = modifier
        .size(250.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.LightGray)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 160.dp)
    ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(contentDescriptionId),
                contentScale = ContentScale.Crop,
                modifier = imageModifier
            )

            Spacer(modifier = Modifier.height(100.dp))

            Column(
                modifier = modifier
                    .background(Color(0xFFCCCCFF))
                    .padding(28.dp)
            ) {
                Text(
                    text = stringResource(contentDescriptionId),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                )
                Row {
                    Text(
                        text = stringResource(authorNameId),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = stringResource(authorYearId),
                        fontWeight = FontWeight.Normal
                    )
                }
            }

        }
    }

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Preview"
)
@Composable
fun AppGalleryPreview(){
    ArtGalleryAppTheme {
        ArtLayout()
    }
}