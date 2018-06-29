package com.example.anilturan.sozlukapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anilturan.sozlukapp.api.ApiClient;
import com.example.anilturan.sozlukapp.api.GlosbeApiService;
import com.example.anilturan.sozlukapp.api.ServiceGenerator;
import com.example.anilturan.sozlukapp.model.GlosbeResponse;
import com.example.anilturan.sozlukapp.model.SpinnerModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private GlosbeApiService glosbeApiService;
    private RecyclerView recyclerView;
    private Button searchButton;
    private EditText searchText;
    private String json = "json";
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private MeanAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glosbeApiService = ServiceGenerator.createService(GlosbeApiService.class);

        searchButton = findViewById(R.id.button);
        searchText = findViewById(R.id.searchText);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        recyclerView = findViewById(R.id.recyclerView);
        setData();


        mAdapter = new MeanAdapter(this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String langFrom = spinnerFrom.getSelectedItem().toString();
                String langTo = spinnerTo.getSelectedItem().toString();
                getApiMean(getLanguageCode(langFrom), getLanguageCode(langTo), json, searchText.getText().toString());

                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                }
            }
        });
    }

    private void getApiMean(String from, String dest, String format, String phrase) {
        GlosbeApiService iService = ApiClient.getClient(MainActivity.this).create(GlosbeApiService.class);
        Call<GlosbeResponse> call = iService.getApiMean2(from, dest, format, phrase);
        call.enqueue(new Callback<GlosbeResponse>() {


            @Override
            public void onResponse(Call<GlosbeResponse> call, Response<GlosbeResponse> response) {
                if (response.body().getTuc().size() > 0) {
                    if (response.body().getResult().equals("ok")) {
                        GlosbeResponse gResponse = new GlosbeResponse();
                        try {
                            for (int i = 0; i < response.body().getTuc().size(); i++) {
                                gResponse.setTuc(response.body().getTuc());
                            }
                            for (int i = 0; i < response.body().getTuc().size(); i++) {

                                gResponse.getTuc().get(i).setPhrase(response.body().getTuc().get(i).getPhrase());
                            }
                        } catch (Exception e) {
                            Log.e("Hata", "Hata");
                        }
                        mAdapter.setList(gResponse);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Kelime Bulunamadı.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GlosbeResponse> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }

    public void setData() {

        String languages[] = {"Türkçe", "İngilizce", "Almanca", "Azerice", "Çince", "Farsça", "Fransızca", "Hintçe", "İspanyolca", "İtalyanca", "Japonca", "Kırgızca", "Özbekçe", "Rusça"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    public String getLanguageCode(String language) {

        ArrayList<SpinnerModel> languageList = new ArrayList<SpinnerModel>();
        languageList.add(new SpinnerModel("tur", "Türkçe"));
        languageList.add(new SpinnerModel("eng", "İngilizce"));
        languageList.add(new SpinnerModel("deu", "Almanca"));
        languageList.add(new SpinnerModel("aze", "Azerice"));
        languageList.add(new SpinnerModel("zho", "Çince"));
        languageList.add(new SpinnerModel("fas", "Farsça"));
        languageList.add(new SpinnerModel("fra", "Fransızca"));
        languageList.add(new SpinnerModel("hin", "Hintçe"));
        languageList.add(new SpinnerModel("spa", "İspanyolca"));
        languageList.add(new SpinnerModel("ita", "İtalyanca"));
        languageList.add(new SpinnerModel("jpn", "Japonca"));
        languageList.add(new SpinnerModel("kir", "Kırgızca"));
        languageList.add(new SpinnerModel("uzb", "Özbekçe"));
        languageList.add(new SpinnerModel("rus", "Rusça"));

        for (int i = 0; i < languageList.size(); i++) {
            if (languageList.get(i).getLanguage().toString().equals(language)) {
                return languageList.get(i).getLangCode().toString();
            }
        }
        return "tur";
    }
}
