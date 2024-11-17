package com.hongstudio.core.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.hongstudio.core.model.CalculatorSelected
import kotlinx.serialization.json.Json

val CalculatorSelectedNavType = object : NavType<CalculatorSelected>(isNullableAllowed = false) {
    override fun get(
        bundle: Bundle,
        key: String
    ): CalculatorSelected? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): CalculatorSelected {
        return Json.decodeFromString(value)
    }

    override fun put(
        bundle: Bundle,
        key: String,
        value: CalculatorSelected
    ) {
        bundle.putString(key, Json.encodeToString(CalculatorSelected.serializer(), value))
    }

    override fun serializeAsValue(value: CalculatorSelected): String {
        return Json.encodeToString(CalculatorSelected.serializer(), value)
    }
}
