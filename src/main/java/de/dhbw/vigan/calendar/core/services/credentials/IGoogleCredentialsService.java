package de.dhbw.vigan.calendar.core.services.credentials;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * Represents the service that is used to retrieve credentials.
 */
public interface IGoogleCredentialsService {
    /**
     * Gets credentials that are used to communicate with the Google Calendar API.
     */
    Credential getCredentials(NetHttpTransport netHttpTransport) throws Exception;
}