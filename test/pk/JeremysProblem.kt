package pk

import com.google.common.truth.Truth.*
import org.junit.Test

class JeremysProblem {
    @Test fun streams() {
        val ones = arrayListOf(1, 1, 1)
        assertThat(ones.none { it != 1 })
        assertThat(ones.distinct().size == 1)
    }
}
