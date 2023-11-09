package ru.practicum.android.diploma.sharing.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.practicum.android.diploma.sharing.domain.repository.SharingRepository

class SharingRepositoryImpl(private val context: Context) : SharingRepository {

    override fun shareJobLink(jobLink: String) {
        Intent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, jobLink)
            type = "text/plain"
            context.startActivity(this, null)
        }
    }

    override fun shareEmail(email: String) {
        Intent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).apply {
            action = Intent.ACTION_SENDTO
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            context.startActivity(this)
        }
    }

    override fun sharePhoneNumber(phoneNumber: String) {
        Intent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse("tel:$phoneNumber")
            context.startActivity(this)
        }
    }
}