package forallstudio.mobilephone.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import forallstudio.mobilephone.R;

public class MobileDetailActivity extends AppCompatActivity {

    private static final String BUNDLE_MOBILE_ID = "BUNDLE_MOBILE_ID";

    public static void open(Context context, int mobileId) {
        Intent intent = new Intent(context, MobileDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_MOBILE_ID, mobileId);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
        int mobileId = getMobileId();
        showMobileDetail(mobileId);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showMobileDetail(int mobileId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MobileDetailFragment.newInstance(mobileId))
                .commitAllowingStateLoss();
    }

    private int getMobileId() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            return bundle.getInt(BUNDLE_MOBILE_ID, -1);
        } catch (NullPointerException e) {
            return -1;
        }
    }

}