package com.test.plugin_example

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

/** Provides access to the device's display metrics for dimension calculations. */
val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics

/** Converts an integer value to dp (density-independent pixels). */
val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        displayMetrics
    ).toInt()

/** Converts an integer value to sp (scale-independent pixels). */
val Int.sp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        displayMetrics
    )

// -----------------------------
// ViewGroup LayoutParam Helpers
// -----------------------------

/** Returns LayoutParams with WRAP_CONTENT for both width and height. */
fun ViewGroup.wrapContentSize(): ViewGroup.LayoutParams =
    ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

/** Returns LayoutParams with MATCH_PARENT for both width and height. */
fun ViewGroup.fillMaxSize(): ViewGroup.LayoutParams =
    ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )

/** Returns LayoutParams with MATCH_PARENT height and retains current width. */
fun ViewGroup.fillMaxHeight(): ViewGroup.LayoutParams {
    val width = this.layoutParams?.width ?: ViewGroup.LayoutParams.MATCH_PARENT
    return ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT)
}

/** Returns LayoutParams with MATCH_PARENT width and retains current height. */
fun ViewGroup.fillMaxWidth(): ViewGroup.LayoutParams {
    val height = this.layoutParams?.height ?: ViewGroup.LayoutParams.MATCH_PARENT
    return ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
}

/**
 * Returns LayoutParams with specific height and retains current width.
 * @param h Desired height in pixels.
 */
fun ViewGroup.height(h: Int): ViewGroup.LayoutParams {
    val width = this.layoutParams?.width ?: ViewGroup.LayoutParams.MATCH_PARENT
    return ViewGroup.LayoutParams(width, h)
}

/**
 * Returns LayoutParams with specific width and retains current height.
 * @param w Desired width in pixels.
 */
fun ViewGroup.width(w: Int): ViewGroup.LayoutParams {
    val height = this.layoutParams?.height ?: ViewGroup.LayoutParams.MATCH_PARENT
    return ViewGroup.LayoutParams(w, height)
}

/**
 * Returns LayoutParams with given width and height.
 * @param w Desired width in pixels.
 * @param h Desired height in pixels.
 */
fun ViewGroup.size(w: Int, h: Int): ViewGroup.LayoutParams =
    ViewGroup.LayoutParams(w, h)

/**
 * Returns LayoutParams with given size for both width and height (square).
 * @param size Desired size in pixels for both width and height.
 */
fun ViewGroup.size(size: Int): ViewGroup.LayoutParams =
    ViewGroup.LayoutParams(size, size)

// -----------------------
// View LayoutParam Helpers
// -----------------------

/** Returns LayoutParams with WRAP_CONTENT for both width and height. */
fun View.wrapContentSize(): ViewGroup.LayoutParams =
    ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

/** Returns LayoutParams with MATCH_PARENT for both width and height. */
fun View.fillMaxSize(): ViewGroup.LayoutParams =
    ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )

/** Returns LayoutParams with MATCH_PARENT height and retains current width. */
fun View.fillMaxHeight(): ViewGroup.LayoutParams {
    val width = this.layoutParams?.width ?: ViewGroup.LayoutParams.MATCH_PARENT
    return ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT)
}

/** Returns LayoutParams with MATCH_PARENT width and retains current height. */
fun View.fillMaxWidth(): ViewGroup.LayoutParams {
    val height = this.layoutParams?.height ?: ViewGroup.LayoutParams.MATCH_PARENT
    return ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
}

/**
 * Returns LayoutParams with specific height and retains current width.
 * @param h Desired height in pixels.
 */
fun View.height(h: Int): ViewGroup.LayoutParams {
    val width = this.layoutParams?.width ?: ViewGroup.LayoutParams.MATCH_PARENT
    return ViewGroup.LayoutParams(width, h)
}

/**
 * Returns LayoutParams with specific width and retains current height.
 * @param w Desired width in pixels.
 */
fun View.width(w: Int): ViewGroup.LayoutParams {
    val height = this.layoutParams?.height ?: ViewGroup.LayoutParams.MATCH_PARENT
    return ViewGroup.LayoutParams(w, height)
}

/**
 * Returns LayoutParams with given width and height.
 * @param w Desired width in pixels.
 * @param h Desired height in pixels.
 */
fun View.size(w: Int, h: Int): ViewGroup.LayoutParams =
    ViewGroup.LayoutParams(w, h)

/**
 * Returns LayoutParams with given size for both width and height (square).
 * @param size Desired size in pixels for both width and height.
 */
fun View.size(size: Int): ViewGroup.LayoutParams =
    ViewGroup.LayoutParams(size, size)
