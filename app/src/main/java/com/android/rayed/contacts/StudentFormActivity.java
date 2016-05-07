package com.android.rayed.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class StudentFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        if (hasUpdateIntent()){
            Student student = getOriginalStudent();
            StudentFormHelperActivity helperActivity = new StudentFormHelperActivity(this);
            helperActivity.fillForm(student);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_student_form, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_student_form_done:
                StudentFormHelperActivity helper = new StudentFormHelperActivity(this);
                Student student = helper.createStudent();
                // Check Fields
                // Save Student to Database
                StudentDAO studentDAO = new StudentDAO(this);

                if(hasUpdateIntent()){
                    studentDAO.update(student, getOriginalStudent().getStudentId());
                    Toast.makeText(StudentFormActivity.this, "Old Student Updated", Toast.LENGTH_SHORT).show();
                } else {
                    studentDAO.insert(student);
                    Toast.makeText(StudentFormActivity.this, "New Student Created", Toast.LENGTH_SHORT).show();
                }
                studentDAO.close();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Student getOriginalStudent() {
        return (Student) getIntent().getSerializableExtra("student");
    }

    public boolean hasUpdateIntent() {
        return getIntent().hasExtra("student");
    }
}
//                if (student.getStudentName().isEmpty()){
//                    Toast.makeText(StudentFormActivity.this, "Enter A Name First", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(StudentFormActivity.this, student.getStudentName() + " was created", Toast.LENGTH_SHORT).show();
//                }