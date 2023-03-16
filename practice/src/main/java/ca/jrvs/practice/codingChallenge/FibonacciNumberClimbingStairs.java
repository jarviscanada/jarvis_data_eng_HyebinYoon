package ca.jrvs.practice.codingChallenge;

/**
 * Coding Challenge: Fibonacci Number/Climbing Stairs
 * https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-916a73dc5e1b48b8a3c61a52c28c962a
 *
 */
public class FibonacciNumberClimbingStairs {
    /**
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0){
            return 0;
        }
        else if (n ==1){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }

    /**
     * You store previous fib so you don't need to repeat the process/method again for previous fibs
     * If you want to fib5, you need to do fib(1)~(5) but if you store them, you can just get the result and add
     * @param n
     * @return
     */
    public int fibMemoize (int n){
        int [] memo = new int [31];
        memo[0] =0;
        memo[1] =1;

        for (int i =2; i<=30;i++){
            memo[i] = memo[i-1]+memo[i-2];
        }
        return memo[n];
    }
    /**
     * Time complexity O(n), linear
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        // 1 <= n <= 45
        int[] result = new int[46];
        result[1] = 1;
        result[2] = 2;

        for(int i = 3; i < 46; i++)
            result[i] = result[i - 1] + result[i - 2];

        return result[n];
    }

    //Using recursion
    public int climbStairsRec(int n){
        if (n == 0){
            return 0;
        }
        else if (n ==1) {
            return 1;
        }

            return climbStairsRec(n-1) + climbStairsRec(n-2);
    }

}
