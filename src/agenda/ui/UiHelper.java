package agenda.ui;

import agenda.Contato;

public class UiHelper {
	public static void printTitle(String title) {

		StringBuilder out = new StringBuilder();
		out.append("\n");

		String rule = repeat("=", title.length() + 2);

		out.append(rule);
		out.append("\n");
		out.append("# ");

		out.append(title);

		out.append("\n");
		out.append(rule);
		out.append("\n");

		System.out.println(out.toString());
	}

	public static String repeat(String str, int count) {
		StringBuilder stb = new StringBuilder();

		for (int i = 0; i < count; i++) {
			stb.append(str);
		}

		return stb.toString();
	}

	public static String requiredString(String label) {
		return requiredString(label, null, null);
	}

	public static String requiredString(String label, PatternInput pattern, String validationMsg) {
		StringBuilder msg = new StringBuilder();
		msg.append(label);
		msg.append("\n$> ");

		String input = null;

		do {
			System.out.print(msg.toString());
			input = System.console().readLine();

			if (input != null && input.trim().length() > 0) {

				if (pattern == null) {
					break;
				}

				if (input.matches(pattern.regex)) {
					break;
				}
			}

			if (validationMsg == null) {
				System.out.println("\nValor inválido!");
			} else {
				System.out.println(validationMsg);
			}
		} while (true);

		return input;
	}

	public static String padl(Object value, int width, char fill) {
		StringBuffer valstr = new StringBuffer((value != null) ? value.toString() : "");

		if (valstr.length() > 0) {
			int last = 0;
			while (last < valstr.length() && valstr.charAt(last) == fill) {
				last++;
			}

			valstr.delete(0, last);
		}

		if (valstr.length() < width) {
			while (valstr.length() < width) {
				valstr.insert(0, fill);
			}
		} else {
			valstr.delete(width, valstr.length());
		}

		return valstr.toString();
	}
	
	public static enum PatternInput{
		CELULAR("(?:\\d+){7,9}"), //Pra precisar não digitar (xx) xxxxx-xxxxx
		EMAIL("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"); //Precisa melhorar?  copia a regex daqui ó: http://emailregex.com/
		
		private String regex;
		
		private PatternInput(String regex) {
			this.regex = regex;
		}
	}
	
	public static void print(Contato contato){
		FormPrinter fp = new FormPrinter();
		fp.put("Nome", contato.getNome());
		fp.put("Celuar", contato.getCelular());
		fp.put("Email", contato.getEmail());
		
		System.out.println(fp.asString());
	}
	
}
