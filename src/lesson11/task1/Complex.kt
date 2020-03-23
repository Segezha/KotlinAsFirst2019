@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson1.task1.sqr

/**
 * Класс "комплексое число".
 *
 * Общая сложность задания -- лёгкая.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(s: String) : this(rev(s).first, rev(s).second)

    /**
     * Сложение.
     */
    operator fun plus(other: Complex) = Complex(
        re + other.re, im + other.im
    )

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-im, -re)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(re * other.re - im * other.im, re * other.im + other.re * im)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex =
        Complex(
            (re * other.re + im * other.im) / (sqr(other.re) + sqr(other.im)),
            (other.re * im + re * other.im) / (sqr(other.re) + sqr(other.im))
        )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && re == other.re && im == other.im

    /**
     * Преобразование в строку
     */
    fun conversion(): String =
        when {
            re == 0.0 -> "${im}i"
            im == 0.0 -> "$re"
            (re == 0.0) && (im == 0.0) -> "0"
            im == 1.0 -> "$re+i"
            im == -1.0 -> "$re-i"
            else -> "$re+${im}i"
        }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}

private fun rev(s: String): Pair<Double, Double> {
    var first = 0.0
    var second = 0.0
    var k = 1.0
    for (i in 0 until s.length - 1) {
        if ((s[i] == '+') || (s[i] == '-')) {
            if (s[i] == '-') k = -1.0
            first = second.also { second = 0.0 }
            if (s[i + 1] == 'i') second = 1.0
        } else second = second * 10 + s[i].toString().toDouble()
    }
    return Pair(first, second * k)
}