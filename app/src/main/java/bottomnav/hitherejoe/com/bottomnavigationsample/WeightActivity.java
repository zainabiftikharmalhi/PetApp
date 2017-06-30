package bottomnav.hitherejoe.com.bottomnavigationsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import static android.R.attr.description;

public class WeightActivity extends AppCompatActivity {

    EditText date;
    EditText weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
    }
}
