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

import com.jaime_urresti.tpintegrador.modelo.Tarjeta;

import java.util.List;

public class TarjetasRecyclerAdapter extends RecyclerView.Adapter<TarjetasRecyclerAdapter.TarjetasViewHolder> {


    private List<Tarjeta> mDataSet;
    public TarjetasRecyclerAdapter(List<Tarjeta> myDataSet){

        this.mDataSet = myDataSet;

    }

    public static class TarjetasViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView original;
        TextView traduccion;
        public TarjetasViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.cardTarjeta);
            original = itemView.findViewById(R.id.textIdiomaCard);
            traduccion = itemView.findViewById(R.id.textTraduccionCard);

        }
    }





    @NonNull
    @Override
    public TarjetasRecyclerAdapter.TarjetasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjetascard,parent,false);

        TarjetasRecyclerAdapter.TarjetasViewHolder vh = new TarjetasRecyclerAdapter.TarjetasViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull TarjetasRecyclerAdapter.TarjetasViewHolder holder, int position) {
        holder.original.setTag(position);
        holder.traduccion.setTag(position);
        holder.card.setTag(position);

        Tarjeta tarjeta = mDataSet.get(position);

        holder.original.setText(tarjeta.getTextoOrignal());
        holder.traduccion.setText(tarjeta.getTextoTraduccion());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("id",tarjeta.getIdTarjeta());


                Navigation.findNavController((View)view.getParent()).navigate(
                        R.id.action_tarjetasFragment_to_detalleTarjetaFragment,bundle);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }



}
