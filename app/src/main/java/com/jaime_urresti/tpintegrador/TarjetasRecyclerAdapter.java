package com.jaime_urresti.tpintegrador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TarjetasRecyclerAdapter extends RecyclerView.Adapter<TarjetasRecyclerAdapter.TarjetasViewHolder> {


    private List<String> mDataSet;
    public TarjetasRecyclerAdapter(List<String> myDataSet){

        this.mDataSet = myDataSet;

    }

    public static class TarjetasViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView original;
        TextView traduccion;
        public TarjetasViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.cardTarjeta);
            original = itemView.findViewById(R.id.textOriginalCard);
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


        holder.original.setText("How Are You Cabecita?");
        holder.traduccion.setText("Como andas Cabecita?");

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController((View)view.getParent()).navigate(
                        R.id.action_tarjetasFragment_to_detalleTarjetaFragment);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }



}
