import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

public class Palindrome{

public static List<String>distinctPalindrome=new ArrayList<String>();

public static class Node {

 int start, end;

 Node link;

 Node[] childrens = new Node[26];

 int len;

 public Node(int start, int end) {

  this.start = start;

  this.end = end;

  this.len = this.end - this.start + 1;

 }

 public Node(int len) {

  this.len = len;

 }

};

static Node root1 = new Node(-1);

static Node root2 = new Node(0);

static {

 root2.link = root1;

}

static Node cn = root2;

static int max = 0;

public static void insert(char[] cs, int idx) {

 int ci = cs[idx] - 97;

 Node lastLnode = cn;

 Node lastCn = null;

 do {

  if (lastLnode == root1) {

   if (root1.childrens[ci] == null) {

    root1.childrens[ci] = new Node(idx, idx);

    distinctPalindrome.add(new String(cs,idx,1));

    root1.childrens[ci].link = root2;

   }

   if (lastCn == null) {

    cn = root1.childrens[ci];

   } else {

    lastCn.link = root1.childrens[ci];

   }

   break;

  } else {

   int start = idx - lastLnode.len - 1;

   int end = idx;

   if (start >= 0 && cs[start] == cs[end]) {

    if (lastLnode.childrens[ci] != null) {

     if (lastCn == null) {

      cn = lastLnode.childrens[ci];

     } else {

      lastCn.link = lastLnode.childrens[ci];

     }

     break;

    } else {

     lastLnode.childrens[ci] = new Node(start, end);

     distinctPalindrome.add(new String(cs,start,end-start+1));

     

     if (lastCn == null) {    

      cn = lastLnode.childrens[ci];;

     } else {

      lastCn.link = lastLnode.childrens[ci];    

     }

     

     lastCn = lastLnode.childrens[ci];

    }

   }

   lastLnode = lastLnode.link;

  }

 } while (true);

}

public static void recalMax(int len) {

 max = max > len ? max : len;

}

public static void main(String[] args) {

 Scanner scanner = new Scanner(System.in);

 char[] cs = scanner.nextLine().toCharArray();

 for (int i = 0; i < cs.length; i++) {

  insert(cs, i);

  recalMax(cn.len);

 }

 System.out.println(max);

 for(String p:distinctPalindrome) {

  System.out.println(p);

 }

 scanner.close();

}

}
