package com.codeproj.traininghandler.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public abstract class GenericAPITest extends TestCase {
    protected String testRequestId;
    protected static final String RESOURCES="testresources";
    protected static Long createdId;
    private ResourceBundle rb;
    
    public static final String TRUE_RESPONSE = "{\"booleanValue\":\"true\"}";
    
    public GenericAPITest(String name) {
        super(name);
        rb = ResourceBundle.getBundle(RESOURCES, Locale.UK);
    }

    private JSONObject extractResponseObject(HttpResponse response) {
        String responseBody = null;
        responseBody = extractResponseReturnText(response);
        
        JSONObject responseObj = null;        
        try {
            responseObj = new JSONObject(responseBody);
        } catch (JSONException ex) {
            Logger.getLogger(GenericAPITest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Failed to parse response body into JSON object");
        }
        
        return responseObj;
    }

	private String extractResponseReturnText(HttpResponse response) {
		String responseBody = "";
		BufferedReader rd = null;
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
		return responseBody;
	}

    protected JSONObject postRequest(String resource, List<NameValuePair> parameters, boolean expectedSuccess) {        
        String url = getResource(resource);
        
        HttpResponse response = null;
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
    		HttpPost request = new HttpPost(url);
    		request.addHeader("accept", "application/json");
    		request.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
    		response = httpclient.execute(request);
        } catch (IOException ex) {
            Logger.getLogger(GenericAPITest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Failed to post request to " + url);
        }
        
        assertNotNull(response);
        
        return extractResponseObject(response);
    }
    
    protected JSONObject getRequest(String resource, boolean expectedSuccess, String getParams) {        
    	HttpResponse response = doGetRequest(resource, getParams);
    	
    	return extractResponseObject(response);
    }

	private HttpResponse doGetRequest(String resource, String getParams) {
		String url = getResource(resource);
    	
    	if (!StringUtils.isEmpty(getParams)) {
    		url += getParams;
    	}
    	
    	HttpResponse response = null;
    	try {
    		HttpClient httpclient = HttpClientBuilder.create().build();
    		HttpGet request = new HttpGet(url);
    		request.addHeader("accept", "application/json");
    		response = httpclient.execute(request);
    	} catch (IOException ex) {
    		Logger.getLogger(GenericAPITest.class.getName()).log(Level.SEVERE, null, ex);
    		fail("Failed to post request to " + url);
    	}
    	
    	assertNotNull(response);
		return response;
	}
    
    protected String getRequestReturnTextResponse(String resource, boolean expectedSuccess, String getParams) {
    	HttpResponse response = doGetRequest(resource, getParams);
    	return extractResponseReturnText(response);
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
    
    protected boolean validateGenerateIdResponse(JSONObject responseObj) {
    	try {
    		Integer id = (Integer)responseObj.get("value");
			if (id > 0) {
				createdId = new Long(id);
				return true;
			}
			return false;
		} catch (JSONException e) {
			return false;
		}
    }
    
    protected boolean validateBooleanResponse(JSONObject responseObj) {
    	try {
    		String booleanValue = (String)responseObj.get("booleanValue");
    		if ("true".equals(booleanValue)) {
    			return true;
    		}
    		return false;
    	} catch (JSONException e) {
    		return false;
    	}
    }
    
    protected boolean confirmObjectNotFound(String response) {
    	if (response.contains("General error page")) {
    		return true;
    	}
    	return false;
    }
}
