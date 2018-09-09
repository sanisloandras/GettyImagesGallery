package com.sanislo.soft.gettyimagesgallery.domain

import java.util.ArrayList

abstract class Mapper<FROM, TO> {
    abstract fun map(input: FROM): TO

    fun map(fromList: List<FROM>?): List<TO>? {
        var toList: MutableList<TO>? = null
        if (fromList != null) {
            toList = ArrayList()
            var to: TO
            for (from in fromList) {
                to = map(from)
                toList.add(to)
            }
        }
        return toList
    }
}

