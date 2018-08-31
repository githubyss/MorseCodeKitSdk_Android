package com.githubyss.mobile.morsecode.kit.player.typewriter.generator

/**
 * MckitTypewriterDurationGenerateStrategyConfig
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitTypewriterDurationGenerateStrategyConfig private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = MckitTypewriterDurationGenerateStrategyConfig()
    }


    var typewriterDurationGenerateStrategy = MckitTypewriterDurationGeneratePresentStrategy() as MckitTypewriterDurationGenerateStrategy
        private set

    var hasBuilt = false
        private set


    object Builder {
        private var typewriterDurationGenerateStrategy = MckitTypewriterDurationGeneratePresentStrategy() as MckitTypewriterDurationGenerateStrategy

        fun setStrategy(strategy: MckitTypewriterDurationGenerateStrategy): Builder {
            typewriterDurationGenerateStrategy = strategy
            return this@Builder
        }

        fun create(): MckitTypewriterDurationGenerateStrategyConfig {
            applyConfig(instance)
            return instance
        }

        private fun applyConfig(config: MckitTypewriterDurationGenerateStrategyConfig) {
            config.typewriterDurationGenerateStrategy = typewriterDurationGenerateStrategy

            config.hasBuilt = true
        }
    }
}
