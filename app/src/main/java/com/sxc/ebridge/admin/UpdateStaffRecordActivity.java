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

public class UpdateStaffRecordActivity extends AppCompatActivity {
    EditText etId,etName,etDepartment,etContact;
    Button btnUpdate;

    String id,name,department,contact;

    StaffDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_staff_decord);

        DB=new StaffDBHelper(this);

        etId=findViewById(R.id.et_staff_id);
        etName=findViewById(R.id.et_staff_name);
        etDepartment=findViewById(R.id.et_department);
        etContact=findViewById(R.id.et_contact);
        btnUpdate=findViewById(R.id.btn_update);
        Bundle bundle = getIntent().getBundleExtra("STAFF_DETAILS");
        if (bundle != null) {
            id=bundle.getString("ID");
            name=bundle.getString("NAME");
            department=bundle.getString("DEPARTMENT");
            contact=bundle.getString("CONTACT");

            etId.setText(id);
            etName.setText(name);
            etDepartment.setText(department);
            etContact.setText(contact);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=etId.getText().toString().trim();
                name=etName.getText().toString().trim();
                department=etDepartment.getText().toString().trim();
                contact=etContact.getText().toString().trim();

                if (TextUtils.isEmpty(id) && TextUtils.isEmpty(name)  && TextUtils.isEmpty(department) &&
                        TextUtils.isEmpty(contact) ){
                    Toast.makeText(UpdateStaffRecordActivity.this, "Please fill all the details", Toast.LENGTH_LONG).show();
                }else {
                    Boolean checkUpdate=DB.updateStaffsData(id,name,department,contact);
                    if (checkUpdate){
                        Toast.makeText(UpdateStaffRecordActivity.this, id +" Updated on the database", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(UpdateStaffRecordActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}