package com.testm.interviewproject.PlayBack

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.View
import androidx.lifecycle.ViewModel
import com.testm.interviewproject.R
import com.testm.interviewproject.utils.App.Companion.errorToast
import kotlinx.android.synthetic.main.audio.view.*

class PlaybackViewModel : ViewModel(), MediaPlayer.OnPreparedListener {

    lateinit var currentView: View
    lateinit var mediaPlayer: MediaPlayer

    fun setMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build())
            setOnPreparedListener(this@PlaybackViewModel)
        }
    }

    fun onClick(url: String, viewItem: View) {

        if (this::currentView.isInitialized) {
            mediaPlayer.reset()
            setButtonIcon(R.drawable.ic_play)
        }

        currentView = viewItem
        if (viewItem.playPauseBtn.tag.equals("play")) {
            viewItem.playPauseBtn.tag = "pause"
            viewItem.progressBar.visibility = View.VISIBLE
            viewItem.playPauseBtn.visibility = View.GONE
            try {
                mediaPlayer.setDataSource(url)
                mediaPlayer.prepareAsync()
            } catch (err: IllegalArgumentException) {
                errorToast("An error has occurred, please check you url path")
            }
        } else viewItem.playPauseBtn.tag = "play"
    }

    private fun setButtonIcon(icon: Int) {
        currentView.playPauseBtn.setImageResource(icon)
        currentView.progressBar.visibility = View.GONE
        currentView.playPauseBtn.visibility = View.VISIBLE
    }

    override fun onPrepared(p0: MediaPlayer?) {
        mediaPlayer.start()
        setButtonIcon(R.drawable.ic_pause)
    }
}