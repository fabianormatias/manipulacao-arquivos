package br.com.waiso.leitura.escrita.exemplo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileTestUser2 {
	
	public static void main(String[] args) {
		
		// Criamos 6 users
		User user1 = new User(1, "Ronaldinho Gaúcho", "Jogador de Futebol");
		User user2 = new User(2, "Nelson Piquet", "Piloto de Fómula 1");
		User user3 = new User(3, "Monteiro Lobato", "Escritor");
		User user4 = new User(4, "Luana Piovani", "Atriz");
		User user5 = new User(5, "Ana Maria Braga", "Apresentadora de Televisão");
		User user6 = new User(6, "João Carlos", "Programador JEE");
		// adicionamos os users em um lista
		List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
		userList.add(user5);
		userList.add(user6);
		// passamos a lista por parâmetro
		layout(userList);
	}

	private static void layout(List<User> users) {
		File dir = new File("//Users/fabianorodriguesmatias/Developer/testes/testes");
		File arq = new File(dir, "teste3.txt");

        try {
            arq.createNewFile();
            FileWriter fileWriter = new FileWriter(arq, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            //Utilizamos o método print() para escrever na
            // mesma linha e um ponto e vírgula no final.
            //O println forçará a troca de linha
            // para o próximo user.
            for (User user : users) {
                printWriter.print(user.getId() + ";");
                printWriter.print(user.getNome() + ";");
                printWriter.println(user.getObservacao());
            }
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileReader fileReader = new FileReader(arq);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = "";

            //Lista que irá guardar o resultado, ou seja,
            // cada linha do arquivo que corresponde a um User
            List<String> result = new ArrayList<String>();

            while ((linha = bufferedReader.readLine()) != null) {
                System.out.println(linha);
                if (linha != null && !linha.isEmpty()) {
                    result.add(linha);
                }
            }
            fileReader.close();
            bufferedReader.close();

            for (String s : result) {
                //Usamos o método split da classe String
                // para separar as partes entre os ponto e vírgulas.
                //Guardamos o resultado em um array
                String[] user = s.split(";");

                //Criamos um objeto User e setamos em seus atributos
                //as posições correspondentes do array
                User u = new User();
                u.setId(Integer.valueOf(user[0]));
                u.setNome(user[1]);
                u.setObservacao(user[2]);

                //exibe o conteúdo do objeto u
                System.out.println(u.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}