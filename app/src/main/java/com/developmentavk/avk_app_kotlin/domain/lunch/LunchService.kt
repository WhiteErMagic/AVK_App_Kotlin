package com.developmentavk.avk_app_kotlin.domain.lunch

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.*
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*

class LunchService: Service() {

    var timer:Timer? = null
    var lunchTime = 0
    var onLunchTimeChanged: ((Int) -> Unit)? = null

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val notificationManager by lazy {
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    private val notificationBuilder by lazy {
        createNotificationBuilder()
    }

    companion object{
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1
        const val LUNCH_TIME = "lunch_time"
    }

    private fun createNotificationBuilder() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("Lunch")
        .setContentText("Service lunch")

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChanel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChanel)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        if(lunchTime == 0)
            lunchTime = intent.getIntExtra(LUNCH_TIME, 0)
        return LocalBinder()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if(lunchTime == 0)
            lunchTime = intent.getIntExtra(LUNCH_TIME, 0)
        return START_REDELIVER_INTENT
    }

    fun startLunch() {
        coroutineScope.launch {
            if (timer == null) {
                timer = Timer()
            }
            timer!!.scheduleAtFixedRate(tTask(), 0, 5000)
        }
    }

    inner class LocalBinder() : Binder() {
        fun getService() = this@LunchService
    }

    private fun stopLunch() {
        timer?.cancel()
        timer = null
        coroutineScope.cancel()
        stopForeground(true)
        stopSelf()
    }

    inner class tTask : TimerTask() {
        override fun run() {
            try {
                onLunchTimeChanged?.invoke(--lunchTime)
            }catch (e:Exception){

            }
            if(lunchTime == 0) {
                bell()
                stopLunch()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLunch()
    }

    fun bell() {
        val alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val vibrator = applicationContext.getSystemService(VIBRATOR_SERVICE) as Vibrator
        val mills = 5000L
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val mVibratePattern = longArrayOf(0, 400, 200, 600, 200, 800, 200, 1000)
                val mAmplitudes = intArrayOf(0, 255, 0, 255, 0, 255, 0, 255)
                var effect = if (vibrator.hasAmplitudeControl()) VibrationEffect.createWaveform(
                    mVibratePattern,
                    mAmplitudes,
                    -1
                ) else VibrationEffect.createWaveform(mVibratePattern, -1)
                vibrator.vibrate(effect)
            } else {
                vibrator.vibrate(mills)
            }
        }
        val mp = MediaPlayer.create(applicationContext, alert)
        val duration = mp.duration
        mp.start()
        val cur = System.currentTimeMillis()
        while (duration.toLong() > System.currentTimeMillis() - cur) {}
        mp.stop()
        stopLunch()
    }
}