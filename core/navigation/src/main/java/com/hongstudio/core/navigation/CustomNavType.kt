package com.hongstudio.core.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.hongstudio.core.model.CalculatorData
import kotlinx.serialization.json.Json

val CalculatorDataNavType = object : NavType<CalculatorData>(isNullableAllowed = false) {
    override fun get(
        bundle: Bundle,
        key: String
    ): CalculatorData? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): CalculatorData {
        return Json.decodeFromString(value)
    }

    override fun put(
        bundle: Bundle,
        key: String,
        value: CalculatorData
    ) {
        bundle.putString(key, Json.encodeToString(CalculatorData.serializer(), value))
    }

    override fun serializeAsValue(value: CalculatorData): String {
        return Json.encodeToString(CalculatorData.serializer(), value)
    }
}