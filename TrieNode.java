/**
 * This is an implementation of a node in a trie. 
 * @author Janu Verma
 * jv367@cornell.edu
 **/


import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class TrieNode{
    public TrieNode parent;
    public TrieNode[] children;
    public boolean isLeaf; // Quick way to check if any children exist
    public boolean isWord; // does this node represent teh last character
    public char character; //character the node represents

    
    /**
     * Constructor for top level root node.
     */
    public TrieNode()
    {
        children = new TrieNode[26];
        isLeaf = true;
        isWord = false;
    }

    
    /**
     * Constructor for the child node.
     */
    public TrieNode(char character){
        this();
        this.character = character;
    }

    
    
    /**
     * Adds a word to this node. This method is called recursively and
     * adds child nodes for each successive letter in the word,
     * therefore recursive calls will be made with partial words.
     * @param word - the word to add
     */
    protected void addWord(String word){
        isLeaf = false;
        int charPos = word.charAt(0) - 'a';
        if (children[charPos] == null){
            children[charPos] = new TrieNode(word.charAt(0));
            children[charPos].parent = this;
        }
        if (word.length() > 1){
            children[charPos].addWord(word.substring(1));
        }
        else{
            children[charPos].isWord = true;
        }
        
    }
    
    
    
    
    /**
     * Return the child TrieNode representing rthe given char,
     * or null if no node exists.
     * @param c
     * @return TrieNode
     */
    protected TrieNode getNode(char c){
        return children[c-'a'];
    }
    
 
    
    /**
     * checks if the given character is a children.
     * @param char c
     * @return true if c is a children
     */
    public boolean containsKey(char c){
        List followers = new ArrayList();
        for (TrieNode x : children) {
            if (x != null) {
                char y = x.character;
                followers.add(y);
            }
        }
        return (followers.contains(c));
    }
    
    
    
    /**
     * Returns a List of String objects which are lower in the
     * hierarchy than this node.
     * @return List of words
     */
    protected List getWords() {
        //Create a list to return.
        List list = new ArrayList();
        
        // If this node represents a word, add it.
        if (isWord) {
            list.add(toString());
        }
        // if any children
        if (!isLeaf) {
            //Add any words belonging to any children
            for (int i = 0; i < children.length; i++) {
                if (children[i] != null) {
                    list.addAll(children[i].getWords());
                }
            }
        }
        return list;
    }

    
    
    
    
    /**
     * Gets the string that this node represents.
     * e.g.g if this node represents the charcter t, whose parent
     * represents the character a, whose parent represents the charcter
     * c, then the string would be cat.
     * @return String 
     */
    
    public String toString(){
        if (parent == null){
            return "";
        }
        else{
            return parent.toString() + new String(new char[]{character});
        }
    }
}
