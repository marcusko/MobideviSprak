package ph.edu.dlsu.mobidev.marcus.broadcastreceivernewsms;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class IncomingSms extends BroadcastReceiver {


	
	// Get the object of SmsManager
	final SmsManager sms = SmsManager.getDefault();
	
	public void onReceive(Context context, Intent intent) {

		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();

		// >=19 SDK
//        SmsMessage[] message =  Telephony.Sms.Intents.getMessagesFromIntent(intent);
//        message[0];
		try {

			if (bundle != null) {

				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				for (int i = 0; i < pdusObj.length; i++) {

					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
					//alarm manager

					String phoneNumber = currentMessage.getDisplayOriginatingAddress();

					String senderNum = phoneNumber;
					String message = currentMessage.getDisplayMessageBody();

					Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

					int duration = Toast.LENGTH_LONG;
					Toast.makeText(context, "senderNum: " + senderNum + ", message: " + message, duration).show();




						try {
							if(phoneNumber.equals("+639777501340")) {
								if(message.equals("missing")) {
									Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
									Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
									r.play();
									r.stop();
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						Intent activityIntent = new Intent(context, BroadcastNewSms.class);
						activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						activityIntent.putExtra("ring", true);
						context.startActivity(activityIntent);

						// activities, services, receivers, content providers (Shared databases)
						// activties
						// service (intent service) ->

					} // end for loop
				} // bundle is null

			}catch(Exception e){
				Log.e("SmsReceiver", "Exception smsReceiver" + e);
			}

		}



}