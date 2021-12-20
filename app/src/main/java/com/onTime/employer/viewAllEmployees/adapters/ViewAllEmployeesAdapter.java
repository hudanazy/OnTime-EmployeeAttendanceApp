package com.onTime.employer.viewAllEmployees.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onTime.R;
import com.onTime.employer.viewAllEmployees.models.ViewAllEmployeesModel;

import java.util.ArrayList;

public class ViewAllEmployeesAdapter extends RecyclerView.Adapter<ViewAllEmployeesAdapter.ViewHolder> {

    View view;
    ArrayList<ViewAllEmployeesModel> viewAllEmployeesModelArrayList;
    public ViewAllEmployeesAdapter(ArrayList<ViewAllEmployeesModel> viewAllEmployeesModelArrayList) {
        this.viewAllEmployeesModelArrayList=viewAllEmployeesModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_employees_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.statusTv.setText(viewAllEmployeesModelArrayList.get(position).getStatus());
        holder.nameTv.setText(viewAllEmployeesModelArrayList.get(position).getEmployeeName());
        holder.checkInTv.setText(viewAllEmployeesModelArrayList.get(position).getCheckIn());
        holder.checkOutTv.setText(viewAllEmployeesModelArrayList.get(position).getCheckOut());
        holder.dateTv.setText(viewAllEmployeesModelArrayList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return viewAllEmployeesModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView statusTv,nameTv,checkInTv,checkOutTv,dateTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            statusTv=itemView.findViewById(R.id.statusTv);
            nameTv=itemView.findViewById(R.id.nameTv);
            checkInTv=itemView.findViewById(R.id.checkInTv);
            checkOutTv=itemView.findViewById(R.id.checkOutTv);
            dateTv=itemView.findViewById(R.id.dateTv);
        }
    }
}