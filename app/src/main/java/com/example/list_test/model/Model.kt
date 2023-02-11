package com.example.list_test.model

import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime

class Model {

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

    private val products = listOf(
        ProductInfoPerDay(
            products = listOf(
                ProductInfo(true, "Nowogard 2023", LocalDateTime.now()),
                ProductInfo(false, "Wrocław 2021", LocalDateTime.now().minusDays(1).minusHours(2))
            ),
            day = LocalDateTime.now()
        ),
        ProductInfoPerDay(
            products = listOf(
                ProductInfo(false, "Szczecin 2019", LocalDateTime.now().minusDays(1)),
                ProductInfo(false, "Poznań 2021", LocalDateTime.now().minusDays(1).minusHours(2))
            ),
            day = LocalDateTime.now().minusDays(1)
        ),
        ProductInfoPerDay(
            products = listOf(
                ProductInfo(true, "Kobierzyce 2024", LocalDateTime.now().minusDays(2)),
                ProductInfo(true, "Malanów 2018", LocalDateTime.now().minusDays(2).minusHours(4))
            ),
            day = LocalDateTime.now().minusDays(2)
        )
    )

    private val elementsBeforeFilter = listOf(
        ProductInfo(true, "Nowogard 2023", LocalDateTime.now()),
        ProductInfo(false, "Wrocław 2021", LocalDateTime.now().minusDays(1).minusHours(2)),
        ProductInfo(true, "Poznań 2022", LocalDateTime.now().minusDays(3).minusHours(2)),
        ProductInfo(false, "Wrocław 2021", LocalDateTime.now().minusDays(1).minusHours(2)),
        ProductInfo(true, "Poznań 2022", LocalDateTime.now().minusDays(3).minusHours(2))
    )

    val groupedProducts = MutableStateFlow(products)
    val allElements = MutableStateFlow(elementsBeforeFilter)

}
