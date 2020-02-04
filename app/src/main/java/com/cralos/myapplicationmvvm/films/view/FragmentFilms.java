package com.cralos.myapplicationmvvm.films.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cralos.myapplicationmvvm.OnClickBack;
import com.cralos.myapplicationmvvm.R;
import com.cralos.myapplicationmvvm.databinding.FragmentFilmsBinding;
import com.cralos.myapplicationmvvm.films.adapters.FilmsAdapter;
import com.cralos.myapplicationmvvm.films.interfaces.OnClickFilm;
import com.cralos.myapplicationmvvm.films.models.Film;
import com.cralos.myapplicationmvvm.films.viewmodel.FilmsModel;
import com.cralos.myapplicationmvvm.loader.Loader;

import java.util.List;

public class FragmentFilms extends Fragment implements OnClickBack, OnClickFilm {

    public static final String TAG = FragmentFilms.class.getSimpleName();

    /*dataBinding*/
    private FragmentFilmsBinding binding;

    /*viewModel*/
    private FilmsModel viewModel;

    /*adapter*/
    private FilmsAdapter adapter;

    /*loader*/
    private Loader loader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_films, container, false);
        initFragmentFilms();
        return binding.getRoot();
    }

    @Override
    public void onClickBack() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void initFragmentFilms() {
        loader = new Loader();
        binding.setOnClickBackListener(this);
        viewModel = ViewModelProviders.of(this).get(FilmsModel.class);
        viewModel.init();
        setupFilmsRecycler();
        setupLoader();
        viewModel.getFilmsFromApi();
    }

    private void setupFilmsRecycler() {

        viewModel.getFilms().observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                binding.recyclerViewFilms.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerViewFilms.setHasFixedSize(false);
                adapter = new FilmsAdapter(films, getContext());
                adapter.setOnClickFilmListener(FragmentFilms.this);
                binding.recyclerViewFilms.setAdapter(adapter);
            }
        });

    }

    private void setupLoader() {
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    showLoader();
                } else {
                    hiodeLoader();
                }
            }
        });
    }

    @Override
    public void onClickFilm(Film film) {
        Toast.makeText(getContext(), "Director: " + film.getDirector(), Toast.LENGTH_SHORT).show();
    }

    private void showLoader() {
        loader.show(getActivity().getSupportFragmentManager(), Loader.TAG);
    }

    private void hiodeLoader() {
        loader.dismiss();
    }


}
