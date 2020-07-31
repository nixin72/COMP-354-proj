package Errors;

public class SquareRootOfNegativeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public SquareRootOfNegativeException() {}

  public SquareRootOfNegativeException(String message) {
    super(message);
  }

  public SquareRootOfNegativeException(Throwable cause) {
    super(cause);
  }

  public SquareRootOfNegativeException(String message, Throwable cause) {
    super(message, cause);
  }

  public SquareRootOfNegativeException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
