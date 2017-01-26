package ben.dvdhub;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends Activity {
	
	private DatabaseHelper dataB;
	private DVD thisDVD;
	
	private Button save;
	
	private TextView title;
	private TextView cast;
	private TextView desc;
	private Spinner genre;
	private TextView price;
	private TextView year;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);
		
		Log.i("DVDHub","Binding textfields");
		title = (TextView)findViewById(R.id.titleET);
		cast = (TextView)findViewById(R.id.castET);
		desc = (TextView)findViewById(R.id.descET);
		genre = (Spinner)findViewById(R.id.genreItems);
		price = (TextView)findViewById(R.id.priceET);
		year = (TextView)findViewById(R.id.yearET);
		
		Log.i("DVDHub","Binding buttons");
		save = (Button)findViewById(R.id.saveButt);
		
		save.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View v) {
            	try{
                editDVD();
            	} catch (Exception e){
            		Log.i("DVDHub", "Error:" + e.getMessage());
            	}
            }
        });
		
		Log.i("DVDHub","applying Context");
		
		thisDVD = Globals.currDVD;
		Log.i("DVDHub","Populating textFields");
		populateFields();
		Log.i("DVDHub", "Fields populated");
	}
	
	public void populateFields(){
		title.setText(thisDVD.getTitle());
		cast.setText(thisDVD.getCast());
		desc.setText(thisDVD.getDesc());
		Log.i("DVDHub","Accessing Resources");
		Resources res = getResources();
		String[] genreArray = res.getStringArray(R.array.Genre);
		Log.i("DVDHub","First non-empty genreArray is: " + genreArray[1]);
		Log.i("DVDHub", "Going through while loop.");
		for(int i = 0; i < 4; i++){
			if(genreArray[i] == thisDVD.getGenre()){
				genre.setSelection(i);
			}
		}
		Log.i("DVDHub","Setting selection point for genre.");
		price.setText(thisDVD.getPrice());
		year.setText(thisDVD.getYear());
	}
	
	public void editDVD(){
		if(unfilledText()){
			getNewDVD();
			dataB.updateItem(thisDVD);
			Toast.makeText(getApplicationContext(), "DVD changed", Toast.LENGTH_LONG).show();
			finish();
		}
	}
	
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
