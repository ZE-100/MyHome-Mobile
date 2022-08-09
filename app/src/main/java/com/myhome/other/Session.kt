package com.myhome.other

import com.myhome.blueprint.Account
import com.myhome.blueprint.SecurityHeaders
import com.myhome.blueprint.Member
import com.myhome.util.debug.DebugMode
import com.myhome.blueprint.Members
import com.myhome.service.api.constants.ApiConst
import com.myhome.service.data.DataHandlingService
import com.myhome.util.Logger

class Session {
    companion object Factory {

        private var USER_ACCOUNT: Account = Account()

        private var AUTH: SecurityHeaders = SecurityHeaders()

        private var CURRENT_MEMBER: Member = Member()

        private var ALL_MEMBERS: Members = Members()

        private var DEBUG: DebugMode = DebugMode.DEACTIVATED

        private val dataService = DataHandlingService()

        fun addAccount(account: Account) {
            this.USER_ACCOUNT = account
            dataService.saveData()
        }

        fun getAccount(): Account {
            return this.USER_ACCOUNT
        }

        fun addAuth(securityHeaders: SecurityHeaders) {
            this.AUTH = securityHeaders
            dataService.saveData()
        }

        fun replaceHeader(key: String, value: String) {
            this.AUTH.replaceHeader(key, value)
            dataService.saveData()
        }

        fun getAuth(): SecurityHeaders? {
            return this.AUTH
        }

        fun setCurrentMember(member: Member) {
            this.CURRENT_MEMBER = member
            dataService.saveData()
        }

        fun getCurrentMember(): Member {
            return this.CURRENT_MEMBER
        }

        fun setAllMembers(members: Members) {
            this.ALL_MEMBERS = members
        }

        fun getAllMembers(): Members {
            return this.ALL_MEMBERS
        }

        fun destroy() {
            this.USER_ACCOUNT = Account()
            this.AUTH = SecurityHeaders()
            this.CURRENT_MEMBER = Member()
            this.ALL_MEMBERS = Members()

            dataService.saveData()
        }

        fun userLoginPresent(): Boolean {
            return this.USER_ACCOUNT.isSet()
        }

        fun memberSelected(): Boolean {
            return this.CURRENT_MEMBER.isSet()
        }

        fun debugMode(): DebugMode {
            return this.DEBUG
        }

        fun switchDebug() {
            when (this.DEBUG) {
                DebugMode.ONLINE -> {
                    this.DEBUG = DebugMode.OFFLINE
                    createMockSession()
                }
                DebugMode.OFFLINE -> this.DEBUG = DebugMode.DEACTIVATED
                DebugMode.DEACTIVATED -> this.DEBUG = DebugMode.ONLINE
            }

            Logger.log(ApiConst.LOG_DEBUG, Session.javaClass, "DEBUG-MODE: ${this.DEBUG.value()} | destroyed")
        }

        fun destroyDebugMockSession() {
            this.DEBUG = DebugMode.DEACTIVATED

            destroy()

            Logger.log(ApiConst.LOG_DEBUG, Session.javaClass, "DEBUG-MODE: ${this.DEBUG.value()} | destroyed")
        }

        private fun createMockSession() {
            addAccount(Account("mock@mockmail.mock", "mock", "mock123"))

            setAllMembers(
                Members(listOf(
                Member(2, "Member 1", 5),
                Member(1, "Member 2", 1),
                Member(55, "Member 3", 8)))
            )

            setCurrentMember(getAllMembers().members[0])
            dataService.saveData()
        }
    }
}
