package fr.univ_rouen.hansa.actions;

import java.util.LinkedList;

/**
 * Cette classe représente une pile
 * Created by Valentin on 10/02/2015.
 */
public class Stack<E> {

    private LinkedList<E> stack;

    public Stack(){
        stack = new LinkedList<>();
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

}
