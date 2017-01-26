package ben.dvdhub;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListDVDs extends Activity{
	
	public static final int MENU_VIEW = Menu.FIRST+1;
	public static final int MENU_EDIT = Menu.FIRST+2;
	public static final int MENU_DELETE = Menu.FIRST+3;
	
	private ListView dvdLV;
	private DatabaseHelper dataB;
	private ArrayAdapter<String> ad;
	private ArrayList<DVD> rack = new ArrayList<DVD>();
	private ArrayList<String> titles = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
        setContentView(R.layout.activity_list_dvds);
        
        Log.i("DVDHub", "establishing database connection");
        dataB = new DatabaseHelper(this);
        dvdLV = (ListView)findViewById(R.id.dvdList);
        Log.i("DVDHub","query executed");
        Log.i("DVDHub","Retrieving information from database");
        rack = dataB.getDVDs();
        Log.i("DVDHub", "Retrieval successful");
        Log.i("DVDHub", "Size of list is " + rack.size()+ " dvds");
        
        for(int i = 0; i < rack.size(); i++){
        	Log.i("DVDHub", "Getting title of "+ i + "th dvd: " + rack.get(i).getTitle());
        }
        
        initAdap();
        
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        
        Log.i("DVDHub","ArrayAdapter assigned.");
        /*adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, dvdCursor,
        		new String[] {DatabaseHelper.colDVDTitle, DatabaseHelper.colDVDYear}, new int[]{android.R.layout.simple_list_item_1, android.R.layout.simple_list_item_2});
        Log.i("DVDHub", "SimpleCursorAdapter assigned to ListAdapter");*/
        dvdLV.setAdapter(ad);
        Log.i("DVDHub","Applying ListView to the ContextMenu");
        registerForContextMenu(dvdLV);
        Log.i("DVDHub","exiting onCreate method");
	}

	public void initAdap(){
		rack.clear();
		titles.clear();
		rack = dataB.getDVDs();
		for(int i = 0; i < rack.size(); i++){
			titles.add(rack.get(i).getTitle());
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

		menu.add(Menu.NONE, MENU_VIEW, Menu.NONE, "View");
		//menu.add(Menu.NONE, MENU_EDIT, Menu.NONE, "Edit");
		//menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info=
			(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		switch (item.getItemId()) {
			case MENU_VIEW:
				goToDVD(info.position);
				return(true);		
			case MENU_EDIT:
				goToDVD(info.position);
				return(true);
			case MENU_DELETE:
				goToDVD(info.position);
				return(true);
		}
		return(super.onContextItemSelected(item));
	}
		
		private void goToDVD(int index) {
			View v = findViewById(android.R.id.content);
			Intent intent = new Intent(v.getContext(), ViewActivity.class);
			Globals.currDVD = rack.get(index);
			v.getContext().startActivity(intent);		
		}
	
}
