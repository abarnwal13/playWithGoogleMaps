package com.playwithgooglemaps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ApiException {
        System.out.println("Hello world!");

        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCU6gU0jpypEHy4-kygQ85NgVb2S9XbFFs").build();
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                "Mumbai, India").await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(results[0].addressComponents));
        
        String [] origins = new String [] {"India"};
        String [] destinations = new String [] {"North America"};
        
        DistanceMatrixApiRequest distanceMatrixApiRequest = DistanceMatrixApi.getDistanceMatrix(context, origins, destinations);
//        distanceMatrixApiRequest.mode(TravelMode.TRANSIT);

        DistanceMatrix distanceMatrix =  distanceMatrixApiRequest.await();

        System.out.println(gson.toJson(distanceMatrix));
        
        
    }
}