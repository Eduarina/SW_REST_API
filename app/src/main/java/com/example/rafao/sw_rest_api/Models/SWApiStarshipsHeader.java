package com.example.rafao.sw_rest_api.Models;

import android.support.annotation.Nullable;

import java.util.ArrayList;

public class SWApiStarshipsHeader {
    public int count;
    @Nullable public String next;
    @Nullable public String previous;
    public ArrayList<SWApiStarships> results;
}
