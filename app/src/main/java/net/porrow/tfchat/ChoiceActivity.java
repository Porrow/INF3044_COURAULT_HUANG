package net.porrow.tfchat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button p1;
    private Button p2;
    private Button p3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        p1 = (Button) findViewById(R.id.porte1);
        p1.setOnClickListener(this);
        p2 = (Button) findViewById(R.id.porte2);
        p2.setOnClickListener(this);
        p3 = (Button) findViewById(R.id.porte3);
        p3.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.choice_menu, menu);
        return true;
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
    public void onClick(View v) {
        RoomActivity.isInit = false;
        RoomActivity.messages.clear();
        switch (v.getId()) {
            case R.id.porte1:
                Connection.sendQ("ROOM#0");
                break;
            case R.id.porte2:
                Connection.sendQ("ROOM#1");
                break;
            case R.id.porte3:
                Connection.sendQ("ROOM#2");
                break;
        }
        Intent inte = new Intent(this, RoomActivity.class);
        startActivity(inte);
    }
}
