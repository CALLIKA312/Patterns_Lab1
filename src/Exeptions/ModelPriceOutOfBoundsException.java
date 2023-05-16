package Exeptions;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException(String message, int price) {
        super(message);
    }
}
