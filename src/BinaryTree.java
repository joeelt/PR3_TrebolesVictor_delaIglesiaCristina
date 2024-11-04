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
            if(this.right != null) {
                if(this.right.info != null) {
                    space = 0;
                    while (level >= space) {
                        space++;
                        System.out.print("   ");
                    }
                    System.out.println(this.right.info.getName());
                } else {
                    System.out.println();
                }
                this.right.displayTreeRecursive(level + 1);
            }
            if(this.left != null) {
                if(this.left.info != null) {
                    space = 0;
                    while (level >= space) {
                        space++;
                        System.out.print("   ");
                    }
                    System.out.println(this.left.info.getName());
                } else {
                    System.out.println();
                }
                this.left.displayTreeRecursive(level + 1);
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
                if(this.left != null) {
                    return this.left.isDescentFromRecursive(place);
                }
                if(this.right != null) {
                    return this.right.isDescentFromRecursive(place);
                }
            }
            return false;
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }


    }

    public String getName() {
        return root.info.getName();
    }

    private NodeA preorderLoad(BufferedReader bur) {
        NodeA nou = new NodeA(null);
        NodeA aux = nou;
        String[] s;
        int level = 0;
        boolean r = false;
        try {
            String line = bur.readLine();
            while (line != null) {
                if(level == 0) {
                    aux.info = new Person(line);
                    line = bur.readLine();
                    level++;
                    aux = aux.left;
                } else {
                    s = line.split(";");
                    if(s.length == 1) {
                        aux.info = new Person(line);
                        line = bur.readLine();
                    } else if(s.length == 2) {
                        aux.right.info = null;
                    } else if(s.length == 3) {
                        aux.right.info = null;
                        aux.left.info = null;
                    }
                    if(r) {
                        r = false;
                        aux = aux.right;
                    } else {
                        r = true;
                        aux = aux.left;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
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
