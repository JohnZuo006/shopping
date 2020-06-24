package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import common.ServerResponse;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/Upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------111");
		String originHeader=request.getHeader("Origin");
		System.out.println(originHeader);
		/* 允许跨域的主机地址 */
		response.setHeader("access-control-allow-origin", "http://127.0.0.1:8848");
		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		/* 重新预检验跨域的缓存时间 (s) */
		response.setHeader("Access-Control-Max-Age", "3600");
		/* 允许跨域的请求头 */
		response.setHeader("Access-Control-Allow-Headers", "*");
		/* 是否携带cookie */
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		String path="D:/www/";
		String fn=null;
        
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload sfu=new ServletFileUpload(factory);
        sfu.setHeaderEncoding("UTF-8");  //处理中文问题
        sfu.setSizeMax(1024*1024*3);   //限制文件大小
        
        try {
            List<FileItem> fileItems= sfu.parseRequest(request);  //解码请求 得到所有表单元素
            for (FileItem fi : fileItems) {
                //有可能是 文件，也可能是普通文字 
                if (fi.isFormField()) { //这个选项是 文字 
                    System.out.println("表单值为："+fi.getString());
                }else{
                    // 是文件
                	//获取图片后缀名
                    String format=fi.getName().substring(fi.getName().indexOf("."), fi.getName().length());
                    //图片命名
                    fn=UUID.randomUUID().toString().replaceAll("-", "")+format;
                    System.out.println("文件名是："+fn);  //文件名 
                    // fn 是可能是这样的 c:\abc\de\tt\fish.jpg
                    fi.write(new File(path,fn));
                    
                }                
            }    
            ServerResponse<String> sr = new ServerResponse<>();
            String url="http://localhost:8080/Uploadpic/"+fn;
            sr.setStatus(0);
            sr.setData(url);
    		Gson gson = new Gson();
    		String json = gson.toJson(sr);

    		try {
    			PrintWriter pw = response.getWriter();
    			pw.print(json);
    			pw.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }	}

}
