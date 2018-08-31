package com.githubyss.mobile.morsecode.kit.player.typewriter.generator

import com.githubyss.mobile.morsecode.kit.converter.MckitMorseCodeConverterConfig

/**
 * MckitTypewriterDurationGenerateStrategy
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
abstract class MckitTypewriterDurationGenerateStrategy {
    interface OnTypewriterDurationGenerateListener {
        fun onSucceeded(typewriterDurationList: List<Int>)
        fun onFailed(failingInfo: String)
        fun onCancelled()
    }


    abstract fun startGenerateTypewriterDuration(message: String, onTypewriterDurationGenerateListener: OnTypewriterDurationGenerateListener)
    abstract fun stopGenerateTypewriterDuration()


    protected val morseCodeConverterConfig =
            if (!MckitMorseCodeConverterConfig.instance.hasBuilt)
                MckitMorseCodeConverterConfig.Builder
                        .create()
            else
                MckitMorseCodeConverterConfig.instance
}
