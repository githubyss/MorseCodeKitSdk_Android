package com.githubyss.mobile.morsecode.kit.player.typewriter.player

/**
 * MckitTypewriterPlayerConfig
 * <Description>
 * <Details>
 *
 * @designPatterns Singleton, Builder
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitTypewriterPlayerConfig private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = MckitTypewriterPlayerConfig()
    }


//    enum class TypewriteTimingEnum(val timing: String) {
//        AHEAD("ahead"), PRESENT("present"), POSTPONE("postpone")
//    }


    var startIdx = 0
        private set

//    var typewriteTiming = MckitTypewriterPlayerConfig.TypewriteTimingEnum.PRESENT
//        private set

    var canAutoScrollBottom = true
        private set

    var hasBuilt = false
        private set


    object Builder {
        private var startIdx = 0
        //        private var typewriteTiming = MckitTypewriterPlayerConfig.TypewriteTimingEnum.PRESENT
        private var canAutoScrollBottom = true

        fun setStartIdx(startIdx: Int): Builder {
            var idx = startIdx
            if (idx <= 0) {
                idx = 0
            }

            Builder.startIdx = idx
            return this@Builder
        }

//        fun setTypewriteTiming(typewriteTiming: TypewriteTimingEnum): Builder {
//            this@Builder.typewriteTiming = typewriteTiming
//            return this@Builder
//        }

        fun setCanAutoScrollBottom(canAutoScrollBottom: Boolean): Builder {
            Builder.canAutoScrollBottom = canAutoScrollBottom
            return this@Builder
        }

        fun create(): MckitTypewriterPlayerConfig {
            applyConfig(instance)
            return instance
        }

        private fun applyConfig(config: MckitTypewriterPlayerConfig) {
            config.startIdx = startIdx
//            config.typewriteTiming = this@Builder.typewriteTiming
            config.canAutoScrollBottom = canAutoScrollBottom

            config.hasBuilt = true
        }
    }
}
