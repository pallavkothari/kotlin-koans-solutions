package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }

    operator fun plus(interval: TimeInterval): MyDate = this.addTimeIntervals(interval, 1)

    operator fun plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate =
            this.addTimeIntervals(repeatedTimeInterval.ti, repeatedTimeInterval.n)

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(i: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, i)

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(date: MyDate): Boolean {
        return date >= start && date <= endInclusive
    }
}

operator fun DateRange.iterator(): Iterator<MyDate> = DateIterator(this)

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current = dateRange.start

    override fun hasNext(): Boolean = current <= dateRange.endInclusive

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

}

data class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)