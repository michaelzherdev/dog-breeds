package org.mzherdev.awsdogs;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtils {
	private HttpUtils() {
	}

	public static InputStream getContent(String link) {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(link);
		HttpResponse response;
		InputStream content = null;
		try {
			response = client.execute(request);
			content = response.getEntity().getContent();
		} catch (IOException e) {
			log.error("Some error happened during request to {}: {}", link, e.getMessage());
		}
		return content;
	}
}
