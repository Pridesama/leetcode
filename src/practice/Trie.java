package practice;

/**
 * 208. Implement Trie (Prefix Tree)
 *
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Note: You may assume that all inputs are consist of lowercase letters a-z.
 */
public class Trie {
    class TrieNode {
        boolean isEnd = false;
        // 也可以用 char[26]
        TrieNode[] children = new TrieNode[26];

        TrieNode insert(char ch) {
            TrieNode node = findChild(ch);
            if (null == node) {
                node = new TrieNode();
                children[ch - 'a'] = node;
            }
            return node;
        }

        TrieNode findChild(char ch) {
            return children[ch - 'a'];
        }
    }
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode start = root;
        for (int i = 0; i < word.length(); i++) {
            start = start.insert(word.charAt(i));
        }
        // 为单词加结束符
        start.isEnd = true;
    }

    public TrieNode findPrefixLeaf(String prefix) {
        TrieNode start = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            TrieNode tarNode = start.findChild(ch);
            if (null == tarNode) return null;
            start = tarNode;
        }
        return start;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode leaf = findPrefixLeaf(word);
        return null != leaf && leaf.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode leaf = findPrefixLeaf(prefix);
        return null != leaf;
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    public static void main (String args[]) {
        Trie obj = new Trie();
        obj.insert("trie");
        obj.insert("abc");
        System.out.println(String.format("is word %s exists? %b", "abc", obj.search("abc")));
        System.out.println(String.format("is word %s exists? %b", "ab", obj.search("ab")));
        System.out.println(String.format("is prefix %s exists? %b", "abc", obj.startsWith("abc")));
        obj.insert("ab");
        System.out.println(String.format("is word %s exists? %b", "ab", obj.search("ab")));
        System.out.println(String.format("is prefix %s exists? %b", "ab", obj.startsWith("ab")));
    }
}
