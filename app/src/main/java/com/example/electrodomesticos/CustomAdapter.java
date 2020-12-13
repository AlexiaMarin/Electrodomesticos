package com.example.electrodomesticos;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    ListActivityTelevison listActivityTelevison;
    List<TelevisonModel> mTelevisonModelList;

    public CustomAdapter(ListActivityTelevison listActivity, List<TelevisonModel> televisonModelList) {
        this.listActivityTelevison = listActivity;
        this.mTelevisonModelList = televisonModelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
    @Override
    public void onItemClick(View view, int position) {
            String marca = mTelevisonModelList.get(position).getBrand();
            String modelo = mTelevisonModelList.get(position).getModel();
            String precio = mTelevisonModelList.get(position).getPrice();
            Toast.makeText(listActivityTelevison, marca + " " + modelo + " " + precio, Toast.LENGTH_SHORT).show();
        }
            @Override
            public void onItemLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listActivityTelevison);
                String[] options = {"Actualizar datos", "Eliminar"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            String id = mTelevisonModelList.get(position).getId();
                            String brand = mTelevisonModelList.get(position).getBrand();
                            String inches = mTelevisonModelList.get(position).getInches();
                            String color = mTelevisonModelList.get(position).getColor();
                            String model = mTelevisonModelList.get(position).getModel();
                            String weight = mTelevisonModelList.get(position).getWeight();
                            String measure = mTelevisonModelList.get(position).getMeasure();
                            String price = mTelevisonModelList.get(position).getPrice();
                            String os = mTelevisonModelList.get(position).getOs();
                            String voltage = mTelevisonModelList.get(position).getVoltage();
                            String connectortype = mTelevisonModelList.get(position).getConnectortype();
                            String screentype = mTelevisonModelList.get(position).getScreentype();
                            String maker = mTelevisonModelList.get(position).getMaker();
                            String resolution = mTelevisonModelList.get(position).getResolution();
                            String audio= mTelevisonModelList.get(position).getAudio();
                            String accessory= mTelevisonModelList.get(position).getAccessory();
                            String serie= mTelevisonModelList.get(position).getSerie();



                            Intent actualizarDatos = new Intent(listActivityTelevison, MainActivity.class);
                            actualizarDatos.putExtra("updateId", id);
                            actualizarDatos.putExtra("updateBrand", brand);
                            actualizarDatos.putExtra("updateInches", inches);
                            actualizarDatos.putExtra("updateColor", color);
                            actualizarDatos.putExtra("updateMode", model);
                            actualizarDatos.putExtra("updateWeight", weight);
                            actualizarDatos.putExtra("updateMasure", measure);
                            actualizarDatos.putExtra("updatePrice", price);
                            actualizarDatos.putExtra("updateOs", os);
                            actualizarDatos.putExtra("updateVoltage", voltage);
                            actualizarDatos.putExtra("updateconnectortype", connectortype);
                            actualizarDatos.putExtra("updateScreentype", screentype);
                            actualizarDatos.putExtra("updateMaker", maker);
                            actualizarDatos.putExtra("updateResolution", resolution);
                            actualizarDatos.putExtra("updateAudio", audio);
                            actualizarDatos.putExtra("updateAccessory", accessory);
                            actualizarDatos.putExtra("updateSerie", serie);




                            listActivityTelevison.startActivity(actualizarDatos);
                            // String id, String nombre, String apaterno, String amaterno, String sexo, String direccion, String facebook, String instagram
                        }

                        if (which == 1) {
                            listActivityTelevison.eliminarRegistro(position);
                        }
                    }
                }).create().show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvMarca.setText(
                mTelevisonModelList.get(i).getBrand()
                        + " " +  mTelevisonModelList.get(i).getModel()
                        + " " +  mTelevisonModelList.get(i).getPrice()
        );
    }

    @Override
    public int getItemCount() {
        return mTelevisonModelList.size();
    }
}

