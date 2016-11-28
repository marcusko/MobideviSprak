package ph.edu.dlsu.mobidev.marcus.broadcastreceivernewsms;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.androidexample.broadcastreceiver.R;


public class BroadcastNewSms extends Activity {
Button buttonAdd;

	public static final int REQUEST_CODE_ADD_PHONE = 0;
	public static final int REQUEST_CODE_DELETE_PHONE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_broadcast_new_sms);

		buttonAdd= (Button) findViewById(R.id.button_add);

		buttonAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(getBaseContext(), AddNewPhone.class);
				startActivityForResult(i, REQUEST_CODE_ADD_PHONE);
			}

		});







	}
}
