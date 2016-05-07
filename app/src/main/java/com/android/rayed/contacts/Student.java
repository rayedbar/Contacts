package com.android.rayed.contacts;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by Rayed on 4/12/2016.
 */
public class Student implements Serializable{

    private int id;
    private final String studentName;
    private final String studentAddress;
    private final String studentPhoneNumber;
    private final String studentEmail;
    private final String studentWebsite;
    private final double studentRating;

    public Student(String studentName, String studentAddress, String studentPhoneNumber, String studentEmail, String studentWebsite, double studentRating) {
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentEmail = studentEmail;
        this.studentWebsite = studentWebsite;
        this.studentRating = studentRating;

    }

    public Student(int id, String studentName, String studentAddress, String studentPhoneNumber, String studentEmail, String studentWebsite, double studentRating) {
        this.id = id;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentEmail = studentEmail;
        this.studentWebsite = studentWebsite;
        this.studentRating = studentRating;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getStudentRating() {
        return (float) studentRating;
    }

    @Override
    public String toString() {
        return id + ". " + studentName;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put("name", studentName);
        values.put("address", studentAddress);
        values.put("phone", studentPhoneNumber);
        values.put("email", studentEmail);
        values.put("website", studentWebsite);
        values.put("rating", studentRating);
        return  values;
    }

    public int getStudentId() {
        return id;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentWebsite() {
        return studentWebsite;
    }
}
