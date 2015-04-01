package fr.univ_rouen.hansa.actions;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

public class Stack<E> {

    private LinkedList<E> stack;

    public Stack(){
        stack = Lists.newLinkedList();
    }

    public E pop(){
        if(stack.isEmpty()){
            throw new IllegalStateException();
        }
        return stack.pop();
    }

    public void push(E e){
        if(e == null){
            throw new IllegalArgumentException();
        }
        this.stack.push(e);
    }

    public E head(){
        if(isEmpty()){
            throw new IllegalStateException();
        }
        return this.stack.element();
    }

    public boolean isEmpty(){
        return this.stack.isEmpty();
    }

    public void clear() {
        stack.clear();
    }

    public List<E> getStackContent() {
        return Lists.newArrayList(stack);
    }

}
