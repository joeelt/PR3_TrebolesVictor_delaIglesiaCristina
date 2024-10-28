import java.io.*;
import java.rmi.RemoteException;

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

        private boolean addNodeRecursive(Person unaPersona, String level) { // TODO: hacer recursivo
            if(level.charAt(0) == 'R') {
                if(level.length() == 1) {
                    this.right = new NodeA(unaPersona);
                } else if(level.charAt(1) == 'R') {
                    this.right.right.info = unaPersona;
                } else if(level.charAt(1) == 'L') {
                    this.right.left.info = unaPersona;
                }
            } else if(level.charAt(0) == 'L') {
                if(level.charAt(1) == 'R') {
                    this.left.right.info = unaPersona;
                } else if(level.charAt(1) == 'L') {
                    this.left.left.info = unaPersona;
                }
            }
            return true;
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
            if(root == null) {
                throw new RemoteException("L'arbre esta buit");
            } else {
                root.preorderSaveRecursive(buw);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }

    }

    public  boolean isFrom(String place) {
        if(root == null) {
            return false;
        } else if(root.info.getPlaceOfOrigin().equals(place)) {
            return true;
        }
        return false;
    }

    public boolean isDescentFrom(String place) {
        if(root == null) {
            return false;
        } else {
            return root.isDescentFromRecursive(place);
        }
    }

    public int howManyParents() {
        if(root == null) {
            return 0;
        } else if(root.left == null && root.right == null) {
            return 0;
        } else if(root.left != null && root.right != null) {
            return 2;
        } else {
            return 1;
        }
    }

    public int HowManyGrandParents() {
        if(root == null) {
            return 0;
        } else {
            return root.countNodesRecursive();
        }
    }

    public boolean marriedParents() {
        if(root == null) {
            return false;
        } else return root.left.info.getMaritalStatus() == 2 && root.right.info.getMaritalStatus() == 2;
    }
}
