package br.com.waiso.leitura.escrita.exemplo2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileTestUser1 {
	
	public static void main(String[] args) {
		
		// criando um objeto User
		User user = new User();
		user.setId(1);
		user.setNome("Bart Simpson");
		user.setObservacao("Personagem de desenho animado.");

		// Chamando o método escrever e passando user como parâmetro
		escrever(user);

	}

	private static void escrever(User user) {
		File dir = new File("//Users/fabianorodriguesmatias/Developer/testes/testes");
		File arq = new File(dir, "teste2.txt");

		try {

			// neste ponto criamos o arquivo fisicamente
			arq.createNewFile();

			// Devemos passar no construtor do FileWriter qual arquivo
			// vamos manipular.
			// Esse construtor aceita dois tipos de parâmetros,
			// File ou String.
			// O parâmetro true indica que reescrevemos no arquivo
			// sem apagar o que já existe.
			// O false apagaria o conteúdo do arquivo e escreveria
			// o novo conteúdo.
			// Se não usar o 2° parâmetro, ele por padrão será false.
			// O mais importante, essa linha abre o fluxo do arquivo
			FileWriter fileWriter = new FileWriter(arq, false);

			// Agora vamos usar a classe PrintWriter para escrever
			// fisicamente no arquivo.
			// Precisamos passar o objeto FileReader em seu construtor
			PrintWriter printWriter = new PrintWriter(fileWriter);

			// Agora vamos escrever no arquivo com o método println(),
			// que nos permite escrever linha a linha no arquivo
			printWriter.println(user.getId());
			printWriter.println(user.getNome());
			printWriter.println(user.getObservacao());

			// o método flush libera a escrita no arquivo
			printWriter.flush();

			// No final precisamos fechar o arquivo
			printWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}