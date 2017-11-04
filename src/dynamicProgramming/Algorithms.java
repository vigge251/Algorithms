package dynamicProgramming;

/**
 * Created by Victor on 2017-05-22.
 */
public class Algorithms {

    /**
     *
     * **/
    public static Pair longestCommonSubstringWithCounters(String s1, String s2){
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();

        int n = a.length;
        int m = b.length;

        int best = 0;

        int compCounter = 0;

        for (int i = 0; i < n; i++) {
            int curr = 0;
            int iter = 0;
            for (int j = 0; j < m; j++) {
                compCounter++;
                if((i + j) < n && a[i + iter] == b[j]){
                    curr++;
                    iter++;
                }else {
                    curr = 0;
                }
                if(curr > best){
                    best = curr;
                }

                // Extra check to determine if there is any point in continuing
                if(i + iter >= n || (curr + (m - (j + 1))) <= best){
                    break;
                }
            }

            // Extra check to determine if there is any point in continuing
            if(n - (i + 2) <= best){
                break;
            }
        }

        return Pair.pair(best, compCounter);
    }

    public static Pair longestCommonSubstringMatrix(String s1, String s2, boolean printMatrix){
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();

        int n = a.length;
        int m = b.length;

        int[][] l = new int[n][m];

        int iterCounter = 0;

        int best = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                iterCounter++;

                if(a[i] == b[j]){
                    if(i == 0 || j == 0){
                        l[i][j] = 1;
                    }else{
                        l[i][j] = l[i - 1][j - 1] + 1;
                    }
                    if(l[i][j] > best){
                        best = l[i][j];
                    }
                }else{
                    l[i][j] = 0;
                }
            }
        }

        if(printMatrix){
            printMatrix(l);
        }

        return Pair.pair(best, iterCounter);
    }

    public static Pair longestCommonSubsequence(String s1, String s2, boolean printMatrix){
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();

        int n = a.length + 1;
        int m = b.length + 1;

        int[][] l = new int[n][m];

        int iterCounter = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                iterCounter++;
                if(a[i - 1] == b[j - 1]){
                    l[i][j] = l[i-1][j-1] + 1;
                }else{
                    l[i][j] = Math.max(l[i - 1][j], l[i][j - 1]);
                }
            }
        }

        if(printMatrix){
            printMatrix(l);
        }
        return Pair.pair(l[n - 1][m - 1], iterCounter);
    }

    /**
     * Solves the subset sum problem using dynamic programming.
     * The algorithm keeps a matrix of seq.length rows and m columns. For each value in seq, calculate what numbers
     * we can sum up to by adding this number to our sub-sequence. The solution will be in the last entry in the matrix.
     * Complexity: O(seq.length * m)
     * **/
    public static boolean subsetSum(int[] seq, int m){
        int[][] v = new int[seq.length][m + 1];
        for (int i = 0; i < seq.length; i++) {
            v[i][0] = 1;
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {

                if(i == 0){
                    if(j == seq[i]){
                        v[i][j] = 1;
                    }
                }else if(j < seq[i]){
                    v[i][j] = v[i - 1][j];
                }else if(v[i - 1][j] == 1 || v[i - 1][j - seq[i]] == 1){
                    v[i][j] = 1;
                }
            }
        }

        printMatrix(v);
        return v[v.length - 1][v[0].length - 1] == 1;
    }

    /**
     * Find the lonest strictly increasing sub-sequence
     * The algorithm keeps a result array of the longest sequences so far, at every index in the input.
     * Complexity: O(n^2)
     * **/
    public static int longestIncreasingSubSequence(int[] seq){
        int[] v = new int[seq.length];
        v[0] = 1;

        int max;
        for (int i = 1; i < seq.length; i++) {
            max = 1;
            for (int k = 0; k < i; k++) {
                if(seq[k] < seq[i] && v[k] + 1 > max){
                    max = v[k] + 1;
                }
            }
            v[i] = max;
        }
        max = 0;
        for (int i = 0; i < v.length; i++) {
            if(v[i] > max){
                max = v[i];
            }
        }
        printArray(seq);
        printArray(v);
        return max;
    }

    private static void printMatrix(int[][] m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if(j == 0 && !(j == (m[0].length - 1))){
                    sb.append("[" + m[i][j] + ", ");
                }else if(j == (m[0].length - 1)){
                    sb.append(m[i][j] + "]\n");
                }else{
                    sb.append(m[i][j] + ", ");
                }
            }
        }
        System.out.println(sb.toString());
    }

    private static void printArray(int[] v) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < v.length; i++) {
            if(i != v.length - 1){
                sb.append(v[i] + ", ");
            }else{
                sb.append(v[i] + "]");
            }
        }
        System.out.println(sb.toString());
    }
}
