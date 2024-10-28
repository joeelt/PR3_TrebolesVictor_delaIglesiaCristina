import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Person personaRosa = new Person("Rosa", "Barcelona", Person.MARRIED);
        int op = 0;
        Students students = new Students();
        System.out.println("Menú Principal:");
        System.out.println("1. Mostrar llista d'estudiants");
        System.out.println("2. Mostrar família d'un estudiant");
        System.out.println("3. Afegir un estudiant");
        System.out.println("4. Modificar un estudiant");
        System.out.println("5. Mostrar el informe");
        System.out.println("6. Guardar i Sortir");
        System.out.println("Tria una opció");
        op = scanner.nextInt();

        switch (op) {
            case 1:
                displayAllStudentsNames(students);
                break;
            case 2:

        }

    }

    private static Students readAllStudents(String folderPath) {
        return null;
    }

    private static void saveAllStudents(Students studentsList) {
    }

    private static void displayAllStudentsNames(Students studentsList) {

    }

    private static Students exemples() {
        return null;
    }

    private static void showStudentFamily(Students studentsList) {

    }

    private static void addNewStudent(Students studentsList) {

    }

    private void modifyStudent(Students studentsList) {

    }

    private void mostrarInforme(Students studentsList) {

    }
}