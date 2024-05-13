package algorithms_java.class09;

import java.util.HashMap;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_TrieTree {

    class TrieTree01{
        private class Node{
            public int pass;
            public int end;
            public Node[] next;

            public Node() {
                pass = 0;
                end = 0;
                /**
                 * 其中next[]中何处有Node节点来表示通路上的字符值
                 * a - next[0]
                 * b - next[1]
                 * ...
                 * z = next[25]
                 */
                next = new Node[26];
            }
        }

        private Node root = new Node();

        //将提供的字符串插入到前缀树中
        public void insert(String word){
            if(word == null){
                return;
            }
            Node node = root;
            node.pass++;
            char[] chars = word.toCharArray();
            int path = 0;
            for(int i = 0; i < chars.length; i++){
                path = chars[i] - 'a';
                if(node.next[path] == null) {
                    node.next[path] = new Node();
                }
                node = node.next[path];
                node.pass++;
            }
            node.end++;
        }

        //删掉一次已经加过的字符串
        public void delete(String word){
            if(word == null || search(word) == 0){
                return;
            }
            char[] chars = word.toCharArray();
            Node node = root;
            node.pass--;
            int path = 0;
            for(int i = 0; i < chars.length; i++){
                path = chars[i] - 'a';
                if(--node.next[path].pass == 0){
                    node.next[path] = null;
                    return;
                }
                node = node.next[path];
            }
            node.end--;
        }

        //word这个字符串在前缀树中出现了几次
        public int search(String word){
            if(word == null){
                return 0;
            }
            char[] chars = word.toCharArray();
            Node node = root;
            int path = 0;
            for(int i = 0; i < chars.length; i++){
                path = chars[i] - 'a';
                if(node.next[path] == null){
                    return 0;
                }
                node = node.next[path];
            }
            return node.end;
        }

        //返回加入前缀树的字符串以pre为前缀的个数
        public int prefixNumber(String pre){
            if(pre == null){
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node node = root;
            int path = 0;
            for(int i = 0; i < chars.length; i++){
                path = chars[i] - 'a';
                if(node.next[path] == null){
                    return 0;
                }
                node = node.next[path];
            }
            return node.pass;

        }
    }

    class TrieTree02{
        private class Node02{
            public int pass;
            public int end;
            public HashMap<Integer, Node02> next;

            public Node02() {
                this.pass = 0;
                this.end = 0;
                this.next = new HashMap<>();
            }
        }

        public Node02 root;

        public TrieTree02() {
            root = new Node02();
        }

        public void insert(String word){
            if(word == null){
                return;
            }
            char[] chars = word.toCharArray();
            Node02 node = root;
            node.pass++;

            for(int i = 0; i < chars.length; i++){
                int pass = (int)chars[i];
                if(!node.next.containsKey(pass)){
                    node.next.put(pass, new Node02());
                }
                node = node.next.get(pass);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word){
            if(word == null ||search(word) == 0){
                return;
            }
            char[] chars = word.toCharArray();
            Node02 node = root;
            node.pass--;

            for(int i = 0; i < chars.length; i++){
                int pass = (int)chars[i];
                if(node.next.get(pass).pass-- == 0){
                    node.next.remove(pass);
                    return;
                }
                node = node.next.get(pass);
            }
            node.end--;
        }

        public int search(String word){
            while(word == null){
                return 0;
            }
            char[] chars = word.toCharArray();
            Node02 node = root;
            for(int i = 0; i < chars.length; i++){
                int pass = (int)chars[i];
                if(!node.next.containsKey(pass)){
                    return 0;
                }
                node = node.next.get(pass);
            }
            return node.end;
        }

        public int prefixNumber(String pres){
            if(pres == null){
                return 0;
            }
            char[] chars = pres.toCharArray();
            Node02 node = root;

            for(int i = 0; i < chars.length; i++){
                int pass = (int)chars[i];
                if(!node.next.containsKey(pass)){
                    return 0;
                }
                node = node.next.get(pass);
            }
            return node.pass;
        }
    }
    class Right{
        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word){
            if(box.containsKey(word)){
                box.put(word, box.get(word) + 1);
            }else{
                box.put(word, 1);
            }
        }

        public void delete(String word){
            if(box.containsKey(word)){
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word){
            if(!box.containsKey(word)){
                return 0;
            }else{
                return box.get(word);
            }
        }

        public int prefixNumber(String pre){
            int count = 0;
            for(String str : box.keySet()){
                if(str.startsWith(pre)){
                    count += box.get(str);
                }
            }
            return count;
        }
    }

    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            TrieTree01 trie1 = new Code01_TrieTree().new TrieTree01();
            TrieTree02 trie2 = new Code01_TrieTree().new TrieTree02();
            Right right = new Code01_TrieTree().new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
