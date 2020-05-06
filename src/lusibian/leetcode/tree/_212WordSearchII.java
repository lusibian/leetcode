package lusibian.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class _212WordSearchII {
    class TrieNode {
        boolean isWordEnd;
        TrieNode[] next;

        public TrieNode(boolean isWordEnd) {
            this.isWordEnd = isWordEnd;
            this.next = new TrieNode[26];
        }
    }

    class Trie {
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
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> wordFoundList = new ArrayList<>();
        if (board.length == 0 || board[0].length == 0 || words.length == 0) {
            return wordFoundList;
        }
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWords(board, trie.root, wordFoundList, i, j, "");
            }
        }
        return wordFoundList;
    }

    // 可优化方法：在找到一个新单词时，对字典树进行剪枝，将已找到单词删除
    private void findWords(char[][] board, TrieNode node, List<String> wordFoundList, int row, int column, String word) {
        char tempChar = board[row][column];
        if (tempChar == '#') {
            return;
        }
        int idx = tempChar - 'a';
        if (node.next[idx] != null) {
            // 是否找到一个单词的尾部
            String newWord = word + tempChar;
            if (node.next[idx].isWordEnd) {
                wordFoundList.add(newWord);
                node.next[idx].isWordEnd = false;
            }

            // 递归与回溯
            board[row][column] = '#';
            findWords(board, node.next[idx], wordFoundList, row, column, newWord);
            if (row - 1 >= 0) {
                findWords(board, node.next[idx], wordFoundList, row - 1, column, newWord);
            }
            if (row + 1 < board.length) {
                findWords(board, node.next[idx], wordFoundList, row + 1, column, newWord);
            }
            if (column - 1 >= 0) {
                findWords(board, node.next[idx], wordFoundList, row, column - 1, newWord);
            }
            if (column + 1 < board[0].length) {
                findWords(board, node.next[idx], wordFoundList, row, column + 1, newWord);
            }
            board[row][column] = tempChar;
        }
    }
}
