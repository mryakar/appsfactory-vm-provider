package de.appsfactory.vmprovider.util;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Class for exception messages.
 *
 * @author mryakar
 */
@Data
public class ExceptionResponse {
    private String timestamp;
    private String message;
    private String details;

    public ExceptionResponse(String message, String details) {
        setTimestamp(LocalDateTime.now().toString());
        setMessage(message);
        setDetails(details);
    }
}