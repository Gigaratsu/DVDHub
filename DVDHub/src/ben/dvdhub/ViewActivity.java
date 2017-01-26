package ben.dvdhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ViewActivity extends Activity{
	
	private DVD aDVD;
	
	private TextView title;
	private TextView cast;
	private TextView desc;
	private TextView genre;
	private TextView price;
	private TextView year;
	
	
	public static final int MENU_ABOUT = Menu.FIRST+1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		
		title = (TextView) findViewById(R.id.titleViewOutput);
		cast = (TextView) findViewById(R.id.castViewOutput);
		desc = (TextView) findViewById(R.id.descViewOutput);
		genre = (TextView) findViewById(R.id.genreViewOutput);
		price = (TextView) findViewById(R.id.priceViewOutput);
		year = (TextView) findViewById(R.id.yearViewOutput);
		aDVD = Globals.currDVD;
		setTVs();
	}

	private void setTVs() {
		if(!aDVD.getTitle().isEmpty() && aDVD.getTitle() != null){
			title.setText(aDVD.getTitle());
			cast.setText(aDVD.getCast());
			desc.setText(aDVD.getDesc());
			genre.setText(aDVD.getGenre());
			price.setText(aDVD.getPrice());
			year.setText(aDVD.getYear());
		}
	}
	
	public void editDVD(View view){
		Intent intent = new Intent(view.getContext(), EditActivity.class);
		Log.i("DVDHub","Entering the EditActivity with Insert XML");
		view.getContext().startActivity(intent);
	}
	
	public void delDVD(View view){
		DatabaseHelper dbh = new DatabaseHelper(this);
		try{
			//dbh.getWritableDatabase().execSQL("DELETE FROM dvd_table WHERE id=" + aDVD.getID()+";");
			Log.i("DVDHub", "Attempting to delete DVD #: " + aDVD.getID());
			dbh.deleteItem(aDVD.getID());
			dbh.close();
			finish();//go back to list
		} catch (Exception e){
			Log.i("DVDHub","Error caught when deleting from database: "+ e.getMessage());
		}
	}
	
	
	
	
	
}
