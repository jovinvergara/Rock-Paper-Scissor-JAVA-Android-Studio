package com.rockpaperscissor;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History extends AppCompatActivity {

    SimpleAdapter ad;
    ListView historyList;
    DBHandler DB;

    Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyList = findViewById(R.id.historyList);
        btnBack = findViewById(R.id.btnGoBack);
        DB = new DBHandler(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main;
                main = new Intent(History.this, MainActivity.class);
                startActivity(main);
            }
        });

        GetList();
    }

    public void GetList() {
        List<Map<String,String>> MyDataList = getlist();
        String[] From = {"txtPlayerHand", "txtBotHand", "txtGameWinner"};
        int[] To = {R.id.txtPlayerHand, R.id.txtBotHand, R.id.txtGameWinner};
        ad = new SimpleAdapter(this, MyDataList, R.layout.listlayout, From, To);
        historyList.setAdapter(ad);
    }

    public List<Map<String,String>> getlist(){
        List<Map<String,String>> data = new ArrayList<>();
        Cursor cursor = DB.getData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                Map<String,String> dtHistory = new HashMap<String, String>();

                String pPick = cursor.getString(1);
                String bPick = cursor.getString(2);
                String gWin = cursor.getString(3);

                dtHistory.put("txtPlayerHand", "Player Pick: " + pPick);
                dtHistory.put("txtBotHand", "Bot Pick: " + bPick);
                dtHistory.put("txtGameWinner", "Game Winner: " + gWin);
                data.add(dtHistory);
            }
        }
        return data;
    }
}
