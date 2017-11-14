package com.example.dolby.chatfirebase;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar mToolbar;

    //Bu Viewpager yana kaydırınca sayfalar arası(fragmentler arası) geçişi sağlayan yapı
    private ViewPager mViewPager;

    //Bunu kendi oluşturdugumuz class tan obje olarak aldık
    private SectionsPagerAdapter mSectionsPagerAdapter;

    //Üstteki REQUEST-CHATS-FRIENDS yapısı
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase bağlantısı yaptık
        mAuth = FirebaseAuth.getInstance();

        //Kendi toolbarımızı oluşturduk activity_main.xml in içinde main_page_toolbar var , onuda app_bar_layout.xml den olşturduk.
        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        //Toolbarın title ını koyduk
        getSupportActionBar().setTitle("HorHor");

        //Tab ları oluşturmak ve içine activity ler gömmek için bütün kodlar
        mViewPager = (ViewPager) findViewById(R.id.main_tabPager);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onStart() {
        super.onStart();

        //User daha önce girilmiş mi onu kontrol ediyor
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //Girilmediyse StartActivity e yolluyoruz
        if(currentUser == null){

            sendToStart();

        }
    }

    private void sendToStart() {

        //StartActivity açma metodu
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //Optionmenu(sağ üstte çıkan menü) ayarlama yeri
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        //Menüdeki log out butonu seçilirse
        if(item.getItemId() == R.id.main_logout_btn){

            //FirebaseAuth dan hesabı sign out yapıyor ve StartActivity e gidiyor
            FirebaseAuth.getInstance().signOut();
            sendToStart();
        }

        if (item.getItemId() == R.id.main_settings_btn){

            Intent settingsIntent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(settingsIntent);

        }

        if (item.getItemId() == R.id.main_all_btn){

            Intent allIntent = new Intent(MainActivity.this,UsersActivity.class);
            startActivity(allIntent);
        }

        return true;
    }
}
