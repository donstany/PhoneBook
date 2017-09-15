package com.PhoneBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PhoneBook {
	
	private static final String PHONEBOOK_FILE_LOCATION = "phonebook.txt";
	private static final Pattern PHONE_MATCHER_PATTERN = Pattern.compile("(\\+359|00359|0)8[7-9][2-9]\\d{6}");

	ArrayList<Contact> contacts = new ArrayList<>();

	public PhoneBook() {
		/*
		 * Contact contact1 = new Contact("contact1", 10); Contact contact2 =
		 * new Contact("contact2", 20); Contact contact3 = new
		 * Contact("contact3", 30); Contact contact4 = new Contact("contact4",
		 * 40); Contact contact5 = new Contact("contact5", 50); Contact contact6
		 * = new Contact("contact6", 60); Contact contact7 = new
		 * Contact("contact7", 70); Contact contact8 = new Contact("contact8",
		 * 80); //System.out.println(contact1.getName() + " " +
		 * contact1.getPhone()); //System.out.println(contact2.getName() + " " +
		 * contact2.getPhone());
		 * 
		 * contact1.setOutgoingCalls(100); contact2.setOutgoingCalls(90);
		 * contact3.setOutgoingCalls(90); contact4.setOutgoingCalls(80);
		 * contact5.setOutgoingCalls(124); contact6.setOutgoingCalls(50);
		 * contact7.setOutgoingCalls(50);
		 * 
		 * contacts.add(contact1); contacts.add(contact2);
		 * contacts.add(contact3); contacts.add(contact4);
		 * contacts.add(contact5); contacts.add(contact6);
		 * contacts.add(contact7); contacts.add(contact8);
		 */
	}

	public void addContact(Contact contact) {
		// Add the contact to the list.
		contacts.add(contact);

		// Sort the list after adding the new contact.
		contacts.sort(Contact.namesComparator);
	}
	
	// Overload as a substitute for optional parameter in Java
	// so you can edit the outgoingCalls for the contact.
	public void editContact(String name, String phone) {
		editContact(name, phone, 0);
	}

	public void editContact(String name, String phone, int phoneCalls) {
		Contact cont = new Contact("", "");
		int index = findIndex(name);
		cont = contacts.get(index);
		cont.setPhone(phone);
		cont.setOutgoingCalls(phoneCalls);
	}

	private int findIndex(String name) {
		int index = -1;

		for (Contact elem : contacts) {
			if (name.equals(elem.getName())) {
				index = contacts.indexOf(elem);
				return index;
			}
		}
		return index;
	}

	public void mostCalls() {
		// Sort the list of contacts by outgoingCalls value.
		contacts.sort(Contact.callsComparator);

		if (contacts.size() > 5) {
			for (int i = 0; i <= 5; i++) {
				Contact elem = contacts.get(i);
				System.out.println(elem.getName() + ", " + elem.getPhone() + ", " + elem.getOutgoingCalls());

			}
		} else {
			printContact();
		}
	}

	public void deleteContact(String name) {
		int index = findIndex(name);
		contacts.remove(index);
	}

	public void printContact() {
		System.out.println(contacts);
		// System.out.println("Size of list " + contacts.size());
	}

	public void loadContacts() {
		// Clear the old data.
		contacts = new ArrayList<>();

		// Load the contacts.
		File phonebookFile = new File(PHONEBOOK_FILE_LOCATION);

		// Create the file if it does not already exist.
		try {
			phonebookFile.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException("Unable to create phonebook file!", e);
		}

		System.out.println("Loading phonebook data from [" + phonebookFile.getAbsolutePath() + "]");

		try (FileInputStream input = new FileInputStream(phonebookFile);
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(input))) {
			String inputLine = inputReader.readLine();
			while (inputLine != null) {
				int separatorIndex = inputLine.lastIndexOf(',');
				// Validate each phone book entry.
				if (separatorIndex == -1) {
					System.err.println("Line [" + inputLine + "] is malformed, will not process it!");
				} else {
					String name = inputLine.substring(0, separatorIndex);
					String phone = inputLine.substring(separatorIndex + 1);
					if (PHONE_MATCHER_PATTERN.matcher(phone).matches()) {
						Contact contact = new Contact(name, phone);
						contacts.add(contact);
					} else {
						System.out.println("Phone [" + phone + "] is not a valid phone number!");
					}
				}
				inputLine = inputReader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void serializeContacts() {
		File phonebookFile = new File(PHONEBOOK_FILE_LOCATION);
		// Create the file if it does not already exist.
		try {
			phonebookFile.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException("Unable to create phonebook file!", e);
		}
		try (FileOutputStream output = new FileOutputStream(phonebookFile)) {
			for (Contact contact : contacts) {
				output.write(contact.getName().getBytes());
				output.write(',');
				output.write(contact.getPhone().getBytes());
				output.write(System.lineSeparator().getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
