package forallstudio.mobilephone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import forallstudio.mobilephone.allmobile.MobileListFragment;
import forallstudio.mobilephone.favorite.MobileFavoriteFragment;
import forallstudio.mobilephone.main.MobileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMobileInfoScreen();
        //showMobileListScreen();
        //showMobileFavoriteScreen();
    }

    private void showMobileInfoScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MobileFragment.newInstance())
                .commitAllowingStateLoss();
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