package com.gabrieldavid.tfg_stockwise.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.gabrieldavid.tfg_stockwise.R;
import com.google.android.material.navigation.NavigationView;

import empleado.Empleado;
import pedido.Pedido;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos del layout
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);



// Configuración del ActionBarDrawerToggle
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // Configuración del NavigationItemSelectedListener para manejar eventos de selección de elementos del menú
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Manejar eventos de selección de elementos del menú
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        showToast("Inicio seleccionado");
                        break;
                    case R.id.nav_pedidos:
                        startActivity(new Intent(MainActivity.this, Pedido.class));
                        showToast("Pedidos seleccionados");
                        break;
                    case R.id.nav_empleados:
                        startActivity(new Intent(MainActivity.this, Empleado.class));
                        showToast("Ajustes seleccionados");
                        break;
                    case R.id.nav_contacto:
                        navController.navigate(R.id.nav_contacto);
                        showToast("Ajustes seleccionados");
                        break;
                    case R.id.nav_logout:
                        LoginActivity.auth.signOut();
                        navController.navigate(R.id.nav_logout);
                        showToast("Ajustes seleccionados");
                        break;
                }


                // Cerrar el Navigation Drawer después de la selección del elemento
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    // Método para mostrar un Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Método para manejar el evento de pulsación del botón de retroceso
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}