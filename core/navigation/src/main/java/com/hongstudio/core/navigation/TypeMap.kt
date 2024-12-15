package com.hongstudio.core.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.core.model.CalculatorType
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

object TypeMap {
    val selectedCalculatorsTypeMap = mapOf(
        typeOf<List<CalculatorType>>() to object :
            NavType<List<CalculatorType>>(isNullableAllowed = false) {

            override fun get(bundle: Bundle, key: String): List<CalculatorType>? {
                return bundle.getStringArray(key)?.map { Json.decodeFromString<CalculatorType>(it) }
            }

            override fun parseValue(value: String): List<CalculatorType> {
                return Json.decodeFromString(ListSerializer(CalculatorType.serializer()), value)
            }

            override fun put(bundle: Bundle, key: String, value: List<CalculatorType>) {
                val serializedList = value.map {
                    Json.encodeToString(CalculatorType.serializer(), it)
                }.toTypedArray()
                bundle.putStringArray(key, serializedList)
            }

            override fun serializeAsValue(value: List<CalculatorType>): String {
                return Json.encodeToString(ListSerializer(CalculatorType.serializer()), value)
            }
        }
    )

    val calculatorDataTypeMap = mapOf(
        typeOf<CalculatorData>() to object :
            NavType<CalculatorData>(isNullableAllowed = false) {
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
    )
}

