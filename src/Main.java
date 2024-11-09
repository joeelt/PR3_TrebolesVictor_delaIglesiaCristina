import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        String folderPath = "Files";
        //Students students = exemples();
        Students students = readAllStudents(folderPath);
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
                    sortir = true;
                    break;
                default:
                    System.out.println("Opció incorrecta");

            }
        }

    }

    //private static void testRemoveStudents(Students students) {
        //System.out.println("Nom estudiant: ");
        //String nom = scanner.next();
        //students.removeStudent(nom);

    //}

    private static Students readAllStudents(String folderPath) {
        Students students = new Students();
        File folder = new File(folderPath);
        File[] listFiles = folder.listFiles();
        if (listFiles != null){
            for (File file : listFiles){
                if (file.isFile() && file.getName().endsWith(".txt")){
                    System.out.println("Alumne carregat des del fitxer: " + file.getName());
                    BinaryTree tree = new BinaryTree(file.getPath());
                    students.addStudent(tree);
                }
            }
        }
        return students;
    }

    private static void saveAllStudents(Students studentsList) throws IOException {
       String folderPath = "Files";
       File file = new File(folderPath);

         if (!file.exists()) {
              file.mkdir();
         }

         for (String name : studentsList.getAllStudents()){
             BinaryTree tree = studentsList.getStudent(name);

             if (tree != null){
                 tree.preorderSave();
             } else {
                 System.out.println("No s'ha pogut guardar l'alumne: " + name);
             }
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
        String name = scanner.next();

        BinaryTree studentTree = studentsList.getStudent(name);
        if (studentTree != null){
            studentTree.displayTree();
        } else {
            System.out.println("Estudiant no trobat.");
        }

    }

    private static void addNewStudent(Students studentsList) {

        System.out.println("Introdueix el nom de l'estudiant:");
        String name = scanner.next();
        System.out.println("Introdueix el lloc d'origen de l'estudiant:");
        String origin = scanner.next();
        System.out.println("Introdueix l'estat civil de l'estudiant (0: Solter, 1: Casat, 2: Divorciat, 3: Viduo):");
        int maritalStatus = scanner.nextInt();

        Person newPerson = new Person(name, origin, maritalStatus);
        BinaryTree newTree = new BinaryTree();
        newTree.addNode(newPerson, "");
        studentsList.addStudent(newTree);
    }

    private static void modifyStudent(Students studentsList) {

        System.out.println("Introdueix el nom de l'estudiant:");
        String name = scanner.next();
        BinaryTree studentTree = studentsList.getStudent(name);
        if (studentTree != null) {
            System.out.println("1. Afegir membre de la família.");
            System.out.println("2. Eliminar membre de la família.");
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Introdueix el nom del membre:");
                    String memberName = scanner.next();
                    System.out.println("Introdueix el lloc d'origen del membre:");
                    String memberPlaceOfOrigin = scanner.next();
                    System.out.println("Introdueix l'estat civil del membre (0: Solter, 1: Casat, 2: Divorciat, 3: Viduo):");
                    int memberMaritalStatus = scanner.nextInt();
                    System.out.println("Introdueix el nivell de l'arbre (ex: LLR per afegir a l'esquerra-esquerra-dreta):");
                    String level = scanner.next();

                    Person newMember = new Person(memberName, memberPlaceOfOrigin, memberMaritalStatus);
                    studentTree.addNode(newMember, level);
                    break;
                case 2:
                    System.out.println("Introdueix el nom del membre a eliminar:");
                    String memberNameToRemove = scanner.next();
                    if (studentTree.removePerson(memberNameToRemove)){
                        System.out.println("Eliminat correctament");
                    } else {
                        System.out.println("No s'ha pogut eliminar.");
                    }
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
        int numAlumnes = 0;
        int countFromStudentCity = 0;
        int countFromFamilyCity = 0;
        int unicProgenitor = 0;
        int totalGrandParents = 0;
        int marriedParents = 0;

        System.out.println("Introdueix la ciutat on ha nascut l'estudiant:");
        String studentCity = scanner.next();
        System.out.println("Introdueix la ciutat de procedència de la família:");
        String familyCity = scanner.next();

        ArrayList<String> studentNames = studentsList.getAllStudents();

        for (String name : studentNames){
            numAlumnes++;
            BinaryTree tree = studentsList.getStudent(name);
            if(tree.isFrom(studentCity)) {
                countFromStudentCity++;
            }
            if(tree.isDescentFrom(familyCity)) {
                countFromFamilyCity++;
            }
            if(tree.howManyParents() == 1) {
                unicProgenitor++;
            }
            System.out.println(tree.howManyGrandParents());
            if(tree.howManyGrandParents() >= 2) {
                totalGrandParents++;
            }
            if(tree.marriedParents()) {
                marriedParents++;
            }
        }


        System.out.println("Nombre d'alumnes total: " + numAlumnes);
        System.out.println("Hi ha " + countFromStudentCity + " alumnes de " + studentCity);
        System.out.println("Hi ha " + countFromFamilyCity + " alumnes descendents de " + familyCity);
        System.out.println("Hi ha " + unicProgenitor + " alumnes amb un únic progenitor.");
        System.out.println("Hi ha " + marriedParents + " alumnes amb progenitors no casats.");
        System.out.println("Hi ha " + totalGrandParents + " alumnes amb dos o més avis o àvies.");
    }
}