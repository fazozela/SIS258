class Fibonacci {
    static int getFibo(int n){
        if (n == 0 || n == 1)
            return 1;
        else
            return getFibo(n-1) + getFibo(n-2);
    }
}
