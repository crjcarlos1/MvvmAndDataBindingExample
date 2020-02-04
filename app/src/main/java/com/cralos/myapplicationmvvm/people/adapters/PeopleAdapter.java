package com.cralos.myapplicationmvvm.people.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cralos.myapplicationmvvm.R;
import com.cralos.myapplicationmvvm.databinding.ItemCharacterBinding;
import com.cralos.myapplicationmvvm.people.models.Character;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private List<Character> characters;
    private Context context;

    public PeopleAdapter(List<Character> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_character, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.binding.setCharacter(character);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCharacterBinding binding;

        public ViewHolder(@NonNull ItemCharacterBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

}
