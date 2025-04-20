package com.example.composenewsapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composenewsapp.domain.model.Article
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.ArticleCardSize

@Composable
fun ArticleCard(modifier: Modifier = Modifier,
                article: Article,
                onClick:()->Unit
                ) {

    val  context = LocalContext.current
    Row(modifier = Modifier.clickable { onClick() }) {

        AsyncImage(
            modifier = Modifier.size(
                ArticleCardSize
            ).clip(MaterialTheme.shapes.medium)
            ,
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null


        )
    }
}