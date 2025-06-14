package CodeWare.store.exception;
public class CredenciaisInvalidasException extends RuntimeException {

    public CredenciaisInvalidasException() {
        super("Login ou senha inválidos.");
    }
    
}
