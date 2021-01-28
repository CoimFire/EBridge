package com.sxc.ebridge.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sxc.ebridge.R;
import com.sxc.ebridge.adapters.StudentsAdapter;
import com.sxc.ebridge.database.StrudentsDBHelper;

public class ViewAllStudentsActivity extends AppCompatActivity {

    RecyclerView lvStudentList;

    StrudentsDBHelper DB;
    StudentsAdapter studentsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_students);

        lvStudentList=findViewById(R.id.lv_students_list);
        DB=new StrudentsDBHelper(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        // Set the layout manager to your recyclerview
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lvStudentList.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(lvStudentList.getContext(),mLayoutManager.getOrientation());
        lvStudentList.addItemDecoration(dividerItemDecoration);



        studentsAdapter = new StudentsAdapter(DB.getData(),this);
        lvStudentList.setAdapter(studentsAdapter);


    }


}