package bottomnav.hitherejoe.com.bottomnavigationsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * Created by zainabiftikharmalhi on 2017-06-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "animalManager";

    // Table Names
    private static final String TABLE_PET = "pet";
    //private static final String TABLE_PET_WEIGHT = "petWeight";
    //private static final String TABLE_PET_IDENTIFICATION = "petIdentification";


    // Pet Table - column nmaes
    private static final String KEY_PETID = "petID";
    private static final String KEY_NAME = "name";
    private static final String KEY_BREED = "breed";
    private static final String KEY_OWNER = "owner";
    private static final String KEY_SEX = "sex";
    private static final String KEY_BORN = "born";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_FEEDING = "feeding";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_PET = "CREATE TABLE "
            + TABLE_PET + "(" + KEY_PETID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
            + " TEXT," + KEY_BREED + " TEXT," + KEY_OWNER + " TEXT," + KEY_WEIGHT + " TEXT," + KEY_SEX
            + " TEXT," + KEY_BORN + " TEXT," + KEY_FEEDING + " TEXT " + ")";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PET);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PET);
        onCreate(db);
    }

    /*
 * Creating a pet
 */
    public void createPet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pet.getName());
        values.put(KEY_BREED, pet.getBreed());
        values.put(KEY_OWNER,pet.getOwner());
        values.put(KEY_SEX,pet.getSex());
        values.put(KEY_BORN,pet.getBorn());
        values.put(KEY_WEIGHT,pet.getWeight());
        values.put(KEY_FEEDING,pet.getFeeding());

        // insert row
        long pet_id = db.insert(TABLE_PET, null, values);

    }

    /**
     * getting all pets
     * */
    public ArrayList<Pet> getAllPets() {
        ArrayList<Pet> pets = new ArrayList<Pet>();
        String selectQuery = "SELECT  * FROM " + TABLE_PET;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Pet t = new Pet();
                t.setPetID(c.getString((c.getColumnIndex(KEY_PETID))));
                t.setName(c.getString((c.getColumnIndex(KEY_NAME))));
                t.setBreed(c.getString((c.getColumnIndex(KEY_BREED))));
                t.setOwner(c.getString(c.getColumnIndex(KEY_OWNER)));
                t.setSex(c.getString((c.getColumnIndex(KEY_SEX))));
                t.setBorn(c.getString((c.getColumnIndex(KEY_BORN))));
                t.setWeight(c.getString((c.getColumnIndex(KEY_WEIGHT))));
                t.setFeeding(c.getString((c.getColumnIndex(KEY_FEEDING))));

                // adding to tags list
                pets.add(t);
            } while (c.moveToNext());
        }
        return pets;
    }

    public void deletePet(int petID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_PET, KEY_PETID + " = ?",
                new String[] { String.valueOf(petID)});
        db.close();
    }

    public void updatePet(Pet pet)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pet.getName());
        values.put(KEY_BREED, pet.getBreed());
        values.put(KEY_OWNER, pet.getOwner());
        values.put(KEY_SEX, pet.getSex());
        values.put(KEY_WEIGHT, pet.getWeight());
        values.put(KEY_FEEDING, pet.getFeeding());
        values.put(KEY_BORN,pet.getBorn());


        // updating row
        db.update(TABLE_PET, values, KEY_PETID + " = ?",
                new String[] { pet.getPetID() });

    }
    public Pet selectPet(int petID)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PET, new String[] { KEY_PETID,KEY_NAME,
                        KEY_BREED, KEY_OWNER, KEY_SEX,KEY_BORN,KEY_WEIGHT,KEY_FEEDING}, KEY_PETID + "=?",
                new String[] { String.valueOf(petID) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

         Pet pet = new Pet(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                 cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));

        // return pet
        return pet;

    }



}
