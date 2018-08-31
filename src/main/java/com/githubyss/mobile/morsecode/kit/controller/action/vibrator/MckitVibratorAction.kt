package com.githubyss.mobile.morsecode.kit.controller.action.vibrator

/**
 * MckitVibratorAction
 * <Description>
 * <Details>
 *
 * @designPatterns State
 *
 * @author Ace Yan
 * @github githubyss
 */
interface MckitVibratorAction {
    fun startPlay(vibratorData: Array<Any>)
    fun stopPlay()
    fun releaseResource()
}