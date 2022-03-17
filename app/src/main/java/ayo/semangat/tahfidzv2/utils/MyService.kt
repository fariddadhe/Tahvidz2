package ayo.semangat.tahfidzv2.utils

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import java.lang.UnsupportedOperationException

class MyService : Service() {

    companion object{
        private val TAG = MyService::class.java.simpleName
    }

    private var mBinder = MyBinder()
    private val startTime = System.currentTimeMillis()

    internal inner class MyBinder: Binder(){
        val getService: MyService = this@MyService
    }

    private var mTimer: CountDownTimer = object : CountDownTimer(100000, 1000){
        override fun onTick(p0: Long) {
            val elapsedTime = System.currentTimeMillis() - startTime
            Log.d(TAG, "onTick: $elapsedTime")
        }

        override fun onFinish() {

        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind: ")
        mTimer.start()
        return mBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ")
        mTimer.cancel()
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind")
    }

}