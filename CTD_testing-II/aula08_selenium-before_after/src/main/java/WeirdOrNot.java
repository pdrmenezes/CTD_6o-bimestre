public class WeirdOrNot {
    private int n;

    public WeirdOrNot() {
        this.n = n;
    }

    public void checkWeirdness(int n) {
        if (n % 2 != 0) {
            System.out.println("Weird");
        } else if (n % 2 == 0 && n >= 2 && n <= 5) {
            System.out.println("Not Weird");
        } else if (n % 2 == 0 && n >= 6 && n <= 20) {
            System.out.println("Weird");
        } else if (n % 2 == 0 && n > 20) {
            System.out.println("Not Weird");
        } else {
            System.out.println("oops");
        }
    }
}