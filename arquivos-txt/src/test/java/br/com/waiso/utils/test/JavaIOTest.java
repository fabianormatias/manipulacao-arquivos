package br.com.waiso.utils.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import br.com.waiso.utils.JavaIO;

/**
 * Testing the JavaIO class.
 * 
 * @author Samuel T. C. Santos
 *
 * @version $Revision: 1.0 $
 */
public class JavaIOTest {

	private String localBase;
	private String slash = JavaIO.SEPARATOR;
	private String pathLocal = JavaIO.USER_DIR + slash;

	@Before
	public void setUp() {
		if (JavaIO.isWindows()) {
			localBase = "c:/data";
		} else {
			localBase = pathLocal + "test";
		}
	}

	@Test
	public void shouldCreateDirOnCurrentWorkspace() {
		JavaIO.createLocalFolder("data");
		assertTrue(JavaIO.exist(pathLocal + "data"));
	}

	@Test
	public void shouldRemoveDirFromCurrentWorkspace() {
		JavaIO.createLocalFolder("folderA");
		assertTrue(JavaIO.exist(pathLocal + "folderA"));
		JavaIO.deleteLocalFolder("folderA");
		assertFalse(JavaIO.exist(pathLocal + "folderA"));
	}

	@Test
	public void shouldCreateFileAnyWhere() {
		JavaIO.createFolder(localBase + slash + "io");
		assertTrue(JavaIO.exist(localBase + slash + "io"));
	}

	@Test
	public void shoultRemoveFileAnyWhere() {
		JavaIO.createFolder(localBase + slash + "myDir");
		assertTrue(JavaIO.exist(localBase + slash + "myDir"));
		JavaIO.deleteFolder(localBase + slash + "myDir");
		assertFalse(JavaIO.exist(localBase + slash + "myDir"));
	}

	@Test
	public void shouldVerifyIfFileOrDirExists() {
		JavaIO.createFolder(localBase + slash + "io");
		assertFalse(JavaIO.exist(localBase + slash + "ivy" + slash + "texto.txt"));
		assertTrue(JavaIO.exist(localBase + slash + "io"));
	}

	@Test
	public void shouldToCreateTextFile() {
		JavaIO.createTextFile(localBase + slash + "javaio", "myFile.txt", "uhhuaaa!!!", false);
		assertTrue(JavaIO.exist(localBase + slash + "javaio" + slash + "myFile.txt"));
		File file = new File(localBase + slash + "javaio" + slash + "myFile.txt");
		assertTrue(file.length() > 0);
	}

	@Test
	public void shouldDoOverwriteWhenOptionForFALSE() {
		JavaIO.createTextFile(localBase + slash + "javaio", "myFile.txt", "uhhuaaa!!!", false);
		JavaIO.createTextFile(localBase + slash + "javaio", "myFile.txt", "uhhuaaa!!!", false);
		String content = JavaIO.openTextFile(localBase + slash + "javaio" + slash + "myFile.txt");
		assertThat(content, is(equalTo("uhhuaaa!!!\n")));
	}

	@Test
	public void shouldDoNotOverwriteWhenOptionForTRUE() {
		if (JavaIO.exist(localBase + slash + "javaio" + slash + "myFile.txt"))
			JavaIO.deleteFile(localBase + slash + "javaio" + slash + "myFile.txt");

		JavaIO.createTextFile(localBase + slash + "javaio", "myFile.txt", "uhhuaaa!!!", true);
		JavaIO.createTextFile(localBase + slash + "javaio", "myFile.txt", "uhhuaaa!!!", true);
		String content = JavaIO.openTextFile(localBase + slash + "javaio" + slash + "myFile.txt");
		assertThat(content, is(equalTo("uhhuaaa!!!uhhuaaa!!!\n")));
	}

	@Test
	public void shouldOpenTextFileAndRetrieveContent() {
		JavaIO.createTextFile(localBase + slash + "io", "texto.txt", "texto no arquivo!", false);
		assertTrue(JavaIO.exist(localBase + slash + "io" + slash + "texto.txt"));
		String content = JavaIO.openTextFile(localBase + slash + "io" + slash + "texto.txt");
		assertTrue(content.contains("texto no arquivo!"));
	}

	@Test
	public void shouldToDoRenameFile() {
		if (JavaIO.exist(localBase + slash + "io"))
			JavaIO.deleteFolder(localBase + slash + "io");

		JavaIO.createTextFile(localBase + slash + "io", "antigo.txt", "content to text file!", false);
		assertTrue(JavaIO.exist(localBase + slash + "io" + slash + "antigo.txt"));
		JavaIO.renameFile(localBase + slash + "io", "antigo.txt", "novo.txt");
		assertFalse(JavaIO.exist(localBase + slash + "io" + slash + "antigo.txt"));
		assertTrue(JavaIO.exist(localBase + slash + "io" + slash + "novo.txt"));
	}

	@Test
	public void shouldDeleteFiles() {
		JavaIO.createTextFile(localBase + slash + "io", "newfile.txt", "content to text file!", false);
		assertTrue(JavaIO.exist(localBase + slash + "io" + slash + "newfile.txt"));
		JavaIO.deleteFile(localBase + slash + "io" + slash + "newfile.txt");
		assertFalse(JavaIO.exist(localBase + slash + "io" + slash + "newfile.txt"));
	}

	/**
	 * Method shouldMoverFileFromDirToAnother.
	 * 
	 * @throws IOException
	 */
	@Test
	public void shouldMoverFileFromDirToAnother() throws IOException {
		JavaIO.createTextFile(localBase + slash + "git", "file.txt", "git", false);
		assertTrue(JavaIO.exist(localBase + slash + "git" + slash + "file.txt"));
		JavaIO.moveFile(localBase + slash + "git", localBase + slash + "svn", "file.txt");
		assertFalse(JavaIO.exist(localBase + slash + "git" + slash + "file.txt"));
		assertTrue(JavaIO.exist(localBase + slash + "svn" + slash + "file.txt"));
	}

	@Test
	public void shouldToAppendOnTextFiles() {
		String dir = localBase + slash + "increment";
		String file = "a.txt";
		String path = dir + slash + file;
		if (JavaIO.exist(path)) {
			JavaIO.deleteFile(path);
		}
		JavaIO.createTextFile(dir, file, "a", true);
		String content = JavaIO.openTextFile(path);
		assertTrue(content.contains("a"));
		JavaIO.createTextFile(dir, file, "a", true);
		content = JavaIO.openTextFile(path);
		assertTrue(content.contains("aa"));
	}

	/**
	 * Method shouldCopyFileFromOriginToDestination.
	 * 
	 * @throws IOException
	 */
	@Test
	public void shouldCopyFileFromOriginToDestination() throws IOException {
		JavaIO.createFolder(localBase + slash + "copy" + slash + "origin");
		JavaIO.createTextFile(localBase + slash + "copy" + slash + "origin", "a.txt",
				"Chuaua\nchuaua\nChuaua\nShuaua\nXuaua", true);
		JavaIO.createFolder(localBase + slash + "copy" + slash + "destiny");
		JavaIO.copy(localBase + slash + "copy" + slash + "origin" + slash + "a.txt",
				localBase + slash + "copy" + slash + "destiny" + slash + "a.txt");
		assertTrue(JavaIO.exist(localBase + slash + "copy" + slash + "destiny" + slash + "a.txt"));
	}

	@Test
	public void shouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris() {
		if (JavaIO.OPERATING_SYSTEM.toLowerCase().contains("win")) {
			assertTrue(JavaIO.isWindows());
		} else {
			assertTrue(JavaIO.isUnix() || JavaIO.isMac() || JavaIO.isSolaris());
		}
	}

	@Test
	public void shouldGetSegmentsInPath() {
		String[] segments = JavaIO.getSegments("c:" + slash + "tests" + slash + "workspace");
		assertTrue(segments.length == 3);
		segments = JavaIO.getSegments("c:/tests");
		assertTrue(segments.length == 2);
	}

	@Test
	public void shouldGetLastSegment() {
		String path = "file:/camila/net_filtro_vt/ies";
		assertEquals("ies", JavaIO.getLastSegment(path));
	}

	@Test
	public void shouldRemoveLastSegment() {
		String path = "file:/camila/net/ies";
		assertEquals("file:/camila/net/", JavaIO.removeLastSegment(path));
	}

	/**
	 * Method shouldCopyFilesWhenDestinationFolderDoesNotExist.
	 * 
	 * @throws IOException
	 */
	@Test
	public void shouldCopyFilesWhenDestinationFolderDoesNotExist() throws IOException {
		JavaIO.createTextFile(localBase + slash + "dirOrigin", "origin.txt", "AAAA", true);
		JavaIO.copy(localBase + slash + "dirOrigin", localBase + slash + "dirDest", "origin.txt");
		assertTrue(JavaIO.exist(localBase + slash + "dirDest" + slash + "origin.txt"));
	}

	@Test
	public void shouldToListAllFilesInFolder() {
		String local = JavaIO.USER_DIR + slash + "files";
		File[] files = JavaIO.list(local);
		assertTrue(files.length == 1);
	}

	/**
	 * Method shouldCopyAllFilesAndFolders.
	 * 
	 * @throws IOException
	 */
	@Test
	public void shouldCopyAllFilesAndFolders() throws IOException {
		String origin = JavaIO.USER_DIR + slash + "files";
		String destiny = JavaIO.USER_DIR + slash + "data";
		JavaIO.copyAll(new File(origin), new File(destiny), true);
		assertTrue(JavaIO.list(destiny).length == 1);
	}
}