package it.eng.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Button miobutt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        miobutt= (Button)findViewById(R.id.miobutt);
        miobutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FotoOverNewActivity(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view)
    {
        Log.d("Hello World","test2");

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Messaggio di alert\n\nNon Modale.");

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tap to Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
    }

    public void sendMessageToast(View view)
    {
        Toast.makeText(MainActivity.this, "messaggio Toast - Length_Short.", Toast.LENGTH_SHORT).show();
    }

    public void sendMessageIntent(View view)
    {
        sendMessageToast(view);

        // istanzia l'intent
        Intent intent = new Intent(this, ActivityDisplay.class);

        // recupera l'oggetto text per mezzo dello ID (creto in fase di compilazione)
        EditText editText = (EditText)findViewById(R.id.edit_message);

        // estrae il test digitato
        String message = editText.getText().toString();

        // è un qualificatore per "filtrare" in fase di ricezione del messaggio
        intent.putExtra(Common.EXTRA_MESSAGE, message);

        startActivity(intent);
    }

    /*
    public void Foto(View view)
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Common.GET_IMAGE);
    }
    */

    public void FotoOverNewActivity(View view)
    {
        Intent intent = new Intent(this, ActivityDisplay.class);
        String message = "pippo";
        intent.putExtra(Common.EXTRA_FOTO, message);
        startActivity(intent);
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



}
