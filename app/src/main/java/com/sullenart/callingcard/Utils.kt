package com.sullenart.callingcard

import android.graphics.*

fun Bitmap.getRounded(): Bitmap {
    val output = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)

    val paint = Paint().apply {
        isAntiAlias = true
        //color = Color.RED
    }

    val rect = Rect(0, 0, this.width, this.height)

    val canvas = Canvas(output).apply {
        drawARGB(0, 0, 0, 0)
        drawOval(RectF(rect), paint)
    }

    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, rect, rect, paint)

    return output
}

fun <T> MutableCollection<T>.setAll(incoming: List<T>) {
    clear()
    addAll(incoming)
}
