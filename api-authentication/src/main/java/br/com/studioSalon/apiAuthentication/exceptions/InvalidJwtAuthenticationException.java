package br.com.studioSalon.apiAuthentication.exceptions;

public class InvalidJwtAuthenticationException extends RuntimeException {
  public InvalidJwtAuthenticationException(String message) {
    super(message);
  }
}
