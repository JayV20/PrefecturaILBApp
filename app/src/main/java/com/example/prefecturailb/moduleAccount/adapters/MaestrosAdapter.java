package com.example.prefecturailb.moduleAccount.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prefecturailb.R;
import com.example.prefecturailb.common.pojo.Maestro;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MaestrosAdapter extends RecyclerView.Adapter <MaestrosAdapter.ViewHolder>{

    private List <Maestro> maestros;
    private Context context;
    private OnItemClickListener listener;

    public MaestrosAdapter(List <Maestro> maestros, OnItemClickListener listener){
        this.listener = listener;
        this.maestros = maestros;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profesores, parent, false);
        this.context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaestrosAdapter.ViewHolder holder, int position) {
        final Maestro maestro = maestros.get(position);
        holder.setListener(maestro,listener);
        holder.tv_name.setText(maestro.getNombre());
        holder.civ_photo.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_account_circle));
        //holder.civ_photo.setImageURI(maestro.getImagenUrl());
    }

    @Override
    public int getItemCount() {
        return maestros.size();
    }

    public void setMaestros(ArrayList<Maestro> maestros){
        this.maestros=maestros;
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.civ_photo)
        CircleImageView civ_photo;
        @BindView(R.id.containerMain)
        ConstraintLayout containerMain;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        void setListener(Maestro maestro ,final OnItemClickListener listener){
            containerMain.setOnClickListener(view ->listener.onItemClicked(maestro));
        }
    }
}
