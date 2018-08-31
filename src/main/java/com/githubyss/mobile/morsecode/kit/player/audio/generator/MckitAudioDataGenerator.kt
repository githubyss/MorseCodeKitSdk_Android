package com.githubyss.mobile.morsecode.kit.player.audio.generator

/**
 * MckitAudioDataGenerator
 * <Description>
 * <Details>
 *
 * @designPatterns Singleton, Builder, Strategy
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitAudioDataGenerator private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = MckitAudioDataGenerator()
    }


    /** Building the audioDataGeneratorStrategyConfig by default variate value in itself when it was not built by user. by Ace Yan */
    private val audioDataGeneratorStrategyConfig =
            if (!MckitAudioDataGenerateStrategyConfig.instance.hasBuilt)
                MckitAudioDataGenerateStrategyConfig.Builder.create()
            else
                MckitAudioDataGenerateStrategyConfig.instance


    fun startGenerateAudioData(durationPatternList: List<Int>, onAudioDataGenerateListener: MckitAudioDataGenerateStrategy.OnAudioDataGenerateListener) {
        audioDataGeneratorStrategyConfig.audioDataGenerateStrategy.startGenerateAudioData(durationPatternList, onAudioDataGenerateListener)
    }

    fun startGenerateAudioData(durationPatternArray: Array<Int>, onAudioDataGenerateListener: MckitAudioDataGenerateStrategy.OnAudioDataGenerateListener) {
        audioDataGeneratorStrategyConfig.audioDataGenerateStrategy.startGenerateAudioData(durationPatternArray, onAudioDataGenerateListener)
    }

    fun startGenerateAudioData(audioDurationMillis: Int, onAudioDataGenerateListener: MckitAudioDataGenerateStrategy.OnAudioDataGenerateListener) {
        audioDataGeneratorStrategyConfig.audioDataGenerateStrategy.startGenerateAudioData(audioDurationMillis, onAudioDataGenerateListener)
    }

    fun startGenerateAudioData(message: String, onAudioDataGenerateListener: MckitAudioDataGenerateStrategy.OnAudioDataGenerateListener) {
        audioDataGeneratorStrategyConfig.audioDataGenerateStrategy.startGenerateAudioData(message, onAudioDataGenerateListener)
    }

    fun stopGenerateAudioData() {
        audioDataGeneratorStrategyConfig.audioDataGenerateStrategy.stopGenerateAudioData()
    }
}
