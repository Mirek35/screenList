package com.example.list_test.model

import java.time.LocalDateTime

data class ProductInfoPerDay(val products: List<ProductInfo>, val day: LocalDateTime)