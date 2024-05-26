package com.example.sampleview


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sampleview.ui.theme.SampleViewTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val rainbowColorsBrush = remember {
                Brush.sweepGradient(
                    listOf(
                        Color(0xFF9575CD),
                        Color(0xFFBA68C8),
                        Color(0xFFE57373),
                        Color(0xFFFFB74D),
                        Color(0xFFFFF176),
                        Color(0xFFAED581),
                        Color(0xFF4DD0E1),
                        Color(0xFF9575CD)
                    )
                )
            }

            Surface() {
                SampleViewTheme {
                    ShadowSample2()
                }
            }

        }
    }
}

@Preview
@Composable
private fun ShadowSample2(){

    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawWithRainbowBlend(rainbowColorsBrush),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        val borderWidth = 4.dp
        val colorGray = Color.DarkGray
        val colorWhite = Color.Red

        Image(
            painter = painterResource(id = R.drawable.sample_image ),
            contentDescription = null,
            contentScale = ContentScale.Crop,

//            modifier = Modifier
//                .fillMaxSize()
//                .background(gradientGrayWhite)
//                .border(
//                    BorderStroke(borderWidth, rainbowColorsBrush),
//                    shape = RoundedCornerShape(16.dp),
//                    )
//                .padding(borderWidth)
//                .clip(RoundedCornerShape(10.dp))
            modifier = Modifier.drawWithCache {
                val gradient = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Magenta),

                    startY = size.height/4,
                    endY = size.height/40
                )
                
                onDrawWithContent {
                    drawContent()
                    drawRect(gradient,blendMode = BlendMode.Multiply)
                }
            }
                .border(
                    BorderStroke(borderWidth, rainbowColorsBrush),
                    shape = RoundedCornerShape(16.dp),
                    )
                .fillMaxSize()
        )

    }
}

fun Modifier.drawWithRainbowBlend(brush: Brush): Modifier = this.then(
    Modifier.drawBehind {
        drawRect(brush = brush, blendMode = BlendMode.ColorBurn)
    }
)
