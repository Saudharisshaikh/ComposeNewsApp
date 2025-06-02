package com.example.composenewsapp.presentation.common


import android.annotation.SuppressLint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composenewsapp.R
import com.example.composenewsapp.ui.theme.NewsAppTheme
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.ArticleCardSize
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.ExtraSmallPadding
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.ExtraSmallPadding2
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.MeddiumPadding1
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.SmallSizeIcon

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect() = composed {

    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2F,
        targetValue =  0.9F,
        animationSpec = infiniteRepeatable(
            animation = tween(delayMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
 background(colorResource(R.color.shimmer).copy(alpha))
}


@Composable
fun ArticleShimmerEffect(modifier: Modifier = Modifier) {
    
        val  context = LocalContext.current
           Row(modifier = Modifier) {

               Box(
                   modifier = Modifier
                       .size(
                           ArticleCardSize
                       )
                       .clip(MaterialTheme.shapes.medium)
                       .shimmerEffect()
               )

               Column(
                   verticalArrangement = Arrangement.SpaceAround,
                   modifier = Modifier
                       .padding(horizontal = ExtraSmallPadding)
                       .height(ArticleCardSize)
               ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .padding(horizontal = MeddiumPadding1)
                            .shimmerEffect()
                    )

                 Row (
                     verticalAlignment = Alignment.CenterVertically,
                     )
                 {

                  Box(
                      modifier = Modifier
                          .fillMaxWidth(0.5f)
                          .height(15.dp)
                          .padding(horizontal = MeddiumPadding1)
                          .shimmerEffect()
                  )


            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ArticleShowPreview(modifier: Modifier = Modifier) {
         NewsAppTheme {
            ArticleShimmerEffect()
         }
}
