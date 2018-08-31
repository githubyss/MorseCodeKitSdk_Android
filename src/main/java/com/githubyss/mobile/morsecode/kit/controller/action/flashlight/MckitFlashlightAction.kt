package com.githubyss.mobile.morsecode.kit.controller.action.flashlight

/**
 * MckitFlashlightAction
 * <Description>
 * <Details>
 *
 * @designPatterns State
 *
 * @author Ace Yan
 * @github githubyss
 */
interface MckitFlashlightAction {
    fun startPlay(flashlightData: Array<Any>)
    fun stopPlay()
    fun releaseResource()
}