package com.githubyss.mobile.morsecode.kit.controller.action.typewriter

import android.view.View
import com.githubyss.mobile.morsecode.kit.player.typewriter.player.MckitTypewriterPlayStrategy

/**
 * MckitTypewriterOffAction
 * <Description>
 * <Details>
 *
 * @designPatterns State
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitTypewriterOffAction : MckitTypewriterAction {
    override fun startPlay(typewriterDataStr: String, typewriterDurationList: List<Int>, typewriterView: View, onTypewriterPlayListener: MckitTypewriterPlayStrategy.OnTypewriterPlayListener) {
    }

    override fun stopPlay() {
    }

    override fun releaseResource() {
    }
}
