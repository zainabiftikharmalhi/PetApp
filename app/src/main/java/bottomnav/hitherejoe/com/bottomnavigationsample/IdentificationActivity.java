package bottomnav.hitherejoe.com.bottomnavigationsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.data;
import static android.R.attr.descendantFocusability;
import static android.R.attr.preferenceCategoryStyle;

public class IdentificationActivity extends AppCompatActivity {

    EditText provider;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        provider = (EditText)findViewById(R.id.provider_edittext);
        description =(EditText)findViewById(R.id.description_edittext);

        ListView Identifications_List;
        final CustomAdapter adapter;
        final ArrayList<String> Identifications = new ArrayList<>();
        Identifications_List = (ListView)findViewById(R.id.identification_ListView);
        adapter = new CustomAdapter(getApplicationContext(),0,Identifications);
        Identifications_List.setAdapter(adapter);




        TextView save_textview = (TextView) findViewById(R.id.save_textview);
        save_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Identification is saved",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(IdentificationActivity.this, AddPet.class);
                Bundle bundle = new Bundle();
                bundle.putString("Provider", provider.getText().toString());
                bundle.putString("Description", description.getText().toString());
                intent.putExtras(bundle);
               // Identifications.add(provider.getText().toString() + " " + description.getText().toString());
                 Identifications.add("Zainab");

                adapter.notifyDataSetChanged();
                startActivity(intent);


            }
        });

    }


}
