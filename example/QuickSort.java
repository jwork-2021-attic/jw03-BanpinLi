package example;

import java.util.Random;

public class QuickSort implements Sorter {
    private int[] nums;
    private StringBuilder process;

    @Override
    public void load(int[] items) {
        this.nums = items;
    }

    @Override
    public void sort() {
        process = new StringBuilder();
        quickSort(nums, 0, nums.length);
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start == end || start + 1 == end) {
            return;
        }

        Random r = new Random();
        int randomSub = r.nextInt(end - start) + start;
        int flagNum = nums[randomSub];
        swap(start, randomSub);

        int left = start + 1;
        int right = end - 1;
        while (left < right) {
            while (left < right && nums[left] <= flagNum) {
                left++;
            }
            while (left < right && nums[right] > flagNum) {
                right--;
            }
            if (left != right) {
                swap(left, right);
            }
        }
        int flagSub = left;
        if (nums[left] > flagNum) {
            flagSub = left - 1;
            swap(left - 1, start);
        } else {
            swap(left, start);
        }

        quickSort(nums, start, flagSub);
        quickSort(nums, flagSub + 1, end);
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        process.append(nums[i] + "<->" + nums[j] + "\n");
    }

    @Override
    public String getPlan() {
        return process.toString();
    }
}
