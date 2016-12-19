package net.porrow.tfchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText edn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = (Button) findViewById(R.id.connec);
        btn.setOnClickListener(this);
        edn = (EditText) findViewById(R.id.editName);
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(getApplicationContext(), "#"+edn.getText()+"#", Toast.LENGTH_LONG).show();
        switch (v.getId()) {
            case R.id.connec:
                if(!edn.getText().toString().equals("")){
                    //Intent inte = new Intent(this, ChoiceActivity.class);
                    //startActivity(inte);
                    Connection.startAction(this);
                }
                else
                    Toast.makeText(getApplicationContext(), "Veuillez entrer votre pseudo", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
