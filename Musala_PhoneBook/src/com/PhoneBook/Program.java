package com.PhoneBook;

import java.util.Scanner;

public class Program {

	private static final int SHOW_ALL = 1;
	private static final int TOP = 2;
	private static final int ADD = 3;
	private static final int REMOVE = 4;
	private static final int EDIT = 5;
	private static final int LOAD = 6;
	private static final int SAVE = 7;
	private static final int EXIT = 9;

	public static void main(String[] args) {

		int choice;
		String name;
		String phone;
		PhoneBook pb = new PhoneBook();
		Scanner scanner = new Scanner(System.in);

		do {
			choice = showMainMenu(scanner);

			switch (choice) {

			case SHOW_ALL: // (Prints/Shows) the list with the contacts
				System.out.print("Contacts in the current list are: ");
				pb.printContact();
				break;			
				
			case TOP: // (Print/Show) the top 5 contacts with most outgoing calls
				pb.mostCalls();
				break;

			case ADD: // Adds a new contact to the list
				name = showNameMenu(scanner, ADD);
				phone = showPhoneMenu(scanner);
				Contact contact = new Contact(name, phone);
				pb.addContact(contact);
				break;

			case REMOVE: // (Delete/Remove) a contact from the list by name
				name = showNameMenu(scanner, REMOVE);
				pb.deleteContact(name);
				break;

			case EDIT: // Edit a contacts phone by name
				name = showNameMenu(scanner, EDIT);
				phone = showPhoneMenu(scanner);
				pb.editContact(name, phone);
				break;
				
			case LOAD: // Load contacts from a file
				pb.loadContacts();
				break;

			case SAVE: // Save the current contacts to a file
				pb.serializeContacts();
				break;

			default:
				break;

			} // End of switch
		} while (!(choice == EXIT)); // End of loop

		scanner.close(); // Closing scanner
	}

	private static int showMainMenu(Scanner scan) {

		System.out.println("\t\tPhoneBook™ Premium");
		System.out.println("--------------------------------------------------");
		System.out.println("1 - Show all contacts in the PhoneBook™");
		System.out.println("2 - Show top 5 contacts with most outgoing calls");
		System.out.println("3 - Add a new PhoneBook™ contact");
		System.out.println("4 - Delete a contact from the PhoneBook™");
		System.out.println("5 - Edit contacts phone");
		System.out.println("6 - Load contacts from a file");
		System.out.println("7 - Save contacts to a file");
		System.out.println("9 - Exit PhoneBook™\n");
		System.out.print("Select an option from the menu: ");

		int choice = scan.nextInt();
		
		return choice;
	}

	private static String showNameMenu(Scanner scan, int mode) {

		switch (mode) {
		case ADD:
			System.out.println("\nAdding new contact");
			break;
		case REMOVE:
			System.out.println("\nDeleting a contact");
			break;
		case EDIT:
			System.out.println("\nEditing a contacts phone");
			break;
		}
		
		System.out.print("Enter the contact name: ");
		String name = scan.next().trim();
		return name;
	}

	private static String showPhoneMenu(Scanner scan) {
		System.out.print("Enter the contact phone number: ");
		String phone = scan.next().trim();
		return phone;
	}
}