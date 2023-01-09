package com.example.list_test

import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime

class PhoneState {

    fun filterClicked(label: String) {
        if (label == "Dzisiaj") {
            allElements.value = elementsBeforeFilter.filter {
                it.data.dayOfMonth == LocalDateTime.now().dayOfMonth
            }
        } else if (label == "Wczoraj") {
            allElements.value = elementsBeforeFilter.filter {
                it.data.dayOfMonth == LocalDateTime.now().dayOfMonth - 1
            }
        }
    }

    private val elementsBeforeFilter = listOf(
        ElementsForOneRow("ok", "Nowogard 2023", LocalDateTime.now()),
        ElementsForOneRow("n", "Wrocław 2021", LocalDateTime.now().minusDays(1).minusHours(2)),
        ElementsForOneRow("ok", "Poznań 2022", LocalDateTime.now().minusDays(3).minusHours(2))
    )

    val allElements = MutableStateFlow(
        elementsBeforeFilter
    )

}
