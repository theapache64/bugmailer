package com.theah64.bugmailer.utils

import android.util.Base64
import android.util.Log

import java.security.Key

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/*
 * Used to simple text enc and dec
 * Created by Shifar Shifz on 6/29/2015.
 */
object DarKnight {

    private val ALGORITHM = "AES"

    private val SALT = byteArrayOf('t'.toByte(), 'H'.toByte(), 'e'.toByte(), 'A'.toByte(), 'p'.toByte(), 'A'.toByte(), 'c'.toByte(), 'H'.toByte(), 'e'.toByte(), '6'.toByte(), '4'.toByte(), '1'.toByte(), '0'.toByte(), '0'.toByte(), '0'.toByte(), '0'.toByte())
    private val X = DarKnight::class.java.simpleName

    val salt: Key
        get() = SecretKeySpec(SALT, ALGORITHM)


    fun getDecrypted(encodedText: String): String? {

        val salt = salt
        try {
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, salt)
            val decodedValue = Base64.decode(encodedText, Base64.DEFAULT)
            val decValue = cipher.doFinal(decodedValue)
            val decrypted = String(decValue)
            Log.d(X, "Decryption Success: $decrypted")
            return decrypted
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(X, "Error:" + e.message)
        }

        return null
    }

}