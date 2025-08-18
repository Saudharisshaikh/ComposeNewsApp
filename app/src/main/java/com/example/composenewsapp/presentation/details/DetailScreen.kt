package com.example.composenewsapp.presentation.details

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composenewsapp.R
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.presentation.details.components.DetailsTopBar
import com.example.composenewsapp.presentation.navgraph.Route
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.ArticleImageHeight
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.MeddiumPadding1

@SuppressLint("SuspiciousIndentation")
@Composable
fun DetailScreen(
    article:Article,
    event:(DetailEvent)-> Unit,
    navigateUp:()-> Unit

) {
 val context = LocalContext.current

    Column (modifier = Modifier.fillMaxSize()
        .statusBarsPadding()){

        DetailsTopBar(
            onBackClick = navigateUp,
            onShareClick = {
             Intent(Intent.ACTION_SEND).also {

                 it.putExtra(Intent.EXTRA_TEXT,article.url)
                 it.type = "text/plain"
             }

        },
            onBrowsingClick = {
            Intent(Intent.ACTION_VIEW).also {
                it.data = Uri.parse(article.url)
                if(it.resolveActivity(context.packageManager) != null){
                    context.startActivity(it)
                    if(it.resolveActivity(context.packageManager)!= null){
                        context.startActivity(it)
                    }
                }
            }


        }, onBookmarkClick = {
            event(DetailEvent.saveArticle)
            })

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                top = MeddiumPadding1,
                end =  MeddiumPadding1,
                start = MeddiumPadding1
            )

        ) {
           item {
               AsyncImage(
                   model = ImageRequest.Builder(context = context).data(article.url).build(),
                   contentDescription = null,
                   modifier = Modifier.fillMaxWidth().height(ArticleImageHeight)
                       .clip(MaterialTheme.shapes.medium)
               )
               Spacer(modifier = Modifier.height(MeddiumPadding1))
               Text(
                   text = article.title,
                   style = MaterialTheme.typography.displaySmall,
                   color = colorResource(R.color.text_title)
               )

               Text(
                   text = article.content,
                   style = MaterialTheme.typography.bodyMedium,
                   color = colorResource(R.color.body)
               )
           }
        }

    }

}