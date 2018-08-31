package com.githubyss.mobile.morsecode.kit.player.typewriter.player

/**
 * MckitTypewriterPlayStrategyConfig
 * <Description>
 * <Details>
 *
 * @designPatterns Singleton, Builder
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitTypewriterPlayStrategyConfig private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = MckitTypewriterPlayStrategyConfig()
    }


    var typewriterPlayStrategy = MckitTypewriterPlayTextViewStrategy() as MckitTypewriterPlayStrategy
        private set

    var hasBuilt = false
        private set


    object Builder {
        private var typewriterPlayStrategy = MckitTypewriterPlayTextViewStrategy() as MckitTypewriterPlayStrategy

        fun setStrategy(strategy: MckitTypewriterPlayStrategy): Builder {
            typewriterPlayStrategy = strategy
            return this@Builder
        }

        fun create(): MckitTypewriterPlayStrategyConfig {
            applyConfig(instance)
            return instance
        }

        private fun applyConfig(config: MckitTypewriterPlayStrategyConfig) {
            config.typewriterPlayStrategy = typewriterPlayStrategy

            config.hasBuilt = true
        }
    }
}
