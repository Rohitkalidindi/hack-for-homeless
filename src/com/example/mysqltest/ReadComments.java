package com.example.mysqltest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

public class ReadComments extends Activity  {

	
//	private static final String url = "jdbc:mysql://<server ip>:<sql port#>/<database name>";
//    private static final String user = "<username>";
//    private static final String pass = "<password>";
//   

    private static final String url = "jdbc:mysql://acmhack.nicholasfong.com:3306/cta_people?autoReconnect=true";
    private static final String user = "cta_people";
    private static final String pass = "CTAs1lv1a";
    public String result ="No act of kindness is ever Wested!!";
    protected ArrayList<String> currentList;
    ListView list;
    ArrayAdapter<String> adapter;
    WebView webView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_comments);
		TextView textView = (TextView) findViewById(R.id.Text);  
        textView.setText(result); 
        

		
		
//		
//		
//		Thread thread = new Thread(this);
//	    thread.start();
//	    
	    
	    
	}
	
	public void openNearByHealthCliniqueList(View v)
	{
		Intent intent5 = new Intent();
		intent5.setClass(this, HealthCliniqueNearBy.class); 
		//intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent5);
		this.finish();	
	}
	
	public void Call911(View v)
	{
		Intent intent6 = new Intent();
		intent6.setClass(this, HelpLines.class); 
		//intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent6);
	}
	
	public void FromTheWall(View v)
	{
		Intent intent6 = new Intent();
		intent6.setClass(this, FromTheWall.class); 
		//intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent6);
		this.finish();	
	}
	
	//On FoodCenters button pressed
	public void openNearByFoodCentersList(View v)
	{
		Intent intent3 = new Intent();
		intent3.setClass(this, FoodCentersNearBy.class); 
		//intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent3);
		this.finish();	
	}
//	
//	private void testDB() {
//		// TODO Auto-generated method stub
//		
//		     TextView tv = (TextView)findViewById(R.id.Text);
//		        try {
//		            Class.forName("com.mysql.jdbc.Driver");
//		            Connection con = DriverManager.getConnection(url, user, pass);
//		            tv.setText("Connecting To fucking DB!!!!!!");
//		            /* System.out.println("Database connection success"); */
//
//		            String result = "Database connection success\n";
//		            Statement st = con.createStatement();
//		            ResultSet rs = st.executeQuery("SELECT * FROM dummy_hmis WHERE ServiceCode = 'FOOD'");
//		            ResultSetMetaData rsmd = rs.getMetaData();
//
//		            while(rs.next()) {
//		                result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
//		                result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
//		                result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
//		            }
//		            tv.setText(result.substring(0, 5));
//		            //Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();
//		            con.close();
//		        }
//		        catch(Exception e) {
//		            e.printStackTrace();
//		            tv.setText(e.toString()); 
//
//		    }
//	}
//
//
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_read_comments, menu);
//		return true;
//	}
//
//	@Override
//	public void run() {
//		
//		currentList = new ArrayList<String>();
//		TextView tv = (TextView)findViewById(R.id.Text);
//		System.out.println("Select Records Example by using the Prepared Statement!");
//		  Connection con = null;
//		  //int count = 0;
//		  try{
//		  Class.forName("com.mysql.jdbc.Driver");
//		  con = DriverManager.getConnection(url, user, pass);
//		  try{
//			  String sql;
//		//	  sql
//		//	  = "SELECT title,year_made FROM movies WHERE year_made >= ? AND year_made <= ?";
//			  sql 
//			  = "SELECT * FROM dummy_hmis WHERE ServiceCode = 'FOOD'";
//		  PreparedStatement prest = con.prepareStatement(sql);
//		  //prest.setInt(1,1980);
//		  //prest.setInt(2,2004);
//		  ResultSet rs = prest.executeQuery();
//		  ResultSetMetaData rsmd = rs.getMetaData();
//		  
//          while(rs.next()) {
//        	  addMyRecord(new MyRecord(rs.getInt(1),rs.getString(2),
//        			  rs.getString(3),
//        			  rs.getString(4),
//        			  rs.getString(5),
//        			  rs.getString(6),
//        			  rs.getString(7),
//        			  rs.getString(8),
//        			  rs.getString(9)).toString());
////              result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
////              result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
////              result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
//          }
//		  
////          for(String i: currentList){
////        	  result = result+">>"+i;
////          }
//		  
//		  prest.close();
//		  con.close();
//		  
//		  
//		  
//		  }
//		  catch (SQLException s){
//		  System.out.println("SQL statement is not executed!");
//		  result=result+s.getMessage();
//
//		  }
//		  }
//		  catch (Exception e){
//		  e.printStackTrace();
//		  
//          tv.setText(e.toString());
//		  }
//		  handlerFood.sendEmptyMessage(0);  
//		  
//		  
//		 
//	}	
//	private Handler handlerFood = new Handler() {
//	      public void handleMessage(Message msg) {
//	    	  
//	          TextView textView = (TextView) findViewById(R.id.Text);  
//	          textView.setText(result); 
//	          list=(ListView)findViewById(R.id.listView1);
//	          adapter = new ArrayAdapter<String>(ReadComments.this, android.R.layout.simple_list_item_1,currentList);
//			  list.setAdapter(adapter);
//	      }
//	   };
//
//	   
//	   public void addMyRecord(String item){
//		   currentList.add(item);
//		}


}




