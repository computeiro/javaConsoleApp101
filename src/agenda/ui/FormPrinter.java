package agenda.ui;

import java.util.Collection;
import java.util.LinkedHashSet;

public class FormPrinter {

    private String separator;
    Collection<FormItem> form;

    public String getSeparator() {
	return separator;
    }

    public void setSeparator(String separator) {
	this.separator = separator;
    }

    public FormPrinter() {
	form = new LinkedHashSet<FormItem>();
	separator = ": ";
    }

    public void putEnunciado(String enunciado) {
	form.add(new FormItem(enunciado, null));
    }

    public void put(String label, String value) {
	form.add(new FormItem(label, value));
    }

    public void show() {
	System.console().printf(asString());
    }

    @Override
    public String toString() {
	return asString();
    }

    public String asString() {
	StringBuilder stb = new StringBuilder();
	// Encontra a string de maior comprimento
	int maxSize = 0;

	for (FormItem item : form) {

	    if (item.description == null) {
		continue;
	    }

	    if (item.label.length() > maxSize) {
		maxSize = item.label.length();
	    }
	}

	for (FormItem item : form) {

	    if (item.description == null) {
		stb.append(item.label);
		stb.append("\n");
	    } else {
		stb.append(UiHelper.padl(item.label, maxSize, '\u0020'));
		stb.append(separator);
		stb.append(item.description);
		stb.append("\n");
	    }

	}
	stb.append("\n");

	return stb.toString();
    }

    private class FormItem {
	String label;
	String description;

	FormItem(String label, String description) {
	    this.label = label;
	    this.description = description;
	}
    }
}
