package id.andriawan24.mini_games_2048.models

data class Matrix(val arr: MutableList<Int?> = arrayOfNulls<Int?>(16).toMutableList()) {
    fun matrixCopy(newArray: MutableList<Int?>): Matrix = Matrix().copy(
        arr = newArray
    )

    fun asMatrix(): MutableList<List<Int?>> {
        val outerList = mutableListOf<List<Int?>>()
        repeat(ROWS_COUNT) { row ->
            val innerList = mutableListOf<Int?>()
            repeat(arr.size / ROWS_COUNT) { i ->
                innerList.add(i, arr[row + ROWS_COUNT + i])
            }
            outerList.add(innerList)
        }
        return outerList
    }

    companion object {
        const val ROWS_COUNT = 4
    }
}