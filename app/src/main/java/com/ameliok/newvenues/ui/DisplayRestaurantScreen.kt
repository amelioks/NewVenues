package com.ameliok.newvenues.ui

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DisplayRestaurantFragmentContainer() {
    val fragment = remember {
        DisplayRestaurantFragment()
    }
    // The fragment will be added to the AndroidView
    AndroidView(
        factory = { context ->
            FrameLayout(context).apply {
                id = View.generateViewId()
            }
        },
        update = { view ->
            // Add the fragment to the container
            val fragmentManager = (view.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(view.id, fragment)
                .commit()
        }
    )

}