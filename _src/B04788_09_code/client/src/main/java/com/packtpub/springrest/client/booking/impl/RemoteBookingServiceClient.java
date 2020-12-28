package com.packtpub.springrest.client.booking.impl;

import com.packtpub.springrest.client.Booking;
import com.packtpub.springrest.client.auth.DigestAuthHttpRequestFactory;
import com.packtpub.springrest.client.booking.BookingServiceClient;
import com.packtpub.springrest.client.internal.ApiResponse;
import com.packtpub.springrest.client.internal.ResponseHandler;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

/**
 * Remote {@link BookingServiceClient} implementation.
 *
 * @author Ludovic Dewailly
 */
public class RemoteBookingServiceClient implements BookingServiceClient {

    public static enum Scheme {
        BASIC,
        DIGEST
    }

    private final Scheme authScheme = Scheme.BASIC;
    private final String serviceUrl;
    private final RestTemplate template;

    public RemoteBookingServiceClient(String serviceUrl, String username, String password) {
        if (serviceUrl == null) {
            throw new IllegalArgumentException("serviceUrl cannot be null");
        }
        this.serviceUrl = serviceUrl;
        if (username == null) {
            throw new IllegalArgumentException("username cannot be null");
        }
        if (password == null) {
            throw new IllegalArgumentException("password cannot be null");
        }
        switch (authScheme) {
            case BASIC:
                template = new RestTemplate();
                String credentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
                template.getInterceptors().add((request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Basic " + credentials);
                    return execution.execute(request, body);
                });
                break;
            case DIGEST:
                template = new RestTemplate(createRequestFactory(serviceUrl, username, password));
                break;
            default:
                throw new IllegalArgumentException("Unsupported authentication scheme " + authScheme);
        }
    }

    private static HttpComponentsClientHttpRequestFactory createRequestFactory(String serviceUrl, String username,
                                                                               String password) {
        String[] tokens = serviceUrl.split("://");
        String protocol = tokens[0];
        tokens = tokens[1].split(":");
        HttpHost host = new HttpHost(tokens[1], Integer.parseInt(tokens[1]), protocol);
        CredentialsProvider provider = new BasicCredentialsProvider();
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        provider.setCredentials(AuthScope.ANY, credentials);
        return new DigestAuthHttpRequestFactory(host, client);
    }

    @Override
    public Booking getBooking(long bookingId) {
        if (bookingId <= 0) {
            throw new IllegalArgumentException("bookingId must be greater than zero");
        }
        ParameterizedTypeReference<ApiResponse<Booking>> typeReference = new ParameterizedTypeReference<ApiResponse<Booking>>() {};
        return (Booking) ResponseHandler.handle(
                () -> template.exchange(serviceUrl + "/bookings/" + bookingId, HttpMethod.GET, null, typeReference).getBody());
    }
}
