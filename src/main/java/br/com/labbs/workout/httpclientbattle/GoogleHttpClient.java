package br.com.labbs.workout.httpclientbattle;

import br.com.labbs.workout.httpclientbattle.shared.Env;
import br.com.labbs.workout.httpclientbattle.shared.HttpClient;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

@SuppressWarnings("unused")
public class GoogleHttpClient implements HttpClient<HttpRequest, HttpResponse> {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final HttpRequestFactory factory = HTTP_TRANSPORT.createRequestFactory();
    private static final String GOOGLE = "GOOGLE_HTTP_CLIENT";
    private static final GenericUrl GENERIC_URL = new GenericUrl(Env.URL_SERVER.get());

    @Override
    public String getClientName() {
        return GOOGLE;
    }

    @Override
    public HttpRequest newRequest(String url) {
        HttpRequest httpRequest = null;
        try {
            httpRequest = factory.buildGetRequest(GENERIC_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpRequest;
    }

    @Override
    public void addHeaderToRequest(HttpRequest httpRequest, String key, String value) {
        httpRequest.setHeaders(new HttpHeaders().set(key, value));
    }

    @Override
    public HttpResponse execRequest(HttpRequest httpRequest, int request_number) throws Exception {
        return httpRequest.execute();
    }

    @Override
    public int getResponseStatusCode(HttpResponse httpResponse) {
        return httpResponse.getStatusCode();
    }
}
