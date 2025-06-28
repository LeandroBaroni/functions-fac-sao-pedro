package com.leandrobaroni2103.functions_fac_sao_pedro.exceptions;

public class ResponseException extends RuntimeException {
  private final String statusCode;
  private final int code;

  public ResponseException(String message) {
    super(message);
    this.statusCode = "@application/internal-error";
    this.code = 400;
  }

  public ResponseException(String message, String statusCode) {
    super(message);
    this.statusCode = statusCode;
    this.code = 400;
  }

  public ResponseException(String message, String statusCode, int code) {
    super(message);
    this.statusCode = statusCode;
    this.code = code;
  }
}
