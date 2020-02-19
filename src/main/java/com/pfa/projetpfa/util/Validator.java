package com.pfa.projetpfa.util;

import com.pfa.projetpfa.entities.Product;
import com.pfa.projetpfa.entities.User;

public class Validator {

	public static boolean isAlphaNumerical(String input) {

		if (input != null && input != "") {
			if (input.matches("[a-zA-Z0-9]*")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNumerical(String input) {
		if (input != null && input != "") {
			if (input.matches("[0-9]*")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidEmail(String input) {
		if (input != null && input != "") {
			if (input.matches("^[a-zA-Z0-9._]*@[a-zA-Z0-9.-]*$")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isImageFile(String input) {
		if (input != null && input != "") {
			input = input.toLowerCase();
			if (input.endsWith(".png") || input.endsWith(".jpg") || input.endsWith(".jpeg") || input.endsWith(".gif")) {
				return true;
			}
		}
		return false;
	}

	public static String removeSpaces(String input) {
		String filterInput = "";
		if (input != null && input != "") {
			filterInput = input.replace(" ", "");
		}
		return filterInput;
	}

	public static boolean isUserEmpty(User user) {
		if (user.getPassword() == null || user.getPassword() == "") {
			return true;
		}
		if (user.getUsername() == null || user.getUsername() == "") {
			return true;
		}
		return false;
	}



	public static boolean isProductEmpty(Product prod) {

		if (prod.getDesignation() == null || prod.getDesignation() == "") {
			return true;
		}
		if (prod.getPrice() == 0) {
			return true;
		}
		if (prod.getQuantity() == 0) {
			return true;
		}
		return false;
	}
	public static boolean isStringEmpty(String input) {
		if (input == null || input.equals("")) {
			return true;
		}
		return false;
	}
}
