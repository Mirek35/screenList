package com.example.list_test

import java.time.LocalDateTime

class PhoneState {

    val allElements = listOf(
        ElementsForOneRow("ok", "Nowogard 2023", LocalDateTime.now()),
        ElementsForOneRow("nok", "Wrocław 2021", LocalDateTime.now().minusDays(1).minusHours(2)),
        ElementsForOneRow("ok", "Poznań 2022", LocalDateTime.now().minusDays(3).minusHours(2))
    )

}
