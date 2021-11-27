/**
 * Finds local minimum of a function using Hooke-Jeeves algorithm.
 *
 * @param coords initial coordinates of the point
 * @param function function to minimize
 * @param step step size
 * @param reduceCoefficient coefficient of reducing step size
 * @param eps maximum value of the step
 * @param onStep callback called on each step, which takes current coordinates, function value and whether the step was successful
 * @return
 */
fun hookeJeeves(
    coords: List<Double>,
    function: (List<Double>) -> Double,
    step: Double = 0.01,
    reduceCoefficient: Double = 2.0,
    eps: Double = 0.00001,
    onStep: ((List<Double>, Double, Boolean) -> Unit)? = null
): Pair<List<Double>, Double> {
    assert(eps > 0.0)
    assert(step >= eps)
    assert(reduceCoefficient > 1.0)

    var currentCoords = coords.toMutableList()
    var currentStep = step

    while (currentStep >= eps) {
        val probe = probeCoords(currentCoords, currentStep).firstOrNull {
            val initial = function(currentCoords)
            val final = function(it)
            val successful = final < initial
            onStep?.invoke(it, final, successful)
            successful
        }

        if (probe == null) {
            currentStep /= reduceCoefficient
            continue
        }

        currentCoords = probe.toMutableList()
    }

    return currentCoords to function(currentCoords)
}

private fun probeCoords(coords: List<Double>, step: Double) = sequence<List<Double>> {
    for (i in coords.indices) {
        val newCoords = coords.toMutableList()
        newCoords[i] += step
        yield(newCoords)
        newCoords[i] -= step * 2
        yield(newCoords)
    }
}
