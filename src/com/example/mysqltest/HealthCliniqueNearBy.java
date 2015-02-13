package com.example.mysqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class HealthCliniqueNearBy extends Activity implements Runnable,LocationListener,Comparator<Double>,OnItemClickListener {

	
	
	private static final String url = "jdbc:mysql://acmhack.nicholasfong.com:3306/cta_people?autoReconnect=true";
    private static final String user = "cta_people";
    private static final String pass = "CTAs1lv1a";
    protected ArrayList<MyRecord> currentList;
    ListView list;
    ArrayAdapter<String> adapter;
    String result;
    private Geocoder coder;
    LocationManager locationManager;
    String destination;
    public static double myLatitude;  
    public static double myLongitude;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_clinique_near_by);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		Thread thread = new Thread(this);
	    thread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.activity_health_clinique_near_by, menu);
		return true;
	}
	
	
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(currentList!=null){
			currentList.clear();
		}
		super.onDestroy();
	}
	
	public void GoToMainScreen(View v)
	{
		Intent intent5 = new Intent();
		intent5.setClass(this, ReadComments.class); 
		//intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent5);
		this.finish();	
	}
	
public void run() {
		
		currentList = new ArrayList<MyRecord>();
		TextView tv = (TextView)findViewById(R.id.Text);
		System.out.println("Select Records Example by using the Prepared Statement!");
		 Connection con = null;
		  //int count = 0;
		  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection(url, user, pass);
		  try{
			  String sql;
		//	  sql
		//	  = "SELECT title,year_made FROM movies WHERE year_made >= ? AND year_made <= ?";
			  sql 
			  = "SELECT * FROM dummy_hmis WHERE ServiceCode = 'CLINIC'";
		  PreparedStatement prest = con.prepareStatement(sql);
		  //prest.setInt(1,1980);
		  //prest.setInt(2,2004);
		  ResultSet rs = prest.executeQuery();
		  ResultSetMetaData rsmd = rs.getMetaData();
		  
          while(rs.next()) {
        	  destination = (String)(rs.getString(7)+","+rs.getString(8)+","+rs.getString(9));
        	  addMyRecord(new MyRecord(rs.getInt(1),rs.getString(2),
        			  rs.getString(3),
        			  rs.getString(4),
        			  rs.getString(5),
        			  rs.getString(6),
        			  rs.getString(7),
        			  rs.getString(8),
        			  rs.getString(9),myDistance(destination)));
//              result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
//              result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
//              result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
          }
		  
          
          Collections.sort(currentList, new Comparator<MyRecord>() {
			    public int compare(MyRecord v1, MyRecord v2) {
			        return ((Double)v1.getDistance()).compareTo((Double)(v2.getDistance()));
			    }
			});
//          for(String i: currentList){
//        	  result = result+">>"+i;
//          }
		  
		  prest.close();
		  con.close();
		  
		  
		  
		  }
		  catch (SQLException s){
		  System.out.println("SQL statement is not executed!");
		  result=result+s.getMessage();

		  }
		  }
		  catch (Exception e){
		  e.printStackTrace();
		  
          tv.setText(e.toString());
		  }
		  handlerFood.sendEmptyMessage(0);  
		  
		  
		 
	}	
	private Handler handlerFood = new Handler() {
	      public void handleMessage(Message msg) {
	    	  
//	          TextView textView = (TextView) findViewById(R.id.textView1);  
//	          textView.setText(result); 
	    	  list=(ListView)findViewById(R.id.listView3);
	          CustomAdapter1 adapter=new CustomAdapter1(HealthCliniqueNearBy.this, currentList);
			  list.setAdapter(adapter);
			  list.setOnItemClickListener(HealthCliniqueNearBy.this);
	      }
	   };

	   
	   
	   public void addMyRecord(MyRecord item){
		   currentList.add(item);
		}

	@Override
	public void onLocationChanged(Location location) {
		location.getLatitude();  
        location.getLongitude();  
        myLatitude=location.getLatitude();  
        myLongitude=location.getLongitude();
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	
	public double myDistance(String destination){
		
		double distance=0;
		double destLat=0,destLon = 0;
		coder = new Geocoder(this);
		 try {
		     String locationName =destination;

		        List<Address> addressList = coder.getFromLocationName(locationName, 5);
		        if (addressList != null && addressList.size() > 0) {
		           //destLat = (addressList.get(0).getLatitude() * 1e6);
		           //destLon = (addressList.get(0).getLongitude() * 1e6);
		           
		           destLat = addressList.get(0).getLatitude();
		           destLon = addressList.get(0).getLongitude();
		    }
		 }
		 catch (Exception e) {
		     Log.e("error", e.getMessage().toString());
		}
		 
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {
			            
//			      double myLon=  location.getLongitude();
//			      double myLat = location.getLatitude();
			double myLon=  location.getLongitude();
		      double myLat = location.getLatitude();
			
			
		 distance =(Math.sqrt(Math.pow((myLon-destLon), 2)+Math.pow((myLat-destLat), 2)));
		 
		} return distance;
	}

	@Override
	public int compare(Double lhs, Double rhs) {
		// TODO Auto-generated method stub
		if(lhs>rhs){return -1;}
		if(rhs<lhs){return 1;}
		return 0;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		double distance=0,myLon=0,myLat=0;
		String destinationAddressToPush = currentList.get(position).getAddressLine1();
		double destLat=0,destLon = 0;
		coder = new Geocoder(this);
		try {

		        List<Address> addressList = coder.getFromLocationName(destinationAddressToPush, 5);
		        if (addressList != null && addressList.size() > 0) {
		           destLat = addressList.get(0).getLatitude();//(addressList.get(0).getLatitude() * 1e6);
		           destLon = addressList.get(0).getLongitude();//(addressList.get(0).getLongitude() * 1e6);
		    }
		 }
		 catch (Exception e) {
		     Log.e("error", e.getMessage().toString());
		}
		 Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

			if (location != null) {
				            
				      myLon=  location.getLongitude();
				      myLat = location.getLatitude();
			}
	
	Intent intent1 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?saddr="+myLat+","+myLon+"&daddr="+destLat+","+destLon+"&mode=driving"));
	startActivity(intent1);
	
	}

}

class CustomAdapter1 extends ArrayAdapter{
	int size=1;
	Context context;
	ArrayList<MyRecord> Name;
	
	public CustomAdapter1(Context c, ArrayList<MyRecord> Name) {
		super(c, R.layout.list_row,Name);
		this.context=c;
		this.Name=Name;
		
	}
	public View getView(int position, View convertView, ViewGroup parent){
		
		
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(R.layout.list_row, parent,false);
		
		TextView name=(TextView) row.findViewById(R.id.textView1);
		name.setText(Name.get(position).getProviderName());
		
		TextView time=(TextView) row.findViewById(R.id.textView2);
		time.setText("Time:  "+Name.get(position).getProviderHours());
		
		TextView description=(TextView) row.findViewById(R.id.textView3);
		description.setText("Distance:"+(Math.round(Name.get(position).getDistance()*10000)/100.0)+" mi");
		
		return row;
	}
}