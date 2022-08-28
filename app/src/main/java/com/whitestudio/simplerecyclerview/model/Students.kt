package com.whitestudio.simplerecyclerview.model

import android.os.Parcelable
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.ktx.Firebase
import kotlinx.parcelize.Parcelize

@Parcelize
data class Students(
    var id: String? = "",
    var name: String? = "",
    var school: String? = "",
    var city: String? = ""
): Parcelable {
    companion object {
        const val COLLECTION_NAME = "Students"

        fun DocumentSnapshot.toStudents(): Students? {
            return try {
                val name = getString("name")
                val school = getString("school")
                val city = getString("city")
                Students(
                    id,
                    name,
                    school,
                    city
                )
            } catch (e: Exception) {
                val crashlytics = Firebase.crashlytics
                crashlytics.log("Error converting Students")
                crashlytics.setCustomKey("idStudents", id)
                crashlytics.recordException(e)
                null
            }
        }
    }
}
