package com.example.androidestudy.feature.auth.presentation.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CustomPasswordVisualTransformation(
    private val mask: Char = '\u2022',
    private val maxLength: Int,
    private val errorStyle: SpanStyle = SpanStyle(color = Color.Red)
): VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString(
                text = mask.toString().repeat(text.text.length),
                spanStyles = if (text.length > maxLength) {
                    listOf(AnnotatedString.Range(errorStyle, maxLength, text.length))
                } else {
                    emptyList()
                }
            ),
            OffsetMapping.Identity
        )
    }

    // 単に引数のオブジェクトと比較した結果を返す
    // 必要だと思った一行を追加
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CustomPasswordVisualTransformation) return false
        if (maxLength != other.maxLength || errorStyle != other.errorStyle) return false
        if (mask != other.mask) return false
        return true
    }

    override fun hashCode(): Int {
        return maxLength.hashCode()
    }
}