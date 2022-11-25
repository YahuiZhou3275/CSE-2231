/**
 * Simple class representing a 7-digit phone number in the form "XXX-XXXX"
 * for a phone in the immediate OSU area.
 */
public class PhoneNumber {

    /**
     * The phone number representation.
     */
    private String rep;

    /**
     * Constructor. {@code pNum} must be in the form "XXX-XXXX" where each
     * "X" is a digit '0'-'9'.
     */
    public PhoneNumber(String pNum) {
        this.rep = pNum;
    }

    ...

    @Override
    public int hashCode() {
        int radix = 10;
        int length = this.rep.length();
        int result = 0;
        for (int i = 0; i < length; i++) {
            char digit = this.rep.charAt(i);
            result += Character.digit(digit, radix);
        }
        return result;
    }

    ...
}