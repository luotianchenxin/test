package com.example.rov

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class RockingBar constructor(context: Context,attrs:AttributeSet)
    :View(context, attrs) {
    private var x_coordinate: Float = (width/2).toFloat()
    private var y_coordinate:Float = (height/2).toFloat()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = measuredHeight
        val width = measuredWidth
        val size = width.coerceAtMost(height)
        setMeasuredDimension(size, size)
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val radius = width.toFloat().coerceAtMost(height.toFloat())/4

        val out_paint = Paint()
        out_paint.isAntiAlias = true
        out_paint.strokeWidth = 10F
        out_paint.setARGB(255, 209, 205, 204)

        val in_paint = Paint()
        in_paint.isAntiAlias = true
        in_paint.strokeWidth = 10F
        in_paint.setARGB(255, 251, 244, 45)

        canvas?.drawCircle((width/2).toFloat(), (height/2).toFloat(), radius, out_paint)

        if(x_coordinate >0 && y_coordinate>0){
            canvas?.drawCircle(x_coordinate,y_coordinate, radius/2, in_paint)
        }
        else{
            canvas?.drawCircle((width/2).toFloat(), (height/2).toFloat(), radius/2, in_paint)
        }


    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event != null
            && judgeScope(event.x, event.y)
            &&event.action != MotionEvent.ACTION_UP) {

            x_coordinate = event.x
            y_coordinate = event.y


        }else{
            x_coordinate = (width/2).toFloat()
            y_coordinate = (height/2).toFloat()
        }
        invalidate()

        return super.onTouchEvent(event)
    }

    private fun judgeScope(x:Float,y:Float):Boolean{
        val radius = width.toFloat().coerceAtMost(height.toFloat())/3
        val xMax = width/2 + radius
        val xMin = width/2 - radius
        val yMax = height/2 + radius
        val yMin = height/2 - radius
        return x in xMin..xMax && y in yMin..yMax
    }


}