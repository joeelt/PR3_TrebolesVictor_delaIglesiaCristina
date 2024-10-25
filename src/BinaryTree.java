import java.io.*;

public class BinaryTree {
    protected class NodeA{
        protected NodeA right;
        protected NodeA left;
        protected Person info;

        protected NodeA(Person info) {
            this.info = info;
        }

        protected NodeA(Person info, NodeA left, NodeA right) {
            this.info = info;
            this.left = left;
            this.right = right;
        }

        private void preorderSaveRecursive(BufferedWriter buw) {
            if (left != null) {
                left.preorderSaveRecursive(buw);
            }
            if (right != null) {
                right.preorderSaveRecursive(buw);
            }
        }

        private boolean addNodeRecursive(Person unaPersona, String level) {
            return false;
        }

        private void displayTreeRecursive(int level) {

        }

        private void removePersonRecursive(String name) {}

        private boolean isDescentFromRecursive(String place) {
         return false;
        }

        private int countNodesRecursive() {
            return 0;
        }
    }

    protected NodeA root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(String filename) {
        try {
            BufferedReader bur = new BufferedReader(new FileReader(filename));
            root = preorderLoad(bur);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }


    }

    public String getName() {
        return root.info.getName();
    }

    private NodeA preorderLoad(BufferedReader bur) {
        return null;
    }

    public boolean addNode(Person unaPersona, String level) {
        return root.addNodeRecursive(unaPersona, level);
    }

    public void displayTree() {
        root.displayTreeRecursive(0);
    }

    public void preorderSave() {
        try {
            BufferedWriter buw = new BufferedWriter(new FileWriter(root.info.getName()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }

    }

    public  boolean isFrom(String place) {
        return false;
    }

    public boolean isDescentFrom(String place) {
        return false;
    }

    public int howManyParents() {
        return 0;
    }

    public boolean marriedParents() {
        return false;
    }
}
