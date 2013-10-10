package example.autoturnonthewifi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AndroidWifiStateChangedDetect extends Activity {
 
 TextView WifiState;
 
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
       WifiState = (TextView)findViewById(R.id.wifistate);
       Button OnWifi = (Button)findViewById(R.id.onwifi);
       Button OffWifi = (Button)findViewById(R.id.offwifi);
      
       this.registerReceiver(this.WifiStateChangedReceiver,
               new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
      
       OnWifi.setOnClickListener(new Button.OnClickListener(){

   @Override
   public void onClick(View arg0) {
    // TODO Auto-generated method stub
    WifiManager wifiManager = (WifiManager)getBaseContext().getSystemService(Context.WIFI_SERVICE);
    wifiManager.setWifiEnabled(true);
   }});
      
       OffWifi.setOnClickListener(new Button.OnClickListener(){

   @Override
   public void onClick(View arg0) {
    // TODO Auto-generated method stub
    WifiManager wifiManager = (WifiManager)getBaseContext().getSystemService(Context.WIFI_SERVICE);
    wifiManager.setWifiEnabled(false);
   }});
   }
  
   private BroadcastReceiver WifiStateChangedReceiver
   = new BroadcastReceiver(){

  @Override
  public void onReceive(Context context, Intent intent) {
   // TODO Auto-generated method stub
   
   int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE ,
     WifiManager.WIFI_STATE_UNKNOWN);
   
   switch(extraWifiState){
   case WifiManager.WIFI_STATE_DISABLED:
    WifiState.setText("WIFI STATE DISABLED");
    break;
   case WifiManager.WIFI_STATE_DISABLING:
    WifiState.setText("WIFI STATE DISABLING");
    break;
   case WifiManager.WIFI_STATE_ENABLED:
    WifiState.setText("WIFI STATE ENABLED");
    break;
   case WifiManager.WIFI_STATE_ENABLING:
    WifiState.setText("WIFI STATE ENABLING");
    break;
   case WifiManager.WIFI_STATE_UNKNOWN:
    WifiState.setText("WIFI STATE UNKNOWN");
    break;
   }
   
  }};
}

