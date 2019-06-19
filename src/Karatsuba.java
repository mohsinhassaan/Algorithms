/**
 * Karatsuba
 */
public class Karatsuba { // FIXME

    public static void main(String[] args) {
        String temp1 = "3141592653589793238462643383279502884197169399375105820974944592",
                temp2 = "2718281828459045235360287471352662497757247093699959574966967627";

        int[] num1 = toArray(temp1), num2 = toArray(temp2);

        int[] ans = multiply(num1, num2);

        for (int digit : ans) {
            // if (digit >= 10) {
            // System.err.println("Something went wrong");
            // System.err.println(digit);
            // }
            System.out.print(digit);
        }
        System.out.println();
    }

    public static int[] multiply(int[] x, int[] y) {
        int[] ans;

        x = removeLeadingZeros(x);
        y = removeLeadingZeros(y);

        int product = x[0] * y[0];

        if (product >= 10) {
            ans = new int[x.length + y.length];
        } else {
            ans = new int[x.length + y.length - 1];
        }

        if (x.length == 1 || y.length == 1) {
            if (product >= 10) {
                ans[0] = product / 10;
                ans[1] = product % 10;
            } else {
                ans[0] = product;
            }
            return removeLeadingZeros(ans);
        }

        int n = Math.max(x.length, y.length);

        x = setSize(x, n);
        y = setSize(y, n);

        int[] a = new int[n / 2], b = new int[n - n / 2], c = new int[n / 2], d = new int[n - n / 2];

        for (int i = 0; i < n / 2; i++) {
            a[i] = x[i];
            c[i] = y[i];
        }
        for (int i = n / 2; i < n; i++) {
            b[i - n / 2] = x[i];
            d[i - n / 2] = y[i];
        }

        int[] ac = multiply(a, c), bd = multiply(b, d);

        int[] temp = subtract(subtract(multiply(add(a, b), add(c, d)), ac), bd);

        int[] newAC = new int[ac.length + n];
        int[] fin = new int[temp.length + n / 2];

        for (int i = 0; i < ac.length; i++) {
            newAC[i] = ac[i];
        }

        for (int i = 0; i < temp.length; i++) {
            fin[i] = temp[i];
        }

        ans = add(newAC, add(fin, bd));

        ans = removeLeadingZeros(ans);

        return ans;
    }

    public static boolean contains(int[] arr, int n) {
        for (int var : arr) {
            if (var == n) {
                return true;
            }
        }
        return false;
    }

    public static int[] toArray(String n) {
        char[] temp = n.toCharArray();
        int[] arr = new int[temp.length];

        for (int i = 0; i < temp.length; ++i) {
            arr[i] = temp[i] - '0';
        }

        return arr;
    }

    public static int[] add(int[] x, int[] y) {
        int n = Math.max(x.length, y.length);

        x = setSize(x, n);
        y = setSize(y, n);

        int[] ans, tempAns = new int[n + 1];

        int added;
        for (int i = n - 1; i >= 0; --i) {
            added = x[i] + y[i];
            if (added >= 10) {
                tempAns[i] += 1;
                added %= 10;
            }
            tempAns[i + 1] += added;
        }

        ans = removeLeadingZeros(tempAns);

        return ans;
    }

    public static int[] subtract(int[] x, int[] y) {
        int n = Math.max(x.length, y.length);

        x = setSize(x, n);
        y = setSize(y, n);

        int[] ans, tempAns = new int[n];

        int subtracted;
        for (int i = n - 1; i >= 0; --i) {
            subtracted = x[i] - y[i];
            if (subtracted < 0) {
                subtracted += 10;
                if (i > 0) {
                    tempAns[i - 1] -= 1;
                } else {
                    tempAns[i] *= -1;
                }
            }
            tempAns[i] += subtracted;
        }
        if (tempAns[0] < 0) {
            for (int digit : tempAns) {
                if (digit > 0) {
                    digit *= -1;
                }
            }
        }

        ans = removeLeadingZeros(tempAns);

        return ans;
    }

    public static int[] removeLeadingZeros(int[] arr) {
        int count = 0;

        for (int digit : arr) {
            if (digit != 0) {
                break;
            }
            count++;
        }

        if (count == arr.length) {
            return new int[1];
        }

        int[] removed = new int[arr.length - count];

        for (int i = 0; i < removed.length; i++) {
            removed[i] = arr[i + count];
        }

        return removed;
    }

    public static int[] setSize(int[] arr, int n) {
        int[] fixed;
        if (arr.length == n) {
            return arr;
        } else if (arr.length < n) {
            fixed = new int[n];
            for (int i = 0; i < arr.length; i++) {
                fixed[i + n - arr.length] = arr[i];
            }
        } else {
            int[] temp = removeLeadingZeros(arr);
            if (temp.length < n) {
                fixed = setSize(temp, n);
            } else if (temp.length > n) {
                return null;
            } else {
                fixed = temp;
            }
        }
        return fixed;
    }
}