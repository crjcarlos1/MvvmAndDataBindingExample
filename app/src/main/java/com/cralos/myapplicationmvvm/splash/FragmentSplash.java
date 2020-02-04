package com.cralos.myapplicationmvvm.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cralos.myapplicationmvvm.R;
import com.cralos.myapplicationmvvm.menu.view.FragmentMenu;

public class FragmentSplash extends Fragment {
    public static final String TAG = FragmentSplash.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showMainFragmentMenu();
            }
        }, 2000);

        return view;
    }

    private void showMainFragmentMenu() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentMenu.TAG);
        transaction.replace(R.id.containerFragments, new FragmentMenu(), FragmentMenu.TAG).commit();
    }

}
