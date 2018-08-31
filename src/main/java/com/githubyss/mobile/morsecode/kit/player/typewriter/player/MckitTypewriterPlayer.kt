package com.githubyss.mobile.morsecode.kit.player.typewriter.player

import android.os.Bundle
import android.view.View
import com.githubyss.mobile.morsecode.kit.constant.MckitKeyConstants
import java.util.*

/**
 * MckitTypewriterPlayer
 * <Description>
 * <Details>
 *
 * @designPatterns Singleton, Builder, Strategy
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitTypewriterPlayer private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = MckitTypewriterPlayer()
    }


    private val typewriterPlayStrategyConfig =
            if (!MckitTypewriterPlayStrategyConfig.instance.hasBuilt)
                MckitTypewriterPlayStrategyConfig.Builder.create()
            else
                MckitTypewriterPlayStrategyConfig.instance


    fun startPlayTypewriter(typewriterDataStr: String, typewriterDurationList: List<Int>, typewriterView: View, onTypewriterPlayListener: MckitTypewriterPlayStrategy.OnTypewriterPlayListener) {
        val bundle = Bundle()
        bundle.putString(MckitKeyConstants.TypewriterKey.MESSAGE_STR, typewriterDataStr)
        bundle.putIntegerArrayList(MckitKeyConstants.TypewriterKey.DURATION_LIST, typewriterDurationList as ArrayList<Int>)

        typewriterPlayStrategyConfig.typewriterPlayStrategy.startPlayTypewriter(bundle, typewriterView, onTypewriterPlayListener)
    }

    fun stopPlayTypewriter() {
        typewriterPlayStrategyConfig.typewriterPlayStrategy.stopPlayTypewriter()
    }
}
