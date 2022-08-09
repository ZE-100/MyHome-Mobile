package com.myhome.service.data

import android.content.SharedPreferences
import com.myhome.Application
import com.myhome.blueprint.Account
import com.myhome.blueprint.Member
import com.myhome.blueprint.SecurityHeaders
import com.myhome.other.Session
import com.myhome.other.SharedPref

class DataHandlingService {

    private val sharedPref: SharedPreferences = Application.sp

    fun saveData() {
        val editor = sharedPref.edit()

        val account = Session.getAccount()
        val currentMember = Session.getCurrentMember()

        editor.apply {
              putString(SharedPref.EMAIL, account.email)
              putString(SharedPref.PASSWORD, account.password)
              putString(SharedPref.TOKEN, account.token)
        }.apply()

        editor.apply {
            putLong(SharedPref.MEMBER_ID, currentMember.id)
            putString(SharedPref.MEMBER_NAME, currentMember.name)
            putInt(SharedPref.MEMBER_ICON, currentMember.icon)
        }.apply()
    }

    fun loadData() {
        val email = sharedPref.getString(SharedPref.EMAIL, null)
        val password = sharedPref.getString(SharedPref.PASSWORD, null)
        val token = sharedPref.getString(SharedPref.TOKEN, null)

        val currentMemberId = sharedPref.getLong(SharedPref.MEMBER_ID, -1L)
        val currentMemberName = sharedPref.getString(SharedPref.MEMBER_NAME, null)
        val currentMemberIcon = sharedPref.getInt(SharedPref.MEMBER_ICON, -1)

        var account = Account()
        var member = Member()

        //TODO Update logic: Implement automatic login request if email & pw found
        if (email != null && password != null && token != null)
             account = Account(email, password, token)

        if(currentMemberId != -1L && currentMemberName != null && currentMemberIcon != -1)
            member = Member(currentMemberId, currentMemberName, currentMemberIcon)

        updateSession(account, member)
    }

    private fun updateSession(account: Account, member: Member) {
        Session.addAccount(account)
        Session.addAuth(SecurityHeaders(account))
        Session.setCurrentMember(member)
    }
}
