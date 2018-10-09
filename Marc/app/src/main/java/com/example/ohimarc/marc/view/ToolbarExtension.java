package com.example.ohimarc.marc.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ohimarc.marc.R;
import com.example.ohimarc.marc.presenter.ToolbarExtensionPresenter;
import com.example.ohimarc.marc.view.mainMenu.StartMenuActivity;

abstract public class ToolbarExtension extends AppCompatActivity implements ToolbarExtensionView {

    ToolbarExtensionPresenter tep = new ToolbarExtensionPresenter(this);

    protected TextView titleText;
    protected Toolbar tb;
    protected DrawerLayout navView;
    protected ActionBarDrawerToggle navToggle;
    protected NavigationView navigation;

    private void initViews(int viewID) {
        tb = findViewById(R.id.toolbar);
        titleText = findViewById(R.id.toolbar_text);
        navView = findViewById(viewID);
        navigation = findViewById(R.id.navigation);
    }

    private void setUpToolbar() {
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void initNavToggle(Activity act) {
        navToggle = new ActionBarDrawerToggle(act, navView, R.string.Open, R.string.Close);
        navView.addDrawerListener(navToggle);
        navToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void initExtension(Activity activity, int viewID, String title) {
        initViews(viewID);
        setUpToolbar();
        initNavToggle(activity);
        initNavigationListeners();
        titleText.setText(title);
    }

    protected void initExtension(Activity activity, int viewID) {
        initViews(viewID);
        setUpToolbar();
        initNavToggle(activity);
        initNavigationListeners();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_items, menu);
        return true;
    }

    private boolean thisActivityIsNextActivity(Class<?> nextClass) {
        if (this.getClass().getSimpleName().equals(nextClass.getSimpleName())) {
            return true;
        }
        return false;
    }

    private boolean inHome() {
        if (this.getClass().getSimpleName().equals(Home.class.getSimpleName())) {
            return true;
        }
        return false;
    }

    private void initNavigationListeners() {
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                Class<?> nextActivity;
                switch (menuItem.getItemId()) {
                    case (R.id.home_button):            //Special case, home is not allowed to be finished.
                        navView.closeDrawers();
                        if (!inHome()) {
                            finish();
                        }
                        return true;
                    case (R.id.exercises_button):
                        intent = new Intent(getApplicationContext(), FlashcardActivity.class);
                        nextActivity = FlashcardActivity.class;
                        break;
                    case (R.id.achievements_button):
                        intent = null;
                        nextActivity = null;
                        break;
                    case (R.id.decks_button):
                        intent = new Intent(getApplicationContext(),AddRemoveDeckActivity.class);
                        nextActivity = AddRemoveDeckActivity.class;
                        break;
                    case (R.id.settings_button):
                        intent = null;
                        nextActivity = null;
                        break;
                    default:
                        intent = null;
                        nextActivity = null;
                        break;
                }
                if (intent != null) {
                    if (inHome()) {
                        navView.closeDrawers();
                        startActivity(intent);
                    } else if (thisActivityIsNextActivity(nextActivity)) {
                        navView.closeDrawers();
                    } else {
                        startActivity(intent);
                        finish();
                    }
                }
                return true;
            }
        });
    }

    public void logoutClicked(View v) {
        tep.logoutButton();
    }

    public void navigateLogout() {
        Intent intent = new Intent(getApplicationContext(), StartMenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (navToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}