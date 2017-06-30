package bottomnav.hitherejoe.com.bottomnavigationsample;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textFavorites;
    private TextView textSchedules;
    private TextView textMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textFavorites = (TextView) findViewById(R.id.text_favorites);
        textSchedules = (TextView) findViewById(R.id.text_schedules);
        textMusic = (TextView) findViewById(R.id.text_music);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    Fragment selectedFragment = null;
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_pet:
                                selectedFragment = ItemOneFragment.newInstance();
                                //textFavorites.setVisibility(View.VISIBLE);
                                //textSchedules.setVisibility(View.GONE);
                                //textMusic.setVisibility(View.GONE);
                                break;
                            case R.id.action_reminder:
                                selectedFragment = ItemTwoFragment.newInstance();
                                //textFavorites.setVisibility(View.GONE);
                                //textSchedules.setVisibility(View.VISIBLE);
                                //textMusic.setVisibility(View.GONE);
                                break;
                            case R.id.action_location:
                                textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);
                                break;
                        }

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
