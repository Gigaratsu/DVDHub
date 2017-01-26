package ben.dvdhub;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends Activity {
	
	private DatabaseHelper dataB;
	private DVD thisDVD;
	
	private TextView title;
	private TextView cast;
	private TextView desc;
	private Spinner genre;
	private TextView price;
	private TextView year;
	
	private Context thisContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);
		
		title = (TextView)findViewById(R.id.titleET);
		cast = (TextView)findViewById(R.id.castET);
		desc = (TextView)findViewById(R.id.descET);
		genre = (Spinner)findViewById(R.id.genreItems);
		price = (TextView)findViewById(R.id.priceET);
		year = (TextView)findViewById(R.id.yearET);
		
		thisContext = getApplicationContext();
	}
	
	public void insertDVD(View view){
		if(unfilledText()){
		getNewDVD();
		//do stuff here for the database
		dataB.addDVD(thisDVD);
		
		Toast.makeText(getApplicationContext(), "DVD saved and uploaded", Toast.LENGTH_LONG).show();
		finish();
		}
		else{
			//toast that you need a title
			Toast.makeText(getApplicationContext(), "Must fill title to save.", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		if(dataB == null){
			dataB = new DatabaseHelper(thisContext);

		}
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		
		if(dataB != null){
			dataB.close();
			dataB = null;
		}
	}
	
	//set parameters as to when should the entry be put into the database. Default has been to be if there is no title.
	//would not make sense to be able to put in other attributes without the DVD title
	private boolean unfilledText() {
		if(title.getText().toString().isEmpty())
			return false;
		else
			return true;
	}

	public void cancelAct(View view){
		finish();
	}

	private void getNewDVD() {
		thisDVD = new DVD();
		thisDVD.setTitle(title.getText().toString());
		thisDVD.setCast(cast.getText().toString());
		thisDVD.setDesc(desc.getText().toString());
		thisDVD.setGenre(genre.getSelectedItem().toString());
		thisDVD.setPrice(price.getText().toString());
		thisDVD.setYear(year.getText().toString());
		
	}
}
