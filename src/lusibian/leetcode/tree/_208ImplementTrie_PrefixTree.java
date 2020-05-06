package lusibian.leetcode.tree;

public class _208ImplementTrie_PrefixTree {
    class Trie {
        class TrieNode {
            boolean isWordEnd;
            TrieNode[] next;

            public TrieNode(boolean isWordEnd) {
                this.isWordEnd = isWordEnd;
                this.next = new TrieNode[26];
            }
        }

        TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode(false);
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            int length = word.length();
            TrieNode node = root;
            for (int i = 0; i < length; i++) {
                int idx = word.charAt(i) - 'a';
                if (node.next[idx] == null) {
                    node.next[idx] = new TrieNode(false);
                }
                node = node.next[idx];
            }
            node.isWordEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode node = findEndNode(word);
            return node != null && node.isWordEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode node = findEndNode(prefix);
            return node != null;
        }

        private TrieNode findEndNode(String str) {
            int length = str.length();
            TrieNode node = root;
            for (int i = 0; i < length; i++) {
                int idx = str.charAt(i) - 'a';
                if (node.next[idx] == null) {
                    return null;
                }
                node = node.next[idx];
            }
            return node;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
