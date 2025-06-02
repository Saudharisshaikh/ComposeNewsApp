package com.example.composenewsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.decode.ImageSource
import com.example.composenewsapp.R
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.presentation.common.ArticleList
import com.example.composenewsapp.presentation.common.SearchBar
import com.example.composenewsapp.presentation.navgraph.Route
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.MeddiumPadding1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(article: LazyPagingItems<Article>, navigate:(String) ->Unit) {

    // remember { ... }
    //This tells Compose to cache the value so it's not recomputed on every recomposition.
    //
    //It remembers the value as long as the composition is not destroyed (e.g., if the screen is not recomposed with a new key).
    //
    //derivedStateOf { ... }
    //This is the key part of your question.
    //
    //💡 What is derivedStateOf?
    //derivedStateOf is a Compose utility used to create a derived State that automatically recalculates only when the values it depends on change.
    //
    //Think of it like a computed property that is efficiently tracked — similar to useMemo in React.
    //
    //✅ Why use derivedStateOf here?
    //You use it to:

    // Optimize recompositions: This block depends on article.itemCount and article.itemSnapshotList.items. If those don’t change, Compose won’t re-evaluate this logic, saving performance.
    //
    //Avoid unnecessary recomputation: Without derivedStateOf, Compose might re-run this block on every recomposition, even if the article hasn't changed.
    //
    //Create reactive UI logic: The title automatically updates only when the underlying data (article) changes.
    //
    //🧠 What the Logic Does
    //If there are more than 10 articles:
// Joins them into a single string using a red square emoji (🟥) as a separator
//
//Else:
//
//It returns an empty string
//
//🔁 Real-World Analogy
//Suppose you're showing the titles of the first 10 news headlines as a fancy, combined string:
   // But you want this string to be generated only if the article list is updated — not on every frame refresh or unrelated recomposition.
    //
    //That’s exactly what derivedStateOf helps with.

    val title by remember {
        derivedStateOf {
            if(article.itemCount > 10){
                article.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "\uD83d\uDFE5")
            }
            else{
                ""
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = MeddiumPadding1)
            .statusBarsPadding()
    ) {
//        Image(
//            painter = painterResource(R.drawable.ic_logo),
//            contentDescription = null,
//            modifier = Modifier
//                .width(150.dp)
//                .height(30.dp)
//                .padding(MeddiumPadding1)
//        )
//
//
//        Spacer(modifier = Modifier.height(MeddiumPadding1))
//
//        SearchBar(modifier = Modifier.padding(horizontal = MeddiumPadding1).fillMaxWidth(),
//            text = "",
//            readOnly = true,
//            onValueChange = {},
//            onClick = {
//                navigate(Route.SearchScreen.route)
//            },
//            onSearch = {})
//
//        Spacer(modifier = Modifier.height(MeddiumPadding1))
//
//        Text(
//            text = title,
//            modifier = Modifier.fillMaxWidth()
//                .padding(MeddiumPadding1)
//                .basicMarquee(),
//            fontSize = 12.sp,
//            color = colorResource(R.color.placeholder)
//        )

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MeddiumPadding1)
        )



        Spacer(modifier = Modifier.height(MeddiumPadding1))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = MeddiumPadding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navigate(Route.SearchScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(MeddiumPadding1))

        Text(
            text = title, modifier = Modifier
                .fillMaxWidth()
                .padding(start = MeddiumPadding1)
                .basicMarquee(), fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
            , maxLines = 3
        )

        Spacer(modifier = Modifier.height(MeddiumPadding1))

        ArticleList(modifier = Modifier.padding(MeddiumPadding1), articles = article, onClick = {
            navigate(Route.DetailScreen.route)
        })
    }
}