package ru.korobeynikov.astonintensiv2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.graphics.PointF
import android.graphics.RectF
import android.graphics.Region
import android.util.AttributeSet
import android.view.View

class DrumView(context:Context,attrs:AttributeSet):View(context,attrs) {

    val p=Paint()
    var centerX=0f
    var centerY=0f

    lateinit var point1: PointF

    //val path=Path()
    val path1=Path()
    val path2=Path()
    val path3=Path()
    val path4=Path()
    val path5=Path()
    val path6=Path()
    val path7=Path()
    val pathPoint=Path()

    val region=Region()

    lateinit var canvas: Canvas

    init {
        p.strokeWidth=3f
        p.style=Paint.Style.FILL_AND_STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX=w/2f
        centerY=h/2f

        //point1=PointF(centerX*0.51f,centerY*0.95f)
        //point2=PointF(centerX*0.94f,centerY*0.7f)
        //point3=PointF(centerX*1.06f,centerY*0.55f)
    }

    fun selectColor():Int{

        val rectF=RectF()
        val pathArray= arrayOf(path1,path2,path3,path4,path5,path6,path7)
        for(i in pathArray.indices){
            val path=pathArray[i]
            path.computeBounds(rectF,true)
            region.setPath(path,Region(rectF.left.toInt(),rectF.top.toInt(),rectF.right.toInt(),rectF.bottom.toInt()))
            //val isCorrectColor=region.contains(centerX.toInt(),(centerY*0.9).toInt())
            val isCorrectColor=region.contains((centerX*0.9).toInt(),(centerY*0.9).toInt())
            if(isCorrectColor) return i
        }
        return -1
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        this.canvas=canvas

        p.color=Color.RED
        path1.moveTo(centerX,centerY)
        path1.lineTo(centerX/2,centerY)
        path1.lineTo(centerX*0.6f,centerY*0.9f)
        path1.close()
        canvas.drawPath(path1,p)

        p.color=Color.rgb(255,165,0)
        path2.moveTo(centerX,centerY)
        path2.lineTo(centerX*0.6f,centerY*0.9f)
        path2.lineTo(centerX,centerY*0.75f)
        path2.close()
        canvas.drawPath(path2,p)

        p.color=Color.YELLOW
        path3.moveTo(centerX,centerY)
        path3.lineTo(centerX,centerY*0.75f)
        path3.lineTo(centerX*1.4f,centerY*0.9f)
        path3.close()
        canvas.drawPath(path3,p)

        p.color=Color.GREEN
        path4.moveTo(centerX,centerY)
        path4.lineTo(centerX*1.4f,centerY*0.9f)
        path4.lineTo(centerX*1.5f,centerY)
        path4.close()
        canvas.drawPath(path4,p)

        p.color=Color.CYAN
        path5.moveTo(centerX,centerY)
        path5.lineTo(centerX*1.5f,centerY)
        path5.lineTo(centerX*1.3f,centerY*1.2f)
        path5.close()
        canvas.drawPath(path5,p)

        p.color=Color.BLUE
        path6.moveTo(centerX,centerY)
        path6.lineTo(centerX*1.3f,centerY*1.2f)
        path6.lineTo(centerX,centerY*1.25f)
        path6.lineTo(centerX*0.7f,centerY*1.2f)
        path6.close()
        canvas.drawPath(path6,p)

        p.color=Color.MAGENTA
        path7.moveTo(centerX,centerY)
        path7.lineTo(centerX*0.7f,centerY*1.2f)
        path7.lineTo(centerX/2,centerY)
        path7.close()
        canvas.drawPath(path7,p)

        //not delete
//        p.color=Color.RED
//        path.moveTo(centerX,centerY)
//        path.lineTo(centerX/2,centerY)
//        //path.quadTo(point1.x,point1.y,centerX*0.6f,centerY*0.9f)
//        path.lineTo(centerX*0.6f,centerY*0.9f)
//        path.close()
//        canvas.drawPath(path,p)
//        path.reset()
//
//        p.color=Color.rgb(255,165,0)
//        path.moveTo(centerX,centerY)
//        path.lineTo(centerX*0.6f,centerY*0.9f)
//        //path.quadTo(point2.x,point2.y,centerX,centerY*0.75f)
//        path.lineTo(centerX,centerY*0.75f)
//        path.close()
//        canvas.drawPath(path,p)
//
//        path.reset()
//        p.color=Color.YELLOW
//        path.moveTo(centerX,centerY)
//        path.lineTo(centerX,centerY*0.75f)
//        //path.quadTo(point3.x,point3.y,centerX*1.4f,centerY*0.9f)
//        path.lineTo(centerX*1.4f,centerY*0.9f)
//        path.close()
//        canvas.drawPath(path,p)
//
//        path.reset()
//        p.color=Color.GREEN
//        path.moveTo(centerX,centerY)
//        path.lineTo(centerX*1.4f,centerY*0.9f)
//        path.lineTo(centerX*1.5f,centerY)
//        path.close()
//        canvas.drawPath(path,p)
//
//        path.reset()
//        p.color=Color.CYAN
//        path.moveTo(centerX,centerY)
//        path.lineTo(centerX*1.5f,centerY)
//        path.lineTo(centerX*1.3f,centerY*1.2f)
//        path.close()
//        canvas.drawPath(path,p)
//
//        path.reset()
//        p.color=Color.BLUE
//        path.moveTo(centerX,centerY)
//        path.lineTo(centerX*1.3f,centerY*1.2f)
//        path.lineTo(centerX,centerY*1.25f)
//        path.lineTo(centerX*0.7f,centerY*1.2f)
//        path.close()
//        canvas.drawPath(path,p)
//
//        path.reset()
//        p.color=Color.MAGENTA
//        path.moveTo(centerX,centerY)
//        path.lineTo(centerX*0.7f,centerY*1.2f)
//        path.lineTo(centerX/2,centerY)
//        path.close()
//        canvas.drawPath(path,p)
    }
}