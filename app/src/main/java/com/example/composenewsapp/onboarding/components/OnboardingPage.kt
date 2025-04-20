package com.example.composenewsapp.onboarding.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.composenewsapp.R

import com.example.e_pharmacycompose.presentation.onboarding.Dimens.MeddiumPadding1
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.MeddiumPadding2
import com.example.e_pharmacycompose.presentation.onboarding.Page

@Composable
fun OnBoardingPage(modifier: Modifier = Modifier,
                   page: Page
                   ) {

    Column(modifier = modifier){

        Image(
            painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight(0.6f)
                .fillMaxWidth()

        )
        Spacer(Modifier.height(MeddiumPadding1))
        Text(page.title,
            modifier = Modifier.padding(MeddiumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
            , color = colorResource(R.color.display_small)
        )

        Text(page.title,
            modifier = Modifier.padding(MeddiumPadding2),
            style = MaterialTheme.typography.bodyMedium
            , color = colorResource(R.color.display_small)
        )

    }
}