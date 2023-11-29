package ru.korobeynikov.astonintensiv2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomText(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.BLACK
        paint.textSize = 50f
        paint.textAlign = Paint.Align.LEFT
        canvas.drawText("Текст", 100f, 100f, paint)
    }
}