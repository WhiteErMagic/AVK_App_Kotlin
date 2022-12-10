package com.developmentavk.avk_app_kotlin.presentation.goods

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import com.developmentavk.avk_app_kotlin.R
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.CulcAction
import org.apache.commons.lang3.math.NumberUtils.max
import kotlin.math.min
import kotlin.properties.Delegates

class TableNumView(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int):
    View(context, attributeSet, defStyleAttr, defStyleRes) {

    private var culcField: TableNumField? = null
        set(value){
            field = value
            updateViewSizes()
        }
    private val fieldRect = RectF(0f, 0f, 0f, 0f)
    private val cellRect = RectF()
    private var cellSize: Float = 0f
    private var cellPadding: Float = 0f
    private var gridColor: Int by Delegates.notNull<Int>()
    private lateinit var paint:Paint

    constructor(context: Context, attributeSet: AttributeSet?):
            this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int):
            this(context, attributeSet, defStyleAttr, 0)
    constructor(context: Context): this(context, null)

    init {
        culcField = TableNumField()
        if(attributeSet != null){
            initAttr(attributeSet, defStyleAttr, defStyleRes)
        }else{
            initDefaultColors()
        }
        initPaint()
    }

    private fun initDefaultColors() {
        gridColor = Color.GRAY
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateViewSizes()
    }

    private fun updateViewSizes() {
        val field = this.culcField ?: return

        val safeWidth: Int = width - paddingLeft - paddingRight
        val safeHeight: Int = height - paddingTop - paddingBottom

        val cellWidth: Float = safeWidth / field.cols.toFloat()
        val cellHeight: Float = safeHeight / field.rows.toFloat()

        cellSize = min(cellWidth, cellHeight)
        cellPadding = cellSize * 0.2f

        val fieldWidth = cellSize * field.cols
        val fieldHeight = cellSize * field.rows

        fieldRect.left = paddingLeft + (safeWidth - fieldWidth) / 2
        fieldRect.top = paddingTop + (safeHeight - fieldHeight) / 2
        fieldRect.right = fieldRect.left + fieldWidth
        fieldRect.bottom = fieldRect.top + fieldHeight
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth: Int = suggestedMinimumWidth + paddingLeft + paddingRight
        val minHeight: Int = suggestedMinimumHeight + paddingTop + paddingBottom
        val desiredCellSizeInPixels: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DESIRED_CELL_SIZE,
                resources.displayMetrics).toInt()
        val rows: Int = culcField?.rows ?: 0
        val cols: Int = culcField?.cols ?: 0

        val desiredWidth: Int = max(minWidth, cols * desiredCellSizeInPixels + paddingLeft + paddingRight)
        val desiredHeight: Int = max(minHeight, rows * desiredCellSizeInPixels + paddingTop + paddingBottom)

        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(culcField == null) return
        if(cellSize == 0f) return
        if(fieldRect.width() <= 0f) return
        if(fieldRect.height() <= 0f) return

        drawGrid(canvas!!)
        drawCells(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val field = this.culcField ?: return false
        when(event.action){
            MotionEvent.ACTION_DOWN -> return true
            MotionEvent.ACTION_UP -> {
                val row: Int = getRow(event)
                val col: Int = getCol(event)
                if(row >= 0 && col >= 0 && row < field.rows && col < field.cols) {
                    if(row == 3 && col == 2) {
                        (context as CulcAction).removeNumber()
                    }else {
                        val numberClick = field.getCellNumber(row, col)
                        if(numberClick > -3) {
                            (context as CulcAction).addNumber(numberClick.toString())
                            field.getCellNumber(row, col)
                        }
                    }
                    return true
                }
                return false
            }
        }
        return false
    }

    private fun getCol(event: MotionEvent): Int {
        return ((event.x - fieldRect.left) / cellSize).toInt()
    }

    private fun getRow(event: MotionEvent): Int {
        return ((event.y - fieldRect.top) / cellSize).toInt()
    }

    private fun initPaint(){
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = gridColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics)
    }

    private fun drawGrid(canvas: Canvas){
        val field = this.culcField ?: return

        val xStart = fieldRect.left
        val xEnd = fieldRect.right
        for(i in 0..field.rows){
            val y = fieldRect.top + cellSize * i
            canvas.drawLine(xStart, y, xEnd, y, paint)
        }

        val yStart = fieldRect.top
        val yEnd = fieldRect.bottom
        for(i in 0..field.cols){
            val x = fieldRect.left + cellSize * i
            canvas.drawLine(x, yStart, x, yEnd, paint)
        }
    }

    private fun drawCells(canvas: Canvas) {
        val field = this.culcField ?: return
        for(row in 0 until field.rows)
            for(col in 0 until field.cols){
                val cellNumber = field.getCellNumber(row, col)
                drawNumberDrawable(canvas, cellNumber, row, col)
            }
    }

    private fun drawNumberDrawable(canvas: Canvas, cellNumber: Int, row: Int, col: Int) {
        when(cellNumber){
            1 -> {drawNumber(canvas, R.drawable.number_1, row, col)}
            2 -> {drawNumber(canvas, R.drawable.number_2, row, col)}
            3 -> {drawNumber(canvas, R.drawable.number_3, row, col)}
            4 -> {drawNumber(canvas, R.drawable.number_4, row, col)}
            5 -> {drawNumber(canvas, R.drawable.number_5, row, col)}
            6 -> {drawNumber(canvas, R.drawable.number_6, row, col)}
            7 -> {drawNumber(canvas, R.drawable.number_7, row, col)}
            8 -> {drawNumber(canvas, R.drawable.number_8, row, col)}
            9 -> {drawNumber(canvas, R.drawable.number_9, row, col)}
            0 -> {drawNumber(canvas, R.drawable.number_0, row, col)}
            -2 -> {drawNumber(canvas, R.drawable.delete_left, row, col)}
            else -> {}
        }
    }

    private fun drawNumber(canvas: Canvas, numberDrawable: Int, row: Int, col: Int) {
        val d = resources.getDrawable(numberDrawable, null)
        cellRect.left = fieldRect.left + col * cellSize + cellPadding
        cellRect.top = fieldRect.top + row * cellSize + cellPadding
        cellRect.right = cellRect.left + cellSize - cellPadding * 2
        cellRect.bottom = cellRect.top + cellSize - cellPadding * 2

        d.setBounds(cellRect.left.toInt(), cellRect.top.toInt(),
            cellRect.right.toInt(), cellRect.bottom.toInt())
        d.draw(canvas)
    }

    private fun initAttr(attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int){
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.TableNumView, defStyleAttr, defStyleRes)
        gridColor = typedArray.getColor(R.styleable.TableNumView_tableNumGridColor, GRID_DEFAULT_COLOR)
        typedArray.recycle()
    }

    companion object{
        const val DESIRED_CELL_SIZE = 25f
        const val GRID_DEFAULT_COLOR = Color.GRAY
    }
}