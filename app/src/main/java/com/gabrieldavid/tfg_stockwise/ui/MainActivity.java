package com.gabrieldavid.tfg_stockwise.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.Menu;

import com.gabrieldavid.tfg_stockwise.R;
import com.google.android.material.navigation.NavigationView;

import com.gabrieldavid.tfg_stockwise.ui.empleado.Empleado;
import com.gabrieldavid.tfg_stockwise.ui.pedido.Pedido;


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
                        showToast("Pedidos seleccionado");
                        break;
                    case R.id.nav_empleados:
                        startActivity(new Intent(MainActivity.this, Empleado.class));
                        showToast("Empleados seleccionado");
                        break;
                    case R.id.nav_contacto:
                        navController.navigate(R.id.nav_contacto);
                        showToast("Contactos seleccionado");
                        break;
                    case R.id.nav_logout:
                        LoginActivity.auth.signOut();
                        navController.navigate(R.id.nav_logout);
                        showToast("LogOut seleccionado");
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

    // Inflar el menú en la ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Manejar los clics en los elementos del menú de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_drawer:
                // Abrir el DrawerLayout cuando se hace clic en el icono de hamburguesa
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
