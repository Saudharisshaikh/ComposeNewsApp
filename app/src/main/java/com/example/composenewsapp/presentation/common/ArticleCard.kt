package com.example.composenewsapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composenewsapp.R
import com.example.composenewsapp.data.local.Article
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.ArticleCardSize
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.ExtraSmallPadding
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.ExtraSmallPadding2
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.SmallSizeIcon

@Composable
fun ArticleCard(article: Article,
                onClick:()->Unit
                ) {

    val  context = LocalContext.current
    Row(modifier = Modifier.clickable { onClick() }) {

        AsyncImage(
            modifier = Modifier.size(
                ArticleCardSize
            ).clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
            ,
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null


        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {
            Text(
               text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )


          Row (
              verticalAlignment = Alignment.CenterVertically,
              )
          {
              Text(
                  text = article.source.name,
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                  color = colorResource(
                      R.color.body
                  ),

                  )

              Spacer(modifier = Modifier.width(ExtraSmallPadding2))

              Icon(
                  painterResource(R.drawable.ic_time),
                  contentDescription = null,
                  modifier = Modifier.size(SmallSizeIcon),
                  tint = colorResource(R.color.body)

              )
              Spacer(modifier = Modifier.width(ExtraSmallPadding2))

              Text(
                  text = article.publishedAt,
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                  color = colorResource(
                      R.color.body
                  ),

                  )
          }
        }
        //
    }
}