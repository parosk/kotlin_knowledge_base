package debug_example


import java.util.*

fun main(){
    val cc =  CoordinatesCopy()
    val lineCoordinates: MutableList<Point> = cc.createCoordinateList()
    cc.outputValues(lineCoordinates)
    val p = Point(13, 30)
    cc.removeValue(lineCoordinates, p)
    cc.outputValues(lineCoordinates)
}

data class Point(var x: Int, var y: Int)


class CoordinatesCopy {

     fun outputValues(lineCoordinates: List<Point>) {
        println("Output values...")
        for (p in lineCoordinates) {
            System.out.println(p)
        }
    }

     fun removeValue(lineCoordinates: MutableList<*>, p: Point) {
        lineCoordinates.remove(p)
    }

     fun createCoordinateList(): MutableList<Point> {
        val list: ArrayList<Point> = ArrayList<Point>()
        list.add(Point(12, 20))
        list.add(Point(13, 30))
        return list
    }
}
