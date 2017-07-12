package com.example.android.retrofitpostexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MonthlyCombo extends AppCompatActivity {

    TextView pack,price;
    Button button;
    ImageView monthlycombo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_combo);

        pack = (TextView)findViewById(R.id.monthly_combo_pack);
        //price = (TextView )findViewById(R.id.monthly_combo_pack_price);
        monthlycombo=(ImageView)findViewById(R.id.monthly_combo_image);

        button = (Button)findViewById(R.id.monnthly_combo_place_order);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }
}
