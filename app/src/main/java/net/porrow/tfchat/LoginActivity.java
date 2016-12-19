package net.porrow.tfchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edn;
    private Button btn;
    private static boolean startCo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = (Button) findViewById(R.id.connec);
        btn.setOnClickListener(this);
        edn = (EditText) findViewById(R.id.editName);
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(getApplicationContext(), "#"+edn.getText()+"#", Toast.LENGTH_LONG).show();
        switch (v.getId()) {
            case R.id.connec:
                String name = edn.getText().toString();
                if(!name.equals("")){
                    if(!startCo){
                        //Intent inte = new Intent(this, ChoiceActivity.class);
                        //startActivity(inte);
                        startCo = true;
                        MainService.startAction(this, name);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Connexion en cours...", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Veuillez entrer votre pseudo", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
