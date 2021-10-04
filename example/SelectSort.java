package example;

public class SelectSort implements Sorter {
    private int[] nums;
    private StringBuilder process;

    @Override
    public void load(int[] items) {
        this.nums = items;
    }

    @Override
    public void sort() {
        process = new StringBuilder();
        for (int i = 0; i < nums.length - 1; i++) {
            int curMin = nums[i];
            int index = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < curMin) {
                    curMin = nums[j];
                    index = j;
                }
            }
            if (i != index) {
                swap(i, index);
            }
        }
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
