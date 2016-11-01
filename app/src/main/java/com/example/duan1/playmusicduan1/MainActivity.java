package com.example.duan1.playmusicduan1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView btnListMusic;

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

        ShareButton shareButton = (ShareButton) findViewById(R.id.share_button);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.dropbox.com/s/q13tunnn350dp75/ClastMusic.apk?dl=0"))
                .setImageUrl(Uri.parse("https://s4.postimg.org/5by4e7wot/ic_launcher.png"))
                .setContentTitle("Ứng dụng nghe nhạc Clast Music")
                .setContentDescription("Hãy cài đặt và sử dụng ứng dụng nghe nhạc Clast Music, simple")
                .build();
        shareButton.setShareContent(content);
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
        int id = item.getItemId();

        if (id == R.id.nav_listmusic) {
            // Handle the camera action
        } else if (id == R.id.nav_change_theme) {

        } else if (id == R.id.nav_change_AE) {


        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_about_app) {

        } else if (id == R.id.nav_exit) {
            finish();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu_change_language, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int idcontext = item.getItemId();

        if(idcontext == R.id.menu_English){

        }else  if( idcontext == R.id.menu_VietNam){

        }else if(idcontext == R.id.menu_Janpan){

        }else if(idcontext == R.id.menu_France){

        }
        return super.onContextItemSelected(item);
    }

    private void addControls() {
        btnListMusic = (ImageView) findViewById(R.id.btnlistMusic);
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

}
