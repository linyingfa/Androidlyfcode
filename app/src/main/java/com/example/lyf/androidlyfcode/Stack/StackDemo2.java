package com.example.lyf.androidlyfcode.Stack;

import java.util.Stack;

/**
 * Created by Administrator on 2018/2/9.
 */

public class StackDemo2 {


    public static void main(String[] args) {
        Stack<Integer> st = new Stack<Integer>();
        st.push(new Integer(42));//入栈
        st.push(new Integer(62));

        //
        if (st.empty()) {
            System.out.println("Stack is empty.");
        } else {
            Integer a = (Integer) st.peek();// 查看栈顶元素
            System.out.println(a);
        }


//		返回在栈中从1开始的位置的一个对象。
//		方法调用返回从堆栈中，对象位于顶部的基于1的位置。
        Integer index = (Integer) st.search(42);// 查询指定元素'
        System.out.println("--index -> " + index);


        // 判断栈是否为空
        if (st.empty()) {
            System.out.println("Stack is empty.");
        } else {
            Integer a1 = (Integer) st.pop();//出栈
            Integer a2 = (Integer) st.pop();
            System.out.println(a1);
            System.out.println(a2);
        }

        //查看栈中所有元素
        System.out.println("stack: " + st);

    }


}
