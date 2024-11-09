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
                String p = "";
                if (this.info != null) {
                    if(this.right == null && this.left == null) {
                        p = "; ; ";
                    } else if(this.right == null || this.left == null) {
                        p = "; ";
                    }

                    buw.write(this.info.toString() + p);
                    buw.newLine();
                } else {
                    buw.write(" ");
                    buw.newLine();
                }

                if (this.left != null) {
                    this.left.preorderSaveRecursive(buw);
                }

                if (this.right != null) {
                    this.right.preorderSaveRecursive(buw);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        private boolean addNodeRecursive(Person unaPersona, String level) {
            if(level.isEmpty()) {
                this.info = unaPersona;
                return true;
            } else if(level.charAt(0) == 'R') {
                if(this.right == null) {
                    this.right = new NodeA(null);
                }
               return this.right.addNodeRecursive(unaPersona, level.substring(1));
            } else if(level.charAt(0) == 'L') {
                if(this.left == null) {
                    this.left = new NodeA(null);
                }
                return this.left.addNodeRecursive(unaPersona, level.substring(1));
            }
            return false;
        }

        private void displayTreeRecursive(int level) {
            int space;
            if(level == 0) {
                System.out.println(this.info.getName());
            }
            if(this.left != null) {
                space = 0;
                while (level >= space) {
                    space++;
                    System.out.print("\t\t");
                }
                if(this.left.info != null) {
                    System.out.println(this.left.info.getName());
                } else {
                    System.out.println("*dead");
                }
                this.left.displayTreeRecursive(level + 1);
            }
            if(this.right != null) {
                space = 0;
                while (level >= space) {
                    space++;
                    System.out.print("\t\t");
                }
                if(this.right.info != null) {

                    System.out.println(this.right.info.getName());
                } else {
                    System.out.println("*dead");
                }
                this.right.displayTreeRecursive(level + 1);
            }
        }

        private boolean removePersonRecursive(String name)  {
            if(name != null) {
                if (this.info.getName().equals(name)) {
                    this.info = null;
                    return true;
                }
                if (this.left != null) {
                    this.left.removePersonRecursive(name);
                }
                if (this.right != null) {
                    this.right.removePersonRecursive(name);
                }
                return false;
            }
            return false;
        }

        private boolean isDescentFromRecursive(String place) {
            if(this.info != null && this.info.getPlaceOfOrigin().equals(place)) {
                return true;
            } else {
                return this.left != null && this.left.isDescentFromRecursive(place)
                        || this.right != null && this.right.isDescentFromRecursive(place);
            }
        }

        private int countNodesRecursive() {
            int l = 0, r = 0;
            if(this.info != null) {
                if(this.left != null) {
                    l = this.left.countNodesRecursive();
                }
                if (this.right != null){
                    r = this.right.countNodesRecursive();
                }
            }
            return 1 + l + r;
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
            displayTree();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public String getName() {
        return root.info.getName();
    }

    private NodeA preorderLoad(BufferedReader bur) throws IOException {
        NodeA nou = new NodeA(null);
        String[] s;
        String line = bur.readLine();
        if(!line.equals(" ")) {
            s = line.split(";");
            if(!s[0].equals(" ") ){
                nou.info = new Person(s[0]);
            } else {
                nou.info = null;
            }

            if(s.length == 1) {
                nou.left = preorderLoad(bur);
                nou.right = preorderLoad(bur);
            } else if(s.length == 2) {
                nou.left = preorderLoad(bur);
            }
        } else {
            nou.info = null;
            nou.left = preorderLoad(bur);
            nou.right = preorderLoad(bur);
        }
        return nou;
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
        if (root == null) {
            System.out.println("L'arbre està buit");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files/" + root.info.getName() + ".txt"))) {
            root.preorderSaveRecursive(writer);  // Llamada para guardar la estructura del árbol
            System.out.println("Alumne guardat correctament: " + root.info.getName());
        } catch (IOException e) {
            System.out.println("Error al guardar el fitxer de l'estudiant: " + root.info.getName());
        }
    }

    public boolean removePerson(String name) {
       return root.removePersonRecursive(name);
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
            return root.left != null && root.left.isDescentFromRecursive(place)
                    || root.right != null && root.right.isDescentFromRecursive(place);
        }
    }

    public int howManyParents() {
        if(root == null || root.left == null && root.right == null) {
            return 0;
        } else {
            if((root.left == null || root.left.info == null) && (root.right == null || root.right.info == null)) {
                return 0;
            } else if(root.left != null && root.left.info != null && root.right != null && root.right.info != null) {
                return 2;
            } else {

                return 1;
            }
        }
    }

    public int howManyGrandParents() {
        if(root == null) {
            return 0;
        } else {
            return root.countNodesRecursive()-1-howManyParents();
        }
    }

    public boolean marriedParents() {
        if(root == null) {
            return false;
        } else return root.left != null && root.right != null && root.left.info.getMaritalStatus() == 2 && root.right.info.getMaritalStatus() == 2;
    }
}
