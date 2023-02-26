package Q4;


import java.util.Scanner;

public class SmallerThanPrevious {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedlistSingley linkedList = new LinkedlistSingley();

        // take input from user using linked list
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            int data = scanner.nextInt();
            linkedList.appendNode(data);
        }

        // convert linked list to array
        int[] arr = new int[n];
        LinkedlistSingley.Node current = linkedList.head;
        for (int i = 0; i < n; i++) {
            arr[i] = current.data;
            current = current.next;
        }

        // remove elements that are smaller than their previous ones
        int steps = 0;
        int j = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] >= arr[j]) {
                j++;
                arr[j] = arr[i];
            } else {
                steps++;
            }
        }
        int[] newArr = new int[n - steps];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i];
        }

        // sort the array using bubble sort
        for (int i = 0; i < newArr.length - 1; i++) {
            for (int k = i + 1; k < newArr.length; k++) {
                if (newArr[i] > newArr[k]) {
                    int temp = newArr[i];
                    newArr[k] = temp;
                }
            }
        }

        // print the sorted array and number of steps
        System.out.println("Sorted Array:");
        for (int i = 0; i < newArr.length; i++) {
            System.out.print(newArr[i] + " ");
        }
        System.out.println("\nNumber of steps required to sort the array: " + steps);
    }
}


