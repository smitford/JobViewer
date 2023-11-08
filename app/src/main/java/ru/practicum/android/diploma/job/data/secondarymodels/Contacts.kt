package ru.practicum.android.diploma.job.data.secondarymodels

data class Contacts(
    val email: String,
    val name: String,
    val phones: Array<Phones>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Contacts

        if (email != other.email) return false
        if (name != other.name) return false
        if (!phones.contentEquals(other.phones)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + phones.contentHashCode()
        return result
    }
}