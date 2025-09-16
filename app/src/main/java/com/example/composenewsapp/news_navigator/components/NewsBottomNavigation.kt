package com.example.composenewsapp.news_navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composenewsapp.R
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.ExtraSmallPadding2
import com.example.e_pharmacycompose.presentation.onboarding.Dimens.IconSize

@Composable
fun NewsBottomNavigation(
    items:List<BottomNavigationItem>,
    selected:Int,
    onItemClick:(Int)-> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
                 containerColor = MaterialTheme.colorScheme.background,
                 tonalElevation = 10.dp
        ) {

        items.forEachIndexed{index,item ->

            NavigationBarItem(
                selected = index == selected,
                onClick = {onItemClick(index)},
                icon = {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize))
                        Spacer(Modifier.height(ExtraSmallPadding2))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    selectedIndicatorColor = MaterialTheme.colorScheme.background,
                    disabledIconColor =  colorResource(id = R.color.body),
                    disabledTextColor = colorResource(id = R.color.body)
                )


            )
        }
    }

}

data class BottomNavigationItem(
    @DrawableRes val icon:Int,
    val text:String
)