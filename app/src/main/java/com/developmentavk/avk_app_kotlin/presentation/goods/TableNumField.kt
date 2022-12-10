package com.developmentavk.avk_app_kotlin.presentation.goods

class TableNumField(val cols:Int = 3,
                    val rows:Int = 4,
                    val res:String = "") {

    private val arr: Array<Array<Int>> = arrayOf(
                                            arrayOf(1,2,3),
                                            arrayOf(4,5,6),
                                            arrayOf(7,8,9),
                                            arrayOf(-3,0,-2)
                                        )

    fun getCellNumber(row: Int, col: Int):Int{
        return arr[row][col]
    }
}