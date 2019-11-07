package com.example.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView listView;
    TextView totalRecords;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Home");

        search = findViewById(R.id.searchView);
        search.setOnQueryTextListener(this);
        totalRecords = findViewById(R.id.textViewTotalRecords);
        listView = findViewById(R.id.listViewCustomers);
        refreshListView(Storage.getAllCustomers());
        registerForContextMenu(listView);
    }

    private void refreshListView(List<Customer> customers) {
        totalRecords.setText("Total records: " + customers.size());
        CustomListViewAdapter adapter = new CustomListViewAdapter(customers, this, R.layout.layout_listview);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemClearAll:
                Storage.clear();
                refreshListView(Storage.getAllCustomers());
                break;
            case R.id.menuItemAdd:
                startActivity(new Intent(this.getApplicationContext(), AddActivity.class));
                this.finish();
                break;
            case R.id.menuItemRefresh:
                Storage.fakeData();
                refreshListView(Storage.getAllCustomers());
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Select The Action");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Customer customer = (Customer) listView.getAdapter().getItem(position);

        if (item.getItemId() == R.id.menuItemEdit) {
            Intent intent = new Intent(this.getApplicationContext(), EditActivity.class);
            intent.putExtra("customer", customer);
            startActivity(intent);
            this.finish();
        } else if (item.getItemId() == R.id.menuItemDelete) {
            Storage.delete(customer);
            refreshListView(Storage.getAllCustomers());
        }
        return true;
    }

    List<Customer> doSearch(String text) {
        return Storage.getAllCustomers()
                .stream()
                .filter(t -> t.getName().toUpperCase().contains(text.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        refreshListView(doSearch(query));
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
