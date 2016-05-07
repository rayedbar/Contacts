package com.android.rayed.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rayed on 4/12/2016.
 */
public class StudentDAO extends SQLiteOpenHelper{

    private static final String DB_NAME = "StudentContact";

    public StudentDAO(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE students (" +
                        "id INTEGER PRIMARY KEY, " +
                        "name TEXT, " +
                        "address TEXT, " +
                        "phone TEXT, " +
                        "email TEXT, " +
                        "website TEXT, " +
                        "rating REAL);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Student student) {
        ContentValues values = student.getContentValues();
        SQLiteDatabase database = getWritableDatabase();
        database.insert("students", null, values);

    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM students;", null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String website = cursor.getString(cursor.getColumnIndex("website"));
            double rating = cursor.getDouble(cursor.getColumnIndex("rating"));
            Student student = new Student(id, name, address, phone, email, website, rating);
            students.add(student);
        }
        return students;
    }

    public void removeStudent(Student student) {
        SQLiteDatabase database = getWritableDatabase();
        String[] params = {String.valueOf(student.getStudentId())};
        database.delete("students", "id = ?", params);
        //database.close();
    }


    public void update(Student student, int studentId) {
        ContentValues values = student.getContentValues();
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {studentId + ""};
        db.update("students", values, "id = ?", params);
    }
}
