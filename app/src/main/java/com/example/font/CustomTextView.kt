package com.example.font

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat

class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint: Paint = Paint()
    private val backgroundPaint: Paint = Paint()
    private val customFont: Typeface
    private val text: String = "Rag"

    init {
        // 加载自定义字体
        customFont = ResourcesCompat.getFont(context, R.font.gasoekone_regular)!!

        // 初始化绘制文本的 Paint 对象
        textPaint.typeface = customFont
        textPaint.textSize = 10f
        textPaint.color = Color.BLACK

        // 初始化绘制背景的 Paint 对象
        backgroundPaint.color = Color.LTGRAY

        // 初始化绘制线条的 Paint 对象
        linePaint.color = Color.BLACK
        linePaint.strokeWidth = 1f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.BLUE)

        // 计算文本尺寸
        val textBounds = Rect()
        textPaint.getTextBounds(text, 0, text.length, textBounds)

        // 计算文本绘制位置
        val scaleFactor = 40f
        val textWidth = textPaint.measureText(text) * scaleFactor
        val fontMetrics = textPaint.fontMetrics
        val textHeight = (fontMetrics.descent - fontMetrics.ascent) * scaleFactor
        val x = (width - textWidth) / 2
        val y = (height + textHeight / 2) / 2

        Log.d("CustomTextView onDraw", width.toString() + " " + height.toString())


        // 设置浅灰色背景，填充放大后的文本区域
        val textBackgroundRect = RectF(x, y - textHeight, x + textWidth, y)
        canvas.drawRect(textBackgroundRect, backgroundPaint)

        // 绘制 textBackgroundRect 内每 10 像素间距的一条横线
        var curY = y- textHeight
        do {
            canvas.drawLine(x, curY, x + textWidth, curY, linePaint)
            curY += scaleFactor

            Log.d("CustomTextView onDraw", curY.toString())
        } while (curY < y)

        // 绘制文本
        canvas.save()
        canvas.translate(x, y - textHeight)
        canvas.scale(scaleFactor, scaleFactor)
        canvas.drawText(text, 0f, -textPaint.ascent(), textPaint)
        canvas.restore()

        // 绘制基线
        linePaint.color = Color.RED
        val baselineY = y - textHeight - textPaint.fontMetrics.ascent * scaleFactor
        canvas.drawLine(x, baselineY, x + textWidth, baselineY, linePaint)
    }
}