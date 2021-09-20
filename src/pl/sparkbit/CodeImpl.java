package pl.sparkbit;

import java.util.*;
import java.util.stream.Collectors;

public class CodeImpl implements Code {
    /**
     Your task is to encode the alphabet as a binary tree using the frequencies of letters in the given text. You need
     to perform the following steps:

     * for each character in the text calculate its number of occurrences / frequency, e.g. for string `aba` it would
        be `a -> 2`, `b -> 1`
     * for each character and its frequency create a one-node tree
     * take two trees `T1` and `T2` with lowest frequencies and merge them into a larger tree `T1-2` (`T1` should
        become the left sub-tree and `T2` the right subtree)
     * repeat the previous step until there is only 1 tree left

     That last tree represents the created encoding.
     For example, given text `abacaca` you should get:
             a+b+c(7)
              /  \
             /    \
            /      \
           /        \
         b+c(3)     a(4)
         /   \
        /     \
      b(1)   c(2)
     All the auxiliary classes for building the tree are provided and should not be modified.
     */
    @Override
    public Node createCode(String text) {
        if(text == null || text.isEmpty())
            throw new UnsupportedOperationException();
        int count_chars = 0;
        Set<Character> charSet = new HashSet<Character>();
        List leafList = new ArrayList();
        for (char ch : text.toCharArray()) {
            charSet.add(ch);
            count_chars++;
        }
        for (Iterator<Character> it = charSet.iterator(); it.hasNext(); ) {
            char ch = it.next();
            leafList.add(new Leaf(ch, countChars(ch, text)));
        }
        int leafListSize = leafList.size();
        if (leafListSize > 1) {
            if (leafList.size() == 2) {
                return new InnerNode((Node) leafList.get(0), (Node) leafList.get(1));
            } else {
                Deque<Leaf> highLeafList = new LinkedList<>();
                Leaf highFrequencyLeaf;
                while (leafListSize > 2) {
                    highFrequencyLeaf = selectHighFreqLeaf(leafList);
                    leafList.remove(highFrequencyLeaf);
                    leafListSize = leafList.size();
                    highLeafList.add(highFrequencyLeaf);
                }
                InnerNode frequencyLeaf = new InnerNode((Node) leafList.get(0), (Node) leafList.get(1));
                if (highLeafList.size() > 0) {
                    while (highLeafList.size() > 0) {
                        InnerNode theFrequencyLeaf = frequencyLeaf;
                        frequencyLeaf = new InnerNode(theFrequencyLeaf, highLeafList.getLast());
                        highLeafList.removeLast();
                    }
                }
                return frequencyLeaf;
            }
        } else
            return (Node) leafList.get(0);
    }

    private Leaf selectHighFreqLeaf(List leafList) {
        int maxFrequence = 0;
        Leaf highFreqLeaf = null;
        for (Object theLeaf: leafList.toArray()) {
            Node theNode = (Node) theLeaf;
            if (theNode.frequency > maxFrequence) {
                highFreqLeaf = (Leaf) theLeaf;
                maxFrequence = theNode.frequency;
            }
        }
        return highFreqLeaf;
    }

    private int countChars(char someChar, String text) {
        int frequecy = 0;
        for(int i =0; i < text.length(); i++){
            if(text.charAt(i) == someChar)
                frequecy++;
        }
        return frequecy;
    }
}
