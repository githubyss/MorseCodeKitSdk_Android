package com.githubyss.mobile.morsecode.kit.player.typewriter.player

import android.os.Bundle
import android.view.View

/**
 * MckitTypewriterPlayStrategy
 * <Description>
 * <Details>
 *
 * @designPatterns Strategy
 *
 * @author Ace Yan
 * @github githubyss
 */
abstract class MckitTypewriterPlayStrategy {
    interface OnTypewriterPlayListener {
        fun onSucceeded()
        fun onFailed(failingInfo: String)
        fun onCancelled()
    }


    abstract fun startPlayTypewriter(bundle: Bundle, typewriterView: View, onTypewriterPlayListener: OnTypewriterPlayListener)
    abstract fun stopPlayTypewriter()


    protected val typewriterPlayerConfig =
            if (!MckitTypewriterPlayerConfig.instance.hasBuilt)
                MckitTypewriterPlayerConfig.Builder.create()
            else
                MckitTypewriterPlayerConfig.instance
}
