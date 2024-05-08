package algorithms_java.class09;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_TrieTree {

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
        int num = search(word);
        if(word == null || num == 0){
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
