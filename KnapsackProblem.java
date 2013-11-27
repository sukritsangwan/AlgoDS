import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class KnapsackProblem {

	public static class Item {
		int weight;
		int value;
		
		public Item(int w, int v) {
			weight = w;
			value = v;
		}
	}
	
	public static int getMaxValue(List<Item> items, int maxWeight) {
		int[][] values = new int[items.size()][maxWeight + 1];
		for(int j = items.get(0).weight; j <= maxWeight; j++)
			values[0][j] = items.get(0).value;
		for(int i = 1; i < items.size(); i++) {
			for(int j = 1; j <= maxWeight; j++) {
				if(items.get(i).weight > j)
					values[i][j] = values[i - 1][j];
				else
					values[i][j] = Math.max(values[i - 1][j], items.get(i).value + values[i - 1][j - items.get(i).weight]);
			}
		}
		
		return values[items.size() - 1][maxWeight];
	}
	
	public static void main(String[] args) {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item(5, 7));
		items.add(new Item(3, 4));
		items.add(new Item(6, 8));
		items.add(new Item(7, 9));
		items.add(new Item(4, 6));
		items.add(new Item(8, 10));
		items.add(new Item(6, 6));
		items.add(new Item(11, 17));
		items.add(new Item(5, 7));
		
		System.out.println(getMaxValue(items, 20));
	}

}
