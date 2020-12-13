 package com.example.electrodomesticos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

 public class ListActivityTelevison extends AppCompatActivity {


     java.util.List<TelevisonModel> mTelevisonModelList = new ArrayList<>();
     RecyclerView recyclerview;

     //Crear instancia de FirebaseFirestore
     FirebaseFirestore db;

     CustomAdapter customAdapter;
     RecyclerView.LayoutManager layoutManager;
     ProgressDialog progressDialog;
     Context context;
     FloatingActionButton fabAgregar;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_televison);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Ver registros");

            recyclerview = findViewById(R.id.recyclerview);
            recyclerview.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            recyclerview.setLayoutManager(layoutManager);
            fabAgregar = findViewById(R.id.fabAgregar);

            // Obtener instancia de Firebase
            db = FirebaseFirestore.getInstance();

            verDatos();

            fabAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ListActivityTelevison.this, MainActivity.class));
                    finish();
                }
            });
        }

        public void eliminarRegistro(int index) {
            db.collection("Documents").document(mTelevisonModelList.get(index).getId())
                    .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ListActivityTelevison.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                            verDatos();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ListActivityTelevison.this, "No se ha completado la operación" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        private void verDatos() {
            db.collection("Documents")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            mTelevisonModelList.clear();
                            for (DocumentSnapshot doc : task.getResult()) {
                                TelevisonModel televisonModel = new TelevisonModel(
                                        doc.getString("id"),
                                        doc.getString("brand"),
                                        doc.getString("inches"),
                                        doc.getString("color"),
                                        doc.getString("model"),
                                        doc.getString("weight"),
                                        doc.getString("measure"),
                                        doc.getString("price"),
                                        doc.getString("os"),
                                        doc.getString("voltage"),
                                        doc.getString("connectortype"),
                                        doc.getString("screentype"),
                                        doc.getString("maker"),
                                        doc.getString("resolution"),
                                        doc.getString("audio"),
                                        doc.getString("accessory"),
                                        doc.getString("serie");

                                mTelevisonModelList.add(televisonModel);
                            }
                            customAdapter = new CustomAdapter(ListActivityTelevison.this, mTelevisonModelList);
                            recyclerview.setAdapter(customAdapter);
                        }
                    }).addOnFailureListener(e -> Toast.makeText(ListActivityTelevison.this, e.getMessage(), Toast.LENGTH_SHORT).show());
        }

    }