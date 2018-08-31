package com.githubyss.mobile.morsecode.kit.player.audio.generator

import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils
import com.githubyss.mobile.morsecode.kit.converter.MckitMorseCodeConverterConfig
import com.githubyss.mobile.morsecode.kit.player.audio.player.MckitAudioPlayerConfig
import java.io.EOFException

/**
 * MckitAudioDataGenerateSquareWaveStrategy
 * <Description>
 * <Details>
 *
 * @designPatterns Strategy
 *
 * @author Ace Yan
 * @github githubyss
 */
abstract class MckitAudioDataGenerateStrategy {
    interface OnAudioDataGenerateListener {
        fun onSucceeded(audioDataArray: Array<Float>)
        fun onFailed(failingInfo: String)
        fun onCancelled()
    }


    abstract fun startGenerateAudioData(durationPatternArray: Array<Int>, onAudioDataGenerateListener: OnAudioDataGenerateListener)
    abstract fun startGenerateAudioData(durationPatternList: List<Int>, onAudioDataGenerateListener: OnAudioDataGenerateListener)
    abstract fun startGenerateAudioData(audioDurationMillis: Int, onAudioDataGenerateListener: OnAudioDataGenerateListener)
    abstract fun startGenerateAudioData(message: String, onAudioDataGenerateListener: OnAudioDataGenerateListener)
    abstract fun stopGenerateAudioData()


    /** Building the audioPlayerConfig by default variate value in itself when it was not built by user. by Ace Yan */
    protected val audioPlayerConfig =
            if (!MckitAudioPlayerConfig.instance.hasBuilt)
                MckitAudioPlayerConfig.Builder.create()
            else
                MckitAudioPlayerConfig.instance

    protected val morseCodeConverterConfig =
            if (!MckitMorseCodeConverterConfig.instance.hasBuilt)
                MckitMorseCodeConverterConfig.Builder
                        .create()
            else
                MckitMorseCodeConverterConfig.instance


    protected fun buildTimeSampleArray(audioSampleRateHz: Int, audioDurationMillis: Int): Array<Float> {
        /** Calculate out the size of timeSampleArray . by Ace Yan */
        val timeSampleArraySize = calculateTimeSampleCollectionSize(audioSampleRateHz, audioDurationMillis)

        /** Init timeSampleArray. by Ace Yan */
        val timeSampleArray = Array(timeSampleArraySize, { 0.toFloat() })

        /** Traverse timeSampleArray to calculate out each element in it. by Ace Yan */
        for (idx in 0 until timeSampleArraySize) {
            timeSampleArray[idx] = calculateTimeSample(audioSampleRateHz, idx)
        }

        return timeSampleArray
    }

    protected fun buildTimeSampleList(audioSampleRateHz: Int, audioDurationMillis: Int): List<Float> {
        /** Calculate out the size of timeSampleList . by Ace Yan */
        val timeSampleListSize = calculateTimeSampleCollectionSize(audioSampleRateHz, audioDurationMillis)

        /** Init timeSampleList. by Ace Yan */
        val timeSampleList = ArrayList<Float>()

        /** Traverse timeSampleList to calculate out each element in it. by Ace Yan */
        (0 until timeSampleListSize)
                .mapTo(timeSampleList) { calculateTimeSample(audioSampleRateHz, it) }

        return timeSampleList
    }

    /**
     * MckitAudioDataGenerateStrategy.buildMuteAudioDataList(audioSampleRateHz, audioDurationMillis)
     * <Description> Build muteAudioDataList by the size of timeSample of durationPattern.
     * <Details>
     *
     * @param audioSampleRateHz
     * @param audioDurationMillis
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    protected fun buildMuteAudioDataList(audioSampleRateHz: Int, audioDurationMillis: Int): List<Float> {
        return try {
            List(calculateTimeSampleCollectionSize(audioSampleRateHz, audioDurationMillis)) { 0F }
        } catch (exception: EOFException) {
            ComkitLogcatUtils.e(t = exception)
            emptyList()
        } catch (exception: OutOfMemoryError) {
            ComkitLogcatUtils.e(t = exception)
            emptyList()
        }
    }

    /**
     * MckitAudioDataGenerateStrategy.calculateTimeSampleCollectionSize(audioSampleRateHz, audioDurationMillis)
     * <Description> Calculate out the size of time sample collection.
     * <Details>
     *
     * @param audioSampleRateHz
     * @param audioDurationMillis
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    protected fun calculateTimeSampleCollectionSize(audioSampleRateHz: Int, audioDurationMillis: Int): Int {
        return (Math.floor(audioDurationMillis / 1000.00 * audioSampleRateHz)).toInt()
    }

    /**
     * MckitAudioDataGenerateStrategy.calculateTimeSample(audioSampleRateHz, idx)
     * <Description> Calculate out each time sample element of time sample collection.
     * <Details> Time sample will act as x-coordinate in coordinate system of time-signal.
     *
     * @param audioSampleRateHz
     * @param idx
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    private fun calculateTimeSample(audioSampleRateHz: Int, idx: Int): Float {
        return (0 + (1 / audioSampleRateHz.toFloat()) * idx)
    }
}
