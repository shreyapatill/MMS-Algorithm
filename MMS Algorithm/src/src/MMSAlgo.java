import java.util.ArrayList;
import java.util.List;

public class MMSAlgo {

	public void allocate(final List<List<Integer>> agentValues) {
		int agents = agentValues.size();
		int items = agentValues.get(0).size();

		List<List<Float>> allocations = new ArrayList<>();

		for (int i = 0; i < agents; i++) { // creates list objects to hold each agent's allocations
			allocations.add(new ArrayList<>());
		}
		allocations = scale(agentValues, agents);

		/* while (items > 0) { // check that # of items doesn't reach 0
			for (int i = 0; i < agents; i++) {
				if (items == 0) {
					break;
				}
				int bestIndex = findBestItem(agentValues.get(i));
				int bestVal = agentValues.get(i).get(bestIndex);
				allocations.get(i).add(bestVal); // adds best item to allocation list

				for (int x = 0; x < agents; x++) {
					agentValues.get(x).remove(bestIndex); // removes item from other agent's lists
				}
				--items;
			}

		} */

		
	}

	public Integer findBestItem(List<Integer> agentI) { // iterates through agent's list of values to find the best item
		int maxIndex = 0;

		for (int i = 1; i < agentI.size(); i++) {
			if (agentI.get(maxIndex) < agentI.get(i)) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	public void printAllocations(List<List<Float>> allocations) {
		for (List<Float> agentI : allocations) {
			for (int i = 0; i < agentI.size(); i++) {
				float val = agentI.get(i);
				if (i < agentI.size() - 1) {
					System.out.print(val + ", ");
				} else {
					System.out.print(val);
				}
			}
			System.out.println();
		}
	}

	public List<List<Float>> scale(List<List<Integer>> agentValues, int agents) {
		//finds sum of all values
		int sumVal = 0;
		
		for (List<Integer> agentI : agentValues) {
			for (int v : agentI) {
				sumVal += v;
			}
		}
		
		//scales each value
		List<List<Float>> scaledVal = new ArrayList<>();
		
		for (int i = 0; i < agents; i++) {
			scaledVal.add(new ArrayList<>());
			
			for (int val : agentValues.get(i)) {
				float s = (float)(val * agents) / sumVal;
				scaledVal.get(i).add(s);
			}
		}
		printAllocations(scaledVal);
		
		return scaledVal;
	}

}
