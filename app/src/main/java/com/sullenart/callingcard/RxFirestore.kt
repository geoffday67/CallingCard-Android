package com.sullenart.callingcard

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import io.reactivex.Observable

open class FirestoreBaseModel(
    var id: String = ""
)

inline fun <reified T : FirestoreBaseModel> DocumentSnapshot.toIdObject(): T =
    this.toObject<T>()?.also { it.id = this.id } ?: T::class.java.newInstance()

inline fun <reified T : FirestoreBaseModel> CollectionReference.fetch(): Observable<T> =
    Observable.create { emitter ->
        this.get()
            .addOnSuccessListener { snap ->
                snap.forEach {
                    emitter.onNext(it.toIdObject())
                }
                emitter.onComplete()
            }
            .addOnFailureListener { emitter.onError(it) }
    }
