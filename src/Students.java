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
        Node aux = this.first;
        Node nou;

        String name = nouEstudiant.getName().toLowerCase();

        if(aux == null) {
           this.first = new Node(nouEstudiant);
        } else {
            while (aux.next != null){
                if (aux.next.info.getName().toLowerCase().compareTo(name) > 0){
                    nou = new Node(nouEstudiant);
                    nou.next = aux.next;
                    aux.next = nou;
                    return;
                } else if(aux.next.info.getName().toLowerCase().compareTo(name) == 0) {
                    throw new IllegalArgumentException("L'estudiant ja existeix");
                }
                aux = aux.next;
            }
            aux.next = new Node(nouEstudiant);
        }

    }

    public void removeStudent(String name){
        Node aux = this.first;
        Node prev = null;

        while(aux != null && !aux.info.getName().equalsIgnoreCase(name)){
            prev = aux;
            aux = aux.next;
        }

        if (aux != null){
            if (prev == null){
                first = aux.next;
            } else {
                prev.next = aux.next;
            }
        }


    }

    public BinaryTree getStudent(String name){
        Node aux = first;
        while (aux != null && !aux.info.getName().equalsIgnoreCase(name)){
            aux = aux.next;
        }

        if (aux != null){
            return aux.info;
        } else {
            return null;
        }
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
