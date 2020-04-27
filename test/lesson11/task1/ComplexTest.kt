package lesson11.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ComplexTest {

    private fun assertApproxEquals(expected: Complex, actual: Complex, eps: Double) {
        assertEquals(expected.re, actual.re, eps)
        assertEquals(expected.im, actual.im, eps)
    }

    @Test
    fun plus() {
        assertApproxEquals(complex("4-2i"), complex("1+2i") + complex("3-4i"), 1e-10)
    }

    @Test
    operator fun unaryMinus() {
        assertApproxEquals(Complex(1.0, -2.0), -Complex(2.0, -1.0), 1e-10)
    }

    @Test
    fun minus() {
        assertApproxEquals(complex("2+6i"), complex("5+2i") - complex("3-4i"), 1e-10)
    }

    @Test
    fun times() {
        assertApproxEquals(complex("11+2i"), complex("1+2i") * complex("3-4i"), 1e-10)
    }

    @Test
    fun div() {
        assertApproxEquals(complex("1-1.52i"), complex("11+2i") / complex("3-4i"), 1e-10)
    }

    @Test
    fun equals() {
        assertApproxEquals(Complex(1.0, 2.0), complex("1+2i"), 1e-12)
        assertApproxEquals(Complex(1.0, 0.0), Complex(1.0), 1e-12)
    }
}