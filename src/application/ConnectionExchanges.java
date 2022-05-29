package application;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class ConnectionExchanges {


	ConnectionExchanges(){
	
	}
	
	ConnectionExchanges(String typeIn,String typeOut,double amount){
		
	}
	
	
	public String convertirDivisa(String in, String out,double value) {
		
		HttpClient client  = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.exchangerate.host/convert?from="+in+"&to="+out+"&amount="+value+"&places=2")).build();
		String result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenApply(ConnectionExchanges::parse)
				.join();
		
		return result;
	}
	
	public final static String parse (String responseBody) {
		
		JSONObject results = new JSONObject(responseBody);
		double rate = results.getDouble("result");
		String parsedResponse = String.valueOf(rate);
		return parsedResponse;
	}
		
}
