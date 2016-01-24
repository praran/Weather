package com.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by pradeep on 23/01/2016.
 */
public class WebPageProbe implements Probe {

    private String url;
    private String stringToCheckFor;

    public WebPageProbe(String url, String stringToCheckFor) {
        this.url = url;
        this.stringToCheckFor = stringToCheckFor;
    }

    @Override
    public boolean passes() {
        BufferedReader in = null;
        try {
            URL u = new URL(url);
            URLConnection connection = u.openConnection();
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null){
                 if(inputLine.contains(stringToCheckFor)){
                      return true;
                 }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  false;
    }

    @Override
    public String getFailureDescription() {
        return "hogo ninakkan";
    }


}
