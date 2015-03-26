package guestbook;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class qq_s extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		stat.sh=sh;

		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/xml; charset=UTF8");
		resp.setCharacterEncoding("UTF8");

		String s3 = req.getParameter("p3");
		String s4 = req.getParameter("p4");
		String s = "";
		if (s3 == null && s4 == null) {
			
			//if(stat.sr.trim().equals(""))
				//stat.sr=stat.rff("83.owl");
				
			stat.get_owl83(stat.sr,sh);
			s = stat.sowl;
			
			
			byte[] b = s.getBytes("UTF8");
			out.write(b);
			return;
		}

		if (s3.equals("load")) {
			s = stat.rfu_utf(sh + "/" + URLEncoder.encode(s4, "UTF-8"));
			if (s.indexOf("Ъ") > -1 && s.length() > s.indexOf("Ъ"))
				s = s.substring(s.indexOf("Ъ") + 1);
			stat.sr = s;
			stat.get_owl83(s,sh);
			//stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s;
			stat.page(req, resp, s);
			
			return;
		}

		if (s3.equals("add")) {
			s = stat.rfu_utf(sh + "/" + URLEncoder.encode(s4, "UTF-8"));
			s = s.substring(s.indexOf("Ъ") + 1);
			//stat.sr = (stat.sr+" "+s).replace("\r\n", "");
			stq.add_sr(s, sh);
			
			//stat.stop = stat.stop + "<br> <b><i> - </i></b> Добавить мир: <i>" + s + "</i>";
			stat.page(req, resp, stat.sr);
	
			return;
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}

	private static final long serialVersionUID = 1L;
}