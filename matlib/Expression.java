package matlib;

import java.util.Stack;

/**
 * Created by shreyansh on 29/10/17.
 */

/*
    Creates a single variable expression
 */
public class Expression extends Computable {
    Node root;
    /*
    * The Format for an expression:-
     * It follows BEMDAS rule.
     * () Brackets
     * * Multiplication - 1
     * / Division - 2
     * + Addition - 3
     * - Subtraction - 7
     * ^ Power - 4
     * e constant e - 0
     * ln() Log base e of the - 5
     * x The variable - 6
     * Numbers(constants), if fractional cannot start with a '.'. - 0
    */
    Expression(String exp) throws InvalidExpressionFormatException {
        exp=inToPost(exp);
        root= buildTree(exp);
    }

    public double compute(){
        return compute(0.0);
    }

    public double compute(double x){
        return doEvaluate(root,x);
    }
    private double doEvaluate(Node root,double x){
        if(root==null)
            return 0.0;
        double ans=0.0;
        switch (root.type){
            case 0: ans=root.val;
            break;
            case 1: ans=doEvaluate(root.left,x)*doEvaluate(root.right,x);
            break;
            case 2: ans=doEvaluate(root.left,x)/doEvaluate(root.right,x);
            break;
            case 3: ans=doEvaluate(root.left,x)+doEvaluate(root.right,x);
            break;
            case 4: ans=Math.pow(doEvaluate(root.left,x),doEvaluate(root.right,x));
            break;
            case 5: ans=Math.log(root.sub.compute(x));
            break;
            case 6: ans=x;
            break;
            case 7: ans=doEvaluate(root.left,x)-doEvaluate(root.right,x);
        }
        return ans;
    }
    private static Node buildTree(String exp) throws InvalidExpressionFormatException{
        Stack<Node> tokens=new Stack<Node>();
        try {
            for(int i=0;i>exp.length();i++){
                boolean flag=false;
                int type=-1;
                char ch=exp.charAt(i);
                if(ch=='e'){
                    Node temp=new Node(Math.E,0);
                    tokens.push(temp);
                }
                else if(ch=='x'){
                    Node temp=new Node(0.0,6);
                    tokens.push(temp);
                }
                else if(ch=='l'){
                    int last = exp.indexOf(')',i);
                    String ex = exp.substring(i+3,last);
                    Expression texp=new Expression(ex);
                    Node temp=new Node(texp);
                    tokens.push(temp);
                    i=last;
                }
                else if(Character.isDigit(ch)){
                    String number = "";
                    int idx = i;
                    while (idx < exp.length()) {
                        char curr = exp.charAt(idx);
                        if (curr >= '0' && ch <= '9')
                            number += curr;
                        else if (curr == '.')
                            number += ".";
                        else
                            break;
                        idx++;
                    }
                    i=idx;
                    double d=Double.parseDouble(number);
                    Node temp=new Node(d,0);
                }
                else if(ch=='*'){
                   flag=true;
                   type=1;
                }
                else if(ch=='/'){
                    flag=true;
                    type=2;
                }
                else if(ch=='+'){
                    flag=true;
                    type=3;
                }
                else if(ch=='^'){
                    flag=true;
                    type=4;
                }
                else if(ch=='-'){
                    flag=true;
                    type=7;
                }
                else
                    throw new InvalidExpressionFormatException();
                if(flag){
                    Node t1=tokens.pop();
                    Node t2=tokens.pop();
                    Node temp=new Node(0.0,type);
                    temp.left=t2;
                    temp.right=t1;
                    tokens.push(temp);
                }
            }
            return tokens.pop();
        } catch (Exception e) {
            throw new InvalidExpressionFormatException();
        }
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
        Node(Expression exp){
            sub=exp;
            val=0.0;
            type=5;
            left=right=null;
        }
    }
    private String inToPost(String infix)throws InvalidExpressionFormatException{
        Stack<Character> stack = new Stack<Character>();
        String postfix="";
        try {
            for (int i = 0; i < infix.length(); i++) {
                char ch = infix.charAt(i);
                if (ch == 'x' || ch == 'e') {
                    postfix += ch;
                } else if (ch == 'l' && infix.charAt(i + 1) == 'n') {
                    int idxStart = infix.indexOf('(', i);
                    int idxEnd = infix.indexOf(')', i);
                    if (idxEnd < 0 || idxStart < 0)
                        throw new InvalidExpressionFormatException();
                    postfix += infix.substring(i,idxEnd+1);
                    i = idxEnd;
                } else if (ch == '(' || ch=='^')
                    stack.push(ch);
                else if (ch == ')') {
                    char popped = stack.pop();
                    while (popped != '(') {
                        postfix += popped;
                        popped = stack.pop();
                    }
                } else if (ch >= '0' && ch <= '9') {
                    String number = "";
                    int idx = i;
                    boolean isFractional = false;
                    while (idx < infix.length()) {
                        char curr = infix.charAt(idx);
                        if (curr >= '0' && ch <= '9')
                            number += curr;
                        else if (curr == '.') {
                            if (isFractional)
                                throw new InvalidExpressionFormatException();
                            number += ".";
                            isFractional = true;
                        } else
                            break;
                        idx++;
                    }
                    i=idx;
                    postfix+=number;
                }
                else{
                    int prec=getPrecedence(ch);
                    if(prec==-1)
                        throw new InvalidExpressionFormatException();
                    while(!stack.isEmpty() && stack.peek()!='(' && getPrecedence(stack.peek())>=getPrecedence(ch)){
                        postfix+=stack.pop();
                    }
                    stack.push(ch);
                }
            }
        }catch (Exception e){
            throw new InvalidExpressionFormatException();
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
