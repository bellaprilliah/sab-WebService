package com.example.mywebservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>{
    private ArrayList<HashMap<String, String>> postList;
    private MainActivity activity;

    public MahasiswaAdapter(ArrayList<HashMap<String, String>> postList,
                            MainActivity mainActivity){
        this.postList = postList;
        this.activity = mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_mahasiswa_adapter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        final HashMap<String, String> post = postList.get(position);
        viewHolder.textNrp.setText(post.get("nrp"));
        viewHolder.textNama.setText(post.get("nama"));
        viewHolder.textProdi.setText(post.get("prodi"));
    }

    @Override
    public int getItemCount(){
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNrp;
        TextView textNama;
        TextView textProdi;

        public ViewHolder(View view){
            super(view);
            textNrp = (TextView) view.findViewById(R.id.textNrp);
            textNama = (TextView) view.findViewById(R.id.textNama);
            textProdi = (TextView) view.findViewById(R.id.textProdi);
        }
    }
}
