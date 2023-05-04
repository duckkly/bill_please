package sg.edu.rp.c346.id22011765.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalbill;
    TextView eachpays;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup rg;
    RadioButton paynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount = findViewById(R.id.editTextAmount);
        pax = findViewById(R.id.editTextPax);
        svs = findViewById(R.id.svstgbn);
        gst = findViewById(R.id.gsttgbn);
        totalbill = findViewById(R.id.textViewTotalBill);
        eachpays = findViewById(R.id.textViewEachPays);
        split = findViewById(R.id.buttonSplit);
        reset = findViewById(R.id.buttonReset);
        discount = findViewById(R.id.editTextDiscount);
        rg = findViewById(R.id.rg);
        paynow = findViewById(R.id.radioButtonPayNow);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                pax.setText("");
                svs.setChecked(true);
                gst.setChecked(true);
                discount.setText("");
                rg.check(R.id.radioButtonCash);
                totalbill.setText("Total Bill: ");
                eachpays.setText("Each Pays: ");
                paynow.setChecked(true);
            }
        });

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()!=0 && pax.getText().toString().trim().length()!=0) {
                    double originalamt = Double.parseDouble(amount.getText().toString());
                    double newamt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newamt = originalamt;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newamt = originalamt * 1.07;
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newamt = originalamt * 1.1;
                    } else {
                        newamt = originalamt * 1.17;
                    }


                    if (discount.getText().toString().trim().length() != 0) {
                        newamt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }
                    String payment = " in cash";
                    if (rg.getCheckedRadioButtonId() == R.id.radioButtonPayNow) {
                        payment = " PayNow to 87881564";
                    }
                    totalbill.setText("Total Bill: $" + String.format("%.2f", newamt));
                    int numpax = Integer.parseInt(pax.getText().toString());
                    if (numpax != 1)
                        eachpays.setText("Each Pays $" + String.format("%.2f", newamt / numpax) + payment);
                    else
                        eachpays.setText("Each Pays $" + newamt + payment);
                }
            }
        });
    }
}