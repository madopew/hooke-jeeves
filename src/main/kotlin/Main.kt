const val FORMAT_STRING = "\"%d\",\"%.3f\",\"%.3f\",\"%.3f\",\"%.3f\",\"%.3f\",\"%.3f\",\"%s\""

fun main() {
    val initial = listOf(1.0, 1.0, 1.0, 1.0, 1.0)
    val v = listOf(400.0, 600.0, 800.0, 700.0, 200.0)
    val k = listOf(10.0, 12.0, 11.0, 9.0, 8.0)
    val s = listOf(16.0, 8.0, 8.0, 7.0, 4.0)

    println("step,q1,q2,q3,q4,q5,f,note")
    println(FORMAT_STRING.format(
        0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        taskFunction(initial, v, k, s),
        "Начальные значения"
    ))

    var step = 1
    val (finalCoords, functionValue) = hookeJeeves(
        listOf(1.0, 1.0, 1.0, 1.0, 1.0),
        { taskFunction(it, v, k, s) },
        step = 20.0,
        eps = 0.1,
        onStep = { coords, value, successful -> outputFunction(step++, coords, value, successful) }
    )

    println(FORMAT_STRING.format(
        step,
        finalCoords[0],
        finalCoords[1],
        finalCoords[2],
        finalCoords[3],
        finalCoords[4],
        functionValue,
        "Результат"
    ))
}

fun taskFunction(
    coords: List<Double>,
    v: List<Double>,
    k: List<Double>,
    s: List<Double>
): Double {
    var result = 0.0
    for (i in coords.indices) {
        result += k[i] * v[i] / coords[i] + s[i] * coords[i] / 2
    }
    return result
}

fun outputFunction(
    step: Int,
    coords: List<Double>,
    value: Double,
    successful: Boolean
) {
    println(FORMAT_STRING.format(
        step,
        coords[0],
        coords[1],
        coords[2],
        coords[3],
        coords[4],
        value,
        if (successful) "Успешный" else "Неуспешный"
    ))
}