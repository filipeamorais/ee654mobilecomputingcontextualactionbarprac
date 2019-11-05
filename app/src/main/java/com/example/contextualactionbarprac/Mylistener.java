package com.example.contextualactionbarprac;

import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

class MyListener implements
        AbsListView.MultiChoiceModeListener {
    ListView listV;
    ArrayAdapter myAdapter;
    EditText newItem;

    protected MyListener(ListView lv,
                         ArrayAdapter myAdapter, EditText newItem) {
        listV = lv;
        this.myAdapter = myAdapter;
        this.newItem = newItem;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode,
                                          int position, long id, boolean checked) {
        mode.setTitle(listV.getCheckedItemCount() +
                " selected items");
    }
    @Override
    public boolean onCreateActionMode(ActionMode mode,
                                      Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode,
                                       Menu menu) {
        //to refresh an action mode's action menu
        //whenever it is invalidated
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        //Called when an action mode is about to be
        //exited and destroyed
    }
    @Override
    public boolean onActionItemClicked(ActionMode mode,
                                       MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete:
                removeElements();
                mode.finish();
                return true;
            case R.id.add:
                String txt = newItem.getText().toString();
                if(!txt.equals(""))
                    addElement(newItem.getText().toString());
                mode.finish();
                return true;
        }
        return false;
    }

    protected void removeElements() {
        SparseBooleanArray checked =
                listV.getCheckedItemPositions();
        for(int i=checked.size()-1; i>=0; i--){
            if(checked.valueAt(i)){
                String selectedFriend = (String) listV
                        .getItemAtPosition(checked.keyAt(i));
                myAdapter.remove(listV.
                        getItemAtPosition(checked.keyAt(i)));
            }
        }
    }
    protected void addElement(String str) {
        if((str != null &&
                !str.replaceAll("\\s", "").equals("")))
            myAdapter.add(str);
    }
}

