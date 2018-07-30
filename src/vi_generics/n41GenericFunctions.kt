package vi_generics

import util.TODO
import java.util.*
import kotlin.collections.ArrayList

fun task41(): Nothing = TODO(
    """
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
    return partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
}

// the non-generic way:
//fun List<String>.partitionTo(list1: MutableList<String>, list2: MutableList<String>, predicate: (String) -> Boolean): Pair<List<String>, List<String>> {
//    val (l1, l2) = this.partition(predicate)
//    l1.toCollection(list1)
//    l2.toCollection(list2)
//    return Pair(list1, list2)
//}

// the generic way:
fun <T, C : MutableCollection<T>> Iterable<T>.partitionTo(first: C, second: C, predicate: (T)->Boolean): Pair<C, C> {
    for (item in this) {
        if (predicate(item)) {
            first.add(item)
        } else {
            second.add(item)
        }
    }
    return Pair(first, second)
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
    return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
}