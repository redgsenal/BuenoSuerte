package senal.com.buenosuerte;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    Button btnLuckPick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void pickNumbers(View v){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        final EditText numPicks = (EditText) findViewById(R.id.txtNumPicks);
        final EditText minValue = (EditText) findViewById(R.id.txtMinValue);
        final EditText maxValue = (EditText) findViewById(R.id.txtMaxValue);
        intent.putExtra("numPicks", numPicks.getText().toString());
        intent.putExtra("minValue", minValue.getText().toString());
        intent.putExtra("maxValue", maxValue.getText().toString());
        startActivity(intent);
    }
}