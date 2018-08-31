package com.githubyss.mobile.morsecode.kit.controller.action.audio

import com.githubyss.mobile.morsecode.kit.player.audio.player.MckitAudioPlayer

/**
 * MckitAudioOnAction
 * <Description>
 * <Details>
 *
 * @designPatterns State
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitAudioOnAction : MckitAudioAction {
    override fun startPlay(audioDataArray: Array<Float>, onAudioPlayListener: MckitAudioPlayer.OnAudioPlayListener) {
        MckitAudioPlayer.instance.startPlayAudio(audioDataArray, onAudioPlayListener)
    }

    override fun stopPlay() {
        MckitAudioPlayer.instance.stopPlayAudio()
        MckitAudioPlayer.instance.stopAllAudioTrack()
    }

    override fun releaseResource() {
        MckitAudioPlayer.instance.releaseAudioTrack()
    }
}
