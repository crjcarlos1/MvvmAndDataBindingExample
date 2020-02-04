package com.cralos.myapplicationmvvm.menu.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cralos.myapplicationmvvm.R;
import com.cralos.myapplicationmvvm.databinding.FragmentMenuBinding;
import com.cralos.myapplicationmvvm.films.view.FragmentFilms;
import com.cralos.myapplicationmvvm.menu.interfaces.OnClickMenu;
import com.cralos.myapplicationmvvm.people.view.FragmentPeople;

public class FragmentMenu extends Fragment implements OnClickMenu {
    public static final String TAG = FragmentMenu.class.getSimpleName();

    /*dataBinding*/
    private FragmentMenuBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);
        initFragmentMenu();
        return binding.getRoot();
    }

    @Override
    public void onClickPeople() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentPeople.TAG);
        transaction.replace(R.id.containerFragments, new FragmentPeople(), FragmentPeople.TAG).commit();
    }

    @Override
    public void onClickFilms() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentFilms.TAG);
        transaction.replace(R.id.containerFragments, new FragmentFilms(), FragmentFilms.TAG).commit();
    }

    private void initFragmentMenu() {
        binding.setOnClickListener(this);
    }

}
