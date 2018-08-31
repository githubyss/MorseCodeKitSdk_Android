package com.githubyss.mobile.morsecode.kit.controller.action.typewriter

import android.view.View
import com.githubyss.mobile.morsecode.kit.player.typewriter.player.MckitTypewriterPlayStrategy

/**
 * MckitTypewriterAction
 * <Description>
 * <Details>
 *
 * @designPatterns State
 *
 * @author Ace Yan
 * @github githubyss
 */
interface MckitTypewriterAction {
    fun startPlay(typewriterDataStr: String, typewriterDurationList: List<Int>, typewriterView: View, onTypewriterPlayListener: MckitTypewriterPlayStrategy.OnTypewriterPlayListener)
    fun stopPlay()
    fun releaseResource()
}
