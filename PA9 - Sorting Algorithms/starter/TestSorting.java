

public class TestSorting
{
    public static void main(String[] args) 
    {
        Integer[] numbers = new Integer[6];
        numbers[0] = new Integer(6);
        numbers[1] = new Integer(14);
        numbers[2] = new Integer(11);
        numbers[3] = new Integer(2);
        numbers[4] = new Integer(9);
        numbers[5] = new Integer(5);
        Sorting<Integer> sort = new Sorting<>();
        // sort.mergeSort(numbers);
        sort.mergeSort(numbers);

    }
}