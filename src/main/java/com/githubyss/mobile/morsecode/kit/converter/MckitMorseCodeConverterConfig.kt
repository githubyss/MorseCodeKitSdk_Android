package com.githubyss.mobile.morsecode.kit.converter

import com.githubyss.mobile.common.kit.info.ComkitSystemInfo
import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils
import com.githubyss.mobile.morsecode.kit.constant.MckitEncodeConstants

/**
 * MckitMorseCodeConverterConfig
 * <Description>
 * <Details>
 *
 * @designPatterns Singleton, Builder
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitMorseCodeConverterConfig private constructor() {
    /** Inner static singleton mode. by Ace Yan */
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = MckitMorseCodeConverterConfig()
    }


    /** Basic duration to be used to init ditdah duration, gap duration and other durations to build char duration patterns, units is (ms). by Ace Yan */
    private var baseDuration = 50

    var ditDuration = baseDuration
        private set

    var dahDuration = baseDuration * 3
        private set

    var ditDahGapDuration = baseDuration
        private set

    var charGapDuration = baseDuration * 3
        private set

    var wordGapDuration = baseDuration * 7
        private set

    var startDuration = baseDuration * 10
        private set

    /** Char-DitdahString map. by Ace Yan */
    var char2DitdahStringMap = HashMap<Char, String>()
        private set

    /** Char-DurationPatternArray map. by Ace Yan */
    var char2DurationPatternArrayMap = HashMap<Char, Array<Int>>()
        private set

    /** Char-DurationPatternList map. by Ace Yan */
    var char2DurationPatternListMap = HashMap<Char, List<Int>>()
        private set

    var char2DurationMap = HashMap<Char, Int>()
        private set

    var hasBuilt = false
        private set


    object Builder {
        private var beginTime = 0L
        private var endTime = 0L

        private var baseDuration = 50

        fun setBaseDuration(duration: Int): Builder {
            var baseDuration = duration
            if (baseDuration <= 0) {
                baseDuration = 50
            }

            this@Builder.baseDuration = baseDuration
            return this@Builder
        }

        fun create(): MckitMorseCodeConverterConfig {
            this@Builder.applyConfig(instance)
            return instance
        }

        private fun applyConfig(config: MckitMorseCodeConverterConfig) {
            config.baseDuration = this@Builder.baseDuration
            config.ditDuration = this@Builder.baseDuration
            config.dahDuration = this@Builder.baseDuration * 3
            config.ditDahGapDuration = this@Builder.baseDuration
            config.charGapDuration = this@Builder.baseDuration * 3
            config.wordGapDuration = this@Builder.baseDuration * 7
            config.startDuration = this@Builder.baseDuration * 10

            if (config.char2DitdahStringMap.isEmpty()) {
                config.char2DitdahStringMap = this@Builder.buildChar2DitdahStringMap()
            }
            config.char2DurationPatternArrayMap = this@Builder.buildChar2DurationPatternArrayMap(config)
            config.char2DurationPatternListMap = this@Builder.buildChar2DurationPatternListMap(config)
            config.char2DurationMap = this@Builder.buildChar2DurationMap(config)

            config.hasBuilt = true
        }

        /**
         * Builder.buildChar2DitdahStringMap()
         * <Description> Build a LinkedHashMap to map char and dit-dah.
         * <Details>
         *
         * @param
         * @return
         * @author Ace Yan
         * @github githubyss
         */
        private fun buildChar2DitdahStringMap(): HashMap<Char, String> {
            val char2DitdahStringMap = LinkedHashMap<Char, String>()

            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_A, MckitEncodeConstants.Ditdahs.ALPHABET_A)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_B, MckitEncodeConstants.Ditdahs.ALPHABET_B)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_C, MckitEncodeConstants.Ditdahs.ALPHABET_C)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_D, MckitEncodeConstants.Ditdahs.ALPHABET_D)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_E, MckitEncodeConstants.Ditdahs.ALPHABET_E)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_F, MckitEncodeConstants.Ditdahs.ALPHABET_F)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_G, MckitEncodeConstants.Ditdahs.ALPHABET_G)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_H, MckitEncodeConstants.Ditdahs.ALPHABET_H)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_I, MckitEncodeConstants.Ditdahs.ALPHABET_I)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_J, MckitEncodeConstants.Ditdahs.ALPHABET_J)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_K, MckitEncodeConstants.Ditdahs.ALPHABET_K)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_L, MckitEncodeConstants.Ditdahs.ALPHABET_L)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_M, MckitEncodeConstants.Ditdahs.ALPHABET_M)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_N, MckitEncodeConstants.Ditdahs.ALPHABET_N)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_O, MckitEncodeConstants.Ditdahs.ALPHABET_O)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_P, MckitEncodeConstants.Ditdahs.ALPHABET_P)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_Q, MckitEncodeConstants.Ditdahs.ALPHABET_Q)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_R, MckitEncodeConstants.Ditdahs.ALPHABET_R)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_S, MckitEncodeConstants.Ditdahs.ALPHABET_S)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_T, MckitEncodeConstants.Ditdahs.ALPHABET_T)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_U, MckitEncodeConstants.Ditdahs.ALPHABET_U)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_V, MckitEncodeConstants.Ditdahs.ALPHABET_V)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_W, MckitEncodeConstants.Ditdahs.ALPHABET_W)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_X, MckitEncodeConstants.Ditdahs.ALPHABET_X)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_Y, MckitEncodeConstants.Ditdahs.ALPHABET_Y)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.ALPHABET_Z, MckitEncodeConstants.Ditdahs.ALPHABET_Z)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.NUMBER_0, MckitEncodeConstants.Ditdahs.NUMBER_0)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.NUMBER_1, MckitEncodeConstants.Ditdahs.NUMBER_1)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.NUMBER_2, MckitEncodeConstants.Ditdahs.NUMBER_2)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.NUMBER_3, MckitEncodeConstants.Ditdahs.NUMBER_3)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.NUMBER_4, MckitEncodeConstants.Ditdahs.NUMBER_4)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.NUMBER_5, MckitEncodeConstants.Ditdahs.NUMBER_5)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.NUMBER_6, MckitEncodeConstants.Ditdahs.NUMBER_6)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.NUMBER_7, MckitEncodeConstants.Ditdahs.NUMBER_7)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.NUMBER_8, MckitEncodeConstants.Ditdahs.NUMBER_8)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.NUMBER_9, MckitEncodeConstants.Ditdahs.NUMBER_9)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_DOT, MckitEncodeConstants.Ditdahs.SIGN_DOT)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_COLON, MckitEncodeConstants.Ditdahs.SIGN_COLON)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_COMMA, MckitEncodeConstants.Ditdahs.SIGN_COMMA)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_SEMICOLON, MckitEncodeConstants.Ditdahs.SIGN_SEMICOLON)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_QUESTION, MckitEncodeConstants.Ditdahs.SIGN_QUESTION)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_EQUAL, MckitEncodeConstants.Ditdahs.SIGN_EQUAL)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_SINGLE_QUOTATION, MckitEncodeConstants.Ditdahs.SIGN_SINGLE_QUOTATION)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_SLASH, MckitEncodeConstants.Ditdahs.SIGN_SLASH)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_EXCLAMATION, MckitEncodeConstants.Ditdahs.SIGN_EXCLAMATION)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_DASH, MckitEncodeConstants.Ditdahs.SIGN_DASH)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_UNDERSCORE, MckitEncodeConstants.Ditdahs.SIGN_UNDERSCORE)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_MARK_DOUBLE_QUOTATION, MckitEncodeConstants.Ditdahs.SIGN_MARK_DOUBLE_QUOTATION)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_LEFT_PARENTHESIS, MckitEncodeConstants.Ditdahs.SIGN_LEFT_PARENTHESIS)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_RIGHT_PARENTHESIS, MckitEncodeConstants.Ditdahs.SIGN_RIGHT_PARENTHESIS)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_DOLLAR, MckitEncodeConstants.Ditdahs.SIGN_DOLLAR)
//        char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_AMPERSAND, MckitEncodeConstants.Ditdahs.SIGN_AMPERSAND)
            char2DitdahStringMap.put(MckitEncodeConstants.Chars.SIGN_AT, MckitEncodeConstants.Ditdahs.SIGN_AT)

            ComkitLogcatUtils.`object`(char2DitdahStringMap)

            return char2DitdahStringMap
        }

        /**
         * Builder.buildChar2DurationPatternArrayMap(config)
         * <Description>
         * <Details>
         *
         * @param config
         * @return
         * @author Ace Yan
         * @github githubyss
         */
        private fun buildChar2DurationPatternArrayMap(config: MckitMorseCodeConverterConfig): HashMap<Char, Array<Int>> {
            val ditDuration = config.ditDuration
            val dahDuration = config.dahDuration
            val ditDahGapDuration = config.ditDahGapDuration

            val char2DitdahStringMap = config.char2DitdahStringMap

            val char2DurationPatternArrayMap = LinkedHashMap<Char, Array<Int>>()

            beginTime = ComkitSystemInfo.currentTimeMillis()

            /** Traverse char-ditdahString map to build char-durationPatternArray map. by Ace Yan */
            for (entry in char2DitdahStringMap.entries) {
                val charKey = entry.key
                val charDitdahString = entry.value

                val charDitdahStringLength = charDitdahString.length

                /**
                 * Init durationPatternArray for each char in char-ditdahString map.
                 * This is because there is a ditdah gap duration between every dit duration or dah duration in a char ditdahString value.
                 * But the gap duration after the last dit or dah duration is the char duration,
                 * and the one after the last char of one word is the word duration,
                 * so there is no need to add a ditdah gap duration in these positions.
                 * This is the computational formula: [charDurationPatternArraySize = charDitdahStringLength * 2 - 1].
                 * by Ace Yan
                 */
                val charDurationPatternArraySize = charDitdahStringLength * 2 - 1
                val charDurationPatternArray = Array(charDurationPatternArraySize, { it -> it })

                /** Traverse ditdahString value to get durationPatternArray. by Ace Yan */
                for (idx in 0 until charDitdahStringLength) {
                    /** Insert dit or dah duration in position of [idx * 2] in durationPatternArray. by Ace Yan */
                    if (charDitdahString[idx] == MckitEncodeConstants.Codes.DIT) {
                        charDurationPatternArray[idx * 2] = ditDuration
                    } else if (charDitdahString[idx] == MckitEncodeConstants.Codes.DAH) {
                        charDurationPatternArray[idx * 2] = dahDuration
                    }

                    /** Insert ditdah gap duration in position of [idx * 2 + 1] in durationPatternArray unless idx is the last index of ditdahString. by Ace Yan */
                    if (idx != (charDitdahStringLength - 1)) {
                        charDurationPatternArray[idx * 2 + 1] = ditDahGapDuration
                    }
                }

                char2DurationPatternArrayMap.put(charKey, charDurationPatternArray)
            }

            endTime = ComkitSystemInfo.currentTimeMillis()
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> MckitMorseCodeConverterConfig.buildChar2DurationPatternArrayMap() >>> Elapsed time = ${endTime - beginTime} ms.")
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> MckitMorseCodeConverterConfig.buildChar2DurationPatternArrayMap() >>> char2DurationPatternArrayMapSize = ${char2DurationPatternArrayMap.size}")

            ComkitLogcatUtils.`object`(char2DurationPatternArrayMap)

            return char2DurationPatternArrayMap
        }

        /**
         * Builder.buildChar2DurationPatternListMap(config)
         * <Description>
         * <Details>
         *
         * @param config
         * @return HashMap<Char, List<Long>> Value of the map is readable only.
         * @author Ace Yan
         * @github githubyss
         */
        private fun buildChar2DurationPatternListMap(config: MckitMorseCodeConverterConfig): HashMap<Char, List<Int>> {
            val ditDuration = config.ditDuration
            val dahDuration = config.dahDuration
            val ditDahGapDuration = config.ditDahGapDuration

            val char2DitdahStringMap = config.char2DitdahStringMap

            val char2DurationPatternListMap = LinkedHashMap<Char, List<Int>>()

            beginTime = ComkitSystemInfo.currentTimeMillis()

            for (entry in char2DitdahStringMap.entries) {
                val charKey = entry.key
                val charDitdahString = entry.value

                val charDitdahStringLength = charDitdahString.length

                val charDurationPatternList = ArrayList<Int>()

                for (idx in 0 until charDitdahStringLength) {
                    if (charDitdahString[idx] == MckitEncodeConstants.Codes.DIT) {
                        charDurationPatternList.add(ditDuration)
                    } else if (charDitdahString[idx] == MckitEncodeConstants.Codes.DAH) {
                        charDurationPatternList.add(dahDuration)
                    }

                    if (idx != (charDitdahStringLength - 1)) {
                        charDurationPatternList.add(ditDahGapDuration)
                    }
                }

                char2DurationPatternListMap.put(charKey, charDurationPatternList)
            }

            endTime = ComkitSystemInfo.currentTimeMillis()
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> MckitMorseCodeConverterConfig.buildChar2DurationPatternListMap() >>> Elapsed time = ${endTime - beginTime} ms.")
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> MckitMorseCodeConverterConfig.buildChar2DurationPatternListMap() >>> char2DurationPatternListMapSize = ${char2DurationPatternListMap.size}")

            ComkitLogcatUtils.`object`(char2DurationPatternListMap)

            return char2DurationPatternListMap
        }

        /**
         * Builder.buildChar2DurationMap(config)
         * <Description>
         * <Details>
         *
         * @param config
         * @return
         * @author Ace Yan
         * @github githubyss
         */
        private fun buildChar2DurationMap(config: MckitMorseCodeConverterConfig): HashMap<Char, Int> {
            val char2DurationPatternListMap = config.char2DurationPatternListMap

            val char2DurationMap = LinkedHashMap<Char, Int>()

            beginTime = ComkitSystemInfo.currentTimeMillis()

            for (entry in char2DurationPatternListMap) {
                val charKey = entry.key
                val charDurationPatternList = entry.value

                val charDuration = charDurationPatternList.sum()

                char2DurationMap.put(charKey, charDuration)
            }

            endTime = ComkitSystemInfo.currentTimeMillis()
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> MckitMorseCodeConverterConfig.buildChar2DurationMap() >>> Elapsed time = ${endTime - beginTime} ms.")
            ComkitLogcatUtils.d(msg = "~~~Ace Yan~~~ >>> MckitMorseCodeConverterConfig.buildChar2DurationMap() >>> char2DurationMap = ${char2DurationMap.size}")

            ComkitLogcatUtils.`object`(char2DurationPatternListMap)

            return char2DurationMap
        }
    }
}
