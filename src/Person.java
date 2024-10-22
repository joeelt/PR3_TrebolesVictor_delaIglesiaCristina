public class Person {
    public final int WINDOWED = 0;
    public final int DIVORCED = 0;
    public final int SINGLE = 0;

    private static int maritalStatus;
    private static String placeOfOrigin;
    private static String name;

    public Person(String name, String placeOfOrigin, int maritalStatus) {
        name = this.name;
        placeOfOrigin = this.placeOfOrigin;
        maritalStatus = this.maritalStatus;
    }

    public Person(String formattedString){

    }

    public static String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public static int getMaritalStatus() {
        return maritalStatus;
    }

    public String getMaritalStatusString() {
        return "";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
