package net.porrow.tfchat;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

public class Connection extends IntentService {

    //public static String PARAM;

    public Connection() {
        super("Connection");
        Toast.makeText(getApplicationContext(), "Constructeur", Toast.LENGTH_LONG).show();
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, Connection.class);
        //intent.putExtra(PARAM, name);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(getApplicationContext(), "handle", Toast.LENGTH_LONG).show();
        if (intent != null) {
            //final String param1 = intent.getStringExtra();
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
