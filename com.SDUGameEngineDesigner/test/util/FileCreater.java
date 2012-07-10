/**
 * 
 */
package util;

import java.io.File;
import java.io.IOException;

/**
 * ǿ�ƴ����ļ���Ŀ¼�������Ŀ¼�����ڣ��Զ�����
 * 
 * @author Joycery & Sww
 * 
 */
public class FileCreater {

	/**
	 * ǿ�ƴ����ļ��������Ŀ¼�����ڣ��Զ�����
	 * 
	 * @param file
	 *            Ҫ�������ļ�
	 * @return �Ƿ񴴽��ɹ�
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
