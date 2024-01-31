package com.example.materialthemeapp

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.materialthemeapp.Models.Dog
import com.example.materialthemeapp.Models.dogs
import com.example.materialthemeapp.ui.theme.MaterialThemeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialThemeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(modifier: Modifier = Modifier){
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null
                )

                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.body2
                )

            }
        },
        modifier = modifier
    )
}

@Composable
fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier){
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(dogIcon),
        contentDescription = null
    )
}

@Composable
fun DogInfo(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier){
    Column(modifier = modifier) {

        Text(
            text = stringResource(dogName),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )

        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.h6
        )

    }
}

@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier){
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            DogIcon(dog.imageResourceId)
            DogInfo(dog.name, dog.age)
        }
    }
}

@Composable
fun MainApp(){
    Scaffold(
        topBar = {
            MainAppBar()
        }
    ) {it ->
        LazyColumn(contentPadding = it) {
            items(dogs){
                DogItem(
                    dog = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialThemeAppTheme(darkTheme = false) {
        MainApp()
    }
}

@Preview
@Composable
fun WoofDarkThemePreview() {
    MaterialThemeAppTheme(darkTheme = true) {
        MainApp()
    }
}