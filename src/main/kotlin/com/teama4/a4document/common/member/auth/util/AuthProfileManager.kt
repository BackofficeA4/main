package com.teama4.a4document.common.member.auth.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

object AuthProfileManager {
	class Data {
		val time: LocalDateTime = LocalDateTime.now()
	}

	private val infoList = HashMap<String, Data>()
	private val scope = CoroutineScope(context = Dispatchers.IO)


	fun setInfo(token: String) {
		scope.launch {
			infoList[token] = Data()
			delay(60 * 10 * 1000)
			infoList.remove(token)
		}
	}

	fun checkHasInfo(token: String) =
		infoList[token]?.let { true } ?: false
}