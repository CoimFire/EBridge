package com.sxc.ebridge.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sxc.ebridge.R;
import com.sxc.ebridge.database.StrudentsDBHelper;
import static com.sxc.ebridge.utils.Utils.openAnotherActivity;

public class AdminHomeActivity extends AppCompatActivity {

    TextView tvNewStudentRecord,tvUpdateStudentRecord, tvViewAllstudentdRecords,
            tvNewStaffRecord,tvUpdateStaffecord,tvViewAllStaffsRecords;
    StrudentsDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        tvNewStudentRecord=findViewById(R.id.tv_new_student_record);
        tvUpdateStudentRecord=findViewById(R.id.tv_update_student_record);
        tvViewAllstudentdRecords =findViewById(R.id.tv_view_all_students);

        tvNewStaffRecord=findViewById(R.id.tv_add_staff_record);
        tvUpdateStaffecord=findViewById(R.id.tv_update_staff_record);
        tvViewAllStaffsRecords =findViewById(R.id.tv_view_all_staff);

        DB=new StrudentsDBHelper(this);
        tvNewStudentRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnotherActivity(AdminHomeActivity.this, AddStudentRecordActivity.class);
            }
        });

        tvUpdateStudentRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnotherActivity(AdminHomeActivity.this, UpdateStudentRecordActivity.class);
            }
        });

        tvViewAllstudentdRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnotherActivity(AdminHomeActivity.this, ViewAllStudentsActivity.class);
            }
        });


        tvNewStaffRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnotherActivity(AdminHomeActivity.this, AddStaffRecordActivity.class);
            }
        });

        tvUpdateStaffecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnotherActivity(AdminHomeActivity.this, UpdateStaffRecordActivity.class);
            }
        });

        tvViewAllStaffsRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnotherActivity(AdminHomeActivity.this, ViewAllStaffActivity.class);
            }
        });

    }

}