package com.example.electrodomesticos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    EditText etBrand, etInches, etColor, etModel, etWeight, etMeasure, etPrice, etOs, etVoltage, etConnectortype, etScreentype, etMaker, etResolution, etAudio, etAccessory, etSerie;

    FloatingActionButton fabGuardar, fabListar;

    ProgressDialog progressDialog;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String updateId, updateBrand, updateInches, updateColor, updateModel, updateWeight, updateMeasure, updatePrice, updateOs, updateVoltaje, updateConnectortype, updateScreentype, updateMaker, updateResolution, updateAudio, updateAccessory, updateSerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            etBrand = findViewById(R.id.etBrand);
            etInches = findViewById(R.id.etInches);
            etColor = findViewById(R.id.etColor);
            etModel = findViewById(R.id.etModel);
            etWeight = findViewById(R.id.etWeight);
            etMeasure = findViewById(R.id.etMeasure);
            etPrice = findViewById(R.id.etPrice);
            etOs = findViewById(R.id.etOs);
            etVoltage = findViewById(R.id.etVoltage);
            etConnectortype = findViewById(R.id.etConnectortype);
            etScreentype = findViewById(R.id.etScreentype);
            etMaker = findViewById(R.id.etMaker);
            etResolution = findViewById(R.id.etResolution);
            etAudio = findViewById(R.id.etAudio);
            etAccessory = findViewById(R.id.etAccessory);
            etSerie = findViewById(R.id.etSerie);
            fabGuardar = findViewById(R.id.fabGuardar);
            fabListar = findViewById(R.id.fabListar);

            progressDialog = new ProgressDialog(this);

            db = FirebaseFirestore.getInstance();

            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Agregar registro");


            final Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                actionBar.setTitle("Actualizar Datos");

                updateId = bundle.getString("updateId");
                updateBrand = bundle.getString("updateBrand");
                updateInches = bundle.getString("updateInches");
                updateColor = bundle.getString("updateColor");
                updateModel = bundle.getString("updateModel");
                updateWeight = bundle.getString("updateWeight");
                updateMeasure = bundle.getString("updateMeasure");
                updatePrice = bundle.getString("updatePrice");
                updateOs = bundle.getString("updateOs");
                updateVoltaje = bundle.getString("updateVoltage");
                updateConnectortype = bundle.getString("updateConnectortype");
                updateScreentype = bundle.getString("updateScreentype");
                updateResolution = bundle.getString("updateResolution");
                updateAudio = bundle.getString("updateAudio");
                updateAccessory = bundle.getString("updateAccessory");
                updateSerie = bundle.getString("updateSerie");

                etBrand.setText(updateBrand);
                etInches.setText(updateInches);
                etColor.setText(updateColor);
                etModel.setText(updateModel);
                etWeight.setText(updateWeight);
                etMeasure.setText(updateMeasure);
                etPrice.setText(updatePrice);
                etOs.setText(updateOs);
                etVoltage.setText(updateVoltaje);
                etConnectortype.setText(updateConnectortype);
                etScreentype.setText(updateScreentype);
                etMaker.setText(updateMaker);
                etResolution.setText(updateResolution);
                etAudio.setText(updateAudio);
                etAccessory.setText(updateAccessory);
                etSerie.setText(updateSerie);


            } else {
                actionBar.setTitle("Agregar");
            }


            fabGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle1 = getIntent().getExtras();
                    if (bundle1 != null) {
                        String id = updateId;
                        String brand = etBrand.getText().toString().trim();
                        String inches = etInches.getText().toString().trim();
                        String color = etColor.getText().toString().trim();
                        String model = etModel.getText().toString().trim();
                        String weight = etWeight.getText().toString().trim();
                        String measure = etMeasure.getText().toString().trim();
                        String price = etPrice.getText().toString().trim();
                        String os = etOs.getText().toString().trim();
                        String voltage = etVoltage.getText().toString().trim();
                        String connectortype = etConnectortype.getText().toString().trim();
                        String screentype = etScreentype.getText().toString().trim();
                        String maker = etMaker.getText().toString().trim();
                        String resolution = etResolution.getText().toString().trim();
                        String audio = etAudio.getText().toString().trim();
                        String accessory = etAccessory.getText().toString().trim();
                        String serie = etSerie.getText().toString().trim();

                        actualizarDatos(id, brand, inches, color, model,
                                weight, measure, price, os, voltage, connectortype,
                                screentype, maker, resolution, audio, accessory, serie);

                    } else {
                        String brand = etBrand.getText().toString().trim();
                        String inches = etInches.getText().toString().trim();
                        String color = etColor.getText().toString().trim();
                        String model = etModel.getText().toString().trim();
                        String weight = etWeight.getText().toString().trim();
                        String measure = etMeasure.getText().toString().trim();
                        String price = etPrice.getText().toString().trim();
                        String os = etOs.getText().toString().trim();
                        String voltage = etVoltage.getText().toString().trim();
                        String connectortype = etConnectortype.getText().toString().trim();
                        String screentype = etScreentype.getText().toString().trim();
                        String maker = etMaker.getText().toString().trim();
                        String resolution = etResolution.getText().toString().trim();
                        String audio = etAudio.getText().toString().trim();
                        String accessory = etAccessory.getText().toString().trim();
                        String serie = etSerie.getText().toString().trim();


                        cargarDatos(brand, inches, color, model, weight, measure, price, os, voltage, connectortype, screentype, maker, resolution, audio, accessory, serie);
                    }
                }
            });


            fabListar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, ListActivityTelevison.class));
                    finish();
                }
            });

        }


        private void cargarDatos(String brand, String inches, String color, String model, String weight, String measure, String price, String os, String voltage, String connectortype, String screentype, String maker, String resolution, String audio, String accessory, String serie) {
            progressDialog.setTitle("Agregar datos");
            progressDialog.show();
            String id = UUID.randomUUID().toString();

            Map<String, Object> doc = new HashMap<>();
            doc.put("id", id);
            doc.put("brand", brand);
            doc.put("inches", inches);
            doc.put("color", color);
            doc.put("model", model);
            doc.put("weight", weight);
            doc.put("measure", measure);
            doc.put("price", price);
            doc.put("os", os);
            doc.put("voltage", voltage);
            doc.put("connetortype", connectortype);
            doc.put("screentype", screentype);
            doc.put("maker", maker);
            doc.put("resolution", resolution);
            doc.put ("audio", audio);
            doc.put("accessory", accessory);
            doc.put("serie", serie);


            db.collection("Documents").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Datos almacenados con éxito...", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        private void actualizarDatos(String id, String brand, String inches, String color,
                                     String model, String weight, String measure, String price,
                                     String os, String voltage, String connectortype,String screentype, String maker, String resolution,
                                     String audio, String accessory, String serie) {
            progressDialog.setTitle("Actualizando datos a Firebase");
            progressDialog.show();

        /*
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("nombre", nombre);
        doc.put("apaterno", apaterno);
        doc.put("amaterno", amaterno);
        doc.put("sexo", sexo);
        doc.put("direccion", direccion);
        doc.put("facebook", facebook);
        doc.put("instagram", instagram);

         */


            db.collection("Documents")
                    .document(id).update(
                    "brand", brand,
                    "inches", inches,
                    "color", color,
                    "model", model,
                    "weight", weight,
                    "measure", measure,
                    "price", price,
                    "os", os,
                    "voltaje", voltage,
                    "connectortype", connectortype,
                    "screentype", screentype,
                    "maker", maker,
                    "resolution",resolution,
                    "audio",audio,
                    "accessory", accessory,
                    "serie", serie
            )
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Actualización exitosa...", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }