package com.example.sqlite_maytinh;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Database database;

    ListView lvcomputer;
    ArrayList<computer> arraycomputer;
    computerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvcomputer = (ListView) findViewById(R.id.listviewcomputer);
        arraycomputer = new ArrayList<>();
        adapter = new computerAdapter(this, R.layout.dong_computer, arraycomputer);
        lvcomputer.setAdapter(adapter);
        //tao database
        database = new Database(this, "ghichu.sqlite", null, 1);
        //tao bang computer
        database.QueryData("CREATE TABLE IF NOT EXISTS computer(Id INTERGER PRIMARY KEY AUTOINCREMENT, Tencomputer VARCHAR(200))");

        //insert data
        //database.QueryData("INSERT INTO computer VALUES(null,'Laptop')");
        GetDatacomputer();
    }
    public void DialogXoacomputer(String tencomputer,final int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa "+ tencomputer +" không");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.QueryData("DELETE FROM computer WHERE Id ='"+ id +"'");
                Toast.makeText(MainActivity.this, "Đã xóa" + tencomputer, Toast.LENGTH_SHORT).show();
                GetDatacomputer();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogXoa.show();
    }
    public void DialogSuacomputer(String ten, int id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);

        EditText edtTencomputer = (EditText) dialog.findViewById(R.id.editTextTencomputerEdit);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.buttonXacnhan);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuyEdit);

        edtTencomputer.setText(ten);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenMoi = edtTencomputer.getText().toString().trim();
                database.QueryData("UPDATE computer SET Tencomputer ='"+ tenMoi + "'WHERE Id '"+ id +"' ");
                Toast.makeText(MainActivity.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDatacomputer();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private  void GetDatacomputer(){
    Cursor datacomputer = database.GetData("SELECT * FROM computer");
    arraycomputer.clear();
    while (datacomputer.moveToNext()) {
        String ten = datacomputer.getString(1);
        int id = datacomputer.getInt(0);
        arraycomputer.add(new computer(id, ten));

    }
    adapter.notifyDataSetChanged();
}

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        getMenuInflater().inflate(R.menu.add_computer,menu);
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuAdd){
            DiaLogThem();
        }
        return super.onOptionsItemSelected(item);
    }
    private  void DiaLogThem(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.diaglog_them_computer);

        EditText edtTen =(EditText) dialog.findViewById(R.id.editTextTencomputer);
        Button btnThem = (Button) dialog.findViewById(R.id.buttonThem);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tencv = edtTen.getText().toString();
                if(tencv.equals("")){
                    Toast.makeText(MainActivity.this,"vui lòng nhập",Toast.LENGTH_SHORT);

                }else {
                    database.QueryData("INSERT INTO computer VALUES(null,'"+ tencv +"')");
                    Toast.makeText(MainActivity.this,"Đã thêm",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDatacomputer();
                }
            }
        });


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}