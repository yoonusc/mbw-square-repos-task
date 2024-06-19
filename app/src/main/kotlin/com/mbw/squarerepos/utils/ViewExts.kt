package com.mbw.squarerepos.utils

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

fun View.visible() { visibility = View.VISIBLE }
fun View.invisible() { visibility = View.INVISIBLE }
fun View.gone() { visibility = View.GONE }

fun ViewGroup.scheduleAnimation(resId: Int) {
    val animRotate = AnimationUtils.loadLayoutAnimation(context, resId)
    layoutAnimation = animRotate
    scheduleLayoutAnimation()
    animRotate.start()
    this.invalidate()
}

fun View.springAnimate() {
    this.scaleX = .9f
    this.scaleY = .9f

    val springForce = SpringForce().apply {
        dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        stiffness - SpringForce.STIFFNESS_VERY_LOW
    }

    val animationScaleX = SpringAnimation(this, DynamicAnimation.SCALE_X).apply {
        spring = springForce
    }
    val animationScaleY = SpringAnimation(this, DynamicAnimation.SCALE_Y).apply {
        spring = springForce
    }

    val finalPosition = 1f
    animationScaleX.animateToFinalPosition(finalPosition)
    animationScaleY.animateToFinalPosition(finalPosition)
}
