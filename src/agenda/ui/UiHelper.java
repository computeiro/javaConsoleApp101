package agenda.ui;

import java.util.Scanner;

public class UiHelper {
    private static final int TITLE_WIDTH = 80;
    private Scanner scanner;

    public UiHelper(Scanner scanner) {
	this.scanner = scanner;
    }

    public void printTitle(String title) {
	if (title.length() + 4 >= TITLE_WIDTH) {
	    throw new IllegalStateException("O título não irá caber, aumente o TITLE_WIDTH");
	}

	int padr = (TITLE_WIDTH - title.length()) / 2 - 1; // +2 por causa dos
							   // '#'
	int padl = title.length() % 2 == 0 ? padr : (padr + 1); // Se o Título
								// for impar
								// deixamos um '
								// ' a mais no
								// padding left

	StringBuilder out = new StringBuilder();
	out.append("\n");

	String rule = repeat("=", TITLE_WIDTH);

	out.append(rule);
	out.append("\n");
	out.append("#");

	out.append(repeat(" ", padr));
	out.append(title);
	out.append(repeat(" ", padl));

	out.append("#");
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

    public String requiredString(String label) {
	StringBuilder msg = new StringBuilder();
	msg.append("\n");
	msg.append(label);
	msg.append("\n$> ");

	String input = null;

	do {
	    System.out.println(msg.toString());
	    input = scanner.nextLine();

	    if (input != null && input.trim().length() == 0) {
		return input;
	    }

	    System.out.println("\nValor inválido!");
	} while (true);
    }

    public static String padl(Object value, int width, char fill) {
	StringBuffer valstr = new StringBuffer((value != null) ? value.toString() : "");

	if (valstr.length() > 0) {
	    int last = 0;
	    // fazemos um LTRIM para evitar problemas de perda de informação
	    // valiosa por conta de chamadas encadeadas a PADL
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
}
