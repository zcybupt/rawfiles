package demo_03;

class Node {
    int value;
    Node parent;
    Node left;
    Node right;

    public Node(int value, Node parent, Node left, Node right) {
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
}

public class Code_50_BinarySearchTree {
    public static Node root;

    public static Node search(int ele) {
        Node tmp = root;

        while (tmp != null && tmp.value != ele) {
            if (ele < tmp.value) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }

        return tmp;
    }

    public static Node insert(int ele) {
        if (root == null) {
            root = new Node(ele, null, null, null);
            return root;
        }

        // 找到即将插入元素的父节点
        Node parent = null;
        Node tmp = root;
        while (tmp != null) {
            parent = tmp;
            if (ele < tmp.value) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }

        Node newNode = new Node(ele, parent, null, null);
        if (parent.value > ele) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        return newNode;
    }

    public static Node delete(Node deleteNode) {
        if (deleteNode != null) {
            Node nodeToReturn = null;
            if (deleteNode.left == null) {
                nodeToReturn = transplant(deleteNode, deleteNode.right);
            } else if (deleteNode.right == null) {
                nodeToReturn = transplant(deleteNode, deleteNode.left);
            } else {
                Node successorNode = getMinimum(deleteNode.right);
                if (successorNode.parent != deleteNode) {
                    transplant(successorNode, successorNode.right);
                    successorNode.right = deleteNode.right;
                    successorNode.right.parent = successorNode;
                }
                transplant(deleteNode, successorNode);
                successorNode.left = deleteNode.left;
                successorNode.left.parent = successorNode;
                nodeToReturn = successorNode;
            }
            return nodeToReturn;
        }
        return null;
    }

    public static Node transplant(Node nodeToReplace, Node newNode) {
        if (nodeToReplace.parent == null) {
            root = newNode;
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = newNode;
        } else {
            nodeToReplace.parent.right = newNode;
        }

        if (newNode != null) {
            newNode.parent = nodeToReplace.parent;
        }

        return newNode;
    }

    public static Node getMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    public static void main(String[] args) {
        root = new Node(5, null, null, null);
        insert(7);
        insert(3);
        insert(1);
        insert(4);
        insert(2);
        insert(3);

        System.out.println(root.value);
        System.out.println(root.left.value + " " + root.right.value);
        System.out.println(root.left.left.value + " " + root.left.right.value);
        System.out.println(root.left.left.right.value + " " + root.left.right.left.value);
    }
}
