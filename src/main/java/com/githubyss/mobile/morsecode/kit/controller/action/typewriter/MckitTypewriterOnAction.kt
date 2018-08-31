package com.githubyss.mobile.morsecode.kit.controller.action.typewriter

import android.view.View
import com.githubyss.mobile.morsecode.kit.player.typewriter.player.MckitTypewriterPlayStrategy
import com.githubyss.mobile.morsecode.kit.player.typewriter.player.MckitTypewriterPlayer

/**
 * MckitTypewriterOnAction
 * <Description>
 * <Details>
 *
 * @designPatterns State
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitTypewriterOnAction : MckitTypewriterAction {
    override fun startPlay(typewriterDataStr: String, typewriterDurationList: List<Int>, typewriterView: View, onTypewriterPlayListener: MckitTypewriterPlayStrategy.OnTypewriterPlayListener) {
        MckitTypewriterPlayer.instance.startPlayTypewriter(typewriterDataStr, typewriterDurationList, typewriterView, onTypewriterPlayListener)
    }

    override fun stopPlay() {
        MckitTypewriterPlayer.instance.stopPlayTypewriter()
    }

    override fun releaseResource() {
    }
}
