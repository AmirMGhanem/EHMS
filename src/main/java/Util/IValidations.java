package Util;

public interface IValidations {

	default boolean nameValidation(String str) {
		String expression = "[A-Za-z\\s]+";
		return str.matches(expression);
	}

	default boolean numValidation(String str, int nums) {
		String expression = "^([0-9]{" + nums + "})$";
		return str.matches(expression);
	}

	default boolean numValidation(String str) {
		String expression = "^([0-9])*$";
		return str.matches(expression);
	}

}