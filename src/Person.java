public class Person {
    public static final int WINDOWED = 0;
    public static final int DIVORCED = 1;
    public static final int MARRIED = 2;
    public static final int SINGLE = 3;

    private static int maritalStatus;
    private static String placeOfOrigin;
    private static String name;

    public Person(String name, String placeOfOrigin, int maritalStatus) {
        this.name = name;
        this.placeOfOrigin = placeOfOrigin;
        this.maritalStatus = maritalStatus;
    }

    public Person(String formattedString){
        String[] info = formattedString.split(", |: ");
        this.name = info[0];
        this.placeOfOrigin = info[1];
        this.maritalStatus = Integer.parseInt(info[2]);
    }

    public static String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public static int getMaritalStatus() {
        return maritalStatus;
    }

    public String getMaritalStatusString() {
        switch (maritalStatus){
            case WINDOWED:
                return "Windowed";
            case DIVORCED:
                return "Divorced";
            case MARRIED:
                return "Married";
            case SINGLE:
                return "Single";
            default:
                return "Unkown";
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
