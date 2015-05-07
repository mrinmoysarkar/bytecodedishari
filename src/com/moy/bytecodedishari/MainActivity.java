package com.moy.bytecodedishari;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	HttpClient client;
	static SharedPreferences setting_preference;
	static Editor share_editor;
	public static String share_file_name = "789_sharesetting_sharesetting_sharesetting_123";
	TextView tv;
	ImageView imv;
	Bitmap bmp;
	LinearLayout progress, mainform;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		client = new DefaultHttpClient();
		init_all_view();
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.button1) {
					new HttpAction().execute("get");
				}
			}
		});
		/*
		 * *
		 * setting_preference = getSharedPreferences(share_file_name, 0);
		 * Calendar today1 = Calendar.getInstance(); date =
		 * today1.get(Calendar.DATE); if (date !=
		 * setting_preference.getInt("today", 0) ||
		 * setting_preference.getInt("download_ok", 0) != 1) { share_editor =
		 * setting_preference.edit(); share_editor.putInt("today", date);
		 * share_editor.commit(); hptsk = new HttpGetTask(); hptsk.execute();
		 * Log.i("http", "in http"); } else { memtsk = new load_pricelist();
		 * memtsk.execute(); Log.i("memory", "in memory"); }
		 */
	}

	void init_all_view() {
		btn = (Button) findViewById(R.id.button1);
		tv = (TextView) findViewById(R.id.textView1);
		imv = (ImageView) findViewById(R.id.imageView1);
		mainform = (LinearLayout) findViewById(R.id.form_layout);
		progress = (LinearLayout) findViewById(R.id.loading_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	public class HttpAction extends AsyncTask<String, Void, String> {

		String status;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mainform.setVisibility(View.INVISIBLE);
			progress.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... params) {
			Log.i("in async", "starting");
			StringBuilder url = new StringBuilder(
					"http://www.agribook.wc.lt/robot_logo.jpg");
			status = params[0];
			if (status.equals("get")) {

				// "http://www.agribook.wc.lt/j.json");
				HttpGet get = new HttpGet(url.toString());
				get.addHeader(HTTP.CONTENT_TYPE, "image/jpeg");// "application/json");
				get.addHeader(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
				try {
					HttpResponse response = client.execute(get);
					int status = response.getStatusLine().getStatusCode();// it
																			// should
																			// be
																			// 200
					HttpEntity entity = response.getEntity();
					byte[] x = EntityUtils.toByteArray(entity);
					bmp = BitmapFactory.decodeByteArray(x, 0, x.length);
					// String data = EntityUtils.toString(entity);
					Log.i("http get", new String(x));
					// JSONArray arr = new JSONArray(data);
					// JSONObject obj = arr.getJSONObject(0);
					// return obj.get("color").toString();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (status.equals("post")) {
				HttpPost post = new HttpPost(url.toString());
				List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(
						2);
				nameValuePair.add(new BasicNameValuePair("username",
						"test_user"));
				nameValuePair.add(new BasicNameValuePair("password",
						"123456789"));
				try {
					UrlEncodedFormEntity ent = new UrlEncodedFormEntity(
							nameValuePair, HTTP.UTF_8);
					post.setEntity(ent);

					HttpResponse response = client.execute(post);
					int status = response.getStatusLine().getStatusCode();// it
																			// should
																			// be
																			// 200
					HttpEntity entity = response.getEntity();
					byte[] x = EntityUtils.toByteArray(entity);
					bmp = BitmapFactory.decodeByteArray(x, 0, x.length);
					// String data = EntityUtils.toString(entity);
					Log.i("http post", new String(x));
					// JSONArray arr = new JSONArray(data);
					// JSONObject obj = arr.getJSONObject(0);
					// return obj.get("color").toString();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (status.equals("put")) {
				HttpPut put = new HttpPut(url.toString());
				List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(
						2);
				nameValuePair.add(new BasicNameValuePair("username",
						"test_user"));
				nameValuePair.add(new BasicNameValuePair("password",
						"123456789"));
				try {
					UrlEncodedFormEntity ent = new UrlEncodedFormEntity(
							nameValuePair, HTTP.UTF_8);
					put.setEntity(ent);

					HttpResponse response = client.execute(put);
					int status = response.getStatusLine().getStatusCode();// it
																			// should
																			// be
																			// 200
					HttpEntity entity = response.getEntity();
					byte[] x = EntityUtils.toByteArray(entity);
					bmp = BitmapFactory.decodeByteArray(x, 0, x.length);
					// String data = EntityUtils.toString(entity);
					Log.i("http post", new String(x));
					// JSONArray arr = new JSONArray(data);
					// JSONObject obj = arr.getJSONObject(0);
					// return obj.get("color").toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (status.equals("delete")) {
				HttpDelete delete = new HttpDelete(url.toString());
				delete.setHeader("Accept", "application/json");
				delete.setHeader("Content-type", "application/json");
				try {
					HttpResponse response = client.execute(delete);
					int status = response.getStatusLine().getStatusCode();// it
																			// should
																			// be
																			// 200
					HttpEntity entity = response.getEntity();
					byte[] x = EntityUtils.toByteArray(entity);
					bmp = BitmapFactory.decodeByteArray(x, 0, x.length);
					// String data = EntityUtils.toString(entity);
					Log.i("http post", new String(x));
					// JSONArray arr = new JSONArray(data);
					// JSONObject obj = arr.getJSONObject(0);
					// return obj.get("color").toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// tv.setText(result);
			mainform.setVisibility(View.VISIBLE);
			progress.setVisibility(View.INVISIBLE);
			imv.setImageBitmap(bmp);
		}

	}

}
