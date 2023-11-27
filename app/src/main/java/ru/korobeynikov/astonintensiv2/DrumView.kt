package ru.korobeynikov.astonintensiv2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class DrumView(context:Context,attrs:AttributeSet):View(context,attrs) {

    val p=Paint()
    var centerX=0f
    var centerY=0f
    val path=Path()
    lateinit var redFigureCoords:FloatArray
    lateinit var orangeFigureCoords:FloatArray
    lateinit var yellowFigureCoords:FloatArray
    lateinit var greenFigureCoords:FloatArray
    lateinit var cyanFigureCoords:FloatArray
    lateinit var blueFigureCoords:FloatArray
    lateinit var magentaFigureCoords:FloatArray

    init {
        p.strokeWidth=3f
        p.style=Paint.Style.FILL_AND_STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX=w/2f
        centerY=h/2f
        redFigureCoords= floatArrayOf(centerX/2,centerY,centerX*0.6f,centerY*0.9f)
        orangeFigureCoords= floatArrayOf(centerX*0.6f,centerY*0.9f,centerX,centerY*0.75f)
        yellowFigureCoords= floatArrayOf(centerX,centerY*0.75f,centerX*1.4f,centerY*0.9f)
        greenFigureCoords= floatArrayOf(centerX*1.4f,centerY*0.9f,centerX*1.5f,centerY)
        cyanFigureCoords= floatArrayOf(centerX*1.5f,centerY,centerX*1.3f,centerY*1.2f)
        blueFigureCoords= floatArrayOf(centerX*1.3f,centerY*1.2f,centerX,centerY*1.25f,centerX*0.7f,centerY*1.2f)
        magentaFigureCoords= floatArrayOf(centerX*0.7f,centerY*1.2f,centerX/2,centerY)

        //point1=PointF(centerX*0.51f,centerY*0.95f)
        //point2=PointF(centerX*0.94f,centerY*0.7f)
        //point3=PointF(centerX*1.06f,centerY*0.55f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        p.color=Color.RED
        path.moveTo(centerX,centerY)
        path.lineTo(redFigureCoords[0],redFigureCoords[1])
        path.lineTo(redFigureCoords[2],redFigureCoords[3])
        path.close()
        canvas.drawPath(path,p)
        path.reset()

        p.color=Color.rgb(255,165,0)
        path.moveTo(centerX,centerY)
        path.lineTo(orangeFigureCoords[0],orangeFigureCoords[1])
        path.lineTo(orangeFigureCoords[2],orangeFigureCoords[3])
        path.close()
        canvas.drawPath(path,p)

        path.reset()
        p.color=Color.YELLOW
        path.moveTo(centerX,centerY)
        path.lineTo(yellowFigureCoords[0],yellowFigureCoords[1])
        path.lineTo(yellowFigureCoords[2],yellowFigureCoords[3])
        path.close()
        canvas.drawPath(path,p)

        path.reset()
        p.color=Color.GREEN
        path.moveTo(centerX,centerY)
        path.lineTo(greenFigureCoords[0],greenFigureCoords[1])
        path.lineTo(greenFigureCoords[2],greenFigureCoords[3])
        path.close()
        canvas.drawPath(path,p)

        path.reset()
        p.color=Color.CYAN
        path.moveTo(centerX,centerY)
        path.lineTo(cyanFigureCoords[0],cyanFigureCoords[1])
        path.lineTo(cyanFigureCoords[2],cyanFigureCoords[3])
        path.close()
        canvas.drawPath(path,p)

        path.reset()
        p.color=Color.BLUE
        path.moveTo(centerX,centerY)
        path.lineTo(blueFigureCoords[0],blueFigureCoords[1])
        path.lineTo(blueFigureCoords[2],blueFigureCoords[3])
        path.lineTo(blueFigureCoords[4],blueFigureCoords[5])
        path.close()
        canvas.drawPath(path,p)

        path.reset()
        p.color=Color.MAGENTA
        path.moveTo(centerX,centerY)
        path.lineTo(magentaFigureCoords[0],magentaFigureCoords[1])
        path.lineTo(magentaFigureCoords[2],magentaFigureCoords[3])
        path.close()
        canvas.drawPath(path,p)
    }
}