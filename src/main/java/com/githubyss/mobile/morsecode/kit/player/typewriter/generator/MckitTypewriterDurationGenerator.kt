package com.githubyss.mobile.morsecode.kit.player.typewriter.generator

/**
 * MckitTypewriterDurationGenerator
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitTypewriterDurationGenerator private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = MckitTypewriterDurationGenerator()
    }


    private val typewriterDurationGenerateStrategyConfig =
            if (!MckitTypewriterDurationGenerateStrategyConfig.instance.hasBuilt)
                MckitTypewriterDurationGenerateStrategyConfig.Builder.create()
            else
                MckitTypewriterDurationGenerateStrategyConfig.instance


    fun startGenerateTypewriteDuration(message: String, onTypewriterDurationGenerateListener: MckitTypewriterDurationGenerateStrategy.OnTypewriterDurationGenerateListener) {
        typewriterDurationGenerateStrategyConfig.typewriterDurationGenerateStrategy.startGenerateTypewriterDuration(message, onTypewriterDurationGenerateListener)
    }

    fun stopGenerateTypewriteDuration() {
        typewriterDurationGenerateStrategyConfig.typewriterDurationGenerateStrategy.stopGenerateTypewriterDuration()
    }
}
