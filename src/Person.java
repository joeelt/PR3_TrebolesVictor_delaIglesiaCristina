public class Person {
    public static final int WIDOWED = 0;
    public static final int DIVORCED = 1;
    public static final int MARRIED = 2;
    public static final int SINGLE = 3;

    private int maritalStatus;
    private String placeOfOrigin;
    private String name;

    public Person(String name, String placeOfOrigin, int maritalStatus) {
        this.name = name;
        this.placeOfOrigin = placeOfOrigin;
        this.maritalStatus = maritalStatus;
    }

    public Person(String formattedString){
        String[] info = formattedString.split(", |: ");
        this.name = info[1].substring(1);
        this.placeOfOrigin = info[3].substring(1);
        String maritalStatus = info[5].substring(1);
        switch(maritalStatus) {
            case "Windowed":
                this.maritalStatus = WIDOWED;
                break;
            case "Married":
                this.maritalStatus = MARRIED;
                break;
            case "Divorced":
                this.maritalStatus = DIVORCED;
                break;
            case "Single":
                this.maritalStatus = SINGLE;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public String getMaritalStatusString() {
        switch (maritalStatus){
            case WIDOWED:
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

    public String toString() {
        return "Name: " + name + ", place of Origin: " + placeOfOrigin + ", marital status: " + getMaritalStatusString();
    }
}
