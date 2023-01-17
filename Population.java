import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - sorts a list of populations according to user preferance and prints it out
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Nikolas Margaritis
 *	@since	January 12, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	//SortMethods object
	private SortMethods sort = new SortMethods();
	
	//constructor for Population
	public Population() {
		cities = new ArrayList<City>();
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	public static void main(String[] args) {
		Population pop = new Population();
		pop.printIntroduction();
		pop.readFile();
		pop.run();
	}
	
	/**
	 * reads the file and creates and stores City objects into the array
	 */
	public void readFile() {
		Scanner inputFileScanner = FileUtils.openToRead(DATA_FILE);
		//removes tabs and new lines
		inputFileScanner.useDelimiter("[\t\n]");
		
		//reads file and creates new City objects to be put into array
		while(inputFileScanner.hasNext()) {
			String state = inputFileScanner.next();
			String name = inputFileScanner.next();
			String designation = inputFileScanner.next();
			int population = Integer.parseInt(inputFileScanner.next());
			
			cities.add(new City(state, name, designation, population));
		}

		inputFileScanner.close();
	}
	
	/**
	 * sorts lists based on user input and prints the output
	 */
	public void run() {
		//creates new lists for sorting different ways
		List<City> ascendingPopulation = cities;
		List<City> descendingPopulation = cities;
		List<City> ascendingName = cities;
		List<City> descendingName = cities;

		System.out.println(cities.size() + " cities in the database\n");
		printMenu();

		//runs until user decision is 9 to exit
		int decision = 0;
		while (decision != 9) {
			decision = Prompt.getInt("\nEnter selection");
			System.out.println();
			int index = 1;

			//checks if decision is 1 - 4 to sort using SortMethods
			if (decision >= 1 && decision <= 4) {
				long startMillisec = System.currentTimeMillis();

				//sorts using selection sort
				if (decision == 1) {
					sort.selectionSort(ascendingPopulation);
					System.out.println("Fifty least populous cities");
				}
				//sorts using merge sort
				else if (decision == 2) {
					sort.mergeSort(descendingPopulation, 1);

					for (int i = 0; i < ascendingPopulation.size() / 2; i++) {
						sort.swap(ascendingPopulation, i , ascendingPopulation.size() - 1 - i);
					}

					System.out.println("Fifty most populous cities");
				}
				//sorts using insertion sort
				else if (decision == 3) {
					sort.insertionSort(ascendingName);
					System.out.println("Fity cities sorted by name");
				}
				//sorts using merge sort
				else if (decision == 4) {
					sort.mergeSort(descendingName, 2);

					for (int i = 0; i < descendingName.size() / 2; i++) {
						sort.swap(descendingName, i , descendingName.size() - 1 - i);
					}

					System.out.println("Fifty cities sorted by name descending");
				}

				long endMillisec = System.currentTimeMillis();

				//prints output
				System.out.println("     State                  City                   Type           Population");
				for (int i = 0; i < 50; i++) {
					System.out.printf("%3s: ", index);
					System.out.println(descendingPopulation.get(i));
					index++;
				}
				System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
			}
			//asks user which state to sort for population using merge sort
			else if (decision == 5) {
				List<City> populousStates = new ArrayList<City>();
				String stateName = "";
				boolean exists = true;
				//asks user to input state until valid
				while (exists) {
					stateName = Prompt.getString("Enter state name (ie. Alabama)");
					for (int i = 0; i < cities.size(); i++) {
						if (cities.get(i).getState().equals(stateName)) {
							exists = false;
							i = cities.size();
						}
					}
					if (exists == true)
						System.out.println("ERROR: " + stateName + " is not valid");
				}

				//sorts array using merge sort by population
				sortPopulousCity(populousStates, stateName);
				System.out.println("\nFifty most populous cities in " + stateName);

				//prints array
				System.out.println("     State                  City                   Type           Population");
				for (int i = 0; i < 50; i++) {
					System.out.printf("%3s: ", index);
					System.out.println(populousStates.get(i));
					index++;
				}
				System.out.println();
			}
			//asks user which city to sort for population using merge sort
			else if (decision == 6) {
				List<City> allCity = new ArrayList<City>();
				String cityName = "";
				boolean exists = true;
				//asks user to input city until valid
				while (exists) {
					cityName = Prompt.getString("Enter city name");
					for (int i = 0; i < cities.size(); i++) {
						if (cities.get(i).getCity().equals(cityName)) {
							exists = false;
							i = cities.size();
						}
					}
					if (exists == true)
						System.out.println("ERROR: " + cityName + " is not valid");
				}

				//sorts array using merge sort by population
				sortAllCity(allCity, cityName);
				System.out.println("\nCity " + cityName + " by population");

				//prints array
				System.out.println("     State                  City                   Type           Population");
				for (int i = 0; i < allCity.size(); i++) {
					System.out.printf("%3s: ", index);
					System.out.println(allCity.get(i));
					index++;
				}
				System.out.println();
			}
			else if (decision == 9) {
				System.out.println("Thanks for using Population!");
			}
			//allows user to try again if decision is not valid
			else {
				System.out.println(decision + " is not a valid entry, Try Again\n");
			}
			if (decision != 9)
				printMenu();
		}
	}

	/**
	 * sorts array by given name of state by population
	 * 
	 * @param arr		array needing to be sorted
	 * @param stateName	name of state to be used to sort array
	 */
	public void sortPopulousCity(List<City> arr, String stateName) {
		//creates a list with given state
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i).getState().equals(stateName)) {
				arr.add(cities.get(i));
			}
		}

		//sorts list with merge sort
		sort.mergeSort(arr, 1);
		for (int i = 0; i < arr.size() / 2; i++) {
			sort.swap(arr, i , arr.size() - 1 - i);
		}
	}

	/**
	 * sorts array by given name of city by population
	 * 
	 * @param arr		array needed to be sorted
	 * @param cityName	name of city to be used to sort array
	 */
	public void sortAllCity(List<City> arr, String cityName) {
		//creates a list with given city
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i).getCity().equals(cityName)) {
				arr.add(cities.get(i));
			}
		}

		//sorts list with merge sort
		sort.mergeSort(arr, 1);
		for (int i = 0; i < arr.size() / 2; i++) {
			sort.swap(arr, i , arr.size() - 1 - i);
		}
	}
}
