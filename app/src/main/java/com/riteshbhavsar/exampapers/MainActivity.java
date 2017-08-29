package com.riteshbhavsar.exampapers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.riteshbhavsar.exampapers.model.AppDatabase;
import com.riteshbhavsar.exampapers.model.Branch;
import com.riteshbhavsar.exampapers.model.Course;
import com.riteshbhavsar.exampapers.model.University;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //database reference
    private FirebaseDatabase mDatabase;
    DatabaseReference myUniversity, myBranch, myCourse;


    EditText edt_uni, edt_branch, edt_course;
    Button btn_add;
    AppDatabase mdb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_uni = (EditText) findViewById(R.id.edt_uni);
        edt_branch = (EditText) findViewById(R.id.edt_branch);
        edt_course = (EditText) findViewById(R.id.edt_course);
        btn_add = (Button) findViewById(R.id.btn_add);

        mDatabase = FirebaseDatabase.getInstance();
        myUniversity = mDatabase.getReference(Constants.DATABASE_PATH_UPLOADS_UNIVERSITY);
        myBranch = mDatabase.getReference(Constants.DATABASE_PATH_UPLOADS_BRANCH);
        myCourse = mDatabase.getReference(Constants.DATABASE_PATH_UPLOADS_COURSE);

        mdb = AppDatabase.getAppDatabase(getApplication());

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_uni.getText()!=null){
                    if(!edt_uni.getText().toString().isEmpty()){
                        //store in DB
                        List<University> lst_uni = mdb.universityDao().getAllUniversities();
                        boolean flag= true;
                        for (University university : lst_uni) {
                            if(university.getUni_name().equalsIgnoreCase(edt_uni.getText().toString())){
                                flag=false;
                                break;
                            }
                        }
                        if(flag) {
                            University uni = new University();
                            uni.setUni_name(edt_uni.getText().toString());
                            uni.setUni_id(edt_uni.getText().toString());
                            mdb.universityDao().insertUniversity(uni);

                            //update to firebase DB
                            myUniversity.child(uni.getUni_id()).setValue(uni);
                        }else{
                            edt_uni.setError("already exists in DB");
                        }
                    }

                }
                if(edt_branch.getText()!=null){
                    if(!edt_branch.getText().toString().isEmpty()){
                        //store in DB
                        List<Branch> lst_uni = mdb.branchDao().getAllBranches();
                        boolean flag= true;
                        for (Branch university : lst_uni) {
                            if(university.getBr_name().equalsIgnoreCase(edt_branch.getText().toString())){
                                flag=false;
                                break;
                            }
                        }
                        if(flag) {

                            Branch uni = new Branch();
                        uni.setBr_name(edt_branch.getText().toString());
                        uni.setBr_id(edt_branch.getText().toString());
                        mdb.branchDao().insertBranch(uni);
                        //update to firebase DB
                            myBranch.child(uni.getBr_id()).setValue(uni, new DatabaseReference.CompletionListener() {

                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if (databaseError != null) {
                                        Toast.makeText(MainActivity.this, "Data could not be saved. " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            edt_branch.setError("already exists in DB");
                        }

                    }

                }

                if(edt_course.getText()!=null){
                    if(!edt_course.getText().toString().isEmpty()){
                        //store in DB
                        List<Course> lst_uni = mdb.courseDao().getAllCourses();
                        boolean flag= true;
                        for (Course university : lst_uni) {
                            if(university.getCo_name().equalsIgnoreCase(edt_course.getText().toString())){
                                flag=false;
                                break;
                            }
                        }
                        if(flag) {
                            Course uni = new Course();
                        uni.setCo_name(edt_course.getText().toString());
                        uni.setCo_id(edt_course.getText().toString());
                        mdb.courseDao().insertCourse(uni);
                        //update to firebase DB
                            myCourse.child(uni.getCo_id()).setValue(uni);
                        }else{
                            edt_course.setError("already exists in DB");
                        }
                    }
                }
            }
        });

        //adding an event listener to fetch values
        myBranch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
//                progressDialog.dismiss();
                LinkedList<Branch> live_branches = new LinkedList<Branch>();
                List<Branch> db_branches = new ArrayList<Branch>();

                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Branch upload = postSnapshot.getValue(Branch.class);
                    live_branches.add(upload);
                }

                db_branches = mdb.branchDao().getAllBranches();
                boolean flag= true;
                for (Branch live_branch : live_branches) {
                    flag= true;
                    for (Branch university : db_branches) {
                        if(university.getBr_name().equalsIgnoreCase(live_branch.getBr_name())){
                            flag=false;
                            break;
                        }
                    }
                    if(flag){
                        //add to db
                        mdb.branchDao().insertBranch(live_branch);
                    }
                }

                //creating adapter
//                adapter = new MyAdapter(getApplicationContext(), uploads);

                //adding adapter to recyclerview
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                progressDialog.dismiss();
            }
        });

        myUniversity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
//                progressDialog.dismiss();
                LinkedList<University> live_branches = new LinkedList<University>();
                List<University> db_branches = new ArrayList<University>();

                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    University upload = postSnapshot.getValue(University.class);
                    live_branches.add(upload);
                }

                db_branches = mdb.universityDao().getAllUniversities();
                boolean flag= true;
                for (University live_branch : live_branches) {
                    flag= true;
                    for (University university : db_branches) {
                        if(university.getUni_name().equalsIgnoreCase(live_branch.getUni_name())){
                            flag=false;
                            break;
                        }
                    }
                    if(flag){
                        //add to db
                        mdb.universityDao().insertUniversity(live_branch);
                    }
                }

                //creating adapter
//                adapter = new MyAdapter(getApplicationContext(), uploads);

                //adding adapter to recyclerview
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                progressDialog.dismiss();
            }
        });

        myCourse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
//                progressDialog.dismiss();
                LinkedList<Course> live_branches = new LinkedList<Course>();
                List<Course> db_branches = new ArrayList<Course>();

                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Course upload = postSnapshot.getValue(Course.class);
                    live_branches.add(upload);
                }

                db_branches = mdb.courseDao().getAllCourses();
                boolean flag= true;
                for (Course live_branch : live_branches) {
                    flag= true;
                    for (Course university : db_branches) {
                        if(university.getCo_name().equalsIgnoreCase(live_branch.getCo_name())){
                            flag=false;
                            break;
                        }
                    }
                    if(flag){
                        //add to db
                        mdb.courseDao().insertCourse(live_branch);
                    }
                }

                //creating adapter
//                adapter = new MyAdapter(getApplicationContext(), uploads);

                //adding adapter to recyclerview
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                progressDialog.dismiss();
            }
        });

    }

}
