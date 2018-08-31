package com.githubyss.mobile.morsecode.kit.controller

/**
 * MckitPlayerPowerController
 * <Description>
 * <Details>
 *
 * @designPatterns State
 *
 * @author Ace Yan
 * @github githubyss
 */
interface MckitPlayerPowerController {
    fun audioOn()
    fun audioOff()

    fun flashlightOn()
    fun flashlightOff()

    fun vibratorOn()
    fun vibratorOff()

    fun typewriterOn()
    fun typewriterOff()
}
