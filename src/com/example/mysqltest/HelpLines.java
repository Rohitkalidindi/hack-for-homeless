package com.example.mysqltest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HelpLines extends Activity implements OnItemClickListener {
	String[] Helpers={"Housing Help","Drug Rehab","Health Clinics"," Legal"};
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_helplines);
		
		list=(ListView)findViewById(R.id.list_helplines);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Helpers);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		switch(position){
		case 0:
			Uri number=Uri.parse("tel:"+"4084680100");
			Intent i=new Intent(Intent.ACTION_CALL,number);
			startActivity(i);
			break;
			
		case 1:
			Uri number1=Uri.parse("tel:"+"18882995213");
			Intent i1=new Intent(Intent.ACTION_CALL,number1);
			startActivity(i1);
			break;
		case 2:
			Uri number2=Uri.parse("tel:"+"4088854782");
			Intent i2=new Intent(Intent.ACTION_CALL,number2);
			startActivity(i2);
			break;
		case 3:
			Uri number3=Uri.parse("tel:"+"4089752730");
			Intent i3=new Intent(Intent.ACTION_CALL,number3);
			startActivity(i3);
			break;
		}
		
	}
}
