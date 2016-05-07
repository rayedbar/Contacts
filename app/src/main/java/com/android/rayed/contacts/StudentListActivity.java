package com.android.rayed.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import javax.crypto.spec.PSource;

public class StudentListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        getStudentList().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(StudentListActivity.this, "Student Short Press", Toast.LENGTH_SHORT).show();
                Student student = (Student) parent.getItemAtPosition(position);
                Intent intent = new Intent(StudentListActivity.this, StudentFormActivity.class);



                intent.putExtra("student", student);
                startActivity(intent);

            }
        });

        registerForContextMenu(getStudentList());
    }

    private ListView getStudentList() {
        return (ListView) findViewById(R.id.student_list_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.equals(getStudentList())){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Student student = (Student) getStudentList().getItemAtPosition(info.position);
            showContextMenuForRemove(menu, student);
        }
    }

    private void showContextMenuForRemove(ContextMenu menu, final Student student) {
        MenuItem remove = menu.add("Remove");
        remove.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                StudentDAO dao = new StudentDAO(StudentListActivity.this);
                dao.removeStudent(student);
                dao.close();
                Toast.makeText(StudentListActivity.this, "Removing ... " + student.getStudentName(), Toast.LENGTH_SHORT).show();
                loadStudents();
                return true;
            }
        });
    }

    private void loadStudents() {
        // Load Students from Database
        StudentDAO dao = new StudentDAO(this);
        List<Student> students = dao.getAllStudents();
        dao.close();

        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, R.layout.student_list_item, students);
        getStudentList().setAdapter(adapter);
    }

    public void addStudent(View view) {
        //Toast.makeText(this, "Add Created", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, StudentFormActivity.class);
        startActivity(intent);
    }
}
