package com.cralos.myapplicationmvvm.people.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cralos.myapplicationmvvm.OnClickBack;
import com.cralos.myapplicationmvvm.R;
import com.cralos.myapplicationmvvm.databinding.FragmentPeopleBinding;
import com.cralos.myapplicationmvvm.loader.Loader;
import com.cralos.myapplicationmvvm.people.adapters.PeopleAdapter;
import com.cralos.myapplicationmvvm.people.models.Character;
import com.cralos.myapplicationmvvm.people.viewmodels.PeopleViewModel;

import java.util.List;

public class FragmentPeople extends Fragment implements OnClickBack {
    public static final String TAG = FragmentPeople.class.getSimpleName();

    /*dataBinding*/
    private FragmentPeopleBinding binding;

    /*viewModel*/
    private PeopleViewModel viewModel;

    /*loader*/
    private Loader loader;
    private boolean readyToCharge = false;

    /*peopleAdapter*/
    private PeopleAdapter adapter;
    private GridLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_people, container, false);
        initFragmentPeople();
        return binding.getRoot();
    }

    @Override
    public void onClickBack() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void initFragmentPeople() {
        binding.setOnClickBackListener(this);
        viewModel = ViewModelProviders.of(FragmentPeople.this).get(PeopleViewModel.class);
        loader = new Loader();
        viewModel = new PeopleViewModel();
        viewModel.initPeopleViewModel();
        setupPeopleRecycler();
        loaderListener();
        viewModel.getCharactersFromAPI();
    }

    private void setupPeopleRecycler() {
        readyToCharge = true;
        layoutManager = new GridLayoutManager(getContext(), 1);
        adapter = new PeopleAdapter(viewModel.getCharacters().getValue(), getContext());
        binding.recyclerViewPeople.setLayoutManager(layoutManager);
        binding.recyclerViewPeople.setAdapter(adapter);

        viewModel.getCharacters().observe(FragmentPeople.this, new Observer<List<Character>>() {
            @Override
            public void onChanged(List<Character> characters) {
                adapter.notifyDataSetChanged();
                readyToCharge = true;
            }
        });

        binding.recyclerViewPeople.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if (readyToCharge) {
                    if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                        readyToCharge = false;
                        viewModel.getCharactersFromAPI();
                    }
                }
            }
        });

    }

    private void loaderListener() {
        viewModel.getIsLoading().observe(FragmentPeople.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    showLoader();
                } else {
                    hideLoader();
                }
            }
        });
    }

    private void showLoader() {
        if (!loader.isAdded())
            loader.show(getActivity().getSupportFragmentManager(), Loader.TAG);
    }

    private void hideLoader() {
        loader.dismiss();
    }

}
