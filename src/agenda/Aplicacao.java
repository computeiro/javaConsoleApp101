package agenda;

import java.util.Scanner;

import agenda.ui.UiHelper;

public class Aplicacao {
    private Scanner scanner;
    private ArvoreAVL arvore;
    private UiHelper uiHelper;

    // Se tiver problemas com acentuação converta o código de cp1252 para UTF-8
    // (sem BOM)
    // Você pode usar o Notepad++ ou qlq site que o valha, ex:
    // http://www.motobit.com/util/charset-codepage-conversion.asp
    public static void main(String[] args) {
	Aplicacao app = new Aplicacao();
	app.execute();
    }

    public Aplicacao() {
	arvore = new ArvoreAVL();
    }

    public void execute() {
	// Não, para isso inicializamos (null) e fazemos um try{ ... }finally{
	// close()}
	// Isso sempre acontece com ZipFile, FileInpustream e 'tudo' que termine
	// em Stream e/ou implementa Closeable
	try {
	    scanner = new Scanner(System.in);
	    uiHelper = new UiHelper(scanner);
	    // StringBuilder é uma opção mais organizada e performática que
	    // "ficar" + "concatenando" + "String" + "(com +)"
	    StringBuilder menu = new StringBuilder();
	    
	    uiHelper.printTitle("Cadastro de Contatos >> Menu Principal");
	    menu.append("  [1]  Novo contato\n"); // 2.a
	    menu.append("  [2]  Localizar contato\n"); // 2.b
	    menu.append("  [3]  Localizar e inserir se não encontrar\n"); // 2.c
	    menu.append("  [4]  Listar contatos (ordem alfabética)\n"); // 2.d
	    menu.append("  [5]  Listar contatos (pré ordem)\n"); // 2.e
	    menu.append("  [6]  Listar contatos (pos ordem)\n"); // 2.f
	    menu.append("  [7]  Listar letra em que a agenda inicia\n"); // 2.f
	    menu.append("  [8]  Listar letra em que a agenda termina\n"); // 2.f
	    menu.append("  [9]  Listar letra em que a agenda termina\n"); // 2.f
	    menu.append("  [10] Remover contato\n"); // 2.f
	    menu.append("  [11] Imprimir todos os nomes de uma letra\n\n"); // 2.f
	    menu.append("  Digite uma opção $> "); // 2.f

	    int opcao = -1; // corresponde a um item do menu

	    do {
		System.out.print(menu.toString());
		opcao = scanner.nextInt();

		switch (opcao) {
		case 1:
		    cadastrarContato();
		    break;
		case 2:
		    System.out.println("Opçao escolhida: 2. Colocar o título da segunda opção");
		    // código para tratar essa opção do menu
		    break;
		case 3:
		    System.out.println("Opçao escolhida: 3. Colocar o título da terceira opção");
		    // código para tratar essa opção do menu
		    break;
		case 4:
		    System.out.println("Opçao escolhida: 4. Colocar o título da terceira opção");
		    // código para tratar essa opção do menu
		    break;
		case 5:
		    System.out.println("Opçao escolhida: 5. Colocar o título da terceira opção");
		    // código para tratar essa opção do menu
		    break;
		case 6:
		    System.out.println("Encerrando o sistema.");
		    break;
		default:
		    System.out.println("Opção inválida. Utilize apenas as opções existentes no menu");
		    break;
		}
	    } while (opcao != 6);// aplicação será repetida enquanto o usuário
				 // não
	} finally {
	    try {
		scanner.close();
	    } catch (Exception ignored) {
	    }
	}
    }

    private void cadastrarContato() {
	ContatoNoh contatoNoh = new ContatoNoh();

	contatoNoh.setNome(uiHelper.requiredString("Digite o nome:"));
	contatoNoh.setCelular(uiHelper.requiredString("Digite o celular:"));
	contatoNoh.setNome(uiHelper.requiredString("Digite o email:"));

	arvore.inserir(contatoNoh);
    }

}
