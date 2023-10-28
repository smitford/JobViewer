package ru.practicum.android.diploma.filter.domain

data class FIlter(
    private val area:String,
    private val country:String,
    private val branch:String,
    private val salary:String,
    private val doNotShow:Boolean
)
