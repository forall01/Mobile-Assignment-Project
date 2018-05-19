package forallstudio.mobilephone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import forallstudio.mobilephone.main.MobileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMobileInfoScreen();
    }

    private void showMobileInfoScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MobileFragment.newInstance())
                .commitAllowingStateLoss();
    }

}