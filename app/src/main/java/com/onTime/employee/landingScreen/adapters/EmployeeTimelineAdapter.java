package com.onTime.employee.landingScreen.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onTime.EmployeeAttendance.TimeLineModel;
import com.onTime.R;

import java.util.ArrayList;

public class EmployeeTimelineAdapter extends RecyclerView.Adapter<EmployeeTimelineAdapter.ViewHolder> {

    View view;
    ArrayList<TimeLineModel> timeLineModelArrayList;
    public EmployeeTimelineAdapter(ArrayList<TimeLineModel> timeLineModelArrayList) {
        this.timeLineModelArrayList=timeLineModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_timeline_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.InTime.setText(timeLineModelArrayList.get(position).getInTime());
        holder.Date.setText(timeLineModelArrayList.get(position).getDate());
        holder.OutTime.setText(timeLineModelArrayList.get(position).getOutTime());
    }

    @Override
    public int getItemCount() {
        return timeLineModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView InTime,Date,OutTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            InTime=itemView.findViewById(R.id.InTime);
            Date=itemView.findViewById(R.id.Date);
            OutTime=itemView.findViewById(R.id.OutTime);
        }
    }
}