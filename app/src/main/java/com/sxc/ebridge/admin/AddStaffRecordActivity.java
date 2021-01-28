package com.sxc.ebridge.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sxc.ebridge.R;
import com.sxc.ebridge.database.StaffDBHelper;
import com.sxc.ebridge.database.StrudentsDBHelper;

public class AddStaffRecordActivity extends AppCompatActivity {
    EditText etId,etName,etDepartment,etContact;
    Button btnInsert;

    String id,name,department,contact;

    StaffDBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff_record);

        DB=new StaffDBHelper(this);

        etId=findViewById(R.id.et_staff_id);
        etName=findViewById(R.id.et_staff_name);
        etDepartment=findViewById(R.id.et_department);
        etContact=findViewById(R.id.et_contact);
        btnInsert=findViewById(R.id.btn_insert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=etId.getText().toString().trim();
                name=etName.getText().toString().trim();
                department=etDepartment.getText().toString().trim();
                contact=etContact.getText().toString().trim();

                if (TextUtils.isEmpty(id) && TextUtils.isEmpty(name)  && TextUtils.isEmpty(department) &&
                        TextUtils.isEmpty(contact) ){
                    Toast.makeText(AddStaffRecordActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkInsert=DB.insertStaffData(id,name,department,contact);
                    if (checkInsert){
                        Toast.makeText(AddStaffRecordActivity.this, id +" Added to the database", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AddStaffRecordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}