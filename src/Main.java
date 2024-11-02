import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Students students = exemples();
        boolean sortir = false;
        while (!sortir) {
            int op = 0;

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
                    showStudentFamily(students);
                    break;
                case 3:
                    addNewStudent(students);
                    break;
                case 4:
                    modifyStudent(students);
                    break;
                case 5:
                    mostrarInforme(students);
                    break;
                case 6:
                    saveAllStudents(students);
                    break;
                default:
                    System.out.println("Opció incorrecta");

            }
        }

    }

    private static Students readAllStudents(String folderPath) {
        Students students = new Students();
        File folder = new File(folderPath);
        File[] listFiles = folder.listFiles();
        if (listFiles != null){
            for (File file : listFiles){
                if (file.isFile() && file.getName().endsWith(".txt")){
                    BinaryTree tree = new BinaryTree(file.getPath());
                    students.addStudent(tree);
                }
            }
        }
        return students;
    }

    private static void saveAllStudents(Students studentsList) {

        ArrayList<String> studentsNames = studentsList.getAllStudents();
        for (String name : studentsNames){
            BinaryTree studentTree = studentsList.getStudent(name);
            studentTree.preorderSave();
        }

    }

    private static void displayAllStudentsNames(Students studentsList) {

        ArrayList<String> studentNames = studentsList.getAllStudents();

        if (studentNames.isEmpty()){
            System.out.println("No hi ha estudiants.");
        }
        else {
            for (String name : studentNames){
                System.out.println(name);
            }
        }

    }

    private static Students exemples() {
        Students studentsList = new Students();
        BinaryTree tree = new BinaryTree();
        tree.addNode(new Person("Nicol", "Barcelona", Person.SINGLE), ""); // alumne
        tree.addNode(new Person("Ariel", "Girona", Person.DIVORCED), "L"); // progenitor
        tree.addNode(new Person("Noa", "Lleida", Person.DIVORCED), "R");  // progenitor
        tree.addNode(new Person("Kai", "Girona", Person.MARRIED), "LL"); // aví o àvia
        tree.addNode(new Person("Janis", "Girona", Person.MARRIED), "LR");// aví o àvia
        tree.addNode(new Person("Sam", "Lleida", Person.WIDOWED), "RL");// aví o àvia
        tree.displayTree();

        //opció 1: comprovem afegir progenitors d'un node que no existeix
        BinaryTree tree1 = new BinaryTree();
        tree1.addNode(new Person("Alex", "Barcelona", Person.SINGLE), "");
        tree1.addNode(new Person("Carme", "Lleida", Person.WIDOWED), "L");
        tree1.addNode(new Person("Marta", "Lleida", Person.DIVORCED), "LL");
        tree1.addNode(new Person("Joan", "Girona", Person.MARRIED), "RL");
        tree1.addNode(new Person("Pilar", "Girona", Person.MARRIED), "RR");
        tree1.displayTree();

        //opció 2: comprovem el mètode eliminar
        tree1 = new BinaryTree();
        tree1.addNode(new Person("Alex", "Barcelona", Person.SINGLE), "");
        tree1.addNode(new Person("Carme", "Lleida", Person.WIDOWED), "L");
        tree1.addNode(new Person("Marta", "Lleida", Person.DIVORCED), "LL");
        tree1.addNode(new Person("Mort", "Girona", Person.MARRIED), "R");
        tree1.addNode(new Person("Joan", "Girona", Person.MARRIED), "RL");
        tree1.addNode(new Person("Pilar", "Girona", Person.MARRIED), "RR");
        tree1.removePerson("Mort");
        tree1.displayTree();

        BinaryTree tree2 = new BinaryTree();
        tree2.addNode(new Person("Dana", "Tarragona", Person.SINGLE), "");
        tree2.addNode(new Person("Ester", "Reus", Person.MARRIED), "L");
        tree2.addNode(new Person("Oleguer", "Tarragona", Person.MARRIED), "R");
        tree2.addNode(new Person("Maria", "Barcelona", Person.DIVORCED), "LL");
        tree2.addNode(new Person("Clara", "Reus", Person.WIDOWED), "RL");
        tree2.displayTree();

        BinaryTree tree3 = new BinaryTree();
        tree3.addNode(new Person("Chris", "Madrid", Person.SINGLE), ""); // alumne
        tree3.addNode(new Person("Toni", "Barcelona", Person.SINGLE), "L"); // progenitor
        tree3.addNode(new Person("Mireia", "Barcelona", Person.MARRIED), "LR"); // avi o àvia
        tree3.addNode(new Person("Júlia", "València", Person.MARRIED), "LL"); // avi o àvia
        tree3.displayTree();

        BinaryTree tree4 = new BinaryTree();
        tree4.addNode(new Person("Morgan", "Girona", Person.SINGLE), ""); // alumne
        tree4.addNode(new Person("Marc", "Manresa", Person.MARRIED), "L"); // progenitor
        tree4.addNode(new Person("Laia", "Sabadell", Person.MARRIED), "R");  // progenitor
        tree4.addNode(new Person("Marta", "Manresa", Person.MARRIED), "RR"); // avi o àvia
        tree4.displayTree();


        BinaryTree tree5 = new BinaryTree();
        tree5.addNode(new Person("Riley", "París", Person.SINGLE), ""); // alumne
        tree5.addNode(new Person("Ona", "Londres", Person.MARRIED), "L"); // progenitor
        tree5.addNode(new Person("Nil", "París", Person.MARRIED), "R");  // progenitor
        tree5.addNode(new Person("Eulàlia", "Londres", Person.WIDOWED), "LL"); // avi o àvia
        tree5.addNode(new Person("Miquel", "Londres", Person.DIVORCED), "RR"); // avi o àvia
        tree5.addNode(new Person("Francesc", "París", Person.DIVORCED), "RL"); // avi o àvia
        tree5.displayTree();

        studentsList.addStudent(tree);
        studentsList.addStudent(tree1);
        studentsList.addStudent(tree2);
        studentsList.addStudent(tree3);
        studentsList.addStudent(tree4);
        studentsList.addStudent(tree5);

        return studentsList;
    }

    private static void showStudentFamily(Students studentsList) {

        System.out.println("Introdueix el nom de l'estudiant:");
        String name = scanner.nextLine();

        BinaryTree studentTree = studentsList.getStudent(name);
        if (studentTree != null){
            studentTree.displayTree();
        } else {
            System.out.println("Estudiant no trobat.");
        }

    }

    private static void addNewStudent(Students studentsList) {

        System.out.println("Introdueix el nom de l'estudiant:");
        String name = scanner.nextLine();
        System.out.println("Introdueix el lloc d'origen de l'estudiant:");
        String origin = scanner.nextLine();
        System.out.println("Introdueix l'estat civil de l'estudiant (0: Solter, 1: Casat, 2: Divorciat, 3: Viduo):");
        int maritalStatus = scanner.nextInt();

        Person newPerson = new Person(name, origin, maritalStatus);
        BinaryTree newTree = new BinaryTree();
        newTree.addNode(newPerson, "");
        studentsList.addStudent(newTree);
    }

    private static void modifyStudent(Students studentsList) {

        System.out.println("Introdueix el nom de l'estudiant:");
        String name = scanner.nextLine();
        BinaryTree studentTree = studentsList.getStudent(name);
        if (studentTree != null) {
            System.out.println("1. Afegir membre de la família.");
            System.out.println("2. Eliminar membre de la família.");
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Introdueix el nom del membre:");
                    String memberName = scanner.nextLine();
                    System.out.println("Introdueix el lloc d'origen del membre:");
                    String memberPlaceOfOrigin = scanner.nextLine();
                    System.out.println("Introdueix l'estat civil del membre (0: Solter, 1: Casat, 2: Divorciat, 3: Viduo):");
                    int memberMaritalStatus = scanner.nextInt();
                    System.out.println("Introdueix el nivell de l'arbre (ex: LLR per afegir a l'esquerra-esquerra-dreta):");
                    String level = scanner.nextLine();

                    Person newMember = new Person(memberName, memberPlaceOfOrigin, memberMaritalStatus);
                    studentTree.addNode(newMember, level);
                    break;
                case 2:
                    System.out.println("Introdueix el nom del membre a eliminar:");
                    String memberNameToRemove = scanner.nextLine();
                    studentTree.removePerson(memberNameToRemove);
                    break;
                default:
                    System.out.println("Opció incorrecta.");
                    break;
            }
        }
        else {
            System.out.println("Estudiant no trobat.");
        }

    }

    private static void mostrarInforme(Students studentsList) {

        System.out.println("Introdueix la ciutat on ha nascut l'estudiant:");
        String studentCity = scanner.nextLine();
        System.out.println("Introdueix la ciutat de procedència de la família:");
        String familyCity = scanner.nextLine();

        ArrayList<String> studentNames = studentsList.getAllStudents();
        int countFromStudentCity = 0;
        int countFromFamilyCity = 0;

        for (String name : studentNames) {
            BinaryTree studentTree = studentsList.getStudent(name);
            if (studentTree.isFrom(studentCity)) {
                countFromStudentCity++;
            }
            if (studentTree.isDescentFrom(familyCity)) {
                countFromFamilyCity++;
            }
        }

        System.out.println("Nombre d'estudiants nascuts a " + studentCity + ": " + countFromStudentCity);
        System.out.println("Nombre d'estudiants amb família procedent de " + familyCity + ": " + countFromFamilyCity);

    }
}