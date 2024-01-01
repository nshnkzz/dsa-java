public class SlidingWindow {

    /**
     * https://leetcode.com/problems/subarray-sum-equals-k/description/
     * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
     * A subarray is a contiguous non-empty sequence of elements within an array.
     * 
     * This solutionn is slower since it uses sliding window and bruteforce has been used for 
     * handling cases in which the array contains non positive elements. for faster solution, use hashmap.
     * this solution considers only contiguous elements. for non contiguous case, use DP
     * @param arr
     * @param sum
     * @param isToUseDP
     * @return number os subsets of array with given sum
     */
    public static int countSubarraySum(int arr[], int sum, boolean isToUseDP) {
        if (isToUseDP) {
            return DP.countSubarraySum(arr, sum, !isToUseDP);
        }

        boolean bruteforce = false;

        for(int i: arr)
        {
            if(i <= 0)
            {
                bruteforce = true;
                break;
            }
            
        }

        int sizeOfArray = arr.length;
        int counter = 0;
        if(bruteforce)
        {
            for (int start = 0; start < sizeOfArray; start++) {
                int currentSum = 0;
                for (int end = start; end < sizeOfArray; end++) {
                    currentSum += arr[end];
                    if (currentSum == sum) {
                        counter++;
                    }
                }
            }
        
            return counter;
        }

    
        int currentSum = arr[0]; // Initialize currentSum with the first element
        int i = 0;
        int j = 0;
    
        while (j < sizeOfArray) {
            
            // System.out.println("i  && j:" + i + " & " + j);
            // System.out.println("CurrentSum: " + currentSum);
            if (currentSum == sum) {
                counter++;
                currentSum -= arr[i];
                i++;
                if (i > j) {
                    j++;
                    if (j < sizeOfArray) {
                        currentSum += arr[j];
                    }
                }
            } else if (currentSum < sum) {
                j++;
                if (j < sizeOfArray) {
                    currentSum += arr[j];
                }
            } else {
                currentSum -= arr[i];
                i++;
                if (i > j) {
                    j++;
                    if (j < sizeOfArray) {
                        currentSum += arr[j];
                    }
                }
            }
        }
    
        return counter;
    }
    
}