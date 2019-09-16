import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;

public class AssEx1 {
	public static void main(String[] args) {
	
	FileWriter statement = null;
	String fileName = "statement.txt";  // NAME OF THE FILE AS A STRING.
	double outBalance = 0; // declaring balance variable to be visible for all loops etc.
	
	try {
		statement = new FileWriter(fileName); // CREATING A NEW FILE. 
		
		Scanner inputName = new Scanner(System.in);
		System.out.println("Customer Name: "); 
		String outName = inputName.nextLine(); //USING SCANNER TO CAPTURE CUSTOMER'S NAME.
		
			// USER'S BALANCE. IF USER'S INPUT IS A STRING USER, EXCEPTION IS CAUGHT AND USER HAS TO TYPE IN AN AMOUNT AGAIN. 
			boolean wrongInput = false;
			while(wrongInput == false) { // 
				try {
					Scanner input = new Scanner(System.in);
					System.out.println("Customer Balance: ");
					outBalance = input.nextDouble();
					wrongInput = true;
					}
				catch (InputMismatchException e) {
					System.out.println("Incorrect input, only digits allowed.");
					}
			}
			
		CustomerAccount account = new CustomerAccount(outName, outBalance); // CREATING A NEW ACCOUNT
		System.out.println("Welcome " + account.getName() + "." + " Your balance is " + outBalance); // PRINTING A WELCOME MESSAGE THAT HAS CUSTOMER'S NAME NAD BALANCE IN IT.
		statement.write(account.getName() + "." + " Current balance " + outBalance + "\n"); // WRITING TO THE 'STATEMENT.TXT' FILE. FIRST LINE (CUSTOMER'S NAME AND BALANCE).
		
		// USING INFINITE LOOP TO ALLOW USER 
			while(true) {
				int quantityOfWine = 0;
				double priceOfWine = 0;
				String nameOfWine = null;
				
				System.out.println("Please enter wine name: ");
				Scanner wineName = new Scanner(System.in);
				nameOfWine = wineName.nextLine();
				
					// IF USER'S INPUT IS AN EMPTY STRING, THE PROGRAM TERMINATES.
					if(nameOfWine.equals("")) {
					break;
					} 

					// WINE QUANTITY. IF USER'S INPUT IS A STRING USER, EXCEPTION IS CAUGHT AND USER HAS TO TYPE IN AN AMOUNT AGAIN. 
					boolean wrongInput2 = false;
					while(wrongInput2 == false) {
						try {
							System.out.println("Please enter quantity (-ve for return): ");
							Scanner wineQuantity = new Scanner(System.in);
							quantityOfWine = wineQuantity.nextInt();
							wrongInput2 = true;
						}
						catch (InputMismatchException e) {
							System.out.println("Incorrect input, only digits allowed.");
						}
					}
					
					
					// WINE PRICE. IF USER'S INPUT IS A STRING USER, EXCEPTION IS CAUGHT AND USER HAS TO TYPE IN AN AMOUNT AGAIN. 
					boolean wrongInput3 = false;
					while(wrongInput3 == false) {
						try {
							System.out.println("Please enter wine price per bottle: ");
							Scanner winePrice = new Scanner(System.in);
							priceOfWine = winePrice.nextDouble();
							wrongInput3 = true;
						}
						catch (InputMismatchException e) {
							System.out.println("Incorrect input, only digits allowed.");
						}
					}
				
				Wine orderedWine = new Wine(nameOfWine, quantityOfWine, priceOfWine); //CREATING A NEW WINE OBJECT.
				account.changeBalance(priceOfWine, quantityOfWine); // CALLING THE CHANGEBALANCE METHOD TO CHANGE CUSTOMER'S BALANCE.
				System.out.println(account.getName() + ", " + "your balance is now " + account.getBalance()); //PRINTING CUSTOMER'S NAME AND UPDATED BALANCE USING GETTERS.
				// WRITING TO THE 'STATEMENT.TXT'
				statement.write(orderedWine.getWineName() + "(" + priceOfWine + ")" + ", " + orderedWine.getwineQuantity()+
						" units," + " total cost: " + orderedWine.totalCost(priceOfWine, quantityOfWine) + ", " + "new balance: "+ account.getBalance() + "\n");
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if(statement!=null) {
				try {
				statement.close(); // CLOSING THE 'STATEMENT.TXT' FILE 
				} catch (IOException e) {
				e.printStackTrace();
				}
			}
		}
	}
}