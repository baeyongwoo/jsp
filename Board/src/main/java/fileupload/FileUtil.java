package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {
	
	public static MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize) {
		try {
			return new MultipartRequest(req, saveDirectory,maxPostSize, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}//파일 업로드 
	
	public static void download(HttpServletRequest req, HttpServletResponse resp, String directory, String sfileName, String ofileName) {
		String sDirector = req.getServletContext().getRealPath(directory);
		try {
			File file = new File(sDirector, sfileName);
			InputStream iStream = new FileInputStream(file);
			
			String client = req.getHeader("User-Agent");
			
			if(client.indexOf("WOW64") == -1) {
				ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
				
			}else {
				ofileName = new String(ofileName.getBytes("KCS5601"), "ISO-8859-1");
			}
			
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition","attachment; filename=\"" + ofileName + "\"");
			resp.setHeader("Content-Length", "" + file.length());
			
			OutputStream oStream = resp.getOutputStream();
			
			byte b[] = new byte[(int)file.length()];
			int readbuffer = 0;
			while((readbuffer =  iStream.read(b)) > 0) {
				oStream.write(b,0,readbuffer);
			}
			
			iStream.close();
			oStream.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("예외가 발생했습니다.");
			e.printStackTrace();
		}
	}//file download end
	
	 public static void deleteFile(HttpServletRequest req, String directory, String filename) {
	        String sDirectory = req.getServletContext().getRealPath(directory);
	        File file = new File(sDirectory + File.separator + filename);
	        if (file.exists()) {
	            file.delete();
	        }
	    }
	 //file delete end
	
	
	
}
