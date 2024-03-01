import java.util.*;

public class Main {
    static int evaluateExpression(String expression) {
        List<Integer> numbers = new ArrayList<>();
        List<Character> symbols = new ArrayList<>();

        //add 0 if the starting char is -
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }

        int numberStartIndex = 0;
        for (int i = 1; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '+' || ch == '-') {
                numbers.add(Integer.parseInt(expression.substring(numberStartIndex, i)));
                symbols.add(ch);
                numberStartIndex = i + 1;
            }
        }
        numbers.add(Integer.parseInt(expression.substring(numberStartIndex)));//add last element

        int result = numbers.getFirst();
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i) == '-') {
                result -= numbers.get(i + 1);
            } else if (symbols.get(i) == '+') {
                result += numbers.get(i + 1);
            }
        }
        return result;
    }

    static int numberOfHappyStrings(List<String> strings) {
        int cnt = 0;
        boolean isHappy;
        for (int i = 0; i < strings.size(); i++) {
            isHappy = true;
            String str = strings.get(i);
            for (int j = 0; j < str.length() - 1; j++) {
                if (str.charAt(j) == str.charAt(j + 1)) {
                    isHappy = false;
                    break;
                }
            }
            if (isHappy) cnt++;
        }
        return cnt;
    }

    static ListNode reverseList(ListNode head) {
        return head.reverseNode();
    }

    static int[] findIntersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // {1,2,3,3,4,5}
        Arrays.sort(nums2); // {3,4,4,5,6,7}

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    if (list.isEmpty() || nums1[i] != list.get(list.size() - 1)) {
                        list.add(nums1[i]);
                    }
                    break;
                }
            }
        }
        //convert list toarray
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    static void printIntersection(int[] nums1, int[] nums2) {
        int[] result = findIntersection(nums1, nums2);
        System.out.println("Intersection of " + Arrays.toString(nums1) + " and " + Arrays.toString(nums2) + ": " + Arrays.toString(result));
    }

    static int lenOfLongSubarr(int[] nums, int k) {
        int answ = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int l = i; l <= j; l++) {
                    sum += nums[l];
                }
                if (sum == k) {
                    answ = Math.max(answ, j - i + 1);
                }
            }
        }
        return answ;
    }

    static boolean isValidSequence(int[] array, int[] sequence) {
        int cnt = 0;
        int expectedNumber = sequence[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == expectedNumber) {
                cnt++;
                if (cnt == sequence.length) {
                    return true;
                }
                expectedNumber = sequence[cnt];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //1
        String[] expressions = {"5+20-8+5", "-10-5+20-8+5", "-10+5-20+8-5", "10-20+30-40"};//some test cases I came up with
        for (String expression : expressions) {
            System.out.println("Expression: " + expression);
            System.out.println("Result: " + evaluateExpression(expression) + "\n");
        }

        //2
        List<String> strings = Arrays.asList(
                "abc",//True
                "aabb",//False
                "aaa",//False
                "abca",//True
                "abcabcc",//False
                "abbabc",//False
                "abababab",//True
                "aabbaabbaabb"//False
        );
        System.out.println("Happy String amount: " + numberOfHappyStrings(strings) + "\n");

        //3
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original List:");
        head.print();

            //reverse
        ListNode reversedHead = reverseList(head);
        System.out.println("Reversed List:");
        reversedHead.print();

        //4
        int[] nums1 = {1, 2, 3, 3, 4, 5};
        int[] nums2 = {3, 4, 4, 5, 6, 7};
        printIntersection(nums1, nums2);

        int[] nums3 = {1, 2, 2, 3, 3, 3};
        int[] nums4 = {2, 3, 3, 4, 4, 5};
        printIntersection(nums3, nums4);

        int[] nums5 = {1, 2, 3, 4, 5};
        int[] nums6 = {6, 7, 8, 9, 10};
        printIntersection(nums5, nums6);
        System.out.println();

        //5
        int[] arrayNums = {1, 2, 3, 4, 5}; // 2 + 3 + 4 = 9 (3 action)
        int k = 9;
        System.out.println("result: " + lenOfLongSubarr(arrayNums, k));

        //6
        int[] array = {5, 1, 22, 25, 6, -1, 8, 10};
        int[] sequence = {1, 6, -1, 10};
        System.out.println("The sequence is valid: " + (isValidSequence(array, sequence) ? "true" : "false"));
    }
}