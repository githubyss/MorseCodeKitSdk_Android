package com.githubyss.mobile.morsecode.kit.player.typewriter.generator

import android.os.AsyncTask
import com.githubyss.mobile.common.kit.info.ComkitSystemInfo
import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils
import com.githubyss.mobile.common.kit.resource.ComkitResUtils
import com.githubyss.mobile.morsecode.kit.R
import com.githubyss.mobile.morsecode.kit.converter.MckitMorseCodeConverterConfig
import java.io.EOFException

/**
 * MckitTypewriterDurationGeneratePresentStrategy
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitTypewriterDurationGeneratePresentStrategy : MckitTypewriterDurationGenerateStrategy() {
    private var typewriterDurationGenerateAsyncTask: TypewriterDurationGenerateAsyncTask? = null

    private var beginTime = 0L
    private var endTime = 0L

    private var exceptionInfo = ""


    private inner class TypewriterDurationGenerateAsyncTask(private val onTypewriterDurationGenerateListener: OnTypewriterDurationGenerateListener) : AsyncTask<String, Int, List<Int>>() {
        override fun doInBackground(vararg params: String?): List<Int> {
            if (isCancelled) {
                ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> TypewriterDurationGenerateAsyncTask.doInBackground() >>> isCancelled")
                return emptyList()
            }

            beginTime = ComkitSystemInfo.currentTimeMillis()

            return try {
                buildTypewriterDurationList(params[0] ?: String(), morseCodeConverterConfig)
            } catch (exception: InterruptedException) {
                ComkitLogcatUtils.e(t = exception)
                emptyList()
            }
        }

        override fun onPostExecute(result: List<Int>?) {
            if (isCancelled) {
                return
            }

            endTime = ComkitSystemInfo.currentTimeMillis()
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> TypewriterDurationGenerateAsyncTask.onPostExecute() >>> Elapsed time = ${endTime - beginTime} ms.")
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> TypewriterDurationGenerateAsyncTask.onPostExecute() >>> audioDataSize = ${result?.size}")
            ComkitLogcatUtils.`object`(result)

            if (result?.isEmpty() != false
                    || exceptionInfo.contains(ComkitResUtils.getString(resId = R.string.mckitFailingInfo) ?: "")) {
                onTypewriterDurationGenerateListener.onFailed(exceptionInfo)
                return
            }

            onTypewriterDurationGenerateListener.onSucceeded(result)
        }

        override fun onCancelled() {
            endTime = ComkitSystemInfo.currentTimeMillis()
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> TypewriterDurationGenerateAsyncTask.onCancelled() >>> Elapsed time = ${endTime - beginTime} ms.")

            onTypewriterDurationGenerateListener.onCancelled()
        }
    }


    override fun startGenerateTypewriterDuration(message: String, onTypewriterDurationGenerateListener: OnTypewriterDurationGenerateListener) {
        typewriterDurationGenerateAsyncTask = TypewriterDurationGenerateAsyncTask(onTypewriterDurationGenerateListener)
        typewriterDurationGenerateAsyncTask?.execute(message)
    }

    override fun stopGenerateTypewriterDuration() {
        if (typewriterDurationGenerateAsyncTask?.status == AsyncTask.Status.RUNNING) {
            typewriterDurationGenerateAsyncTask?.cancel(true)
            typewriterDurationGenerateAsyncTask = null
        }
    }


    /**
     * MckitTypewriterDurationGeneratePresentStrategy.buildTypewriterDurationList(message)
     * <Description>
     * <Details>
     *
     * @param message
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    private fun buildTypewriterDurationList(message: String, morseCodeConverterConfig: MckitMorseCodeConverterConfig): List<Int> {
        val charGapDuration = morseCodeConverterConfig.charGapDuration
        val wordGapDuration = morseCodeConverterConfig.wordGapDuration
        val startDuration = morseCodeConverterConfig.startDuration

        val char2DurationMap = morseCodeConverterConfig.char2DurationMap

        if (message.isEmpty()) {
            return arrayListOf(startDuration)
        }

        var lastCharWasWhiteSpace = true

        val messageDurationList = ArrayList<Int>()
        messageDurationList.add(startDuration)

        return try {
            (0 until message.length)
                    .asSequence()
                    .map { message[it] }
                    .forEach {
                        if (typewriterDurationGenerateAsyncTask?.isCancelled != false) {
                            return emptyList()
                        }

                        if (Character.isWhitespace(it)) {
                            if (!lastCharWasWhiteSpace) {
                                messageDurationList[messageDurationList.size - 1] = (messageDurationList.last() + wordGapDuration)
                                lastCharWasWhiteSpace = true
                            }
                        } else {
                            if (!lastCharWasWhiteSpace) {
                                messageDurationList[messageDurationList.size - 1] = (messageDurationList.last() + charGapDuration)
                            }

                            lastCharWasWhiteSpace = false

                            messageDurationList.add(char2DurationMap[it] ?: 0)
                        }
                    }

            messageDurationList
        } catch (exception: EOFException) {
            ComkitLogcatUtils.e(t = exception)
            exceptionInfo = "${ComkitResUtils.getString(resId = R.string.mckitFailingInfo)} ${exception.javaClass.simpleName}!"
            emptyList()
        } catch (exception: OutOfMemoryError) {
            ComkitLogcatUtils.e(t = exception)
            exceptionInfo = "${ComkitResUtils.getString(resId = R.string.mckitFailingInfo)} ${exception.javaClass.simpleName}!"
            emptyList()
        }
    }
}
