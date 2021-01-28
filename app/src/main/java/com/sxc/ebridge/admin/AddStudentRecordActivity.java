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

public class AddStudentRecordActivity extends AppCompatActivity {

    EditText etId,etName,etDob,etDepartment,etContact,etBatch;
    Button btnInsert;

    String id,name,dob,department,contact,batch;

    StrudentsDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_record);

        DB=new StrudentsDBHelper(this);

        etId=findViewById(R.id.et_student_id);
        etName=findViewById(R.id.et_student_name);
        etDob=findViewById(R.id.et_dob);
        etDepartment=findViewById(R.id.et_department);
        etContact=findViewById(R.id.et_contact);
        etBatch=findViewById(R.id.et_batch);
        btnInsert=findViewById(R.id.btn_insert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(AddStudentRecordActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkInsert=DB.insertStudentsData(id,name,department,contact,dob,batch);
                    if (checkInsert){
                        Toast.makeText(AddStudentRecordActivity.this, id +" Added to the database", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AddStudentRecordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}