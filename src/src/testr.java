public class testr {
    public static void main(String[] args) {
        int[] inventory = {1,0,3,2,4};
        output(inventory);
    }

    private static void output(int[] inventory) {
        for (int i = 0; i < inventory.length; i++) {
            System.out.println("商品：" + i+ "个数 = " + inventory[i]);
        }
    }
}
