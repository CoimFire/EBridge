package com.sxc.ebridge.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sxc.ebridge.R;
import com.sxc.ebridge.database.StrudentsDBHelper;

public class UpdateStudentRecordActivity extends AppCompatActivity {
    EditText etId,etName,etDob,etDepartment,etContact,etBatch;
    Button btnUpdate;

    String id,name,dob,department,contact,batch;

    StrudentsDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_record);



        DB=new StrudentsDBHelper(this);

        etId=findViewById(R.id.et_student_id);
        etName=findViewById(R.id.et_student_name);
        etDob=findViewById(R.id.et_dob);
        etDepartment=findViewById(R.id.et_department);
        etContact=findViewById(R.id.et_contact);
        etBatch=findViewById(R.id.et_batch);
        btnUpdate=findViewById(R.id.btn_update);
        Bundle bundle = getIntent().getBundleExtra("STUDENT_DETAILS");
        if (bundle != null) {
            id=bundle.getString("ID");
            name=bundle.getString("NAME");
            dob=bundle.getString("DOB");
            department=bundle.getString("DEPARTMENT");
            contact=bundle.getString("CONTACT");
            batch=bundle.getString("BATCH");

            etId.setText(id);
            etName.setText(name);
            etDob.setText(dob);
            etDepartment.setText(department);
            etContact.setText(contact);
            etBatch.setText(batch);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=etId.getText().toString().trim();
                name=etName.getText().toString().trim();
                dob=etDob.getText().toString().trim();
                department=etDepartment.getText().toString().trim();
                contact=etContact.getText().toString().trim();
                batch=etBatch.getText().toString().trim();

                if (TextUtils.isEmpty(id) && TextUtils.isEmpty(name) && TextUtils.isEmpty(dob) && TextUtils.isEmpty(department) &&
                        TextUtils.isEmpty(contact) && TextUtils.isEmpty(batch)){
                    Toast.makeText(UpdateStudentRecordActivity.this, "Please fill all the details", Toast.LENGTH_LONG).show();
                }else {
                    Boolean checkUpdate=DB.updateStudentsData(id,name,department,contact,dob,batch);
                    if (checkUpdate){
                        Toast.makeText(UpdateStudentRecordActivity.this, id +" Updated on the database", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(UpdateStudentRecordActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}