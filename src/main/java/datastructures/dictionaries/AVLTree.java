package datastructures.dictionaries;

import cse332.datastructures.trees.BinarySearchTree;

/**
 * AVLTree must be a subclass of BinarySearchTree<E> and must use
 * inheritance and calls to superclass methods to avoid unnecessary
 * duplication or copying of functionality.
 * <p>
 * 1. Create a subclass of BSTNode, perhaps named AVLNode.
 * 2. Override the insert method such that it creates AVLNode instances
 * instead of BSTNode instances.
 * 3. Do NOT "replace" the children array in BSTNode with a new
 * children array or left and right fields in AVLNode.  This will
 * instead mask the super-class fields (i.e., the resulting node
 * would actually have multiple copies of the node fields, with
 * code accessing one pair or the other depending on the type of
 * the references used to access the instance).  Such masking will
 * lead to highly perplexing and erroneous behavior. Instead,
 * continue using the existing BSTNode children array.
 * 4. Ensure that the class does not have redundant methods
 * 5. Cast a BSTNode to an AVLNode whenever necessary in your AVLTree.
 * This will result a lot of casts, so we recommend you make private methods
 * that encapsulate those casts.
 * 6. Do NOT override the toString method. It is used for grading.
 * 7. The internal structure of your AVLTree (from this.root to the leaves) must be correct
 */

public class AVLTree<K extends Comparable<? super K>, V> extends BinarySearchTree<K, V> {
    private V prev;
    private class AVLNode extends BSTNode {
        public int height;

        public AVLNode(K key, V value) {
            super(key, value);
            height = 0;
        }
    }

    public AVLTree() {
        super();
        this.size = 0;
    }

    public V insert(K key, V value) {
        if(key == null || value == null) {
            throw new IllegalArgumentException();
        }
        prev = null;
        root = createTree((AVLNode) root, key, value);
        return prev;
    }

    private AVLNode createTree(AVLNode node, K key, V value) {
        if(node == null) {
            this.size++;
            return new AVLNode(key,value);
        } else if(node.key.compareTo(key) > 0){
             node.children[0] = createTree((AVLNode) node.children[0], key, value);
        } else if(node.key.compareTo(key) < 0) {
            node.children[1] = createTree((AVLNode) node.children[1], key, value);
        } else {
            prev = node.value;
            node.value = value;
        }
        return rotation(node);
    }

    private int heightOfNode(AVLNode node) {
        if(node == null) {
            return -1;
        }
        return node.height;
    }

    private void height(AVLNode node) {
        node.height = 1 + Math.max(heightOfNode((AVLNode) node.children[0]), heightOfNode((AVLNode) node.children[1]));
    }

    private AVLNode rotation(AVLNode node) {
        height(node);
        AVLNode child1 = (AVLNode)node.children[0];
        AVLNode child2 = (AVLNode)node.children[1];

        int balanceFactor = heightOfNode(child1) - heightOfNode(child2);
        if(balanceFactor < -1) {
            AVLNode grandChild1 = (AVLNode)node.children[1].children[0];
            AVLNode grandChild2 = (AVLNode)node.children[1].children[1];

            if(heightOfNode(grandChild2) > heightOfNode(grandChild1)) {
                node = left(node);
            } else {
                node = doubleRight(node);
            }
        } else if(balanceFactor > 1) {
            AVLNode grandChild1 = (AVLNode)node.children[0].children[0];
            AVLNode grandChild2 = (AVLNode)node.children[0].children[1];

            if(heightOfNode(grandChild1) >heightOfNode(grandChild2)) {
                node = right(node);
            } else {
                node = doubleLeft(node);
            }
        }
        return node;
    }

    private AVLNode left(AVLNode node) {
        AVLNode tmp = (AVLNode) node.children[1];
        node.children[1] = tmp.children[0];
        tmp.children[0] = node;
        height(node);
        height(tmp);
        return tmp;
    }

    private AVLNode doubleLeft(AVLNode node) {
        node.children[0] = left((AVLNode) node.children[0]);
        node = right(node);
        return node;
    }

    private AVLNode doubleRight(AVLNode node) {
        node.children[1] = right((AVLNode) node.children[1]);
        node = left(node);
        return node;
    }

    private AVLNode right(AVLNode node) {
        AVLNode tmp = (AVLNode) node.children[0];
        node.children[0] = tmp.children[1];
        tmp.children[1] = node;
        height(node);
        height(tmp);
        return tmp;
    }
}
