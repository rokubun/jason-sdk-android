package cat.rokubun.jason.utils

import java.security.MessageDigest

/**
 * SHA-256 hasher
 */
object Hasher {
    fun hash(text: String?): String {
        val bytes = text?.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}