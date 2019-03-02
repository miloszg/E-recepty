package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.parse.starter.NotesActivity.notatki;

public class MedsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList medArrayList=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meds);
        medArrayList.add("Nazwa preparatu    Postać    dawka     opakowanie     Producent");
        medArrayList.add("Paracetamol Accord tabletki    500 mg    100 tabl.    Accord Healthcare");
        medArrayList.add("Apap Junior    granulat   250mg   10 saszetek    US Pharmacia");
        medArrayList.add("Panadol    tabletki powlekane  500 mg     12 tabl.    GlaxoSmithKline");

        listView=findViewById(R.id.medListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medArrayList);
        listView.setAdapter(arrayAdapter);

    }
}
