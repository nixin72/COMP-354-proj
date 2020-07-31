package Errors;

public class NegativePowerOfZeroException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NegativePowerOfZeroException() {}

  public NegativePowerOfZeroException(String message) {
    super(message);
  }

  public NegativePowerOfZeroException(Throwable cause) {
    super(cause);
  }

  public NegativePowerOfZeroException(String message, Throwable cause) {
    super(message, cause);
  }

  public NegativePowerOfZeroException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
