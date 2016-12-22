package net.porrow.tfchat;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edn;
    private Button btn;

    public static LoginActivity logAct;
    public static boolean startCo = false;

    public Handler hand = new Handler() {  //Définition d'un Handler pour modifier le texte du TextView depuis le Thread de Gestion.
        @Override
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case 0:
                    edn.setText("");
                    Toast.makeText(getApplicationContext(), getString(R.string.thereare)+Connection.nUsers+getString(R.string.users), Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), getString(R.string.una)+Connection.HOST, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logAct = this;
        btn = (Button) findViewById(R.id.connec);
        btn.setOnClickListener(this);
        edn = (EditText) findViewById(R.id.editName);
        edn.addTextChangedListener(new TextWatcher() {
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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Connection.running){
            Toast.makeText(getApplicationContext(), R.string.deco, Toast.LENGTH_LONG).show();
            Connection.sendQ("FIN# ");
            startCo = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connec:
                String name = edn.getText().toString();
                if(!name.equals("")){
                    if(!startCo){
                        startCo = true;
                        MainService.startAction(this, name);
                    }
                    else
                        Toast.makeText(getApplicationContext(), R.string.coco, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), R.string.username, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
