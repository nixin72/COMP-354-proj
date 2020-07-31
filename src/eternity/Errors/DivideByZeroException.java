package Errors;

public class DivideByZeroException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DivideByZeroException() {}

  public DivideByZeroException(String message) {
    super(message);
  }

  public DivideByZeroException(Throwable cause) {
    super(cause);
  }

  public DivideByZeroException(String message, Throwable cause) {
    super(message, cause);
  }

  public DivideByZeroException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
