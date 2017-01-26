package ben.dvdhub;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	
	private Intent menuCon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void startAct(View view){
		switch(view.getId()){
			case R.id.insertActButt:
				menuCon = new Intent(MainActivity.this, InsertActivity.class);
				startActivity(menuCon);
				break;
				
			case R.id.listActButt:
				menuCon = new Intent(MainActivity.this, ListDVDs.class);
				startActivity(menuCon);
				break;
				
			case R.id.editActButt:
				menuCon = new Intent(MainActivity.this, EditActivity.class);
				startActivity(menuCon);
				break;
		}
	}
	
	public void killDB(View view){
		Log.i("DVDHub","Refreshing Database.");
		DatabaseHelper db = new DatabaseHelper(this);
		db.deleteTable();
		db.onCreate(db.getWritableDatabase());
	}
	
	 public void disAbout(View view) {
		    /**
		     * Listing 10-23: Configuring an Alert Dialog
		     */
		    Context context = MainActivity.this;
		    String title = "DVDHub";
		    String message = "Welcome to DVDHub by Benjamin A. Boyce. This application was created to satisfy the requirements for " +
		    		"the midterm of Mobile Computing 2015 EV1 by demonstrating a mastery in utilizing SQLite and databases.";
		    String button1String = "Good Work.";
		    String button2String = "Could be better.";

		    AlertDialog.Builder ad = new AlertDialog.Builder(context);
		    ad.setTitle(title);
		    ad.setMessage(message);

		    ad.setPositiveButton(
		      button1String,
		      new DialogInterface.OnClickListener() {
		        @Override
				public void onClick(DialogInterface dialog, int arg1) {
		          //do nothing
		        }
		      }
		    );

		    ad.setNegativeButton(
		      button2String,
		      new DialogInterface.OnClickListener(){
		        @Override
				public void onClick(DialogInterface dialog, int arg1) {
		          // do nothing
		        }
		      }
		    );

		    // 
		    ad.show();
	}

}
