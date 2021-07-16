package com.example.app2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.app2.R;
import com.example.app2.database.DbHelper;
import com.example.app2.fragments.ClienteFragment;
import com.example.app2.fragments.ProdutoFragment;
import com.example.app2.fragments.SelectBancoDialogFragment;
import com.example.app2.helper.DBSelector;
import com.example.app2.models.Cliente;
import com.example.app2.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app2.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        SelectBancoDialogFragment.OnDialogButtonClick{

    private Toolbar toolbar;
    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (DBSelector.getInstance().getDbName() == null){
            DBSelector.getInstance().setDbName(DbHelper.DB_1);

            abrirDialogBanco();
            createHomeFragment();
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle.syncState();

    }

    public void abrirDialogBanco(){
        SelectBancoDialogFragment selectBancoDialogFragment = new SelectBancoDialogFragment();
        selectBancoDialogFragment.setOnDialogButtonClick(this);
        selectBancoDialogFragment.show(getSupportFragmentManager(), "selectDatabase");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_selectBanco:
                abrirDialogBanco();
                break;

            case R.id.nav_cliente:
                ClienteFragment clienteFragment = new ClienteFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, clienteFragment)
                        .commit();
                toolbar.setTitle("Clientes");
                break;

            case R.id.nav_produto:
                ProdutoFragment produtoFragment = new ProdutoFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, produtoFragment)
                        .commit();
                toolbar.setTitle("Produtos");
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return false;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void createHomeFragment(){

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, homeFragment)
                .commit();
    }

    @Override
    public void onButtonClick() {
        createHomeFragment();
    }


}