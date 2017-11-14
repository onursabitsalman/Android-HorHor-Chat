package com.example.dolby.chatfirebase;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


//Bu Class ı kendimiz oluşturduk kolaylık olsun diye fragment yerleştirme işinde
class SectionsPagerAdapter extends FragmentPagerAdapter{


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
     }

    @Override
    public Fragment getItem(int position) {

        //Burada sırası ile yukardaki tablara hangi fragmentlerin geleceğini belirledik

        switch (position){
            case 0:
                RequestsFragment requestsFragment = new RequestsFragment();
                return requestsFragment;
            case 1:
                ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;
            case 2:
                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        //Kaç tane fragment olacagını belirledik veya kaç tab olacagını
        return 3;
    }


    //Layoutlara title koymak için oluşturduk sıradan title koyuyor
    public CharSequence getPageTitle(int position){

        switch (position){
            case 0:
                return "REQUESTS";
            case 1:
                return "CHATS";
            case 2:
                return "FRIENDS";
            default:
                return null;
        }
    }
}
