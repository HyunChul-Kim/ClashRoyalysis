package com.app.chul.clashroyalysis.bus

class RxEvent {
    data class EventAddTag(val tag: String)
    data class EventDeleteTag(val tag: String)
}