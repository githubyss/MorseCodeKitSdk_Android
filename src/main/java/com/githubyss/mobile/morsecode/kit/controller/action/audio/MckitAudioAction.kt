package com.githubyss.mobile.morsecode.kit.controller.action.audio

import com.githubyss.mobile.morsecode.kit.player.audio.player.MckitAudioPlayer

/**
 * MckitAudioAction
 * <Description>
 * <Details>
 *
 * @designPatterns State
 *
 * @author Ace Yan
 * @github githubyss
 */
interface MckitAudioAction {
    fun startPlay(audioDataArray: Array<Float>, onAudioPlayListener: MckitAudioPlayer.OnAudioPlayListener)
    fun stopPlay()
    fun releaseResource()
}
