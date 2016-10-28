package com.example.duan1.playmusicduan1;

import android.content.Intent;
import android.os.Environment;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayMusicActivity extends AppCompatActivity {
    ListView lv;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        addControls();

        final ArrayList<File> mySongs = findSongs(Environment.getExternalStorageDirectory());
        items = new String[mySongs.size()];
        for (int i = 0; i < mySongs.size(); i++) {
           // toast(mySongs.get(i).getName().toString());
            items[i] = mySongs.get(i).getName().toString().replace(".mp3","").replace(".wav","");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listsong_layout, R.id.textView2, items);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplication(), Controller.class).putExtra("pos", position).putExtra("songlist", mySongs));
            }
        });
    }

    private void addControls() {
        lv = (ListView) findViewById(R.id.lvPlaylist);
    }

    private void addEvents() {

    }

    public ArrayList<File> findSongs(File root) {
        ArrayList<File> al = new ArrayList<File>();
        File[] files = root.listFiles();
        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                al.addAll(findSongs(singleFile));
            } else {
                if (singleFile.getName().endsWith(".mp3") ) {
                    al.add(singleFile);
                    // || singleFile.getName().endsWith(".wav") Nhạc Chuông
                }
            }
        }
        return al;
    }

    public void toast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}
