import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanTree extends BinaryTree<HuffmanTree.CandW> implements HuffmanInterface{
    class CandW{
        public char c;
        public int w;
        public int timestamp;
        CandW(char c, int w, int timestamp){
            this.c = c;
            this.w = w;
            this.timestamp = timestamp;
        }
    }

    class Node implements Comparable{
        Node left;
        Node right;
        CandW data;
        Node(CandW data){
            this.data = data;
        }

        @Override
        public int compareTo(Object o) {
            Node other = (Node) o;
            if (data.w == other.data.w){
                return 0;
            }
            return data.w - other.data.w;
        }
    }

    HuffmanTree(String str){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        ArrayList<CandW> charList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++){
            boolean found = false;
            for (CandW element : charList){
                if (element.c == str.charAt(i)){
                    found = true;
                    element.w++;
                }
            }
            if (!found){
                charList.add(new CandW(str.charAt(i), 1, i));
            }
        }
        for (int i = 0; i < charList.size(); i++){
            pq.add(new Node(charList.get(i)));
        }
        for (Node n : pq){
            System.out.println(n.data.c + " : " + n.data.w);
        }
        System.out.println();
        Node head = new Node(new CandW(' ', 0, 0));
        while (!pq.isEmpty()){
            Node pop1 = pq.poll();
            Node pop2 = pq.poll();
            if (pop2 == null){
                head = pop1;
                break;
            }
            System.out.println(pop1.data.c + " : " + pop1.data.w);
            System.out.println(pop2.data.c + " : " + pop2.data.w + "\n");
            Node newNode = new Node(new CandW('~', pop1.data.w + pop2.data.w, 0));
            if (newNode.data.c == '~'){
                newNode.left = pop1;
                newNode.right = pop2;
            }else {
                newNode.left = pop2;
                newNode.right = pop1;
            }
            pq.add(newNode);
        }

        System.out.println(head.data.c + " : " + head.data.w);
        System.out.println(head.left.data.c + " : " + head.left.data.w);
        System.out.println(head.right.data.c + " : " + head.right.data.w);
        System.out.println(head.right.right.data.c + " : " + head.right.right.data.w);
        System.out.println(head.right.right.right.data.c + " : " + head.right.right.right.data.w);
    }

    @Override
    public String decode(String codedMessage) {
        return null;
    }

    @Override
    public String encode(String message) {
        return null;
    }

    public static void main(String args[]){
        HuffmanTree h = new HuffmanTree("aaaaaaaaaaaaaaabbbbbbbccccccddddddeeeee");
    }
}
