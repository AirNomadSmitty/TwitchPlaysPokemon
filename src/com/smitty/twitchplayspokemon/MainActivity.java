package com.smitty.twitchplayspokemon;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;



public class MainActivity extends ActionBarActivity{
	private WebView mWebView;
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updater_web);
        
	     mWebView = (WebView) findViewById(R.id.webview);
	     mWebView.getSettings().setJavaScriptEnabled(true);
	     mWebView.setWebChromeClient(new WebChromeClient());
	     
	     //Using the tag to track if we're filtering for important updates or not
	     mWebView.setTag(0);
	
	     // Finally make the WebView load something...
	     mWebView.loadUrl("http://www.reddit.com/live/nawsz3vn7ui6hdsgciytwcxadi/");
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
        switch (id){
        
        case R.id.action_settings:
            return true;
        case R.id.action_filter_important:
        	if ((Integer)mWebView.getTag()==0){
                mWebView.setTag(1);
        		mWebView.loadUrl("javascript:$('tr.thing').not(':has(strong)').css('display','none');$('.liveupdate-listing').bind('DOMNodeInserted',function(){$('tr.thing').not(':has(strong)').css('display','none')})");
        	}
        	else{
        		mWebView.setTag(0);
        		mWebView.loadUrl("javascript:$('tr.thing').css('display','inline');$('.liveupdate-listing').unbind('DOMNodeInserted')");
        	}
        	return true;
        case R.id.action_refresh_updates:
        	mWebView.setTag(0); 
            mWebView.reload();
            return true;
        default:
        	return super.onOptionsItemSelected(item);
        }
    }
    

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
