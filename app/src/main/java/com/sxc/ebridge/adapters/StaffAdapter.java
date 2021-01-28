package com.sxc.ebridge.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sxc.ebridge.R;
import com.sxc.ebridge.admin.ViewStudentSingleRecordActivity;
import com.sxc.ebridge.domains.Users;

import java.util.ArrayList;

public class StaffAdapter  extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {

    private ArrayList<Users> staffList;
    private Context context;

    public StaffAdapter(ArrayList<Users> staffList, Context context) {
        this.staffList = staffList;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffAdapter.StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View staffView= LayoutInflater.from(context).inflate(R.layout.layout_display_all_staffs,parent,false);


        return new StaffAdapter.StaffViewHolder(staffView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffAdapter.StaffViewHolder holder, int position) {

        holder.tvId.setText("ID : "+staffList.get(position).getId());
        holder.tvName.setText("Name : "+staffList.get(position).getName());
        holder.tvDepartment.setText("Department : "+staffList.get(position).getDepartment());
        holder.tvContact.setText("Contact : "+staffList.get(position).getContact());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewStudentSingleRecordActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("ID",staffList.get(position).getId());
                bundle.putString("NAME",staffList.get(position).getName());
                bundle.putString("DEPARTMENT",staffList.get(position).getDepartment());
                bundle.putString("CONTACT",staffList.get(position).getContact());
                intent.putExtra("STUDENT_DETAILS",bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    public class StaffViewHolder extends RecyclerView.ViewHolder {

        TextView tvId,tvName,tvDepartment,tvContact;
        View view;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            tvId=itemView.findViewById(R.id.tv_staff_id);
            tvName=itemView.findViewById(R.id.tv_staff_name);
            tvDepartment=itemView.findViewById(R.id.tv_department);
            tvContact=itemView.findViewById(R.id.tv_contact);

        }
    }
}
