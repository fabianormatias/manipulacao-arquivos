package com.g6.escrita.modelocobol3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.regex.Pattern;

public class JavaIOUtils {

	/**
	 * Diretório User
	 */
	public static final String USER_DIR = System.getProperty("user.dir");
	
	/**
	 * Separador de diretório '\' ou '/'
	 */
	public static final String SEPARATOR = System.getProperty("file.separator");
	
	/**
	 * Separador de linhas '\n'
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
	
	/**
	 * Verifica o nome do sistema operacional.
	 */
	public static final String OPERATING_SYSTEM = System.getProperty("os.name");

	/**
	 * 
	 * Cria um diretório dentro de seu espaço de trabalho do projeto.
	 * 
	 * @param folderName
	 * 				workpace/myProject/folderName
	 */
	public static void createLocalFolder(String folderName) {
		createFolder(USER_DIR + SEPARATOR + folderName);
	}

	/**
	 * Remove um diretório dentro de seu espaço de trabalho do projeto.
	 * 
	 * @param folderName
	 *            	workpace/myProject/folderName
	 */
	public static void deleteLocalFolder(String folderName) {
		deleteFolder(USER_DIR + SEPARATOR + folderName);
	}

	/**
	 * Cria uma pasta em qualquer lugar no computador.
	 * 
	 * @param path
	 *            exemplo path "c:/tests/io"
	 */
	public static void createFolder(String path) {
		if (!exist(path)) {
			File folder = new File(path);
			folder.mkdirs();
		}
	}

	/**
	 * Remove uma pasta em qualquer lugar no computador.
	 * 
	 * @param path
	 *            exemplo de path "c:/tests/io"
	 */
	public static void deleteFolder(String path) {
		if (exist(path)) {
			deleteAll(path);
		}
	}

	/**
	 * Deleta todos os aruivos do diretorio
	 * 
	 * @param path
	 *            o caminho de um diretório no sistema.
	 */
	private static void deleteAll(String path) {
		deleteAll(new File(path));
	}

	/**
	 * Delete diretorios recursivavmente, subpastas e arquivos.
	 * 
	 * @param file
	 *            objeto do tipo File
	 */
	private static void deleteAll(File file) {
		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (File f : files)
				deleteAll(f);
			file.delete();
		}
	}

	/**
	 * Verificar se um arquivo ou diretório existe.
	 * 
	 * @param path
	 *            exemplo de path "c:/tests/io"
	 * 
	 * @return true ou false
	 */
	public static boolean exist(String path) {
		File file = new File(path);
		return file.exists();
	}

	/**
	 * Criar um arquivo de texto no local especificado, verificando se o lugar existe, se não existir, será criado.
	 * 
	 * @param path
	 *            exemplo "c:/tests/io"
	 * @param fileName
	 *            exemplo file.txt
	 * @param content
	 * 			  conteúdo
	 * @param overwrite
	 *            true or false - Informa se o arquivo deve ser substituído
	 */
	public static void createTextFile(String path, String fileName, String content, boolean overwrite) {
		if (!exist(path)) {
			createFolder(path);
		}
		saveFile(path + SEPARATOR + fileName, content, overwrite);
	}

	/**
	 * Salva os dados no disco, permitindo que você escolha para substituir o conteúdo ou não.
	 * 
	 * @param fileName
	 * 			  exemplo file.txt
	 * @param content
	 * 			  conteúdo
	 * @param overwrite
	 *            true or false - Informa se o arquivo deve ser substituído
	 */
	private static void saveFile(String fileName, String content, boolean overwrite) {
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(new File(fileName), overwrite);
			output.write(content.getBytes());
			output.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * Abre o arquivo no caminho informado.
	 * 
	 * @param path
	 *            exemplo "c:/tests/io/file.txt"
	 * 
	 * @return String
	 */
	public static String openTextFile(String path) {
		String returnValue = "";
		FileReader file = null;
		try {
			file = new FileReader(path);
			BufferedReader reader = new BufferedReader(file);
			String line = "";
			while ((line = reader.readLine()) != null) {
				returnValue += line + "\n";
			}
			reader.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return returnValue;
	}

	/**
	 * Copiar um arquivo de um local para outro.
	 * 
	 * @param origem
	 *            origin path
	 * @param destino
	 *            destiny path
	 * @param overwrite
	 *            confirmation to overwrite
	 * 
	 * @throws IOException
	 */
	public static void copy(File origem, File destino, boolean overwrite) throws IOException {
		if (destino.exists() && !overwrite) {
			return;
		}
		if (destino.isFile() && !destino.exists()) {
			destino.createNewFile();
		}
		if (destino.isDirectory() && !destino.exists()) {
			destino.mkdirs();
		}
		FileInputStream fisOrigem = new FileInputStream(origem);
		FileOutputStream fisDestino = new FileOutputStream(destino);
		FileChannel fcOrigem = fisOrigem.getChannel();
		FileChannel fcDestino = fisDestino.getChannel();
		fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
		fisOrigem.close();
		fisDestino.close();
	}

	/**
	 * Fazer cópia de um arquivo.
	 * 
	 * @param reader
	 * @param writer
	 * 
	 * @throws IOException
	 */
	private static void copy(Reader reader, Writer writer) throws IOException {
		BufferedReader bufReader = new BufferedReader(reader);
		String line;
		while (null != (line = bufReader.readLine())) {
			writer.write(line + '\n');
		}
		writer.flush();
		writer.close();
		bufReader.close();
	}

	/**
	 * Copiar um arquivo indicar os caminhos de origem e destino.
	 * 
	 * Utilize este método quando tiver certeza de que o destino existe!
	 * 
	 * @param originPath
	 *            endereço de origem do arquivo.
	 * @param destinyPath
	 *            endereço de destino do arquivo.
	 * 
	 * @throws IOException
	 */
	public static void copy(String originPath, String destinyPath) throws IOException {
		InputStream in = new FileInputStream(originPath);
		OutputStream out = new FileOutputStream(destinyPath, false);
		copy(new InputStreamReader(in), new OutputStreamWriter(out));
	}

	/**
	 * Copiar um arquivo indicar os caminhos de origem e destino.
	 * 
	 * Se a pasta de destino não existir, será criada!
	 * 
	 * @param originPath
	 * @param destinationPath
	 * @param fileName
	 * 
	 * @throws IOException
	 */
	public static void copy(String originPath, String destinationPath, String fileName) throws IOException {
		if (!exist(destinationPath)) {
			createFolder(destinationPath);
		}
		copy(originPath + SEPARATOR + fileName, destinationPath + SEPARATOR + fileName);
	}

	/**
	 * Copie todos os arquivos de um diretório para outro.
	 * 
	 * @param originPath
	 *            endereço de origem do arquivo.
	 * @param destinyPath
	 *            endereço de destino do arquivo.
	 * @param overwrite
	 *            confirmation to overwrite.
	 * 
	 * @throws IOException
	 */
	public static void copyAll(File originPath, File destinyPath, boolean overwrite) throws IOException {
		if (!destinyPath.exists())
			destinyPath.mkdir();
		if (!originPath.isDirectory())
			throw new UnsupportedOperationException("Diretório [" + originPath.getParent() + "] não existe!");

		if (!destinyPath.isDirectory() || !destinyPath.exists())
			throw new UnsupportedOperationException("Diretório [" + destinyPath.getParent() + "] não existe!");

		File[] files = originPath.listFiles();

		for (File file : files) {
			if (file.isDirectory())
				copyAll(file, new File(destinyPath + SEPARATOR + file.getName()), overwrite);
			else {
				copy(file, new File(destinyPath + SEPARATOR + file.getName()), overwrite);
			}
		}
	}

	/**
	 * Renomear um arquivo.
	 * 
	 * @param path
	 *            file path
	 * @param filename
	 *            old file name
	 * @param newName
	 *            new file name
	 */
	public static void renameFile(String path, String filename, String newName) {
		if (exist(path + SEPARATOR + filename)) {
			File file = new File(path + SEPARATOR + filename);
			file.renameTo(new File(path + SEPARATOR + newName));
		}
	}

	/**
	 * Deletar um arquivo.
	 * 
	 * @param path
	 */
	public static void deleteFile(String path) {
		if (exist(path)) {
			File file = new File(path);
			if (file.isFile())
				file.delete();
		}
	}

	/**
	 * Mover um arquivo de origem para o destino.
	 * 
	 * @param pathOrigin
	 *            exemplo "c:/tests/io"
	 * @param pathDestiny
	 *            exemplo "c:/tests/svn"
	 * @param fileName
	 *            exemplo "file.xml"
	 * 
	 * @throws IOException
	 *             File not found or permission denied access.
	 */
	public static void moveFile(String pathOrigin, String pathDestiny, String fileName) throws IOException {
		File origin = new File(pathOrigin + SEPARATOR + fileName);
		if (!exist(pathDestiny)) {
			createFolder(pathDestiny);
		}
		File destiny = new File(pathDestiny + SEPARATOR + fileName);
		copy(origin, destiny, true);
		origin.delete();
	}

	/**
	 * Verifica se o sistema operacional é Windows.
	 * 
	 * @return true ou false
	 */
	public static boolean isWindows() {
		String os = OPERATING_SYSTEM.toLowerCase();
		return (os.indexOf("win") >= 0);
	}

	/**
	 * Verifica se o sistema operacional é Unix.
	 * 
	 * @return true ou false
	 */
	public static boolean isUnix() {
		String os = OPERATING_SYSTEM.toLowerCase();
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0);
	}

	/**
	 * Verifica se o sistema operacional é Mac.
	 * 
	 * @return true ou false
	 */
	public static boolean isMac() {
		String os = OPERATING_SYSTEM.toLowerCase();
		return (os.indexOf("mac") >= 0);
	}

	/**
	 * Verifica se o sistema operacional é Solaris.
	 * 
	 * @return true ou false
	 */
	public static boolean isSolaris() {
		String os = OPERATING_SYSTEM.toLowerCase();
		return (os.indexOf("sunos") >= 0);
	}

	/**
	 * Divide o caminho em partes.
	 * 
	 * @param path
	 * 
	 * @return String[]
	 */
	public static String[] getSegments(String path) {
		if (path.contains("/")) {
			return path.split("/");
		}
		String pattern = Pattern.quote(SEPARATOR);
		return path.split(pattern);
	}

	/**
	 * Pega a última parte do caminho.
	 * 
	 * @param path
	 * 
	 * @return String
	 */
	public static String getLastSegment(String path) {
		String[] segments = getSegments(path);
		return segments[segments.length - 1];
	}

	/**
	 * Remove a última parte do caminho.
	 * 
	 * @param path
	 * 
	 * @return String
	 */
	public static String removeLastSegment(String path) {
		String last = getLastSegment(path);
		return path.replace(last, "");
	}

	/**
	 * Listando todos os arquivos em uma pasta.
	 * 
	 * @param path
	 * 
	 * @return File[]
	 */
	public static File[] list(String path) {
		File f = new File(path);
		return f.listFiles();
	}

}