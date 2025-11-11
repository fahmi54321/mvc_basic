package com.example.mvc.screens.common.navdrawer;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mvc.R;
import com.example.mvc.screens.common.views.BaseObservableViewMvc;
import com.google.android.material.navigation.NavigationView;

public class NavDrawerViewMvcImpl extends BaseObservableViewMvc<NavDrawerViewMvc.Listener> implements NavDrawerViewMvc {

    private final DrawerLayout drawerLayout;
    private final FrameLayout frameLayout;
    private final NavigationView navigationView;

    public NavDrawerViewMvcImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        setRootView(layoutInflater.inflate(R.layout.layout_drawer, viewGroup, false));
        drawerLayout = findViewById(R.id.drawer_layout);
        frameLayout = findViewById(R.id.frame_content);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            drawerLayout.closeDrawers();
            if(menuItem.getItemId() == R.id.drawer_menu_latest_questions){
                for(Listener listener: getListeners()){
                    listener.onQuestionListClicked();
                }
            }
            return false;
        });
    }

    @Override
    public void openDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void closeDrawer() {
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean isDrawerOpen() {
        return drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return frameLayout;
    }
}
