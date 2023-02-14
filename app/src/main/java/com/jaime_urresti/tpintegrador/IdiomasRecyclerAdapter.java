package com.jaime_urresti.tpintegrador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.jaime_urresti.tpintegrador.modelo.Idioma;

import java.util.List;

public class IdiomasRecyclerAdapter extends RecyclerView.Adapter<IdiomasRecyclerAdapter.IdiomasViewHolder>{


    private List<Idioma> mDataSet;
    public IdiomasRecyclerAdapter(List<Idioma> myDataSet){

        this.mDataSet = myDataSet;

    }

    public static class IdiomasViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView idioma;
        public IdiomasViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.cardTarjeta);
            idioma = itemView.findViewById(R.id.textIdiomaCard);


        }
    }





    @NonNull
    @Override
    public IdiomasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.idiomascard,parent,false);

        IdiomasViewHolder vh = new IdiomasViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull IdiomasViewHolder holder, int position) {
        holder.idioma.setTag(position);
        holder.card.setTag(position);

        Idioma idioma = mDataSet.get(position);

        holder.idioma.setText(idioma.getNombreIdioma());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = new Bundle();
                bundle.putInt("id",idioma.getIdIdioma());
                Navigation.findNavController((View)view.getParent()).navigate(
                        R.id.action_idiomasFragment_to_tarjetasFragment,bundle);


             }
        });


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }





}
