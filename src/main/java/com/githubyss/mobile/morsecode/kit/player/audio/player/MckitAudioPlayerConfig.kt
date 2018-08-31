package com.githubyss.mobile.morsecode.kit.player.audio.player

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack

/**
 * MckitAudioPlayerConfig
 * <Description>
 * <Details>
 *
 * @designPatterns Singleton, Builder
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitAudioPlayerConfig private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = MckitAudioPlayerConfig()
    }


    var audioFrequencyHz = 1000
        private set

    var audioSampleRateHz = 4000
        private set

    var audioChannelFormat = AudioFormat.CHANNEL_OUT_MONO
        private set

    var audioEncodingPcmFormat = AudioFormat.ENCODING_PCM_16BIT
        private set

    var audioMinBufferSize = AudioTrack.getMinBufferSize(audioSampleRateHz, audioChannelFormat, audioEncodingPcmFormat)
        private set

    var audioStreamType = AudioManager.STREAM_MUSIC
        private set

    var audioTrackMode = AudioTrack.MODE_STREAM
        private set

    var audioTrack = AudioTrack(audioStreamType, audioSampleRateHz, audioChannelFormat, audioEncodingPcmFormat, audioMinBufferSize, audioTrackMode)
        private set

    var hasBuilt = false
        private set


    object Builder {
        private var audioFrequencyHz = 1000
        private var audioSampleRateHz = 4000
        private var audioChannelFormat = AudioFormat.CHANNEL_OUT_MONO
        private var audioEncodingPcmFormat = AudioFormat.ENCODING_PCM_16BIT

        private var audioStreamType = AudioManager.STREAM_MUSIC
        private var audioTrackMode = AudioTrack.MODE_STREAM

        fun setAudioFrequencyHz(audioFrequencyHz: Int): Builder {
            var frequency = audioFrequencyHz
            if (frequency <= 0) {
                frequency = 1000
            }

            Builder.audioFrequencyHz = frequency
            return this@Builder
        }

        fun setAudioSampleRateHz(audioSampleRateHz: Int): Builder {
            var sampleRate = audioSampleRateHz
            if (sampleRate <= 0) {
                sampleRate = 44100
            }

            Builder.audioSampleRateHz = sampleRate
            return this@Builder
        }

        fun setAudioChannelFormat(audioChannelFormat: Int): Builder {
            Builder.audioChannelFormat = audioChannelFormat
            return this@Builder
        }

        fun setAudioEncodingPcmFormat(audioEncodingPcmFormat: Int): Builder {
            Builder.audioEncodingPcmFormat = audioEncodingPcmFormat
            return this@Builder
        }

        fun setAudioStreamType(audioStreamType: Int): Builder {
            Builder.audioStreamType = audioStreamType
            return this@Builder
        }

        fun setAudioTrackMode(audioTrackMode: Int): Builder {
            Builder.audioTrackMode = audioTrackMode
            return this@Builder
        }

        fun create(): MckitAudioPlayerConfig {
            applyConfig(instance)
            return instance
        }

        private fun applyConfig(config: MckitAudioPlayerConfig) {
            config.audioFrequencyHz = audioFrequencyHz
            config.audioSampleRateHz = audioSampleRateHz
            config.audioChannelFormat = audioChannelFormat
            config.audioEncodingPcmFormat = audioEncodingPcmFormat

            config.audioMinBufferSize = buildMinBufferSize(config)

            config.audioStreamType = audioStreamType
            config.audioTrackMode = audioTrackMode

            /**
             * You must release the old instance of AudioTrack before build a new one to prevent the error as follow:
             *
             * 05-29 18:17:56.466 E/AudioTrack: AudioFlinger could not create track, status: -12
             * 05-29 18:17:56.466 E/AudioTrack-JNI: Error -12 initializing AudioTrack
             * 05-29 18:17:56.466 E/android.media.AudioTrack: Error code -20 when initializing AudioTrack.
             *
             * This is because there are limited amount of instance of AudioTrack in Android system. If there are too many AudioTrack instance that have not been released, it may case error as above as building another new AudioTrack instance. So you should guarantee that there are not too many AudioTrack instance in you app.
             *
             * In this case, it will release the resource of old instance of AudioTrack when you are building a new one according to the new configuration. As a result, it will stop all plays of the old instance no matter what state and playState it is.
             * In other word, you can stop and release the old AudioTrack resource by building a new instance of AudioTrack by this way.
             * by Ace Yan
             */
            config.audioTrack.release()

            /** It will build a brand new instance of AudioTrack when you are building a new MckitAudioPlayerConfig. by Ace Yan */
            config.audioTrack = buildAudioTrack(config)

            config.hasBuilt = true
        }

        private fun buildMinBufferSize(config: MckitAudioPlayerConfig): Int {
            val audioSampleRateHz = config.audioSampleRateHz
            val audioChannelFormat = config.audioChannelFormat
            val audioEncodingPcmFormat = config.audioEncodingPcmFormat

            return AudioTrack.getMinBufferSize(audioSampleRateHz, audioChannelFormat, audioEncodingPcmFormat)
        }

        private fun buildAudioTrack(config: MckitAudioPlayerConfig): AudioTrack {
            val audioSampleRateHz = config.audioSampleRateHz
            val audioChannelFormat = config.audioChannelFormat
            val audioEncodingPcmFormat = config.audioEncodingPcmFormat

            val audioStreamType = config.audioStreamType
            val audioPlayMode = config.audioTrackMode

            val audioMinBufferSize = config.audioMinBufferSize

            return AudioTrack(audioStreamType, audioSampleRateHz, audioChannelFormat, audioEncodingPcmFormat, audioMinBufferSize, audioPlayMode)
        }
    }
}
