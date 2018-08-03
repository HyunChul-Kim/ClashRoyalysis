package com.app.chul.clashroyalysis.bus

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

object RxBus {

    private val disposableMap = HashMap<Any, CompositeDisposable>()
    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

    fun register(subscriber: Any) {
        if(!disposableMap.containsKey(subscriber)) {
            var disposable = CompositeDisposable()

        }
    }
}