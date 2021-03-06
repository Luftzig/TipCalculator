package il.ac.huji.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity implements OnClickListener {

    private boolean roundTo = false;
    private EditText input;
    private TextView tip;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        input = (EditText) findViewById(R.id.edtBillAmount);
        tip = (TextView) findViewById(R.id.txtTipResult);
        Button calc = (Button) findViewById(R.id.btnCalculate);
        checkBox = (CheckBox) findViewById(R.id.chkRound);
        checkBox.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    roundTo = true;
                } else {
                    roundTo = false;
                }
            }

        });
        calc.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calculator, menu);
        return true;
    }

    @Override
    public void onClick(View arg0) {
        String bill = input.getText().toString();
        if (bill.isEmpty()) {
            return;
        }
        double billValue = Float.parseFloat(bill);
        double tipValue = billValue * 12 / 100;
        String formatted = null;
        if (roundTo) {
            tipValue = (double) Math.ceil(tipValue);
            formatted = new DecimalFormat("0").format(tipValue);
        } else {
            formatted = new DecimalFormat("0.00").format(tipValue);
        }
        String tipText = getString(R.string.text_tip) + formatted;
        tip.setText(tipText);
    }
}
