package agenda;

import java.util.Scanner;

import agenda.ArvoreAVL.AvoreIteratorHandler;
import agenda.ui.ConsoleMenu;
import agenda.ui.UiHelper;
import agenda.ui.UiHelper.PatternInput;

public class Aplicacao {
	private Scanner scanner;
	private ArvoreAVL arvore;

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
	
	private void  carregaDadosParaTeste(){
		
		for(String nome : new String[]{"Gosling", "Jobs", "Torvalds", "TimBL" }){
			ContatoNoh ctt = new ContatoNoh();
			ctt.setNome(nome);
			ctt.setCelular("123456789");
			ctt.setCelular("vai@lavajato.com.br");
			arvore.inserir(ctt);
		}
	}

	public void execute() {
		//FiXME retirar isto!
		carregaDadosParaTeste();
		
		// Não, para isso inicializamos (null) e fazemos um try{ ... }finally{
		// close()}
		// Isso sempre acontece com ZipFile, FileInpustream e 'tudo' que termine
		// em Stream e/ou implementa Closeable
		try {
			scanner = new Scanner(System.in);

			ConsoleMenu mainMenu = new ConsoleMenu();
			mainMenu.setPath("Menu Principal");
			
			mainMenu.addItem("Novo contato"); //1
			mainMenu.addItem("Localizar contato"); //2
			mainMenu.addItem("Localizar e inserir se não encontrar"); //3
			mainMenu.addItem("Listar contatos (pré ordem | ordem alfabética)"); //4
			mainMenu.addItem("Listar contatos (pos ordem)"); //5
			mainMenu.addItem("Exibir letra em que a agenda inicia"); //6
			mainMenu.addItem("Exibir letra em que a agenda termina");//7
			mainMenu.addItem("Remover contato"); //8
			mainMenu.addItem("Listar todos os nomes de uma letra"); //9

			int opcao = -1; // corresponde a um item do menu

			do {
				opcao = mainMenu.show();

				switch (opcao) {
				case 1:
					cadastrarContato();
					break;
				case 2:
					localizarContato();
					break;
				case 3:
					cadastrarSeNaoExistir();
					break;
				case 4:
					imprimeArvorePreOrdem();
					break;
				case 5:
					imprimeArvorePosOrdem();
					break;
				case 6:
					exibeLetraInicial();
					break;
				case 7:
					exibeLetraFinal();
					break;
				case 8:
					removerContato();
					break;
				case 9:
					listarNomesPorLetra();
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
	
	private void listarNomesPorLetra() {
		// TODO Auto-generated method stub
		
	}

	private void removerContato() {
		// TODO Auto-generated method stub
		
	}

	private void exibeLetraFinal() {
		// TODO Auto-generated method stub
		
	}

	private void exibeLetraInicial() {
		// TODO Auto-generated method stub
		
	}
	
	private void imprimeArvorePreOrdem() {
		UiHelper.printTitle("Lista de Contatos (Pre Ordem | Alfabetica)");
		arvore.iteracaoPreOrdem(new AvoreIteratorHandlerImpl());
	}

	private void imprimeArvorePosOrdem() {
		UiHelper.printTitle("Lista de Contatos (Pre Ordem | Alfabetica)");
		arvore.iteracaoPosOrdem(new AvoreIteratorHandlerImpl());
	}
	

	//Opcao 1
	private void cadastrarContato() {
		UiHelper.printTitle("Inserindo Contato");
		
		ContatoNoh contatoNoh = new ContatoNoh();
		
		String nome = UiHelper.requiredString("Digite o nome:");
		
		Contato contatoEncontrado = arvore.buscaIterativa(nome);
		
		if(contatoEncontrado != null){
			System.out.println("\n[ERRO] Já existe um contato com este nome");
		}
		
		contatoNoh.setNome(nome);
		contatoNoh.setCelular(UiHelper.requiredString("Digite o celular:", PatternInput.CELULAR, "Digite apenas números do celuar"));
		contatoNoh.setEmail(UiHelper.requiredString("Digite o email:", PatternInput.EMAIL, "Formato inválido de e-mail!"));

		arvore.inserir(contatoNoh);
		
		System.out.println("\n[OK] Contato inserido!");
	}
	
	//Opcao 2
	private void localizarContato() {
		UiHelper.printTitle("Buscar contato");
		String nome = UiHelper.requiredString("Digite o nome:");
		
		Contato contato = arvore.buscaIterativa(nome);
		
		if(contato != null){
			UiHelper.print(contato);
		}else{
			System.out.println("[AVISO] Contato não encontrado!");
		}
	}
	
	private void cadastrarSeNaoExistir(){
		
	}
	
	private static class AvoreIteratorHandlerImpl implements AvoreIteratorHandler{
		@Override
		public void onNextElement(Contato noh) {
			System.out.println(noh.getNome());
		}
	}
}
