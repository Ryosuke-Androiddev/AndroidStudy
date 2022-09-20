package com.example.androidestudy.feature.auth.presentation.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MaxLengthErrorTransformation(
    private val maxLen: Int,
    private val errorStyle: SpanStyle = SpanStyle(color = Color.Red)
): VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString(
                text = text.text,
                spanStyles = if (text.length > maxLen) {
                    listOf(AnnotatedString.Range(errorStyle, maxLen, text.length))
                } else {
                    emptyList()
                }
            ),
            OffsetMapping.Identity
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MaxLengthErrorTransformation) return false
        if (maxLen != other.maxLen || errorStyle != other.errorStyle) return false
        return true
    }

    override fun hashCode(): Int {
        return maxLen.hashCode()
    }
}