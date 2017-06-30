package bottomnav.hitherejoe.com.bottomnavigationsample;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.description;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.os.Build.ID;
import static android.os.Build.VERSION_CODES.N;
import static java.security.AccessController.getContext;
import static java.util.Collections.addAll;

public class AddPet extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<Pet> pet_list;
    static final int REQUEST_CODE = 1;

    static final String LIST_IDENTIFICATIONS = "playerScore";
    EditText Name;
    EditText Breed;
    EditText Owner;
    EditText Born;
    EditText Feeding;
    EditText Weight;
    Switch  Sex;
    Button  button;
    Pet pet;

    static String update = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        Name = (EditText)findViewById(R.id.name_edit_text);
        Breed = (EditText)findViewById(R.id.breed_edit_text);
        Feeding = (EditText)findViewById(R.id.feeding_edit_text);
        Owner  = (EditText)findViewById(R.id.owner_edit_text);
        Born = (EditText)findViewById(R.id.born_edit_text);
        Weight = (EditText)findViewById(R.id.weight_edit_text);
        Sex  = (Switch)findViewById(R.id.sex_switch);
        button = (Button)findViewById(R.id.add_button);


        if(getIntent()!= null)
        {
            if(getIntent().getExtras()!= null)
            {
                if(getIntent().getExtras().get("Update")!= null)
                {
                    String up = getIntent().getExtras().get("Update").toString();
                    if(up.equals("Yes"))
                    {
                        update  = "true";
                    }
                    if(up.equals("No"))
                    {
                        update  = "false";
                    }
                }
            }
        }

        if(update.equals("true"))
        {

            db = new DatabaseHelper(getApplicationContext());
            pet_list =  db.getAllPets();

            int ID = Integer.parseInt(getIntent().getExtras().get("ID").toString());
            Pet pet = db.selectPet(ID);

            Name.setText(pet.getName());
            Breed.setText(pet.getBreed());
            Feeding.setText(pet.getFeeding());
            Owner.setText(pet.getOwner());
            Born.setText(pet.getBorn());
            Weight.setText(pet.getWeight());
            if(pet.getSex().equals("F"))
               Sex.setChecked(true);
            if(pet.getSex().equals("M"))
                Sex.setChecked(false);
            button.setText("Update");


        }

 /*       ImageView img = (ImageView) findViewById(R.id.owner_next);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPet.this, OwnerActivity.class);
                startActivity(intent);
            }
        });

        ImageView age_img = (ImageView) findViewById(R.id.age_next_click);
        age_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPet.this, AgeActivity.class);
                startActivity(intent);
            }
        });

        Button weight_img = (Button) findViewById(R.id.weight_add);
        weight_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Hello world",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddPet.this, WeightActivity.class);
                startActivity(intent);

            }
        });

        ImageView identity_img = (ImageView) findViewById(R.id.identification_next);
        identity_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPet.this, IdentificationActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });*/

       /* ImageView medicine_img = (ImageView) findViewById(R.id.medicine_next);
        medicine_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPet.this, MedificationActivity.class);
                startActivity(intent);
            }
        });


        ImageView vet_img = (ImageView) findViewById(R.id.vet_next);
        vet_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPet.this, VetActivity.class);
                startActivity(intent);
            }
        });*/


        Button add_button = (Button) findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(update.equals("false")) {
                    String petName = Name.getText().toString();
                    String petBreed = Breed.getText().toString();
                    String petOwner = Owner.getText().toString();
                    String petSex;
                    if (Sex.isChecked())
                        petSex = "F";
                    else
                        petSex = "M";
                    String petWeight = Weight.getText().toString();
                    String petBorn = Born.getText().toString();
                    String petFeeding = Feeding.getText().toString();

                    Pet new_pet = new Pet(petName, petBreed, petOwner, petSex, petBorn, petWeight, petFeeding);
                    db = new DatabaseHelper(getApplicationContext());
                    db.createPet(new_pet);
                    Toast.makeText(getApplicationContext(), "Pet added successfully", Toast.LENGTH_SHORT).show();
                }

                if(update.equals("true")) {


                    String petName = Name.getText().toString();
                    String petBreed = Breed.getText().toString();
                    String petOwner = Owner.getText().toString();
                    String petSex;
                    if (Sex.isChecked())
                        petSex = "F";
                    else
                        petSex = "M";
                    String petWeight = Weight.getText().toString();
                    String petBorn = Born.getText().toString();
                    String petFeeding = Feeding.getText().toString();
                    String ID = getIntent().getExtras().get("ID").toString();
                    String petID = ID;


                    Pet updated_pet = new Pet(petID,petName, petBreed, petOwner, petSex, petBorn, petWeight, petFeeding);
                    db = new DatabaseHelper(getApplicationContext());
                    db.updatePet(updated_pet);

                    Toast.makeText(getApplicationContext(), "Pet updated successfully", Toast.LENGTH_SHORT).show();


                }



            }
        });







    }

    @Override
    protected void onResume() {
        super.onResume();


      /*  Intent extras = getIntent();
        if(extras.getStringExtra("Provider") != null)
        {

            //   Identifications = savedInstanceState.getStringArrayList(LIST_IDENTIFICATIONS);
            String provider = extras.getStringExtra("Provider");
            String description = extras.getStringExtra("Description");
            Identifications.add(provider + description);



        }


        Identifications.add("Raza");
        Identifications.add("Raza");
        Identifications.add("Raza");
        ArrayList<String> myList  = new ArrayList<>();
        myList.addAll(Identifications);
        adapter.notifyDataSetChanged();*/

    }
}
