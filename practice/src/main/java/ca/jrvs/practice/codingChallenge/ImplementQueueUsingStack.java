package ca.jrvs.practice.codingChallenge;

/**
 * coding challenge : ImplementQueueUsingStack
 * https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-84c24c24836948cc9e90d008db9cbb85
 */
public class ImplementQueueUsingStack {

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
    /**
     * 1 STACK : if you are comfortable with RECURSION
     * 2 STACKS : if you are NOT comfortable with RECURSION.
     *
     * For 2 stacks, we will be moving all the elements of 1st stack to the 2nd one before pushing a new element(x), and then moving all the elements back again.
     * if 2nd stack is empty we just move 1->2 to 2->1 so when we pop it pops 1->2
     *
     * We will be doing the same using recursion in case we use 1 stack approach.
     */
    public ImplementQueueUsingStack() {


    }

    public void push(int x) {

    }

    public int pop() {

    }

    public int peek() {

    }

    public boolean empty() {

    }
}
