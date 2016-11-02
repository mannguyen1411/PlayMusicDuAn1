package com.example.duan1.playmusicduan1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView btnListMusic;
    ViewPager viewPager;
    CustomSlider customSlider;
    ShareLinkContent content;
    AlertDialog dialog;
    Button button;
    Locale mylocale ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.dropbox.com/s/q13tunnn350dp75/ClastMusic.apk?dl=0"))
                .setImageUrl(Uri.parse("https://s4.postimg.org/5by4e7wot/ic_launcher.png"))
                .setContentTitle("Ứng dụng nghe nhạc Clast Music")
                .setContentDescription("Hãy cài đặt và sử dụng ứng dụng nghe nhạc Clast Music, simple")
                .build();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Dialog dialog = onCreateDialogSingleChoice();

        int id = item.getItemId();

        if (id == R.id.nav_listmusic) {
            // Handle the camera action
        } else if (id == R.id.nav_change_theme) {

        } else if (id == R.id.nav_change_AE) {
            dialog.show();
        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_share) {
            ShareDialog.show(MainActivity.this, content);
        } else if (id == R.id.nav_send) {
            Intent i = new Intent(MainActivity.this, FeedbackActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_about_app) {

        } else if (id == R.id.nav_exit) {
            finish();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.contextmenu_change_language, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.menu_English:
//                break;
//            case R.id.menu_France:
//                break;
//            case R.id.menu_Janpan:
//                break;
//            case R.id.menu_VietNam:
//                break;
//        }
//        return super.onContextItemSelected(item);
//    }

    private void addControls() {
        btnListMusic = (ImageView) findViewById(R.id.btnlistMusic);
        viewPager = (ViewPager) findViewById(R.id.mainSlider);
        customSlider = new CustomSlider(this);
        viewPager.setAdapter(customSlider);
    }

    private void addEvents() {
        btnListMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextListMusic(v);
            }
        });
    }

    public void NextListMusic(View v) {
        Toast.makeText(MainActivity.this, "Load List Music", Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, PlayMusicActivity.class);
        startActivity(i);
    }

    public AlertDialog onCreateDialogSingleChoice() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] array = {"Vietnam", "English", "France", "Japan"};
        AlertDialog.Builder builder1 = builder.setTitle(R.string.changelang).setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Vietnam", Toast.LENGTH_SHORT).show();
                        setLocal("vi");
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
                        setLocal("en");
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "France", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), "Japan", Toast.LENGTH_SHORT).show();
                        break;

                }
            }

        })
                .setPositiveButton(R.string.select, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

    // set information local in device and refresh app
    private void setLocal(String language) {
        mylocale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale =mylocale;
        res.updateConfiguration(conf, dm);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
