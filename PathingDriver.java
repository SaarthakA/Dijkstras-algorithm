import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PathingDriver {
   public static void main(String[] args) throws FileNotFoundException
   {
	   //creating List of cities from file
	   List<City> cities = new ArrayList<City>();
	   Scanner sc = new Scanner(new File("city.dat"));
	   for(int i = 0; i < 20; i++) 
	   {
		   int num = Integer.parseInt(sc.next());
		   String code = sc.next();
		   String name = "blank";
		   name = sc.next();
		   if(!sc.hasNextInt())
			   name = name + " " + sc.next();
		   int pop = Integer.parseInt(sc.next());
		   int elev = Integer.parseInt(sc.next());
		   cities.add(new City(num, code, name, pop, elev));
		   
	   }
	   //create adj matrix
	   int graph [][] = constructGraph(sc, cities);
	   Pathing dist = new Pathing(cities.size());
	   
	   	int choice = 0;
	   	sc = new Scanner(System.in);
		while(choice != 6)
		{
			displayMenu();
			choice = sc.nextInt();
			switch(choice)
			{
			//Find city information
			case 1:
				boolean found = false;
				System.out.print("City code: ");
				String code = sc.next();
				code = code.toUpperCase();
				for(int i = 0; i < cities.size(); i++)
					if(code.equals(cities.get(i).City_Code))
					{
						System.out.println(cities.get(i).Number + " " + 
								cities.get(i).City_Code + " " + cities.get(i).Full_City_Name + " " +
								cities.get(i).Population + " " + cities.get(i).Elevation);
						found = true;
						break;
					}
				if(found == false)
					System.out.println("City Not Found");				
				break;
				//find minimum distance
			case 2:
				System.out.print("Input two City code to find minimum distance from first code to second code: ");
				String code1 = sc.next();
				String code2 = sc.next();
				code1 = code1.toUpperCase();
				code2 = code2.toUpperCase();
				int index1 = getCityIndex(code1,cities);
				int index2 = getCityIndex(code2,cities);
				System.out.print("The shortest distance from " + cities.get(index1).Full_City_Name + " to " +
						cities.get(index2).Full_City_Name + " is ");
				dist.dijkstra(graph, index1, index2);
				break;
				//adding new road
			case 3:
				System.out.print("Input two City code and distance to find add road from first code to second code :");
				String code3 = sc.next();
				String code4 = sc.next();
				int index3 = getCityIndex(code3,cities);
				int index4 = getCityIndex(code4,cities);
				int distance = Integer.parseInt(sc.next());
				boolean exists = false;
				if(graph[index3][index4] != 0)
					{
						System.out.println("Road Already Exits");
						exists = true;
					}
				
				if(exists)
					break;
				graph[index3][index4] = distance;
				System.out.println("You have inserted a road from " + cities.get(index3).Full_City_Name + " to " +
						cities.get(index4).Full_City_Name + " with a distance of " + distance);
				break;
				//deleting a road
			case 4:
				System.out.print("Input two City code and distance to find remove road from first code to second code :");
				String code5 = sc.next();
				String code6 = sc.next();
				int index5 = getCityIndex(code5,cities);
				int index6 = getCityIndex(code6,cities);
				boolean exist = true;
				if(graph[index5][index6] == 0)
					{
						System.out.println("Road from " + cities.get(index5).Full_City_Name + " to " +
								cities.get(index6).Full_City_Name+ " Doesn't Exits");
						exist = false;
					}
				if(!exist)
					break;
				graph[index5][index6] = 0;
				System.out.println("You have removed a road from " + cities.get(index5).Full_City_Name + " to " +
						cities.get(index6).Full_City_Name);
				break;
				//display menu
			case 5:
				displayMenu();
				break;
			}
			System.out.println("===========================================================================");
			dist = new Pathing(cities.size());
		}
		System.out.println("Goodbye");
   }
   
   //constructs adj matrix
   public static int[][] constructGraph(Scanner sc, List cities) throws FileNotFoundException
   {
	   int graph [][] = new int[cities.size()][cities.size()];
	   sc = new Scanner(new File("road.dat"));
	   while(sc.hasNext())
	   {
		   int row = sc.nextInt()-1;
		   int col = sc.nextInt()-1;
		   int value = sc.nextInt();
		   graph [row] [col] = value;
	   }
	   return graph;
   }
   
   //gets index of city in List of cities from its city code 
   public static int getCityIndex(String code, List<City> ls)
   {
	   int index = -1;
	   code = code.toUpperCase();
		for(int i = 0; i < ls.size(); i++)
			if(code.equals(ls.get(i).City_Code))
			{
				index = i;
				break;
			}
	   return index;
   }
   
   //displays menu
   public static void displayMenu()
   {
	   System.out.print("1) Query the city information by entering the city code.\n"+
	   "2) Find the minimum distance between two cities.\n" +
	   "3) Insert a road by entering two city codes and distance.\n" +
	   "4) Remove an existing road by entering two city codes.\n" +
	   "5) Display this message.\n" +
	   "6) Exit.\n" +
	   "Command? ");
   }
}
