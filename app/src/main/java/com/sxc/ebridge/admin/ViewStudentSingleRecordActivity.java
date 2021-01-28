package com.sxc.ebridge.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sxc.ebridge.R;
import com.sxc.ebridge.database.StrudentsDBHelper;

public class ViewStudentSingleRecordActivity extends AppCompatActivity {

    TextView tvId,tvName,tvDob,tvDepartment,tvContact,tvBatch;
    Button btnUpdate,btnDelete;
    String id,name,dob,department,contact,batch;

    StrudentsDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_single_record);


        tvId=findViewById(R.id.tv_student_id);
        tvName=findViewById(R.id.tv_student_name);
        tvDob=findViewById(R.id.tv_dob);
        tvDepartment=findViewById(R.id.tv_department);
        tvContact=findViewById(R.id.tv_contact);
        tvBatch=findViewById(R.id.tv_batch);
        btnUpdate=findViewById(R.id.btn_update);
        btnDelete=findViewById(R.id.btn_delete);

        DB=new StrudentsDBHelper(this);
        Bundle bundle = getIntent().getBundleExtra("STUDENT_DETAILS");
        if (bundle != null) {
            id=bundle.getString("ID");
            name=bundle.getString("NAME");
            dob=bundle.getString("DOB");
            department=bundle.getString("DEPARTMENT");
            contact=bundle.getString("CONTACT");
            batch=bundle.getString("BATCH");

            tvId.setText(id);
            tvName.setText(name);
            tvDob.setText(dob);
            tvDepartment.setText(department);
            tvContact.setText(contact);
            tvBatch.setText(batch);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewStudentSingleRecordActivity.this, UpdateStudentRecordActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("ID",id);
                bundle.putString("NAME",name);
                bundle.putString("DOB",dob);
                bundle.putString("DEPARTMENT",department);
                bundle.putString("CONTACT",contact);
                bundle.putString("BATCH",batch);
                intent.putExtra("STUDENT_DETAILS",bundle);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkDeleted= DB.deleteData(id);
                if (checkDeleted){
                    Toast.makeText(ViewStudentSingleRecordActivity.this, id+" Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ViewStudentSingleRecordActivity.this, "Unable to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}