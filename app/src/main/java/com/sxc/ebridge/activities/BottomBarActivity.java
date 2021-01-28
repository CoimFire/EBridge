package com.sxc.ebridge.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sxc.ebridge.R;
import com.sxc.ebridge.fragments.HomeFragment;
import com.sxc.ebridge.fragments.InfoFragment;
import com.sxc.ebridge.fragments.ProfileFragment;
import com.sxc.ebridge.fragments.RefreshableFragment;
import com.sxc.ebridge.utils.Utils;

import java.util.List;

public class BottomBarActivity extends AppCompatActivity {
    private Fragment home;
    private Fragment info;
    private Fragment profile;

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);

        profile = new ProfileFragment();
        info = new InfoFragment();
        home = new HomeFragment();

        navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        navigation.getMenu().findItem(Utils.CURRENT_NAVIGATION_BAR).setChecked(true);
        selectFragment();
        //Utils.setTopBar(BottomBarActivity.this,getWindow(),getResources());


    }

    private void selectFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            transaction.hide(fragment);
        }
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Utils.CURRENT_NAVIGATION_BAR + "");
        if (fragment != null) {
            transaction.show(fragment);
        }

        if (R.id.navigation_home == Utils.CURRENT_NAVIGATION_BAR) {
            if (fragment == null) {
                transaction.add(R.id.nav_host_fragment, home, Utils.CURRENT_NAVIGATION_BAR + "").show(home);
            }
        } else if (R.id.navigation_profile == Utils.CURRENT_NAVIGATION_BAR) {
            if (fragment == null) {
                transaction.add(R.id.nav_host_fragment, profile, Utils.CURRENT_NAVIGATION_BAR + "").show(profile);
            }
        } else if (R.id.navigation_info == Utils.CURRENT_NAVIGATION_BAR) {
            if (fragment == null) {
                transaction.add(R.id.nav_host_fragment, info, Utils.CURRENT_NAVIGATION_BAR + "").show(info);
            }
        } else {
            if (fragment == null) {
                transaction.add(R.id.nav_host_fragment, home, Utils.CURRENT_NAVIGATION_BAR + "").show(home);
            }
        }

        transaction.setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out);
        transaction.commitAllowingStateLoss();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        if (item.isChecked()) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(Utils.CURRENT_NAVIGATION_BAR + "");
            if (fragment instanceof RefreshableFragment) {
                ((RefreshableFragment) fragment).refresh();
                return true;
            }
        }
        Utils.CURRENT_NAVIGATION_BAR = item.getItemId();
        item.setChecked(true);
        selectFragment();
        return true;
    };
}