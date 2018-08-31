package com.githubyss.mobile.morsecode.kit.player.audio.generator

/**
 * MckitAudioDataGenerateStrategyConfig
 * <Description>
 * <Details>
 *
 * @designPatterns Singleton, Builder
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitAudioDataGenerateStrategyConfig private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = MckitAudioDataGenerateStrategyConfig()
    }


    var audioDataGenerateStrategy = MckitAudioDataGenerateSineWaveStrategy() as MckitAudioDataGenerateStrategy
        private set

    var hasBuilt = false
        private set


    object Builder {
        private var audioDataGenerateStrategy = MckitAudioDataGenerateSineWaveStrategy() as MckitAudioDataGenerateStrategy

        fun setStrategy(strategy: MckitAudioDataGenerateStrategy): Builder {
            audioDataGenerateStrategy = strategy
            return this@Builder
        }

        fun create(): MckitAudioDataGenerateStrategyConfig {
            applyConfig(instance)
            return instance
        }

        private fun applyConfig(config: MckitAudioDataGenerateStrategyConfig) {
            config.audioDataGenerateStrategy = audioDataGenerateStrategy

            config.hasBuilt = true
        }
    }
}
