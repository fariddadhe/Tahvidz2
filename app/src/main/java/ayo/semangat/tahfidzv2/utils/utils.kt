package ayo.semangat.tahfidzv2.utils

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi

fun showLoading(state: Boolean, view: View){
    if (state){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

//@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//fun changeToolState(color: String, type: String, window: Window){
//    ChangeStatusBarColor(
//        window, window.decorView)
//        .Color(color, type)
//}

fun <T: Activity> intentUt(clazz: Class<T>, activity: Activity, flag: Boolean) {
    val intent = Intent(activity, clazz)
    if (flag){
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
    activity.startActivity(intent)
    if (flag){
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}


fun isConnectedToNetwork(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

//fun View.snackbar(message: String){
//    Snackbar.make(
//        this,
//        message,
//        Snackbar.LENGTH_LONG
//    ).also { snackbar ->
//        snackbar.setAction("Ok"){
//            snackbar.dismiss()
//        }
//    }
//}

fun ContentResolver.getFileName(fileUri: Uri): String{
    var name = ""
    val returnCursor = this.query(fileUri, null, null, null, null)
    if (returnCursor != null){
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return name
}

fun milliSecondToTimer(milliSeconds: Int): String {
    var timerString = ""
    var secondsString = ""

    var hours = milliSeconds.toInt() / (1000 * 60 * 60)
    var minutes = milliSeconds.toInt() % (1000 * 60 * 60) / (1000 * 60)
    var seconds = milliSeconds.toInt() % (1000 * 60 * 60) % (1000 * 60) / 1000

    if (hours > 0) {
        timerString = "$hours:"
    }
    if (seconds < 10) {
        secondsString = "0$seconds"
    } else {
        secondsString = "$seconds"
    }

    timerString = "$timerString$minutes:$secondsString"
    return timerString
}