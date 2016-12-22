package net.porrow.tfchat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener, OnListener {

    private static MesAdapter ma;
    public static boolean pause = false;
    public static ArrayList<String> messages = new ArrayList<>();
    public static RoomActivity instance;
    public static boolean isInit = false;

    public Handler hand = new Handler() {  //Définition d'un Handler pour modifier le texte du TextView depuis le Thread de Gestion.
        @Override
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case 0:
                    onFinish();
                    break;
            }
        }
    };

    private Button send;
    private EditText editMes;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        instance = this;
        editMes = (EditText) findViewById(R.id.editMes);
        editMes.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {

                for(int i = s.length(); i > 0; i--)
                    if(s.subSequence(i-1, i).toString().equals("\n"))
                        s.replace(i-1, i, "");
            }
        });
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(this);
        ImageButton save = (ImageButton)findViewById(R.id.save);
        save.setOnClickListener(this);
        ma = new MesAdapter(this, messages);
        rv = (RecyclerView)findViewById(R.id.display);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        if (rv != null) {
            rv.setLayoutManager(lm);
            rv.setAdapter(ma);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        isInit = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        pause = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.item1:
                if(Connection.running){
                    Toast.makeText(getApplicationContext(), R.string.deco, Toast.LENGTH_LONG).show();
                    Connection.sendQ("FIN# ");
                    LoginActivity.startCo = false;
                }
                i = new Intent(this, LoginActivity.class);
                startActivity(i);
                return true;
            case R.id.item2:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fr.wikipedia.org/wiki/Conditions_g%C3%A9n%C3%A9rales_de_vente"));
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.choice_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                String mes = editMes.getText().toString();
                if(mes.equals("")) return;
                Connection.sendQ("TXT#"+mes);
                editMes.setText("");
                break;
            case R.id.save:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.comi)
                        .setMessage(getString(R.string.dialog))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
        }
    }

    @Override
    public void onFinish() {
        ma.update();
        if(pause)
            createNotification(messages.get(messages.size()-1));
        rv.scrollToPosition(messages.size()-1);
    }

    private void createNotification(String txt)
    {
        NotificationManager notificationManager = (NotificationManager)getSystemService(this.NOTIFICATION_SERVICE);

        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), new Intent(this, RoomActivity.class), 0);

        final Notification n  = new Notification.Builder(this)
                .setContentTitle("TFchat")
                .setContentText(txt)
                .setSmallIcon(R.drawable.notif_logo)
                .setContentIntent(pIntent).build();

        n.vibrate = new long[] {0,200,100,200,100,200};
        notificationManager.notify(0, n);
    }
}
