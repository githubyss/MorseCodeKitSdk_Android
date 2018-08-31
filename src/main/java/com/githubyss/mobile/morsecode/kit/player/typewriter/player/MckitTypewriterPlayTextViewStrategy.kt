package com.githubyss.mobile.morsecode.kit.player.typewriter.player

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.githubyss.mobile.common.kit.info.ComkitSystemInfo
import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils
import com.githubyss.mobile.common.kit.resource.ComkitResUtils
import com.githubyss.mobile.morsecode.kit.R
import com.githubyss.mobile.morsecode.kit.constant.MckitKeyConstants

/**
 * MckitTypewriterPlayTextViewStrategy
 * <Description>
 * <Details>
 *
 * @designPatterns Strategy
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitTypewriterPlayTextViewStrategy : MckitTypewriterPlayStrategy() {
    private var typewriterPlayAsyncTask: TypewriterPlayAsyncTask? = null

    private var beginTime = 0L
    private var endTime = 0L

    private var exceptionInfo = String()


    private inner class TypewriterPlayAsyncTask(private val typewriterView: TextView, private val onTypewriterPlayListener: OnTypewriterPlayListener) : AsyncTask<Bundle, Int, Boolean>() {
        override fun doInBackground(vararg params: Bundle?): Boolean? {
            if (isCancelled) {
                ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> TypewriterPlayAsyncTask.doInBackground() >>> isCancelled.")
            }

            beginTime = ComkitSystemInfo.currentTimeMillis()

            return try {
                val bundle = params[0]
                val message = bundle?.getString(MckitKeyConstants.TypewriterKey.MESSAGE_STR) ?: String()
                val data = bundle?.getIntegerArrayList(MckitKeyConstants.TypewriterKey.DURATION_LIST) ?: emptyList<Int>()
                startTypewrite(message, data, typewriterView, typewriterPlayerConfig)
                true
            } catch (exception: InterruptedException) {
                ComkitLogcatUtils.e(t = exception)
                exceptionInfo = "${ComkitResUtils.getString(resId = R.string.mckitFailingInfo)} ${exception.javaClass.simpleName}!"
                false
            }
        }

        override fun onPostExecute(result: Boolean?) {
            if (isCancelled) {
                return
            }

            endTime = ComkitSystemInfo.currentTimeMillis()
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> TypewriterPlayAsyncTask.onPostExecute() >>> Elapsed time = ${endTime - beginTime} ms.")

            if (result != false) {
                onTypewriterPlayListener.onSucceeded()
            } else {
                onTypewriterPlayListener.onFailed(exceptionInfo)
            }
        }

        override fun onCancelled() {
            endTime = ComkitSystemInfo.currentTimeMillis()
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> TypewriterPlayAsyncTask.onPostExecute() >>> Elapsed time = ${endTime - beginTime} ms.")

            onTypewriterPlayListener.onCancelled()
        }
    }


    override fun startPlayTypewriter(bundle: Bundle, typewriterView: View, onTypewriterPlayListener: OnTypewriterPlayListener) {
        typewriterPlayAsyncTask = TypewriterPlayAsyncTask(typewriterView as TextView, onTypewriterPlayListener)
        typewriterPlayAsyncTask?.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, bundle)
    }

    override fun stopPlayTypewriter() {
        if (typewriterPlayAsyncTask?.status == AsyncTask.Status.RUNNING) {
            typewriterPlayAsyncTask?.cancel(true)
            typewriterPlayAsyncTask = null
        }
    }


    private fun startTypewrite(typewriterDataStr: String, typewriterDurationList: List<Int>, typewriterView: TextView, typewriterPlayerConfig: MckitTypewriterPlayerConfig) {
        if (typewriterDataStr.isEmpty()) {
            return
        }

        val startIdx = typewriterPlayerConfig.startIdx
        val canAutoScrollBottom = typewriterPlayerConfig.canAutoScrollBottom

        try {
            Thread.sleep(typewriterDurationList[0].toLong())
            for (idx in startIdx until typewriterDataStr.length) {
                if (typewriterPlayAsyncTask?.isCancelled != false) {
                    return
                }

                val char = typewriterDataStr[idx]

                textViewPostByAppending(typewriterView, char, canAutoScrollBottom)

                if (!Character.isWhitespace(char)) {
                    Thread.sleep(typewriterDurationList[idx + 1].toLong())
                }
            }
        } catch (exception: Exception) {
            ComkitLogcatUtils.e(t = exception)
            exceptionInfo = "${ComkitResUtils.getString(resId = R.string.mckitFailingInfo)} ${exception.javaClass.simpleName}!"
        }
    }

    private fun textViewPostByAppending(textView: TextView, char: Char, canAutoScrollBottom: Boolean) {
        textView.post {
            textView.append(char.toString())
            if (canAutoScrollBottom) {
                textViewAutoScrollBottom(textView)
            }
        }
    }

    private fun textViewAutoScrollBottom(textView: TextView) {
        val offsetHeight = textView.lineCount * textView.lineHeight
        val actualHeight = textView.height - (textView.paddingTop + textView.paddingBottom)
        if (offsetHeight > actualHeight) {
            textView.scrollTo(0, offsetHeight - actualHeight)
        }
    }
}
