package com.example.rafao.sw_rest_api.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rafao.sw_rest_api.Models.SWApiSpecies;
import com.example.rafao.sw_rest_api.Models.SWApiSpeciesHeader;
import com.example.rafao.sw_rest_api.Models.SWApiVehicles;
import com.example.rafao.sw_rest_api.Models.SWApiVehiclesHeader;
import com.example.rafao.sw_rest_api.R;
import com.example.rafao.sw_rest_api.Utils.DownloadAsyncTask;
import com.example.rafao.sw_rest_api.Utils.URLS;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SpeciesFragment extends Fragment {
    public ArrayList<SWApiSpecies> peo = new ArrayList<>();
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_baseapi, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity();
        if (activity == null) return;

        recyclerView = activity.findViewById(R.id.baseApiList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        new DownloadAsyncTask(s -> parseJSON(s)).execute(URLS.SW_API_STARSHIPS_URL);


    }

    private void parseJSON (String json) {
        //los datos descargados en formato JSON se convierten en su representación java utilizando la clase especificada

        SWApiSpeciesHeader specie = new Gson().fromJson (json, SWApiSpeciesHeader.class);
        if (specie == null) return;
        if (specie.next != null) {
            //si existe otro conjunto de resultados, se descarga de forma asíncrona
            new DownloadAsyncTask (this::parseJSON).execute (specie.next);
        } else {
            //cuando ya no hay más conjuntos de resultados en el campo next, los obtenidos se pasan al adapter del recyclerview
            peo.addAll(specie.results);
            recyclerView.setAdapter (new SpeciesAdapter(peo));
        }
        peo.addAll (specie.results);

        //Log.i (URLS.TAG, "" + planets.results.size ());
    }

    class SpeciesAdapter extends RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder> {

        private ArrayList<SWApiSpecies> data;

        SpeciesAdapter(ArrayList<SWApiSpecies> d) {
            data = d;
        }

        @NonNull
        @Override
        public SpeciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            LayoutInflater inflater = LayoutInflater.from (parent.getContext ());
            View view = inflater.inflate (R.layout.swapifilms, parent, false);
            return new SpeciesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SpeciesViewHolder speciesViewHolder, int i) {
            SWApiSpecies persona = data.get (i);
            String [] datos = new String[10];
            datos[0] = persona.name;
            datos[1] = persona.average_height;
            datos[2] = persona.created;
            datos[3] = persona.edited;
            datos[4] = persona.average_lifespan;
            datos[5] = persona.classification;
            datos[6] = persona.designation;
            datos[7] = persona.eye_colors;
            datos[8] = persona.hair_colors;

            speciesViewHolder.setData (datos);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        class SpeciesViewHolder extends RecyclerView.ViewHolder {
            TextView text1, text4, text7;
            TextView text2, text5, text8;
            TextView text3, text6, text9;

            SpeciesViewHolder (View itemView) {
                super (itemView);
                text1 = itemView.findViewById (R.id.Text1);
                text2 = itemView.findViewById (R.id.Text2);
                text3 = itemView.findViewById (R.id.Text3);
                text4 = itemView.findViewById (R.id.Text4);
                text5 = itemView.findViewById (R.id.Text5);
                text6 = itemView.findViewById (R.id.Text6);
                text7 = itemView.findViewById (R.id.Text7);
                text8 = itemView.findViewById (R.id.Text8);
                text9 = itemView.findViewById (R.id.Text9);
            }

            void setData (String[] data1) {
                text1.setText (data1[0]);
                text2.setText (data1[1]);
                text3.setText (data1[2]);
                text4.setText (data1[3]);
                text5.setText (data1[4]);
                text6.setText (data1[5]);
                text7.setText (data1[6]);
                text8.setText (data1[7]);
                text9.setText (data1[8]);

            }
        }
    }
}
