package br.com.executorsonar.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Descompactador {

	public static void main(String[] args) {
		unZipIt("C:/Users/ANDRE/Desktop/anexos/fontawesome-free-5.11.2-web.zip", "C:\\Users\\ANDRE\\Desktop\\anexos\\");
	}

	/**
	 * Unzip it
	 * 
	 * @param zipFile
	 *            input zip file
	 * @param output
	 *            zip file output folder
	 */
	public static void unZipIt(String zipFile, String outputFolder) {

		byte[] buffer = new byte[1024];

		try {

			// create output directory is not exists
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			// get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);

				System.out.println("file unzip : " + newFile.getAbsoluteFile());

				if (newFile.isDirectory()) {
					newFile.mkdir();
				} else {

					// create all non exists folders
					// else you will hit FileNotFoundException for compressed
					// folder
					new File(newFile.getAbsoluteFile().getParent()).mkdirs();
					FileOutputStream fos = new FileOutputStream(newFile.getAbsoluteFile());

					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}

					fos.close();
				}
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

			System.out.println("Done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
