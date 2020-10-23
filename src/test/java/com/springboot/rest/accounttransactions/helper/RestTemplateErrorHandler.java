package com.springboot.rest.accounttransactions.helper;

import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * This class is registered with the rest template to allow the tests to assert on exceptions.
 */
public class RestTemplateErrorHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return false;
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
  }
}
