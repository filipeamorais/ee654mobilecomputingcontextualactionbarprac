package com.example.contextualactionbarprac;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listV;
    ArrayList<String> friends = new ArrayList<>();
    ArrayAdapter myAdapter;
    EditText newItem;
    MyListener myListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newItem = (EditText) findViewById(R.id.newItem);
        listV = (ListView) findViewById(R.id.list);

        myAdapter = new ArrayAdapter(this,
                android.R.layout. simple_list_item_multiple_choice, friends);
        listV.setAdapter(myAdapter);
        friends.add("Java");
        friends.add("C++");
        friends.add("Python");
        friends.add("Ruby");

        listV.setChoiceMode(
                ListView.CHOICE_MODE_MULTIPLE_MODAL);
        myListener = new MyListener(listV, myAdapter,
                newItem);
        listV.setMultiChoiceModeListener(myListener);
    }

    public void ButtonForddingToList(View v) {
        myListener.addElement(
                newItem.getText().toString());
        newItem.setText("");
    }
}

