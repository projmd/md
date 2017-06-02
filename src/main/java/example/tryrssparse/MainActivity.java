package example.tryrssparse;


import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    Context context;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    String navTitles[];
    TypedArray navIcons;
    RecyclerView.Adapter recyclerViewAdapter;
    ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this.getBaseContext();


        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .init();


        //Let's first set up toolbar
        setupToolbar();



        //Initialize Views
        recyclerView  = (RecyclerView) findViewById(R.id.recyclerView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerMainActivity);

        //Setup Titles and Icons of Navigation Drawer
        navTitles = getResources().getStringArray(R.array.SideMenuItems);



        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {

                /**
                 *Here , pass the titles and icons array to the adapter .
                 *Additionally , pass the context of 'this' activity .
                 *So that , later we can use the fragmentManager of this activity to add/replace fragments.
                 */
                recyclerViewAdapter = new RecyclerViewAdapter(navTitles,navIcons,context);
                recyclerView.setAdapter(recyclerViewAdapter);

                /**
                 *It is must to set a Layout Manager For Recycler View
                 *As per docs ,
                 *RecyclerView allows client code to provide custom layout arrangements for child views.
                 *These arrangements are controlled by the RecyclerView.LayoutManager.
                 *A LayoutManager must be provided for RecyclerView to function.
                 */

                recyclerView.setLayoutManager(new LinearLayoutManager(context));



                //Finally setup ActionBarDrawerToggle
                setupDrawerToggle();
            }
        });

        th1.start();



        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {


                //Add the Very First i.e Squad Fragment to the Container
                Fragment squadFragment = new SquadFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.containerView,squadFragment,null);
                fragmentTransaction.commit();


            }
        });

        th.start();

    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

        void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        void setupDrawerToggle(){
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();
        }

    // This fires when a notification is opened by tapping on it or one is received while the app is running.
    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

//        public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {
//            try {
//                if (additionalData != null) {
//                    if (additionalData.has("actionSelected"))
//                        Log.d("OneSignalExample", "OneSignal notification button with id " + additionalData.getString("actionSelected") + " pressed");
//
//                    Log.d("OneSignalExample", "Full additionalData:\n" + additionalData.toString());
//                }
//            } catch (Throwable t) {
//                t.printStackTrace();
//            }
//        }

        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
        }
    }
}

