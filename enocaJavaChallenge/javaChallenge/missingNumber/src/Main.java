import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(101));
        }

        System.out.println("List: " + list);

        List<Integer> copyList = new ArrayList<>(list);

        System.out.println("Copy List: " + copyList);

        int randomNumber = random.nextInt(101);
        System.out.println("Random Number: " + randomNumber);

        if (copyList.contains(randomNumber)) {
            copyList.remove(Integer.valueOf(randomNumber));
        }

        System.out.println("New Copy List: " + copyList);

        int missingNumber = findMissingNumber(list, copyList);

        System.out.println("Missing Number: " + missingNumber);

    }

    private static int findMissingNumber(List<Integer> list, List<Integer> copyList) {
        for (int number : list) {
            if (!copyList.contains(number)) {
                return number;
            }
        }
        return -1;
    }
}
