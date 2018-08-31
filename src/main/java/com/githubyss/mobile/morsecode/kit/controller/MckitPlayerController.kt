package com.githubyss.mobile.morsecode.kit.controller

import android.view.View
import com.githubyss.mobile.morsecode.kit.constant.MckitStatusConstants
import com.githubyss.mobile.morsecode.kit.controller.action.audio.MckitAudioAction
import com.githubyss.mobile.morsecode.kit.controller.action.audio.MckitAudioOffAction
import com.githubyss.mobile.morsecode.kit.controller.action.audio.MckitAudioOnAction
import com.githubyss.mobile.morsecode.kit.controller.action.flashlight.MckitFlashlightAction
import com.githubyss.mobile.morsecode.kit.controller.action.flashlight.MckitFlashlightOffAction
import com.githubyss.mobile.morsecode.kit.controller.action.flashlight.MckitFlashlightOnAction
import com.githubyss.mobile.morsecode.kit.controller.action.typewriter.MckitTypewriterAction
import com.githubyss.mobile.morsecode.kit.controller.action.typewriter.MckitTypewriterOffAction
import com.githubyss.mobile.morsecode.kit.controller.action.typewriter.MckitTypewriterOnAction
import com.githubyss.mobile.morsecode.kit.controller.action.vibrator.MckitVibratorAction
import com.githubyss.mobile.morsecode.kit.controller.action.vibrator.MckitVibratorOffAction
import com.githubyss.mobile.morsecode.kit.controller.action.vibrator.MckitVibratorOnAction
import com.githubyss.mobile.morsecode.kit.global.MckitPlayModeGlobalInfo
import com.githubyss.mobile.morsecode.kit.player.audio.player.MckitAudioPlayer
import com.githubyss.mobile.morsecode.kit.player.typewriter.player.MckitTypewriterPlayStrategy

/**
 * MckitPlayerController
 * <Description>
 * <Details>
 *
 * @designPatterns Singleton, State
 *
 * @author Ace Yan
 * @github githubyss
 */
class MckitPlayerController private constructor() : MckitPlayerPowerController {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = MckitPlayerController()
    }


    private var audioAction = MckitAudioOffAction() as MckitAudioAction
    private var flashlightAction = MckitFlashlightOffAction() as MckitFlashlightAction
    private var vibratorAction = MckitVibratorOffAction() as MckitVibratorAction
    private var typewriterAction = MckitTypewriterOffAction() as MckitTypewriterAction


    fun init() {
        audioAction = if (MckitPlayModeGlobalInfo.audioStatus == MckitStatusConstants.PlayModeStatus.AUDIO_ON) MckitAudioOnAction() else MckitAudioOffAction()
        flashlightAction = if (MckitPlayModeGlobalInfo.flashlightStatus == MckitStatusConstants.PlayModeStatus.FLASHLIGHT_ON) MckitFlashlightOnAction() else MckitFlashlightOffAction()
        vibratorAction = if (MckitPlayModeGlobalInfo.vibratorStatus == MckitStatusConstants.PlayModeStatus.VIBRATION_ON) MckitVibratorOnAction() else MckitVibratorOffAction()
        typewriterAction = if (MckitPlayModeGlobalInfo.typewriterStatus == MckitStatusConstants.PlayModeStatus.TYPEWRITER_ON) MckitTypewriterOnAction() else MckitTypewriterOffAction()
    }

    fun startPlay(
            audioDataArray: Array<Float>, onAudioPlayListener: MckitAudioPlayer.OnAudioPlayListener,
            flashlightDataArray: Array<Any>,
            vibratorDataArray: Array<Any>,
            typewriterDataStr: String, typewriterDurationList: List<Int>, typewriterView: View, onTypewriterPlayListener: MckitTypewriterPlayStrategy.OnTypewriterPlayListener) {
        audioAction.startPlay(audioDataArray, onAudioPlayListener)
        flashlightAction.startPlay(flashlightDataArray)
        vibratorAction.startPlay(vibratorDataArray)
        typewriterAction.startPlay(typewriterDataStr, typewriterDurationList, typewriterView, onTypewriterPlayListener)
    }

    fun stopPlay() {
        audioAction.stopPlay()
        flashlightAction.stopPlay()
        vibratorAction.stopPlay()
        typewriterAction.stopPlay()
    }

    fun releaseResource() {
        audioAction.releaseResource()
        flashlightAction.releaseResource()
        vibratorAction.releaseResource()
        typewriterAction.releaseResource()
    }


    override fun audioOn() {
        audioAction = MckitAudioOnAction()
    }

    override fun audioOff() {
        audioAction = MckitAudioOffAction()
    }

    override fun flashlightOn() {
        flashlightAction = MckitFlashlightOnAction()
    }

    override fun flashlightOff() {
        flashlightAction = MckitFlashlightOffAction()
    }

    override fun vibratorOn() {
        vibratorAction = MckitVibratorOnAction()
    }

    override fun vibratorOff() {
        vibratorAction = MckitVibratorOffAction()
    }

    override fun typewriterOn() {
        typewriterAction = MckitTypewriterOnAction()
    }

    override fun typewriterOff() {
        typewriterAction = MckitTypewriterOffAction()
    }
}
