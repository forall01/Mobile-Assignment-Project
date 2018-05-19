package forallstudio.mobilephone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import forallstudio.mobilephone.allmobile.MobileListFragment;
import forallstudio.mobilephone.favorite.MobileFavoriteFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //showMobileListScreen();
        showMobileFavoriteScreen();
    }

    private void showMobileFavoriteScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MobileFavoriteFragment.newInstance())
                .commitAllowingStateLoss();
    }

    private void showMobileListScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MobileListFragment.newInstance())
                .commitAllowingStateLoss();
    }

}