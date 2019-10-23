import java.lang.reflect.Array;
import java.util.Arrays;

class Sort {
    public static void main(String[] args) {
        //插入排序
        int[] nums1 = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
        insertionSort(nums1);
        System.out.print("插入排序:");
        printNums(nums1);

        //冒泡排序
        int[] nums2 = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
        bubbleSort(nums2);
        System.out.print("冒泡排序:");
        printNums(nums2);

        //快速排序
        int[] nums3 = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
        quickSort(nums3, 0, nums3.length - 1);
        System.out.print("快速排序:");
        printNums(nums3);

        //选择排序
        int[] nums4 = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
        selectionSort(nums4);
        System.out.print("选择排序:");
        printNums(nums4);

        //归并排序
        int[] nums5 = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
        int[] result = mergeSort(nums5);
        System.out.print("归并排序:");
        printNums(result);
    }

    /**
     * 插入排序 - 数列前面部分看为有序，依次将后面的无序数列元素插入到前面的有序数列中，
     * 初始状态有序数列仅有一个元素，即首元素。在将无序数列元素插入有序数列的 过程中，采用了逆序遍历有序数列，相较于顺序遍历会稍显繁琐，但当数列本身
     * 已近排序状态效率会更高。 时间复杂度：O(N2) 稳定性：稳定
     * 
     * 希尔排序 - 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序。
     * 然后逐渐将增量减小，并重复上述过程。直至增量为1,此时数据序列基本有序，最后进行插入排序。 （希尔排序不演示）
     * 
     * @param nums 要排序的数组
     */
    private static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    for (int k = i; k > j + 1; k--) {
                        nums[k] = nums[k - 1];
                    }
                    nums[j + 1] = temp;
                    break;
                } else if (j == 0) {
                    int temp = nums[i];
                    for (int k = i; k > j; k--) {
                        nums[k] = nums[k - 1];
                    }
                    nums[j] = temp;
                    break;
                }
            }
        }

    }

    /**
     * 冒泡排序 - 依次比较相邻两元素，若前一元素大于后一元素则交换之，直至最后一个元素即为最大；
     * 然后重新从首元素开始重复同样的操作，直至倒数第二个元素即为次大元素；依次类推。 如同水中的 气泡，依次将最大或最小元素气泡浮出水面。
     * 
     * 时间复杂度：O(N2) 稳定性：稳定
     * 
     * @param nums
     */
    private static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    /**
     * 快速排序 - （类似于选择排序的定位思想）选一基准元素，依次将剩余元素中小于该
     * 基准元素的值放置其左侧，大于等于该基准元素的值放置其右侧；然后，取基准元素的 前半部分和后半部分分别进行同样的处理；以此类推，直至各子序列剩余一个元素时，
     * 即排序完成（类比二叉树的思想，from up to down）
     * 
     * 时间复杂度：O(NlogN) 稳定性：不稳定
     * 
     * @param nums
     * @param beginIndex
     * @param endIndex
     */
    private static void quickSort(int[] nums, int beginIndex, int endIndex) {
        if (endIndex - beginIndex < 1) {
            return;
        }
        int splitIndex = beginIndex;
        int tagNum = nums[beginIndex]; // 数组第一个元素作为基准数

        for (int i = beginIndex; i <= endIndex; i++) {
            if (nums[i] < tagNum) {
                splitIndex++;
                int temp = nums[i];
                nums[i] = nums[splitIndex];
                nums[splitIndex] = temp;
            }
        }

        int temp = nums[splitIndex];
        nums[splitIndex] = nums[beginIndex];
        nums[beginIndex] = temp;

        quickSort(nums, beginIndex, splitIndex);
        quickSort(nums, splitIndex + 1, endIndex);
    }

    /**
     * 选择排序 - 首先初始化最小元素索引值为首元素，依次遍历待排序数列，若遇到小于该最小索引
     * 位置处的元素则刷新最小索引为该较小元素的位置，直至遇到尾元素，结束一次遍历， 并将最小索引处元素与首元素交换；然后，初始化最小索引值为第二个待排序数列元素
     * 位置，同样的操作，可得到数列第二个元素即为次小元素；以此类推。
     * 
     * 时间复杂度：O(N2) 稳定性：不稳定
     * 
     * @param nums
     */
    private static void selectionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int minIndex = i - 1;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i - 1];
            nums[i - 1] = temp;
        }
    }

    /**
     * 归并排序 - 采用了分治和递归的思想，递归&分治-排序整个数列如同排序两个有序数列， 依次执行这个过程直至排序末端的两个元素，再依次向上层输送排序好的两个
     * 子列进行排序直至整个数列有序（类比二叉树的思想，from down to up）。
     * 
     * 时间复杂度：O(NlogN) 稳定性：稳定
     * 
     * @param nums
     */
    private static int[] mergeSort(int[] nums) {

        if (nums.length <= 1) {
            return nums;
        } else {

            int[] lNums = Arrays.copyOfRange(nums, 0, nums.length / 2);
            int[] rNums = Arrays.copyOfRange(nums, nums.length / 2, nums.length);

            int[] lNewNums = mergeSort(lNums);
            int[] rNewNums = mergeSort(rNums);
            int lNewIndex = 0;
            int rNewIndex = 0;

            int[] result = new int[nums.length];
            int resultInsertIndex = 0;

            while (lNewIndex < lNewNums.length || rNewIndex < rNewNums.length) {
                if (lNewIndex >= lNewNums.length) {
                    if (rNewIndex < rNewNums.length) {
                        result[resultInsertIndex] = rNewNums[rNewIndex];
                        resultInsertIndex++;
                        rNewIndex++;
                    }
                } else if (rNewIndex >= rNewNums.length) {
                    result[resultInsertIndex] = lNewNums[lNewIndex];
                    resultInsertIndex++;
                    lNewIndex++;
                } else if (lNewNums[lNewIndex] > rNewNums[rNewIndex]) {
                    result[resultInsertIndex] = rNewNums[rNewIndex];
                    resultInsertIndex++;
                    rNewIndex++;
                } else {
                    result[resultInsertIndex] = lNewNums[lNewIndex];
                    resultInsertIndex++;
                    lNewIndex++;
                }
            }
            return result;
        }
    }

    private static void printNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                System.out.println(nums[i] + "");
            } else {
                System.out.print(nums[i] + ",");
            }
        }
    }
}