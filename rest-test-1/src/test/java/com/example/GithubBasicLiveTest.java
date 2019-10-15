package com.example;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GithubBasicLiveTest {

    @Test
    public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived() throws ClientProtocolException, IOException {
        // Given
        final String name = randomAlphabetic(8);
        final HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);
        // When
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() throws ClientProtocolException, IOException {
        // Given
        final String jsonMimeType = MediaType.APPLICATION_JSON.toString();
        final HttpUriRequest request = new HttpGet("https://api.github.com/users/eugenp");
        // When
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
        final String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
    }

    @Test
    public void givenUserExists_whenUserInformationIsRetrieved_thenRetrievedResourceIsCorrect() throws ClientProtocolException, IOException {
        // Given
        final HttpUriRequest request = new HttpGet("https://api.github.com/users/eugenp");
        // When
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
        final GitHubUser resource = RetrieveUtil.retrieveResourceFromResponse(httpResponse, GitHubUser.class);
        assertThat("eugenp", Matchers.is(resource.getLogin()));
    }
}
