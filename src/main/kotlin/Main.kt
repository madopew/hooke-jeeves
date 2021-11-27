fun main(args: Array<String>) {
    val initial = listOf(1.0, 1.0, 1.0, 1.0, 1.0)
    val v = listOf(400.0, 600.0, 800.0, 700.0, 200.0)
    val k = listOf(10.0, 12.0, 11.0, 9.0, 8.0)
    val s = listOf(16.0, 8.0, 8.0, 7.0, 4.0)

    println()
    println("%3d   %6.3f   %6.3f   %6.3f   %6.3f   %6.3f   %9.3f".format(
        0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        taskFunction(initial, v, k, s)
    ))

    var step = 1
    val (finalCoords, functionValue) = hookeJeeves(
        listOf(1.0, 1.0, 1.0, 1.0, 1.0),
        { taskFunction(it, v, k, s) },
        step = 20.0,
        eps = 0.1,
        onStep = { coords, value, successful -> outputFunction(step++, coords, value, successful) }
    )

    println("%s   %6.3f   %6.3f   %6.3f   %6.3f   %6.3f   %9.3f".format(
        "res",
        finalCoords[0],
        finalCoords[1],
        finalCoords[2],
        finalCoords[3],
        finalCoords[4],
        functionValue
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
    println("%3d   %6.3f   %6.3f   %6.3f   %6.3f   %6.3f   %9.3f   %s".format(
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