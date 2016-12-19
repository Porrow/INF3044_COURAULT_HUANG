package net.porrow.tfchat;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

public class MainService extends IntentService {

	private static final String PNAME = "net.porrow.tfchat.extra.PNAME";

	public MainService(){
		super("MainService");
	}

	public static void startAction(Context context, String name) {
		Intent intent = new Intent(context, MainService.class);
		intent.putExtra(PNAME, name);
		context.startService(intent);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		if (intent != null) {
			String name = intent.getStringExtra(PNAME);

			Connection c = new Connection(name);
			c.start();
		}
	}
}
