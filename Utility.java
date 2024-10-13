public class Utility {


    //  to set a specific bit
    public static int setBit(int number, int bitPosition) {
        return number | (1 << bitPosition);
    }

    //  to clear a specific bit
    public static int clearBit(int number, int bitPosition) {
        return number & ~(1 << bitPosition);
    }

    //  to toggle a specific bit
    public static int toggleBit(int number, int bitPosition) {
        return number ^ (1 << bitPosition);
    }

    //  to get the value of a specific bit
    public static boolean getBit(int number, int bitPosition) {
        return (number & (1 << bitPosition)) != 0;
    }

    //  for binary addition
    public static int Addition(int a, int b) {
        return a | b;
    }

    //  for binary subtraction
    public static int Subtraction(int a, int b) {
        return a | ~b;
    }

    //  for binary multiplication
    public static int Multiplication(int a, int b) {
        return a * b;
    }

    //  for binary division
    public static int binaryDivision(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        return a / b;
    }

    // Set bit for long
    public static long setBit(long number, int bitPosition) {
        return number | (1L << bitPosition);
    }

    // Clear bit for long
    public static long clearBit(long number, int bitPosition) {
        return number & ~(1L << bitPosition);
    }

    // Toggle bit for long
    public static long toggleBit(long number, int bitPosition) {
        return number ^ (1L << bitPosition);
    }

    // Get bit for long
    public static boolean getBit(long number, int bitPosition) {
        return (number & (1L << bitPosition)) != 0;
    }
    // to set a specific bit
    public static int setBit(byte number, int bitPosition) {
        return number | (1 << bitPosition);
    }

    //  to clear a specific bit
    public static int clearBit(byte number, int bitPosition) {
        return number & ~(1 << bitPosition);
    }

    //  to toggle a specific bit
    public static int toggleBit(byte number, int bitPosition) {
        return number ^ (1 << bitPosition);
    }

    //  to get the value of a specific bit
    public static boolean getBit(byte number, int bitPosition) {
        return (number & (1 << bitPosition)) != 0;
    }

    //  for binary addition
    public static int Addition(byte a, byte b) {
        return a + b;
    }

    //  for binary subtraction
    public static int Subtraction(byte a, byte b) {
        return a - b;
    }

    //  for binary multiplication
    public static int Multiplication(byte a, byte b) {
        return a * b;
    }

    //  for binary division
    public static int binaryDivision(byte a, byte b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        return a / b;
    }
    // to set a specific bit
    public static int setBit(short number, int bitPosition) {
        return number | (1 << bitPosition);
    }

    //  to clear a specific bit
    public static int clearBit(short number, int bitPosition) {
        return number & ~(1 << bitPosition);
    }

    //  to toggle a specific bit
    public static int toggleBit(short number, int bitPosition) {
        return number ^ (1 << bitPosition);
    }

    //  to get the value of a specific bit
    public static boolean getBit(short number, int bitPosition) {
        return (number & (1 << bitPosition)) != 0;
    }

    //  for binary addition
    public static int Addition(short a, short b) {
        return a + b;
    }

    //  for binary subtraction
    public static int Subtraction(short a, short b) {
        return a - b;
    }

    //  for binary multiplication
    public static int Multiplication(short a, short b) {
        return a * b;
    }

    //  for binary division
    public static int binaryDivision(short a, short b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        return a / b;
    }
}
