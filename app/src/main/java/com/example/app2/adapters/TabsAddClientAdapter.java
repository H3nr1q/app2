package com.example.app2.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.app2.fragments.CadastroClienteBasicosFragment;
import com.example.app2.fragments.CadastroEnderecosClientesFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TabsAddClientAdapter extends FragmentPagerAdapter {

    private static final int NUM_TABS = 2;

    public TabsAddClientAdapter (FragmentManager fm){
        super(fm);
    }

    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return CadastroClienteBasicosFragment.newInstance();
            default:
                return CadastroEnderecosClientesFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0){
            return "Básico";
        }

        return "Endereços";
    }
}
