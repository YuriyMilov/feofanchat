package guestbook;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;

public class qqr extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		ServletOutputStream out = resp.getOutputStream();
		//resp.setContentType("text/xml; charset=UTF8");
		resp.setContentType("text/plain; charset=UTF8");
		resp.setCharacterEncoding("UTF8");
		//String s=rf3("test.owl");
		
		String s = stat.sowl;
		if(s.length()==0)
			s=rf3("test.owl");
		
		byte[] b=s.getBytes("UTF8");
		out.write(b);	
		out.flush();
		out.close();
	}
	
	String rf3(String s) throws IOException {
		BlobInfoFactory blf = new BlobInfoFactory();
		Iterator<BlobInfo> info = blf.queryBlobInfos();
		BlobInfo bi = null;
		String sn2 = "";
		while (info.hasNext()) {
			bi = info.next();
			sn2 = bi.getFilename();
			if (sn2.equals(s))
				break;			
		}
		s = BlobstoreFS.readToEnd(bi.getBlobKey());
		return s;
	}
}
