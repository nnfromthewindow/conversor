package application;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectionExchanges {

	public static void main(String[] args) {
	

	HttpClient client  = HttpClient.newHttpClient();
	HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.exchangerate.host/latest")).build();
	client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
	.thenApply(HttpResponse::body).thenAccept(System.out::println).join();

	}
	
	public static String parse (String responseBody) {
		JSONArray exchangeRates = new JSONArray (responseBody);
		for (int i=0; i< exchangeRates.length();i++) {
			JSONObject rates = exchangeRates.getJSONObject(i);
			String rate = rates.getString("rates");
			System.out.println(rate);
		}
		return null;
	}
	
	
	
//String url_str = "https://api.exchangerate.host/latest";
//
//URL url = new URL(url_str);
//HttpURLConnection request = (HttpURLConnection) url.openConnection();
//request.connect();
//
//JsonParser jp = new JsonParser();
//JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
//JsonObject jsonobj = root.getAsJsonObject();
//
//String req_result = jsonobj.get("result").getAsString();

		
}
