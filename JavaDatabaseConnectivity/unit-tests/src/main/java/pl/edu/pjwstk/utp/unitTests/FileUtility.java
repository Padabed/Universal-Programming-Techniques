package pl.edu.pjwstk.utp.unitTests;

import java.io.File;

public final class FileUtility {

	public static boolean isReadableFilePath(String path) {
		File file = new File(path);
		return file.exists() //
				&& file.isFile() //
				&& file.canRead();
	}
}