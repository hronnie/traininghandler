package com.codeproj.traininghandler.common;

import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.round;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public abstract class GenericAPITest extends TestCase {
    protected String testRequestId;
    protected static final String RESOURCES="testresources";
    private ResourceBundle rb;
    
    public GenericAPITest(String name) {
        super(name);
        rb = ResourceBundle.getBundle(RESOURCES, Locale.UK);
    }

    private JSONObject extractResponseObject(HttpResponse response) {
        BufferedReader rd = null;
        String responseBody = null;
        try {
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            StringBuilder responseString = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                responseString.append(line);
            }
            responseBody = responseString.toString();
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(GenericAPITest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Failed to extract response body");
        } finally {
            try {
                rd.close();
            } catch (IOException ex) {
                Logger.getLogger(GenericAPITest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        JSONObject responseObj = null;        
        try {
            responseObj = new JSONObject(responseBody);
        } catch (JSONException ex) {
            Logger.getLogger(GenericAPITest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Failed to parse response body into JSON object");
        }
        
        return responseObj;
    }
    
    private JSONObject checkWrapper(HttpResponse response, boolean expectedSuccess) {
        //check we get a status 200 back 
        int statusCode = response.getStatusLine().getStatusCode();
        JSONObject responseObj = null;
        
        if (statusCode == 200 || statusCode == 500) {
            try {
                // Now examine the incoming response
                responseObj = extractResponseObject(response);
                assertEquals(responseObj.get("success"), expectedSuccess);
                assertEquals(responseObj.get("requestId").toString(), testRequestId);
            } catch (JSONException ex) {
                Logger.getLogger(GenericAPITest.class.getName()).log(Level.SEVERE, null, ex);
                fail("Failed to extract members from response");
            }
        } else {
            fail("Got an unexpected HTTP status " + statusCode);
        }
        
        return responseObj;
    }

    protected JSONObject postRequest(String resource, List<NameValuePair> parameters, boolean expectedSuccess) {        
        String url = getResource(resource);
        
//        testRequestId = Long.toString(round(random() * pow(36, 6)), 36).toUpperCase();
//        parameters.add(new BasicNameValuePair("requestId", testRequestId));
        
        HttpResponse response = null;
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
    		HttpPost request = new HttpPost(url);
    		request.addHeader("accept", "application/json");
    		request.setEntity(new UrlEncodedFormEntity(parameters));
    		response = httpclient.execute(request);
        } catch (IOException ex) {
            Logger.getLogger(GenericAPITest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Failed to post request to " + url);
        }
        
        assertNotNull(response);
        JSONObject responseObj = checkWrapper(response, expectedSuccess);
        try {
            if (expectedSuccess) {
                return (JSONObject) responseObj.get("response");
            } else {
                return (JSONObject) responseObj.get("error");
            }
        } catch (JSONException ex) {
            Logger.getLogger(GenericAPITest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Failed to extract detailled object from JSON body");
            return null;
        }
    }

    protected Object getFieldValue(JSONObject obj, String fieldname) {
        try {
            // Check that the response corresponds to the above parameters
            return obj.get(fieldname);
        } catch (JSONException ex) {
            Logger.getLogger(GenericAPITest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Failed to extract field " + fieldname + " from JSON object");
        }
        return null;
    }
    
    protected String getResource(String key) {
        String value = rb.getString(key);
        assertNotNull(value);
        return value;
    }
}
