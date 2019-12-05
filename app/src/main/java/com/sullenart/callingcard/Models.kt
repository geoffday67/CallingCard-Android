package com.sullenart.callingcard

data class ProfileEntry (
    val title: String = "",
    val subtitle: String = "",
    val content: String = "",
    val order: Int = 0
): FirestoreBaseModel()