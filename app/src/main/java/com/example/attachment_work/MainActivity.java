package com.example.attachment_work;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.attachment_work.adapter.CustomAdapter;
import com.example.attachment_work.model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText eTUserName, eTEmail, eTPassword, eTPhone;
    ListView listViewStudent;
    ArrayList<Student> arrayList;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTUserName = findViewById(R.id.editTextUsername);
        eTEmail = findViewById(R.id.editTextEmail);
        eTPassword = findViewById(R.id.editTextPassword);
        eTPhone = findViewById(R.id.editTextPhoneNumber);
        listViewStudent = (ListView) findViewById(R.id.listViewStudent);
        arrayList = new ArrayList<Student>();
        adapter = new CustomAdapter(MainActivity.this, arrayList);

        listViewStudent.setAdapter(adapter);

    }

    public void saveData(View view) {
        String name = eTUserName.getText().toString();
        String pwd = eTPassword.getText().toString();
        String email = eTEmail.getText().toString();
        String phone = eTPhone.getText().toString();
        Boolean error = false;

        Student std = new Student();
        std.setName(name);
        std.setPwd(pwd);
        std.setEmail(email);
        std.setPhone(phone);

        if (name.isEmpty()){
            eTUserName.setError("Missing Username!!!");
            error  = true;
        }
        else if (name.length() < 6){
            eTUserName.setError("Username is too short");
            error = true;
        }

        if (pwd.isEmpty()){
            eTPassword.setError("Missing Password!!!");
            error = true;
        }
        else if (pwd.length() < 8){
            eTPassword.setError("Password is too short");
            error = true;
        }

        if (phone.isEmpty()){
            error = true;
            eTPhone.setError("Phone Number is Missing!");
        }
        else if(phone.length() < 11 || phone.length() > 11){
            error = true;
            eTPhone.setError("PhoneNumber should be 11 digit!");
        }
        else if (phone.length()==11){
            if (phone.startsWith("01")){

            }
            else{
                error = true;
                eTPhone.setError("Phone number is not valid!");
            }

        }

        if (error){
            Toast.makeText(MainActivity.this, "Data is not correct!", Toast.LENGTH_LONG).show();
        }
        else{
            arrayList.add(std);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Data entry is Successful!!", Toast.LENGTH_SHORT).show();
//            Toast.makeText(MainActivity.this, std.toString(), Toast.LENGTH_LONG).show();
            eTUserName.setText(null);
            eTEmail.setText(null);
            eTPassword.setText(null);
            eTPhone.setText(null);

        }

    }

    public void clearData(View view) {
        eTUserName.setText(null);
        eTEmail.setText(null);
        eTPassword.setText(null);
        eTPhone.setText(null);
    }


}