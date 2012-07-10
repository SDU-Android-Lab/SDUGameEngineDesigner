/**
 * 
 */
package util;

import java.io.File;
import java.io.IOException;

/**
 * 强制创建文件或目录，如果父目录不存在，自动创建
 * 
 * @author Joycery & Sww
 * 
 */
public class FileCreater {

	/**
	 * 强制创建文件，如果父目录不存在，自动创建
	 * 
	 * @param file
	 *            要创建的文件
	 * @return 是否创建成功
	 * @throws IOException
	 */
	public static void createFile(File file) {
		File p = file.getParentFile();
		if (!p.exists()) {
			p.mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
