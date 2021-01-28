package com.sxc.ebridge.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sxc.ebridge.R;
import com.sxc.ebridge.adapters.StaffAdapter;
import com.sxc.ebridge.adapters.StudentsAdapter;
import com.sxc.ebridge.database.StaffDBHelper;
import com.sxc.ebridge.database.StrudentsDBHelper;

public class ViewAllStaffActivity extends AppCompatActivity {

    RecyclerView rvStaffList;

    StaffDBHelper DB;
    StaffAdapter staffAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_staff);


        rvStaffList=findViewById(R.id.lv_students_list);
        DB=new StaffDBHelper(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        // Set the layout manager to your recyclerview
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvStaffList.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(rvStaffList.getContext(),mLayoutManager.getOrientation());
        rvStaffList.addItemDecoration(dividerItemDecoration);



        staffAdapter = new StaffAdapter(DB.getData(),this);
        rvStaffList.setAdapter(staffAdapter);

    }
}