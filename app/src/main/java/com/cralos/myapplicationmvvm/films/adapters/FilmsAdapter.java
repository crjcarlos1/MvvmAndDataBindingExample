package com.cralos.myapplicationmvvm.films.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cralos.myapplicationmvvm.R;
import com.cralos.myapplicationmvvm.databinding.ItemFilmBinding;
import com.cralos.myapplicationmvvm.films.interfaces.OnClickFilm;
import com.cralos.myapplicationmvvm.films.models.Film;

import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolder> {

    private List<Film> films;
    private Context context;
    private OnClickFilm listener;

    public FilmsAdapter(List<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_film, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setFilm(films.get(position));
        holder.binding.setOnClickFilmListener(new OnClickFilm() {
            @Override
            public void onClickFilm(Film film) {
                if (listener != null) {
                    listener.onClickFilm(film);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void setOnClickFilmListener(OnClickFilm listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemFilmBinding binding;

        public ViewHolder(@NonNull ItemFilmBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

}
