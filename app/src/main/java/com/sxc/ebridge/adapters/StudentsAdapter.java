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

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder> {

    private ArrayList<Users> studentList;
    private Context context;

    public StudentsAdapter(ArrayList<Users> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View studentView= LayoutInflater.from(context).inflate(R.layout.layout_display_all_students,parent,false);

        return new StudentsViewHolder(studentView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {

        holder.tvId.setText("ID : "+studentList.get(position).getId());
        holder.tvName.setText("Name : "+studentList.get(position).getName());
        holder.tvDob.setText("DOB : "+studentList.get(position).getDob());
        holder.tvDepartment.setText("Department : "+studentList.get(position).getDepartment());
        holder.tvContact.setText("Contact : "+studentList.get(position).getContact());
        holder.tvBatch.setText("Batch : "+studentList.get(position).getBatch());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewStudentSingleRecordActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("ID",studentList.get(position).getId());
                bundle.putString("NAME",studentList.get(position).getName());
                bundle.putString("DOB",studentList.get(position).getDob());
                bundle.putString("DEPARTMENT",studentList.get(position).getDepartment());
                bundle.putString("CONTACT",studentList.get(position).getContact());
                bundle.putString("BATCH",studentList.get(position).getBatch());
                intent.putExtra("STUDENT_DETAILS",bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentsViewHolder extends RecyclerView.ViewHolder {

        TextView tvId,tvName,tvDob,tvDepartment,tvContact,tvBatch;
        View view;

        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            tvId=itemView.findViewById(R.id.tv_student_id);
            tvName=itemView.findViewById(R.id.tv_student_name);
            tvDob=itemView.findViewById(R.id.tv_dob);
            tvDepartment=itemView.findViewById(R.id.tv_department);
            tvContact=itemView.findViewById(R.id.tv_contact);
            tvBatch=itemView.findViewById(R.id.tv_batch);

        }
    }
}
