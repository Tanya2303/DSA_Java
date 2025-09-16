package Java_Arrays;

public class buy_sell_stock {

    public static int buySellStock(int prices[]) {
        int buy_price = Integer.MAX_VALUE; // Initialize buy price to maximum value
        int max_profit = 0; // Initialize max profit to zero

        for (int i = 0; i < prices.length; i++) {
            if (buy_price < prices[i]) { // If current price is greater than buy price
                int profit = prices[i] - buy_price; // Calculate potential profit
                max_profit = Math.max(max_profit, profit); // Update max profit if current profit is higher
            } else {
                buy_price = prices[i]; // Update buy price to current price
            }
        }
        return max_profit; // Return the maximum profit found
    }
    public static void main(String[] args) {
        int prices[] = {7,1,5,3,6,4};
        System.out.println("Maximum profit: " + buySellStock(prices));
    }
    
}
