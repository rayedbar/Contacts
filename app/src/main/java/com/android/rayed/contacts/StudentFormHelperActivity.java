package com.android.rayed.contacts;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by Rayed on 4/12/2016.
 */
public class StudentFormHelperActivity {

    private final Activity activity;

    public StudentFormHelperActivity(Activity activity) {
        this.activity = activity;
    }

    public Student createStudent() {
        return new Student(getStudentName(), getStudentAddress(), getStudentPhoneNumber(), getStudentEmail(),getStudentWebsite(), getStudentRating());
    }

    private double getStudentRating() {
        RatingBar ratingBar = (RatingBar) activity.findViewById(R.id.student_form_rating);
        return ratingBar.getRating();
    }

    private String getStudentName(){
        int field = R.id.student_form_name;
        return getFieldValue(field);
    }

    private String getStudentAddress(){
        int field = R.id.student_form_address;
        return getFieldValue(field);
    }

    private String getStudentPhoneNumber(){
        int field = R.id.student_form_phone;
        return getFieldValue(field);
    }

    private String getStudentEmail(){
        int field = R.id.student_form_email;
        return getFieldValue(field);
    }

    private String getStudentWebsite(){
        int field = R.id.student_form_website;
        return getFieldValue(field);
    }

    private String getFieldValue(int fieldId) {
        EditText field = (EditText) activity.findViewById(fieldId);
        return field.getText().toString();
    }

    public void fillForm(Student student) {
        fill(R.id.student_form_name, student.getStudentName());
        fill(R.id.student_form_address, student.getStudentAddress());
        fill(R.id.student_form_phone, student.getStudentPhoneNumber());
        fill(R.id.student_form_email, student.getStudentEmail());
        fill(R.id.student_form_website, student.getStudentWebsite());
        RatingBar ratingBar = (RatingBar) activity.findViewById(R.id.student_form_rating);
        ratingBar.setRating((float) student.getStudentRating());
    }

    private void fill(int id, String value) {
        EditText editText = (EditText) activity.findViewById(id);
        editText.setText(value);
    }
}
