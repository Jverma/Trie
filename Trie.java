/**
 * This is an implementation of a trie (prefix tree) in Java.
 * Supports opeations like searching a string, searching a prefix, searching by prefix etc.
 * @author Janu Verma
 * jv367@cornell.edu
 **/


import java.lang.Character;
import java.lang.Object;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;

public class Trie{
    
    private TrieNode root;
    
    
    
    /** Constructor */
    
    public Trie()
    {
        root = new TrieNode();
    }
    
    
    
    /** Adds a word to the trie
     * @param word
     */
    public void addWord(String word){
        root.addWord(word.toLowerCase());
    }
    
   
    /**
     * Add a collection of words to the trie.
     * @param words
     */
    
    public void addAllWords(Collection<String> words){
        for (String w:words)
            addWord(w);
    }
    
    
    /**
     * checks if the String is in the trie.
     * @param string s
     * @return true if the string is in the trie
     */
    public boolean contains(String s){
        TrieNode currentNode = root;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (currentNode.containsKey(c))
                currentNode = currentNode.getNode(c);
            else
                return false;
        }
        return true;
    }

    
    
    /**
     * get the words in the trie with the given
     * prefix
     * @param prefix
     * @return a List contaning String objects containing the words
     * in the Trie with the given prefix.
     */
    public List getWords(String prefix){
        // Find the node which represents the last letter of the prefix.
        TrieNode lastNode = root;
        for (int i = 0; i < prefix.length(); i++){
            lastNode = lastNode.getNode(prefix.charAt(i));
            
            // If no node matches, then no words exits, return empty list
            if (lastNode == null) return new ArrayList();
        }
        // Return the words which eminate from the last node
        return lastNode.getWords();
    }
    
    
    
    
    
    /**
     * Find the longest prefix of a string
     * @param args
     */
    public String longestPrefix(String input){
        String result = "";
        int length = input.length();
        TrieNode crawl = root;
        int level, prevMatch = 0;
        for (level = 0; level < length-1; level++){
            char ch = input.charAt(level);
            TrieNode child = crawl.getNode(ch);  //Get the Node reprsenting the character.
            if (crawl.containsKey(ch)){
                result += ch;
                crawl = child;
                if (crawl.isWord)
                    prevMatch = level + 1;
            }
            else break;
        }
        if (!crawl.isWord)
            return result.substring(0,prevMatch);
        
        else return result;
    }
  
    
}
