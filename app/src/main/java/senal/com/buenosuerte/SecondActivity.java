package senal.com.buenosuerte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView txtnumPicks = (TextView) findViewById(R.id.numPicks);
        TextView txtMinValue = (TextView) findViewById(R.id.minValue);
        TextView txtMaxValue = (TextView) findViewById(R.id.maxValue);
        TextView txtResult= (TextView) findViewById(R.id.result);

        String strNumPicks = getIntValue("numPicks");
        String strMinValue = getIntValue("minValue");
        String strMaxValue = getIntValue("maxValue");

        txtnumPicks.setText(strNumPicks);
        txtMinValue.setText(strMinValue);
        txtMaxValue.setText(strMaxValue);

        int numPicks = parseValue(strNumPicks);
        int minValue = parseValue(strMinValue);
        int maxValue = parseValue(strMaxValue);

        txtResult.setText(genResult(numPicks, minValue, maxValue));
    }

    private String genResult(int numPicks, int minValue, int maxValue){
        StringBuilder errors = new StringBuilder();
        if (numPicks <= 0)
            errors.append("Invalid number of picks. ");
        if (maxValue <= minValue)
            errors.append("Maximum value must be > minimum value. ");
        if (maxValue <= 1)
            errors.append("Maximum value must be > 1 ");
        if (minValue < 1)
            errors.append("Minimum value must be >= 1 ");
        if (errors.length() > 0){
            return errors.toString();
        }else{
            ArrayList<Integer> r = new ArrayList<>();
            SecureRandom rand = null;
            byte[] randomBytes = new byte[128];
            try {
                rand = SecureRandom.getInstance("SHA1PRNG");
                rand.nextBytes(randomBytes);
            } catch (NoSuchAlgorithmException e) {
                //e.printStackTrace();
            }

            for(int c = 0; c < numPicks; ++c){
                boolean next = true;
                int v = 0;
                // no duplicates
                while(next){
                    try {
                        v = rand.nextInt(maxValue) + minValue;
                    }catch (NullPointerException errnull){
                        // skip error
                    }
                    next = r.contains(v);
                }
                r.add(v);
            }
            Collections.sort(r);
            return r.toString();
        }
    }

    private String getIntValue(String varName){
        String v = getIntent().getExtras().getString(varName);
        if (v == null || v.isEmpty())
            return "0";
        return v;
    }

    private int parseValue(String v){
        try{
            return Integer.parseInt(v);
        }catch (NumberFormatException nfe){
            return 0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
