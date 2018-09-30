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

import com.example.rafao.sw_rest_api.Models.SWApiPeople;
import com.example.rafao.sw_rest_api.Models.SWApiPeopleHeader;
import com.example.rafao.sw_rest_api.R;
import com.example.rafao.sw_rest_api.Utils.DownloadAsyncTask;
import com.example.rafao.sw_rest_api.Utils.URLS;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PeopleFragment extends Fragment {
    public ArrayList<SWApiPeople> peo = new ArrayList<>();
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

        new DownloadAsyncTask(s -> parseJSON(s)).execute(URLS.SW_API_PEOPLE_URL);


    }

    private void parseJSON (String json) {
        //los datos descargados en formato JSON se convierten en su representación java utilizando la clase especificada

                SWApiPeopleHeader people = new Gson().fromJson (json, SWApiPeopleHeader.class);
                if (people == null) return;
                if (people.next != null) {
                    //si existe otro conjunto de resultados, se descarga de forma asíncrona
                    new DownloadAsyncTask (this::parseJSON).execute (people.next);
                } else {
                    //cuando ya no hay más conjuntos de resultados en el campo next, los obtenidos se pasan al adapter del recyclerview
                    peo.addAll(people.results);
                    recyclerView.setAdapter (new PeopleAdapter(peo));
                }
                peo.addAll (people.results);

        //Log.i (URLS.TAG, "" + planets.results.size ());
    }

    class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

        private ArrayList<SWApiPeople> data;

        PeopleAdapter (ArrayList<SWApiPeople> d) {
            data = d;
        }

        @NonNull
        @Override
        public PeopleViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from (parent.getContext ());
            View view = inflater.inflate (R.layout.swapifilms, parent, false);
            return new PeopleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PeopleViewHolder peopleViewHolder, int i) {
            SWApiPeople persona = data.get (i);
            String [] datos = new String[10];
            datos[0] = persona.name;
            datos[1] = persona.birth_year;
            datos[2] = persona.created;
            datos[3] = persona.edited;
            datos[4] = persona.eye_color;
            datos[5] = persona.gender;
            datos[6] = persona.height;
            datos[7] = persona.hair_color;
            datos[8] = persona.homeworld;

            peopleViewHolder.setData (datos);
        }

//        @Override
//        public void onBindViewHolder (@NonNull BaseApiFragment.BaseApiAdapter.BaseApiViewHolder holder, int position) {
//            switch (boton){
//                case 1:
//
//                    break;
//                case 2:
//                    SWApiStarships nave = (SWApiStarships) data.get (position);
//                    holder.setData (nave.name, nave.cargo_capacity, nave.model);
//                    break;
//                case 3:
//                    SWApiFilms peli = (SWApiFilms) data.get (position);
//                    holder.setData (peli.title, peli.director, peli.producer);
//                    break;
//                case 4:
//                    SWApiVehicles vehi = (SWApiVehicles) data.get (position);
//                    holder.setData (vehi.name, vehi.length, vehi.cargo_capacity);
//                    break;
//                default:
//                    SWApiSpecies especie = (SWApiSpecies) data.get (position);
//                    holder.setData (especie.name, especie.language, especie.classification);
//                    break;
//            }
//        }

        @Override
        public int getItemCount () {
            return data.size ();
        }

        class PeopleViewHolder extends RecyclerView.ViewHolder {
            TextView text1, text4, text7;
            TextView text2, text5, text8;
            TextView text3, text6, text9;

            PeopleViewHolder (View itemView) {
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
