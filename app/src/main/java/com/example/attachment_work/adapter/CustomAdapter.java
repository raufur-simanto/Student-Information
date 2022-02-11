package com.example.attachment_work.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attachment_work.MainActivity;
import com.example.attachment_work.R;
import com.example.attachment_work.model.Student;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Student> arrayList;

    public CustomAdapter(Context context, ArrayList<Student> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View custom_layout = inflater.inflate(R.layout.item_layout, null);
        TextView tvUserName = custom_layout.findViewById(R.id.textViewUsername);
        TextView tvPhone = custom_layout.findViewById(R.id.textViewPhone);
        ImageView callIcon = custom_layout.findViewById(R.id.imageViewPhone);

        tvUserName.setText(arrayList.get(position).getName());
        tvPhone.setText(arrayList.get(position).getPhone());
        callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String uri = arrayList.get(position).getPhone();
                intent.setData(Uri.parse("tel:"+uri));
                context.startActivity(intent);
            }
        });
        custom_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String student = "Username: "+ arrayList.get(position).getName()+ "\n"
                        +"Email: "+ arrayList.get(position).getEmail()+"\n"
                        +"PhoneNumber : "+arrayList.get(position).getPhone();

//                Toast.makeText(context, student, Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(context)
                        .setTitle("Student Information")
                        .setMessage(student)
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                            }
                        }).show();

            }
        });


        return custom_layout;

    }

}
