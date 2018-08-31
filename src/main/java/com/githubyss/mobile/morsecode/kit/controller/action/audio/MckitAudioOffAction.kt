package com.githubyss.mobile.morsecode.kit.controller.action.audio

import com.githubyss.mobile.morsecode.kit.player.audio.player.MckitAudioPlayer

/**
 * MckitAudioOffAction
 * <Description>
 * <Details>
 *
 * @designPatterns State
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitAudioOffAction : MckitAudioAction {
    override fun startPlay(audioDataArray: Array<Float>, onAudioPlayListener: MckitAudioPlayer.OnAudioPlayListener) {
    }

    override fun stopPlay() {
    }

    override fun releaseResource() {
    }
}
