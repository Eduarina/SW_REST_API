package com.example.rafao.sw_rest_api.Fragments;

import android.annotation.SuppressLint;
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

import com.google.gson.Gson;

import com.example.rafao.sw_rest_api.R;
import com.example.rafao.sw_rest_api.Models.*;
import com.example.rafao.sw_rest_api.Utils.DownloadAsyncTask;
import com.example.rafao.sw_rest_api.Utils.URLS;

import java.util.ArrayList;
import java.util.Collection;

@SuppressLint("ValidFragment")
public class BaseApiFragment<T> extends Fragment{
//    public ArrayList<T> foo = new ArrayList<>();
//    RecyclerView recyclerView;
//    static int boton;
//
//    public BaseApiFragment(int b){
//        boton = b;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate (R.layout.fragment_baseapi, container, false);
//    }
//
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        FragmentActivity activity = getActivity();
//        if (activity == null) return;
//
//        recyclerView = activity.findViewById(R.id.baseApiList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        switch (boton) {
//            case 1:
//                new DownloadAsyncTask(s -> parseJSON(s)).execute(URLS.SW_API_PEOPLE_URL);
//                break;
//            case 2:
//                new DownloadAsyncTask(s -> parseJSON(s)).execute(URLS.SW_API_STARSHIPS_URL);
//                break;
//            case 3:
//                new DownloadAsyncTask(s -> parseJSON(s)).execute(URLS.SW_API_FILMS);
//                break;
//            case 4:
//                new DownloadAsyncTask(s -> parseJSON(s)).execute(URLS.SW_API_VEHICLES);
//                break;
//            case 5:
//                new DownloadAsyncTask(s -> parseJSON(s)).execute(URLS.SW_API_SPECIES);
//                break;
//        }
//
//    }
//
//    private void parseJSON (String json) {
//        //los datos descargados en formato JSON se convierten en su representación java utilizando la clase especificada
//        switch (boton){
//            case 1:
//                ArrayList<SWApiPeople> peo = new ArrayList<>();
//                SWApiPeopleHeader people = new Gson ().fromJson (json, SWApiPeopleHeader.class);
//                if (people == null) return;
//                if (people != null) {
//                    //si existe otro conjunto de resultados, se descarga de forma asíncrona
//                    new DownloadAsyncTask (this::parseJSON).execute (people.next);
//                } else {
//                    //cuando ya no hay más conjuntos de resultados en el campo next, los obtenidos se pasan al adapter del recyclerview
//                    peo.addAll(people.results);
//                    recyclerView.setAdapter (new PeopleAdapter (peo));
//                }
//                peo.addAll (people.results);
//                break;
////            case 2:
////                SWApiStarshipsHeader starships = new Gson ().fromJson (json, SWApiStarshipsHeader.class);
////                ArrayList<SWApiStarships> star = new ArrayList<>();
////                if (starships == null) return;
////                if (starships != null) {
////                    //si existe otro conjunto de resultados, se descarga de forma asíncrona
////                    new DownloadAsyncTask (this::parseJSON).execute (starships.next);
////                } else {
////                    //cuando ya no hay más conjuntos de resultados en el campo next, los obtenidos se pasan al adapter del recyclerview
////                    star.addAll(starships.results);
////                    recyclerView.setAdapter (new BaseApiAdapter (star));
////                }
////                star.addAll (starships.results);
////                break;
////            case 3:
////                SWApiFilmsHeader films = new Gson ().fromJson (json, SWApiFilmsHeader.class);
////                ArrayList<SWApiFilms> filme = new ArrayList<>();
////                if (films== null) return;
////                if (films != null) {
////                    //si existe otro conjunto de resultados, se descarga de forma asíncrona
////                    new DownloadAsyncTask (this::parseJSON).execute (films.next);
////                } else {
////                    //cuando ya no hay más conjuntos de resultados en el campo next, los obtenidos se pasan al adapter del recyclerview
////                    filme.addAll(films.results);
////                    recyclerView.setAdapter (new BaseApiAdapter (filme));
////                }
////                filme.addAll (films.results);
////                break;
////            case 4:
////                SWApiVehiclesHeader vehicles = new Gson ().fromJson (json, SWApiVehiclesHeader.class);
////                ArrayList<SWApiVehicles> vehi = new ArrayList<>();
////                if (vehicles == null) return;
////                if (vehicles != null) {
////                    //si existe otro conjunto de resultados, se descarga de forma asíncrona
////                    new DownloadAsyncTask (this::parseJSON).execute (vehicles.next);
////                } else {
////                    //cuando ya no hay más conjuntos de resultados en el campo next, los obtenidos se pasan al adapter del recyclerview
////                    vehi.addAll(vehicles.results);
////                    recyclerView.setAdapter (new BaseApiAdapter (vehi));
////                }
////                vehi.addAll (vehicles.results);
////                break;
////            default:
////                SWApiSpeciesHeader species = new Gson ().fromJson (json, SWApiSpeciesHeader.class);
////                ArrayList<SWApiSpecies> sp = new ArrayList<>();
////                if (species == null) return;
////                if (species != null) {
////                    //si existe otro conjunto de resultados, se descarga de forma asíncrona
////                    new DownloadAsyncTask (this::parseJSON).execute (species.next);
////                } else {
////                    //cuando ya no hay más conjuntos de resultados en el campo next, los obtenidos se pasan al adapter del recyclerview
////                    sp.addAll(species.results);
////                    recyclerView.setAdapter (new BaseApiAdapter (sp));
////                }
////                sp.addAll (species.results);
////                break;
//        }
//
//        //Log.i (URLS.TAG, "" + planets.results.size ());
//    }
//
//    class PeopleAdapter extends RecyclerView.Adapter<BaseApiFragment.BaseApiViewHolder>{
//
//        ArrayList<SWApiPeople> data;
//
//        public PeopleAdapter(ArrayList<SWApiPeople> data) {
//            this.data = data;
//        }
//
//        @NonNull
//        @Override
//        public BaseApiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//            LayoutInflater inflater = LayoutInflater.from (parent.getContext ());
//            View view = inflater.inflate (R.layout.swapifilms, parent, false);
//            return new BaseApiViewHolder (view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull BaseApiViewHolder holder, int i) {
//            switch (boton) {
//                case 1:
//                    SWApiPeople persona = (SWApiPeople) data.get(i);
//                    holder.setData(persona.name, persona.birth_year, persona.height);
//                    break;
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return data.size();
//        }
//    }
//
////    class BaseApiAdapter<T> extends RecyclerView.Adapter<BaseApiAdapter.BaseApiViewHolder> {
////
////        private ArrayList<T> data;
////
////        BaseApiAdapter (ArrayList<T> d) {
////            data = d;
////        }
////
////        @Override
////        public void onBindViewHolder (@NonNull BaseApiViewHolder holder, int position) {
////            switch (boton){
////                case 1:
////                    SWApiPeople persona = (SWApiPeople) data.get (position);
////                    holder.setData (persona.name, persona.birth_year, persona.height);
////                    break;
////                case 2:
////                    SWApiStarships nave = (SWApiStarships) data.get (position);
////                    holder.setData (nave.name, nave.cargo_capacity, nave.model);
////                    break;
////                case 3:
////                    SWApiFilms peli = (SWApiFilms) data.get (position);
////                    holder.setData (peli.title, peli.director, peli.producer);
////                    break;
////                case 4:
////                    SWApiVehicles vehi = (SWApiVehicles) data.get (position);
////                    holder.setData (vehi.name, vehi.length, vehi.cargo_capacity);
////                    break;
////                default:
////                    SWApiSpecies especie = (SWApiSpecies) data.get (position);
////                    holder.setData (especie.name, especie.language, especie.classification);
////                    break;
////            }
////        }
////
////        @Override
////        public int getItemCount () {
////            return data.size ();
////        }
////
////    }
//
//    class BaseApiViewHolder extends RecyclerView.ViewHolder {
//        TextView tvData1, tvData2, tvData3;
//
//        BaseApiViewHolder (View itemView) {
//            super (itemView);
//            tvData1 = itemView.findViewById (R.id.Text1);
//            tvData2 = itemView.findViewById (R.id.Text2);
//            tvData3 = itemView.findViewById (R.id.Text3);
//        }
//
//        void setData (String data1, String data2, String data3) {
//            tvData1.setText (data1);
//            tvData2.setText (data2);
//            tvData3.setText (data3);
//        }
//    }
//

}
