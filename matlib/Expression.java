package matlib;

import java.util.Stack;

/**
 * Created by shreyansh on 29/10/17.
 */

/*
    Creates a single variable expression
 */
public class Expression {
    Node root;
    /*
    * The Format for an expression:-
     * It follows BEMDAS rule.
     * () Brackets
     * * Multiplication
     * / Division
     * + Addition
     * ^ Power
     * e Exponentiation
     * ln() Log base e of the
     * x The variable
    */
    Expression(String exp){
        buildTree(exp);
    }
    private void buildTree(String exp){

    }
    private static class Node{
        double val;
        int type;
        Node left;
        Node right;
        Expression sub;
        Node(double val,int type){
            this.val=val;
            this.type=type;
            left=null;
            right=null;
            sub=null;
        }
    }
    private String inToPost(String infix)throws InvalidExpressionFormatException{
        Stack<String> stack = new Stack<String>();
        String postfix="";
        for(int i=0;i<infix.length();i++){
            char ch=infix.charAt(i);
            if(ch=='x' || ch=='e'){
                postfix+=ch;
            }
            else if(ch=='l' && infix.charAt(i+1)=='n'){
                int idxStart = infix.indexOf('(',i);
                int idxEnd = infix.indexOf(')',i);
                if(idxEnd<0 || idxStart<0)
                    throw new InvalidExpressionFormatException();

            }
        }
        return postfix;
    }
    private int getPrecedence(char ch){
        if(ch=='+' || ch=='-')
            return 1;
        if(ch=='*' || ch=='/')
            return 2;
        if(ch=='^')
            return 3;
        return -1;
    }
}
