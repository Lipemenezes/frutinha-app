package com.felipe.frutinha.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.felipe.frutinha.R;
import com.felipe.frutinha.dao.FruitDao;
import com.felipe.frutinha.models.Fruit;

import java.util.List;

/**
 * Created by Felipe on 02/07/2018.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder> {
    private List<Fruit> fruits;

    public FruitAdapter(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FruitViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit, parent, false), parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder holder, int position) {
        if (fruits != null && fruits.size() > 0) {
            holder.name.setText( fruits.get(position).getName() );

            if (fruits.get(position).getFav())
                holder.fav.setChecked(true);
            else
                holder.fav.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class FruitViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public CheckBox fav;

        public FruitViewHolder(View itemView, final Context context) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            fav = itemView.findViewById(R.id.item_fav);

            fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (fruits.size() > 0) {
                        Fruit fruit = fruits.get(getLayoutPosition());
                        fruit.setFav(isChecked);
                        FruitDao.createOrUpdate(fruit, context);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fav.toggle();
                    FruitDao.createIfNotExists(fruits.get(getLayoutPosition()), context);
                }
            });

        }
    }

}
