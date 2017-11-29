package cz.vancura.searchapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		 final EditText eText;
		 Button btn;
		 
		  Log.d("search", "**** APP START");

			eText = (EditText) findViewById(R.id.edittext);
		    btn = (Button) findViewById(R.id.button);
		    final TextView result = (TextView) findViewById(R.id.textView1);
		      
		    btn.setOnClickListener(new OnClickListener() {
		         public void onClick(View v) {
		          
		        	final String str = eText.getText().toString();
		            Log.d("search", "Searching for :" + str);
		            result.setText("Searching for :" + str);
		           
		            
					Thread thread = new Thread(new Runnable()
					{
					    @Override
					    public void run() 
					    {
					  			
								try {
										
									
										// looking for
									 	String strNoSpaces = str.replace(" ", "+");
									
										//API key
									 	String key="AIzaSyDCGBtaKURCaFv_jETCyB89Ib3x_uTQojc";
									 	
									 	//Search Engine ID
										String cx = "015536599236281963399:ejpebkihbmq";
										
										String url2 = "https://www.googleapis.com/customsearch/v1?q=" + strNoSpaces + "&key=" + key + "&cx=" + cx + "&alt=json"; 	
										Log.d("search", "Url = "+  url2);
										String result2 = httpGet(url2);
										
										result.setText(result2);										
										
							        }
							        catch(Exception e) {
							            System.out.println("Error1 " + e.getMessage());
							        }
									
								 }	
								
			
						private String httpGet(String urlStr) throws IOException {
							
							URL url = new URL(urlStr);
							
						    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					        
					        if(conn.getResponseCode() != 200) {
					            throw new IOException(conn.getResponseMessage());
					        }
					         
					    	Log.d("search", "Connection status = " + conn.getResponseMessage());
					    	
					        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					        StringBuilder sb = new StringBuilder();
					        String line;
					        
					        while((line = rd.readLine()) != null) {

					        	Log.d("search", "Line =" + rd.readLine());
					      		sb.append(line+"\n");
					            
					        }
					        rd.close();
					        
					        conn.disconnect();
					        return sb.toString();
					    }
					});

					thread.start(); 
			        
		         }
		      });
		}
		
	}
