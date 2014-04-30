package edu.rosehulman.roseperks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

public class PerkStorage extends Activity {
	final static String HOST = "alumniperks.csse.rose-hulman.edu";
	
	

	public static boolean isEmpty(Activity callingActivity) {
		// Check to see if file exists in internal storage
		for (String filename : callingActivity.fileList()) {
			Log.d(PerkStorage.class.getSimpleName(), "File exists: \"" + filename + "\"");
			if (filename.equals("companyList.xml")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @return Returns an input stream from the XML file containing perk data.
	 * Returns null when there is no file. 
	 */
	public static FileInputStream getXMLFile(Context context) {
		try {
			return context.openFileInput("companyList.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Given a string representation of a URL, sets up a connection and gets
    // an input stream.
    private static InputStream openUrl(String urlString) throws IOException, HttpResponseException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        int code = conn.getResponseCode();
        String message = conn.getResponseMessage();
        if (code != 200) {
        	throw new HttpResponseException(code, message);
        }
        InputStream stream = conn.getInputStream();
        return stream;
    }
    
    public static class HttpResponseException extends Exception {
    	int code;
    	String message;

		public HttpResponseException(int code, String message) {
			this.code = code;
			this.message = message;
		}
    	
    }
    
    public static class NetworkDisconnectedException extends Exception {}
    
    public static abstract class PerkUpdateTask extends AsyncTask<Void, Void, Boolean> {
    	private static enum ExitStatus {NORMAL, NETWORK_PROBLEM, NON_OK_HTTP_RESPONSE, NETWORK_DISCONNECTED}
    	private Exception exception = null;
    	private ExitStatus exitStatus = ExitStatus.NORMAL;
    	
    	/**
    	 * Attempts to download fresh perk data and save it to internal storage
    	 * 
    	 * @return true if we finish updating the file
    	 * @throws IOException when there is a network problem
    	 * @throws HttpResponseException when the response code isn't what we expect
    	 * @throws NetworkDisconnectedException when there is no network connection
    	 */
    	private boolean update() throws IOException, HttpResponseException, NetworkDisconnectedException {
    		ConnectivityManager connMgr = (ConnectivityManager) getCallingActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
    		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    		if (networkInfo != null && networkInfo.isConnected()) {
    			// fetch data
    			InputStream stream = openUrl("http://" + HOST + "/companyList.xml");
    			saveXMLFile(stream);
    			return true;
    		} else {
    			// display error
    			throw new NetworkDisconnectedException();
    		}
    	}
    	
		private void saveXMLFile(InputStream stream) throws IOException {
			saveToInternalStorage(stream, "companyList.xml");
		}
		
		// Assumes that filename is valid
		private void saveToInternalStorage(InputStream stream, String filename) throws IOException {
			// Save in internal storage
			FileOutputStream file;
			try {
				// save temp file
				file = getCallingActivity().openFileOutput(filename+"_downloading", MODE_PRIVATE);
				copy(stream, file);
				file.flush();
				file.close();
				
				// rename temp file to actual file location
				File filesDir = getCallingActivity().getFilesDir();
				File fTemp = new File(filesDir, filename+"_downloading");
				if (!fTemp.exists()) {
					Log.wtf(PerkStorage.class.getSimpleName(), "New perk data file doesn't exist");
				}
				if (!fTemp.renameTo(new File(filesDir, filename))) {
					Log.wtf(PerkStorage.class.getSimpleName(), "Couldn't rename perk data file");
				}
			} catch (FileNotFoundException e) {
				Log.wtf(PerkStorage.class.getSimpleName(), "Internal storage issue", e);
				e.printStackTrace();
			}
		}

		private void copy(InputStream in, FileOutputStream out) throws IOException {
			int bytecounter = 0;
			
			byte[] buffer = new byte[65536]; // 65kb
			int len;
			while ((len = in.read(buffer)) != -1) {
			    out.write(buffer, 0, len);
			    bytecounter += len;
			}
			in.close();
			
			Log.i(PerkStorage.class.getSimpleName(), "Downloaded and saved " + bytecounter + " bytes");
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			boolean success = false;
			
			try {
				success = update();
			} catch (IOException e) {
				this.exception = e;
				this.exitStatus = ExitStatus.NETWORK_PROBLEM;
			} catch (HttpResponseException e) {
				this.exception = e;
				this.exitStatus = ExitStatus.NON_OK_HTTP_RESPONSE;
			} catch (NetworkDisconnectedException e) {
				this.exception = e;
				this.exitStatus = ExitStatus.NETWORK_DISCONNECTED;
			}
			
			return success;
		}
    	
		public abstract Activity getCallingActivity();
		
		public void onNetworkProblem(IOException e) {
			Log.e(PerkStorage.class.getSimpleName(), "Something went wrong while downloading perk data.", e);
			e.printStackTrace();
		}
		
		public void onNonOKHttpResponse(HttpResponseException e) {
			Log.e(PerkStorage.class.getSimpleName(), "Perk server returned unusual resonse: " + e.code + ": " + e.message);
			e.printStackTrace();
		}

		public void onNoConnection(NetworkDisconnectedException e) {
			Log.d(PerkStorage.class.getSimpleName(), "Perk update attempted while network disconnected.");
			e.printStackTrace();
		}
		
		protected void onPostExecute(Boolean result) {
			switch (this.exitStatus) {
			case NORMAL:
				break;
			case NETWORK_DISCONNECTED:
				onNoConnection((NetworkDisconnectedException) this.exception);
				break;
			case NETWORK_PROBLEM:
				onNetworkProblem((IOException) this.exception);
				break;
			case NON_OK_HTTP_RESPONSE:
				onNonOKHttpResponse((HttpResponseException) this.exception);
				break;
			}
			if (result) {
				Log.d(PerkStorage.class.getSimpleName(), "Perk update successful.");
			}
		}
    }
}
