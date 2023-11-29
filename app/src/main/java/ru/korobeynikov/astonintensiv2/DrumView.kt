package ru.korobeynikov.astonintensiv2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class DrumView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val angle1 = 360 / 7f
    private val angle2 = 360 / 7f * 2
    private val angle3 = 360 / 7f * 3
    private val angle4 = 360 / 7f * 4
    private val angle5 = 360 / 7f * 5
    private val angle6 = 360 / 7f * 6
    private val p = Paint()
    private var centerX = 0f
    private var centerY = 0f
    private var sizeDrum = 0f
    private lateinit var rectF: RectF

    init {
        p.strokeWidth = 3f
        p.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        sizeDrum = arrayOf(centerX * 0.5f, centerY * 0.5f, centerX * 1.5f, centerY * 1.5f).min()
        rectF = RectF(centerX - sizeDrum, centerY - sizeDrum, centerX + sizeDrum,
            centerY + sizeDrum)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        p.color = Color.RED
        canvas.drawArc(rectF, 0f, angle1, true, p)
        p.color = Color.rgb(255, 165, 0)
        canvas.drawArc(rectF, angle1, angle1, true, p)
        p.color = Color.YELLOW
        canvas.drawArc(rectF, angle2, angle1, true, p)
        p.color = Color.GREEN
        canvas.drawArc(rectF, angle3, angle1, true, p)
        p.color = Color.CYAN
        canvas.drawArc(rectF, angle4, angle1, true, p)
        p.color = Color.BLUE
        canvas.drawArc(rectF, angle5, angle1, true, p)
        p.color = Color.MAGENTA
        canvas.drawArc(rectF, angle6, angle1, true, p)
    }
}