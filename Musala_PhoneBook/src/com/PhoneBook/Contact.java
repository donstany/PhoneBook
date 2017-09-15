package com.PhoneBook;

import java.util.Comparator;

public class Contact {
	private String name;
	private String phone;
	private int outgoingCalls;

	public Contact(String name, String phone) {
		setName(name);
		setPhone(phone);
		outgoingCalls = 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setOutgoingCalls(int outgoingCalls) {
		this.outgoingCalls = outgoingCalls;
	}

	public int getOutgoingCalls() {
		return outgoingCalls;
	}

	public String toString() {
		// String res = this.name + " " + this.phone;
		String res = this.name + ", " + this.phone + ", " + this.outgoingCalls;
		return res;
	}

	public static Comparator<Contact> namesComparator = new Comparator<Contact>() {
		public int compare(Contact c1, Contact c2) {
			String ContactName1 = c1.getName().toUpperCase();
			String ContactName2 = c2.getName().toUpperCase();

			// ascending order
			return ContactName1.compareTo(ContactName2);

			// descending order
			// return ContactName2.compareTo(ContactName1);
		}
	};

	public static Comparator<Contact> callsComparator = new Comparator<Contact>() {
		public int compare(Contact c1, Contact c2) {

			int ContactCalls1 = c1.getOutgoingCalls();
			int ContactCalls2 = c2.getOutgoingCalls();

			// ascending order
			// return ContactCalls1-ContactCalls2;

			// descending order
			return ContactCalls2 - ContactCalls1;

		}
	};
}