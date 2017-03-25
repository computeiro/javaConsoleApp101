package agenda.ui;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConsoleMenu {
    private List<ItemMenu> itens;
    private Set<Integer> optionsSet;
    private FormPrinter message;
    private String path;

    public ConsoleMenu() {
	itens = new ArrayList<ItemMenu>();
	optionsSet = new HashSet<Integer>();
    }

    public void addItem(String descrItem) {
	ItemMenu item = new ItemMenu();
	item.index = itens.size() + 1;
	optionsSet.add(item.index);
	item.label = descrItem;
	itens.add(item);
    }

    public void addItem(int index, String descrItem) {
	ItemMenu item = new ItemMenu();
	item.index = index;
	optionsSet.add(index);
	item.label = descrItem;
	itens.add(item);
    }

    public void setPath(String... path) {
	StringBuffer sb = new StringBuffer();
	int numItens = path.length;
	int i = 0;
	sb.append("# ");

	for (String step : path) {
	    sb.append(step);

	    if (i < (numItens - 1)) {
		sb.append(" >> ");
	    }

	    i++;
	}

	this.path = sb.toString();
    }

    public int show() {
	Console console = System.console();

	// Monta menu
	StringBuffer out = new StringBuffer();
	String line = buildLineSeparator();
	out.append("\n");
	out.append(line);

	if (path != null) {
	    out.append(path);
	    out.append("\n");
	    out.append(line);
	}

	out.append("\n");

	if (message != null) {
	    out.append(message.asString());
	}

	if (itens != null) {
	    for (ItemMenu item : itens) {
		out.append("[");
		out.append(item.index);
		out.append("] ");
		out.append(item.label);
		out.append("\n");
	    }
	}

	out.append("\nOpção:> ");

	// Exibe menu
	boolean isOk = false;
	String in = "";

	while (!isOk) {
	    in = console.readLine(out.toString());
	    isOk = validateChoose(in);
	}

	return Integer.parseInt(in);
    }

    private String buildLineSeparator() {
	if (path == null) {
	    return "";
	}

	StringBuffer sb = new StringBuffer();
	int width = path.length();

	for (int i = 0; i < width; i++) {
	    sb.append("=");
	}

	sb.append("\n");

	return sb.toString();
    }

    public FormPrinter getMessage() {
	return message;
    }

    public void setMessage(FormPrinter message) {
	this.message = message;
    }

    public void setMessage(String message) {
	this.message = new FormPrinter();
	this.message.putEnunciado(message);

    }

    private boolean validateChoose(String in) {
	Console console = System.console();

	// Se null, encerra a aplicação pq provavelemente foi digitado ctrl+C
	if (in == null) {
	    System.exit(0);
	}

	// Verifica se foi digitada alguma coisa
	if ((in.length() == 0) || (in == "")) {
	    console.printf("\nDigite uma das opções!\n");

	    return false;
	}

	// Verifica se é número
	for (int i = 0; i < in.length(); i++) {
	    if (!Character.isDigit(in.charAt(i))) {
		console.printf("\nOpção inválida!\n");

		return false;
	    }
	}

	int item = Integer.parseInt(in);

	// Se for o número 0, a aplicação termina
	if (item == 0) {
	    System.exit(0);
	}

	// Verifica se o número é um item válido
	if ((item >= 1) && (optionsSet.contains(new Integer(in)))) {
	    return true;
	}

	console.printf("\nOpção inválida!\n");

	return false;
    }

    private class ItemMenu {
	private String label;
	private int index;
    }
}
