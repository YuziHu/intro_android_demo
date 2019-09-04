package codepath.apps.demointroandroid;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListViewClicksActivity extends Activity {
	String[] myCountries = { "Andorra", "Armenia"};
	String[] countryDesc = {"Andorra, officially the Principality of Andorra, " +
			"also called the Principality of the Valleys of Andorra, " +
			"is a sovereign landlocked microstate on the Iberian Peninsula, " +
			"in the eastern Pyrenees, bordered by France to the north and Spain to the south.",
			"Armenia, officially the Republic of Armenia, " +
					"is a country in the South Caucasus region of Eurasia. " +
					"Located in Western Asia on the Armenian Highlands, " +
					"it is bordered by Turkey to the west, " +
					"Georgia to the north, " +
					"the de facto independent Republic of Artsakh and Azerbaijan to the east, " +
					"and Iran and Azerbaijan's exclave of Nakhchivan to the south."};
	int[] images = {R.drawable.ad, R.drawable.am};
	MyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_clicks);
//		adapter = new ArrayAdapter<String>(this,
//		  android.R.layout.simple_list_item_1, myCountries);

		ListView listView = (ListView) findViewById(R.id.lvDemo);
		adapter = new MyAdapter(this,myCountries, countryDesc, images);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String country = adapter.getItem(position);
				SimpleAlertDialog.displayWithOK(ListViewClicksActivity.this, country);
				Toast.makeText(ListViewClicksActivity.this, country, Toast.LENGTH_SHORT).show();
			}
			
		});
	}

	class MyAdapter extends ArrayAdapter<String>{
		Context context;
		String[] rCountries;
		String[] rDescriptions;
		int[] rImages;

		MyAdapter(Context c, String[] countries, String[] descriptions, int[] imgs){
			super(c, R.layout.simple_list_view_item, R.id.countryDescription, descriptions);
			this.context = c;
			this.rCountries = countries;
			this.rDescriptions = descriptions;
			this.rImages = imgs;

		}

		@NonNull
		@Override
		public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
			LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View country = layoutInflater.inflate(R.layout.simple_list_view_item, parent, false);
			ImageView images = country.findViewById(R.id.countryFlag);
			TextView countryName = country.findViewById(R.id.countryName);

			images.setImageResource(rImages[position]);
			countryName.setText(rCountries[position]);

			return country;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_view_clicks, menu);
		return true;
	}

}
