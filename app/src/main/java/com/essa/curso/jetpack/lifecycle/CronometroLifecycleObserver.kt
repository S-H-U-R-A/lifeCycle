package com.essa.curso.jetpack.lifecycle

import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class CronometroLifecycleObserver : LifecycleObserver{

    private val TAG_LOG = "LifecycleObserver"

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun suenaAlert(){
        var toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen1.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 300)
        Log.d(TAG_LOG, "On Resume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun suenaAnswer(){
        var toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen1.startTone(ToneGenerator.TONE_CDMA_ANSWER, 300)
        Log.d(TAG_LOG, "On Pause")
    }




}