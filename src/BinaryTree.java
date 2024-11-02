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
            try {
                buw.newLine();
                buw.write(this.info.toString());
                if(this.left == null) {
                    buw.write(";");
                } else {
                    buw.newLine();
                    this.left.preorderSaveRecursive(buw);
                }
                if(this.right == null) {
                    buw.write(" ;");
                } else {
                    buw.newLine();
                    this.right.preorderSaveRecursive(buw);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private boolean addNodeRecursive(Person unaPersona, String level) { // TODO: hacer recursivo
            if(level.isEmpty()) {
                this.info = unaPersona;
                return true;
            } else if(level.charAt(0) == 'R') {
                if(this.right == null) {
                    this.right = new NodeA(null);
                }
               this.right.addNodeRecursive(unaPersona, level.substring(1));
               return true;
            } else if(level.charAt(0) == 'L') {
                if(this.left == null) {
                    this.left = new NodeA(null);
                }
                this.left.addNodeRecursive(unaPersona, level.substring(1));
                return true;
            }
            return false;
        }

        private void displayTreeRecursive(int level) {
            if(level == 0) {
                System.out.println(this.info);
            }
            if(this.right != null) {
                System.out.println(this.right.info);
                this.right.displayTreeRecursive(level + 1);
            }
            if(this.left != null) {
                System.out.println(this.left.info);
                this.left.displayTreeRecursive(level + 1);
            }
        }

        private void removePersonRecursive(String name) {


        }

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
        if(root == null) {
            root = new NodeA(unaPersona);
            return true;
        }
        return root.addNodeRecursive(unaPersona, level);
    }

    public void displayTree() {
        System.out.println("Arbre binari estructura:");
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

    public void removePerson(String name) {
        root.removePersonRecursive(name);

    }

    public  boolean isFrom(String place) {
        if(root == null) {
            return false;
        } else return root.info.getPlaceOfOrigin().equals(place);
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
