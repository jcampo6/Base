package com.example.usuario.reservas;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Principal extends ActionBarActivity {

    private String[] opcionesMenu;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence tituloSeccion;
    private CharSequence tituloApp;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private Resources recursos;
    private int pos;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        recursos = this.getResources();

        if (toolbar != null) {
            toolbar.setTitle(recursos.getString(R.string.title_activity_principal));
            setSupportActionBar(toolbar);
        }

        opcionesMenu = new String[] {"Escenarios", "Reportes"};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, opcionesMenu));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                pos = position;
                switch (position) {
                    case 0:
                        fragment = new Fragment1();
                        break;
                    case 1:
                        fragment = new Fragment2();
                        break;
                   /* case 2:
                        fragment = new Fragment3();
                        break;*/
                }

                FragmentManager fragmentmanager =
                        getSupportFragmentManager();

                fragmentmanager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();

                drawerList.setItemChecked(position, true);
                if(position == -1) {
                    tituloSeccion = recursos.getString(R.string.title_activity_principal);
                }else{
                    tituloSeccion = opcionesMenu[position];
                    getSupportActionBar().setTitle(tituloSeccion);
                }
                drawerLayout.closeDrawer(drawerList);
            }

        });


        tituloApp = getTitle();

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                if(tituloSeccion == null){
                    getSupportActionBar().setTitle(tituloApp);
                    ActivityCompat.invalidateOptionsMenu(Principal.this);
                }else{
                    getSupportActionBar().setTitle(tituloSeccion);
                    ActivityCompat.invalidateOptionsMenu(Principal.this);
                }
                Toast.makeText(getApplicationContext(),tituloSeccion,Toast.LENGTH_SHORT).show();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(tituloApp);
                ActivityCompat.invalidateOptionsMenu(Principal.this);
            }
        };


        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }else{
            return false;
        }
    }


    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean menuAbierto = drawerLayout.isDrawerOpen(drawerList);

       /* if(menuAbierto)
            menu.findItem(R.id.action_search).setVisible(false);
        else
            menu.findItem(R.id.action_search).setVisible(true);*/

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
