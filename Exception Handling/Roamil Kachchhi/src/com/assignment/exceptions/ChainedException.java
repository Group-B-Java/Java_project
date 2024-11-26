public class Main {
    public static void main(String[] args) {
        try {
            throw new CustomException("This is a custom exception without a cause.");
        } catch (CustomException e) {
            e.printStackTrace();
        }

        try {
            throw new CustomException("This is a custom exception with a cause.", new NullPointerException("Cause: Null value"));
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
}
