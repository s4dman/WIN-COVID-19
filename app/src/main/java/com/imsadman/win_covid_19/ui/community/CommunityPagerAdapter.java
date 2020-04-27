package com.imsadman.win_covid_19.ui.community;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CommunityPagerAdapter extends FragmentStateAdapter {

    public CommunityPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0: {
                return new OffersFragment();

            }

            case 1: {
                return new RequestsFragment();

            }

            default:
                return new OffersFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
