package cl.evilgenius.flash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 8520;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

//comprobamos si el usuario esta registrado
        if (new CurrentUser().getCurrentUser() != null) {
            logged();
        } else {
            signUp();
        }

    }

    private void signUp() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(

                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.FacebookBuilder().build()/*,
                                new AuthUI.IdpConfig.TwitterBuilder().build(),
                                new AuthUI.IdpConfig.GitHubBuilder().build(),

                               new AuthUI.IdpConfig.PhoneBuilder().build()*/
                                )
                        )
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RC_SIGN_IN == requestCode) {
            if (RESULT_OK == resultCode) {
                logged();
            }
        }

    }

    private void logged() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
