package com.cralos.myapplicationmvvm.films;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cralos.myapplicationmvvm.OnClickBack;

public class FragmentFilms extends Fragment implements OnClickBack {
    public static final String TAG = FragmentFilms.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClickBack() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
