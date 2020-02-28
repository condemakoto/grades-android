package com.trilogy.bootcampspot.view.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.trilogy.bootcampspot.R

class CircularChartView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint()
    private val bgPaint = Paint()
    var perc: Float = 0f

    init {
        val circularChartStroke = context.resources.getDimension(R.dimen.circular_chart_stroke)

        paint.color = ContextCompat.getColor(context, R.color.colorPrimaryLight)
        paint.isAntiAlias = true
        paint.strokeWidth = circularChartStroke
        paint.style = Paint.Style.STROKE

        bgPaint.color = ContextCompat.getColor(context, R.color.silver)
        bgPaint.isAntiAlias = true
        bgPaint.strokeWidth = circularChartStroke
        bgPaint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        canvas?.let {
            val rec = RectF(
                paint.strokeWidth,
                paint.strokeWidth,
                canvas.width.toFloat() - paint.strokeWidth,
                canvas.height.toFloat() - paint.strokeWidth
            )
            canvas.drawArc(rec, 0f, 360f, false, bgPaint)
            val angle: Float = perc * 3.6.toFloat()
            canvas.drawArc(rec, 45f, angle, false, paint)
        }


    }

}