package com.wira_fkom.pajawanedumobile.data

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
