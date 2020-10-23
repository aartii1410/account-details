package com.springboot.rest.accounttransactions.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  private static final String ERRORS = "errors";
  private static final String ERROR = "error";
  private static final String STATUS = "status";
  private static final String MESSAGE = "message";

  @Autowired
  private ErrorAttributes errorAttributes;


  @ExceptionHandler({AccountNumberNotFoundException.class})
  @ResponseStatus(NOT_FOUND)
  @ResponseBody
  public Map<String, Object> handleHttpNotFoundException(WebRequest request,
      Exception ex) {
    return notFound(request, ex.getMessage());
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(value = BAD_REQUEST)
  @ResponseBody
  public Map<String, Object> handleBadRequestException(WebRequest request,
      BadRequestException ex) {
    return badRequest(request, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ResponseBody
  public Map<String, Object> handleException(WebRequest request, Exception ex) {
    log.error("Error occurred servicing the request with message {} ", ex.getMessage(), ex);
    Map<String, Object> result = this.errorAttributes.getErrorAttributes(request, false);
    result.put(STATUS, INTERNAL_SERVER_ERROR.value());
    result.put(ERROR, "Internal Server Error");
    result.put(MESSAGE, "An internal server error occurred");
    return result;
  }

  private Map<String, Object> badRequest(WebRequest request, String message) {
    return responseBody(request, message, BAD_REQUEST);
  }

  private Map<String, Object> notFound(WebRequest request, String message) {
    return responseBody(request, message, NOT_FOUND);
  }

  private Map<String, Object> responseBody(WebRequest request, String message, HttpStatus status) {
    Map<String, Object> result = this.errorAttributes.getErrorAttributes(request, false);
    result.put(STATUS, status.value());
    result.put(ERROR, status.getReasonPhrase());
    result.put(MESSAGE, message);
    return result;
  }
}