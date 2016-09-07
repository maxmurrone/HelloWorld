package it.eng.helloworld;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ActivityDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Bundle bundle = intent.getExtras();

        Intent intent = getIntent();
        if (manageIntentMessage(intent))
            return;

        if (manageIntentFoto(intent))
            return;

        // nessun intent - produrre messaggio d'errore

        /*
        Intent intent = getIntent();
        String message = intent.getStringExtra(Common.EXTRA_MESSAGE);



        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
        */
    }


    public boolean manageIntentFoto(Intent intent)
    {
        // dichiarazione intent
        Intent getFotoImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(Common.isAnActivity(this.getBaseContext(), intent))
            return true;
        else
            return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Bundle b = data.getExtras();
        if (requestCode == Common.GET_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView img = (ImageView)findViewById(R.id.imageView);
            img.setImageBitmap(imageBitmap);
        }
    }


    public boolean manageIntentMessage(Intent intent)
    {
        String message = intent.getStringExtra(Common.EXTRA_MESSAGE);

        if (message == null)
            return false;

        else
        {
            TextView textView = new TextView(this);
            textView.setTextSize(40);
            textView.setText(message);

            ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display);
            layout.addView(textView);

            return true;
        }
    }
}
