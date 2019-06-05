package com.app.chul.clashroyalysis.bus

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.reactivestreams.Subscription

object RxBus {

    private val disposableMap = HashMap<Any, CompositeDisposable>()
    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

    fun getObservable(): Observable<Any> = publisher

    fun register(subscriber: Any, vararg disposables: Disposable) {
        for(d in disposables) {
            if (!disposableMap.containsKey(subscriber)) {
                var disposable = CompositeDisposable()
                disposable.add(d)
            }
        }
    }

    fun unregister(subscriber: Any) {
        if(disposableMap.containsKey(subscriber)) {
            disposableMap[subscriber]?.clear()
        }
    }

    fun unregisterAll() {
        for(subscriber in disposableMap) {
            disposableMap[subscriber]?.clear()
        }
    }
}