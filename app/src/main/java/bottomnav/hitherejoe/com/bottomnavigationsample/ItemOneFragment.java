package bottomnav.hitherejoe.com.bottomnavigationsample;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.media.CamcorderProfile.get;
import static android.os.Build.VERSION_CODES.M;
import static android.os.Build.VERSION_CODES.N;
import static bottomnav.hitherejoe.com.bottomnavigationsample.R.id.fab;
import static bottomnav.hitherejoe.com.bottomnavigationsample.R.id.text_music;
import static bottomnav.hitherejoe.com.bottomnavigationsample.R.id.up;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemOneFragment extends Fragment {

    DatabaseHelper db;
    ArrayList<Pet> pet_list;
    ListView petsListView;
    PetAdapter adapter;
    final static int REQUEST_CODE = 1;
    static int petCount = 0;
    static int petIDupdated = -1;
    static int listId = -1;

    // ArrayList<String> arr = new ArrayList<>();

    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddPet.class);
                Bundle bundle = new Bundle();
                bundle.putString("Update", "No");
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });


        db = new DatabaseHelper(getContext());
        pet_list = db.getAllPets();
        petCount = pet_list.size();
        petsListView = (ListView) view.findViewById(R.id.list_of_pets);
        db.close();

        petsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getActivity(), "ListView Clicked", Toast.LENGTH_LONG).show();

                final int index = i;
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Press the action to proceed");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Bundle bundle = new Bundle();
                                Intent intent = new Intent(getActivity(), AddPet.class);
                                bundle.putString("Update", "Yes");

                                Pet pet = pet_list.get(index);
                                bundle.putString("ID", pet.getPetID());

                                petIDupdated = Integer.parseInt(pet.getPetID());
                                listId = index;


                                intent.putExtras(bundle);
                                startActivity(intent);

                            }
                        });

                builder1.setNegativeButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Pet pet = pet_list.get(index);
                                db.deletePet(Integer.parseInt(pet.getPetID()));
                                pet_list.remove(index);
                                //adapter.notifyDataSetChanged();
                                petCount = petCount - 1;

                                adapter.notifyDataSetChanged();
                                petsListView.invalidateViews();
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        adapter = null;
        //arr.add("Zainab");
        //arr.add("Raza");
        adapter = new PetAdapter(getContext(), pet_list);
        //adapter = new CustomAdapter(getContext(),0,arr);
        petsListView.setAdapter(adapter);
        db.close();
        adapter.notifyDataSetChanged();


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<Pet> temp_list;

        db = new DatabaseHelper(getContext());
        temp_list = db.getAllPets();
        if (petCount != temp_list.size()) {
            if (petCount + 1 == temp_list.size()) {
                Pet pet = temp_list.get(temp_list.size() - 1);
                pet_list.add(pet);
                petCount = petCount + 1;

            }
        }


        //Toast.makeText(getActivity(), "onActivity Result", Toast.LENGTH_LONG).show();

        adapter.notifyDataSetChanged();
        petsListView.invalidateViews();


    }

    @Override
    public void onResume() {
        super.onResume();

        Toast.makeText(getActivity(), "onResume", Toast.LENGTH_LONG).show();
       if (listId != -1 && petIDupdated != -1) {


            db = new DatabaseHelper(getContext());

            Pet old = pet_list.get(listId);
            Pet updated = db.selectPet(petIDupdated);

            old.setName(updated.getName());

            adapter.notifyDataSetChanged();
            petsListView.invalidateViews();


        }
    }

}
