package eternity;

import java.util.ArrayList;

public class Parser {
	public static double parseInput(String input) {
		var tokens = lex(input);

		return 0.0;
	}

	public static ArrayList<String> lex(String input) {
		var tokens = new ArrayList<String>();
		for (var p = 0; p < input.length();) {
			System.out.println(input.charAt(p));
			var tokenToAdd = "";
			switch (input.charAt(p)) {
			case '+': {
				p++;
				tokenToAdd = "ADD";
			}
				break;
			case '-': {
				p++;
				tokenToAdd = "SUB";
			}
				break;
			case '/': {
				p++;
				tokenToAdd = "DIV";
			}
				break;
			case '*': {
				p++;
				tokenToAdd = "MUL";
			}
				break;
			default: {
				var token = "";
				if (('A' <= input.charAt(p) && input.charAt(p) <= 'Z')
						|| ('a' <= input.charAt(p) && input.charAt(p) <= 'z')) {
					while (input.charAt(p + 1) != '(') {
						token += input.charAt(p);
						p++;
					}
				} else if ('0' <= input.charAt(p) && input.charAt(p) <= '9') {
					var decimal = false;
					while ('0' <= input.charAt(p) && input.charAt(p) <= '9' || input.charAt(p) == '.') {
						if (input.charAt(p) == '.') {
							if (decimal) {
								throw new NumberFormatException("Number cannot contain more than one decimal points.");
							}
							token += input.charAt(p);
							decimal = true;
						} else {
							token += input.charAt(p);
						}
						p++;
					}
				}

				tokenToAdd = token;
			}
			}

			tokens.add(tokenToAdd);
		}

		return tokens;
	}

	public static void main(String args[]) {
		var inputString = "1 + 2";
		var tokens = lex(inputString);
		for (var tok : tokens) {
			System.out.println(tok);
		}
	}
}
