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

    }

    public void addStudent(BinaryTree nouEstudiant){
        Node aux = new Node(nouEstudiant);
        Node prev = first;

        String name = nouEstudiant.getName().toLowerCase();

        while (aux.next != null && name.compareTo(aux.info.getName().toLowerCase()) > 0){
            prev = aux;
            aux = aux.next;
        }

        if (prev == null){
            first = new Node(nouEstudiant);
            first.next = aux;
        } else {
            prev.next = new Node(nouEstudiant);
            prev.next.next = aux;
        }
        
    }

    public void removeStudent(String name){

    }

    public BinaryTree getStudent(String name){
        return null;
    }

    public ArrayList<String> getAllStudents(){
        return null;
    }
}
