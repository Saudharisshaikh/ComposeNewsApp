package com.example.composenewsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.composenewsapp.R
import com.example.composenewsapp.presentation.common.ArticleList
import com.example.composenewsapp.presentation.navgraph.Route
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.MeddiumPadding1

@Composable
fun BookmarkScreen(bookmarkState: BookmarkState,
                   navigate:(String) -> Unit ) {

    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .padding(start = MeddiumPadding1, end = MeddiumPadding1, top = MeddiumPadding1)
        ) {


        Text(text= "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Medium),
            color = colorResource(R.color.text_title)

        )
        Spacer(modifier =  Modifier.height(MeddiumPadding1))
        ArticleList(articles = bookmarkState.articleList, onClick = {navigate(Route.DetailScreen.route)})
    }


}