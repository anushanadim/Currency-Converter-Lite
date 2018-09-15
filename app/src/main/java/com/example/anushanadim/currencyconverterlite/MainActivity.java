package com.example.anushanadim.currencyconverterlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText value;
    TextView result;
    Spinner from,to;
    Button convert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value=findViewById(R.id.value);
        result=findViewById(R.id.result);
        convert=findViewById(R.id.convert);

        //get the spinner from the xml.
        from = findViewById(R.id.from);
//create a list of items for the spinner.
        String[] items = new String[]{"AFN", "EUR", "ALL","USD","INR"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        from.setAdapter(adapter);

        //get the spinner from the xml.
        to = findViewById(R.id.to);
//create a list of items for the spinner.
        items = new String[]{"AFN", "EUR", "ALL","USD","INR"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        to.setAdapter(adapter2);

        convert.setOnClickListener(new View.OnClickListener() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://currency-exchange.p.mashape.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            @Override
            public void onClick(View v) {
                String val2 = value.getText().toString();
                String zero="";
                if (val2.compareTo(zero) ==0) {
                    val2="0";
                }
                    final String val = val2;
                    CurrencyService service = retrofit.create(CurrencyService.class);
                    String fromvalue = from.getSelectedItem().toString();
                    String tovalue = to.getSelectedItem().toString();
                    Call<String> stringCall = service.Coin("Fm7BtZLe9EmshYcc4HDw7LPN7Ofsp1iZ2OEjsnt5YozbekWyfx", fromvalue, val, tovalue);
                    stringCall.enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String responsetring = response.body();
                            Float result1 = Float.valueOf(responsetring);
                            Float result2 = Float.valueOf(val);
                            Float DisplayResult = result1 * result2;
                            String S = Float.toString(DisplayResult);

                            result.setText(S);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            result.setText("Try Again");
                        }
                    });

                }

        });

    }
}
