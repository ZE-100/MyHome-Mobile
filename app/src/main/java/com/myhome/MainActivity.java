package com.myhome;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.myhome.blueprint.Account;
import com.myhome.databinding.ActivityMainBinding;
import com.myhome.other.ApiConstants;
import com.myhome.other.Session;
import com.myhome.other.SharedPreferencesStrings;
import com.myhome.service.data.DataHandlingService;

/**
 * @author z-100
 * This is the apps entry point
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Session.Factory.get() == null)
            if (loadDataOnLogin())
                //Redirect to members page

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private boolean loadDataOnLogin() {
        final DataHandlingService dataHandler = new DataHandlingService();

        final Account accountFromSp = dataHandler.loadData(getSharedPreferences(
                SharedPreferencesStrings.SHARED_PREF_NAME, Context.MODE_PRIVATE));

        if (accountFromSp == null)
            return false;

        Session.Factory.create(accountFromSp);
        return true;
    }
}
