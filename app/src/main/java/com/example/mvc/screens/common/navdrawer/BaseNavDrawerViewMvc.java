package com.example.mvc.screens.common.navdrawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mvc.R;
import com.example.mvc.screens.common.views.BaseObservableViewMvc;
import com.google.android.material.navigation.NavigationView;

public abstract class BaseNavDrawerViewMvc<ListenerType> extends BaseObservableViewMvc<ListenerType> implements NavDrawerViewMvc {

    private final DrawerLayout drawerLayout;
    private final FrameLayout frameLayout;
    private final NavigationView navigationView;

    public BaseNavDrawerViewMvc(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super.setRootView(layoutInflater.inflate(R.layout.layout_drawer, viewGroup, false));
        drawerLayout = findViewById(R.id.drawer_layout);
        frameLayout = findViewById(R.id.frame_content);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            drawerLayout.closeDrawers();
            if(menuItem.getItemId() == R.id.drawer_menu_latest_questions){
                onDrawerItemClicked(DrawerItems.QUESTIONS_LIST);
            }
            return false;
        });
    }

    protected abstract void onDrawerItemClicked(DrawerItems items);

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
    public void setRootView(View mRootView) {
        frameLayout.addView(mRootView);
    }
}
