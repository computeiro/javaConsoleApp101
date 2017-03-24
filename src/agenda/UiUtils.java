package agenda;

public class UiUtils {
    private static final int TITLE_WIDTH = 80;
    
    public static void printTitle(String title) {
	if(title.length() +4 >= TITLE_WIDTH){
	    throw new IllegalStateException("O título não irá caber, aumente o TITLE_WIDTH");
	}
	
	int padr = (TITLE_WIDTH - title.length())/2 -1; //+2 por causa dos '#'
	int padl =  title.length() % 2 == 0 ? padr : (padr +1); // Se o Título for impar deixamos um ' ' a mais no padding left 
	
	StringBuilder out = new StringBuilder();
	out.append("\n");
	
	String rule = repeat("=", TITLE_WIDTH);
	
	out.append(rule);
	out.append("\n");
	out.append("#");
	

	out.append(repeat(" ",padr));
	out.append(title);
	out.append(repeat(" ",padl));
	
	out.append("#");
	out.append("\n");
	out.append(rule);
	out.append("\n");
	
	System.out.println(out.toString());
    }
    
    public static String repeat(String str, int count){
	StringBuilder stb = new StringBuilder();
	
	for(int i = 0; i < count; i++){
	    stb.append(str);
	}
	
	return stb.toString(); 
    }
}
