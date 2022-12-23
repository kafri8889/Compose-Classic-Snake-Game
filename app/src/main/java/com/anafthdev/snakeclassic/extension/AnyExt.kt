package com.anafthdev.snakeclassic.extension

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun Any?.toast(context: Context, length: Int = Toast.LENGTH_SHORT) {
	Handler(Looper.getMainLooper()).post {
		Toast.makeText(context, toString(), length).show()
	}
}

@Composable
fun Any?.toast(length: Int = Toast.LENGTH_SHORT) {
	Toast.makeText(LocalContext.current, toString(), length).show()
}
