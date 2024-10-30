import java.util.ArrayList;

public class Students {

    private class Node{
        BinaryTree info;
        Node next;

        Node(BinaryTree info) {
            this.info = info;
            this.next = null;
        }
    }

    private Node first;

    public Students(){
        first = null;
    }

    public void addStudent(BinaryTree nouEstudiant){
        Node aux = new Node(nouEstudiant);
        Node prev = first;

        String name = nouEstudiant.getName().toLowerCase();

        while (aux.next != null && name.compareTo(aux.info.getName().toLowerCase()) > 0){
            prev = aux;
            aux = aux.next;

            if (prev == null){
                first = new Node(nouEstudiant);
                first.next = aux;
            } else {
                prev.next = new Node(nouEstudiant);
                prev.next.next = aux;
            }
        }
    }

    public void removeStudent(String name){
        Node aux = first;
        Node prev = null;

        while (aux != null && !aux.info.getName().equalsIgnoreCase(name)){
            prev = aux;
            aux = aux.next;
            if (aux != null){
                if (prev == null){
                    first = aux.next;
                } else {
                    prev.next = aux.next;
                }
            }
        }


    }

    public BinaryTree getStudent(String name){

        Node aux = first;

        while (aux != null && !aux.info.getName().equalsIgnoreCase(name)){
            aux = aux.next;
            if (aux != null){
                return aux.info;
            } else {
                return null;
            }

        }
        return null;
    }

    public ArrayList<String> getAllStudents(){
        ArrayList<String> students = new ArrayList<String>();
        Node aux = first;

        while (aux != null){
            students.add(aux.info.getName());
            aux = aux.next;
        }

        return students;
    }
}
