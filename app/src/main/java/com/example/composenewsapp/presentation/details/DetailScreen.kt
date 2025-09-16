package com.example.composenewsapp.presentation.details

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composenewsapp.R
import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.presentation.details.components.DetailsTopBar
import com.example.composenewsapp.presentation.navgraph.Route
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.ArticleImageHeight
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.MeddiumPadding1
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors


@Composable
fun NewsDetailsScreen(
    articlesEntity: ArticlesEntity,
    event:(DetailEvent)-> Unit,
    navigateUp:()-> Unit

) {
    val context = LocalContext.current

    Row(
       horizontalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.width(50.dp))
        Text(
            text = "Details",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Medium),
            color = colorResource(R.color.text_title),
            modifier = Modifier.padding(top = 8.dp)
        )
    }


    Column (modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()){

        DetailsTopBar(
            onBackClick = navigateUp,
            onShareClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_TEXT, articlesEntity.url)
                    type = "text/plain"
                }
                context.startActivity(Intent.createChooser(shareIntent, "Share via"))
            }

        ,
            onBrowsingClick = {
            Intent(Intent.ACTION_VIEW).also {
                it.data = Uri.parse(articlesEntity.url)
                if(it.resolveActivity(context.packageManager) != null){
                    context.startActivity(it)

                }
            }


        },
//            onBookmarkClick = {
//                Log.d("--exp", "NewsDetailsScreen: ")
//            try{
//
//                Observable.fromCallable {
//                    event(
//                        DetailEvent.UpsertDeleteArticle(article = articlesEntity)
//                    )
//                }
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ result -> Log.d("--error", "invoke: ") }, { throwable ->  })
//
//            } catch (e:Exception){
//                Log.d("--exp", "NewsDetailsScreen: ${e.message}")
//            }
//
//            }

            onBookmarkClick = {
                Log.d("--exp", "NewsDetailsScreen: bookmark clicked")

                val executor = Executors.newSingleThreadExecutor()
                val handler = Handler(Looper.getMainLooper())

                executor.execute {
                    try {
                        // Background work
                        event(DetailEvent.UpsertDeleteArticle(article = articlesEntity))

                        // Post success to main thread
                        handler.post {
                            Log.d("--success", "Bookmark operation completed")
                        }
                    } catch (e: Exception) {
                        Log.e("--error", "Bookmark operation failed: ${e.message}")
                        e.printStackTrace()

                        // Post error to main thread if needed
                        handler.post {
                            // Handle UI update for error
                        }
                    }
                }
            }
        )

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
                   model = ImageRequest.Builder(context)
                       .data(articlesEntity.urlToImage ?: "")
                       .crossfade(true)
                       .build(),
                   contentDescription = null,
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(ArticleImageHeight)
                       .clip(MaterialTheme.shapes.medium)
               )

               Text(
                   text = articlesEntity.title ?: "No Title",
                   style = MaterialTheme.typography.displaySmall,
                   color = colorResource(R.color.text_title)
               )

               Text(
                   text = articlesEntity.content ?: "No Content",
                   style = MaterialTheme.typography.bodyMedium,
                   color = colorResource(R.color.body)
               )
           }
        }

    }
}

//@Composable
//fun DetailScreen(
//    articlesEntity: ArticlesEntity,
//    event:(DetailEvent)-> Unit,
//    navigateUp:()-> Unit
//
//) {
//
// val context = LocalContext.current
//
//    Column (modifier = Modifier.fillMaxSize()
//        .statusBarsPadding()){
//
//        DetailsTopBar(
//            onBackClick = navigateUp,
//            onShareClick = {
//                val shareIntent = Intent(Intent.ACTION_SEND).apply {
//                    putExtra(Intent.EXTRA_TEXT, articlesEntity.url)
//                    type = "text/plain"
//                }
//                context.startActivity(Intent.createChooser(shareIntent, "Share via"))
//            }
//
//        ,
//            onBrowsingClick = {
//            Intent(Intent.ACTION_VIEW).also {
//                it.data = Uri.parse(articlesEntity.url)
//                if(it.resolveActivity(context.packageManager) != null){
//                    context.startActivity(it)
//
//                }
//            }
//
//
//        }, onBookmarkClick = {
//            event(DetailEvent.UpsertDeleteArticle(article = articlesEntity))
//            })
//
//        LazyColumn(
//            modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(
//                top = MeddiumPadding1,
//                end =  MeddiumPadding1,
//                start = MeddiumPadding1
//            )
//
//        ) {
//           item {
//               AsyncImage(
//                   model = ImageRequest.Builder(context)
//                       .data(articlesEntity.urlToImage ?: "")
//                       .crossfade(true)
//                       .build(),
//                   contentDescription = null,
//                   modifier = Modifier
//                       .fillMaxWidth()
//                       .height(ArticleImageHeight)
//                       .clip(MaterialTheme.shapes.medium)
//               )
//
//               Text(
//                   text = articlesEntity.title ?: "No Title",
//                   style = MaterialTheme.typography.displaySmall,
//                   color = colorResource(R.color.text_title)
//               )
//
//               Text(
//                   text = articlesEntity.content ?: "No Content",
//                   style = MaterialTheme.typography.bodyMedium,
//                   color = colorResource(R.color.body)
//               )
//           }
//        }
//
//    }
//
//}