package jdbcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcUtil {
	private static DruidDataSource dataSourses=new DruidDataSource();
    static {
        try {
            InputStream inputStream=JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");//定义输入流
            Properties properties=new Properties();
            properties.load(inputStream);//读取输入流
            dataSourses.setDriverClassName("com.mysql.jdbc.Driver");
            dataSourses.setUrl(properties.getProperty("url"));
            dataSourses.setUsername(properties.getProperty("username"));
            dataSourses.setPassword(properties.getProperty("password"));
            dataSourses.setMaxActive(20);
            dataSourses.setMinIdle(8);//设置连接池
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Connection getConnection()
    {
        Connection con=null;
        try {
            con= dataSourses.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }
    private static void close(Connection con, PreparedStatement pstmt)
    {
        try {
            if(con!=null)
            {
                con.close();
            }
            if(pstmt!=null)
            {
                pstmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private static void close(Connection con, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if(rs!=null)
            {
                rs.close();
            }
            close(con,pstmt);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static int executeUpdate(String sql,Object... params)
    {
        int result=0;
        Connection con=getConnection();
        PreparedStatement pstmt=null;
        try {
            pstmt=con.prepareStatement(sql);
            for(int i=0;i<params.length;i++)
            {
                pstmt.setObject(i+1,params[i]);
            }
            result=pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally{
            close(con,pstmt);
        }
        return result;
    }
    public static <T> List<T> executeQuery(String sql, Class<T> clz, Object... params)
    {
        List<T> list=new ArrayList<>();
        Connection con=getConnection();
        PreparedStatement pstmt =null;
        ResultSet rs=null;
        try {
            pstmt=con.prepareStatement(sql);
            for(int i=0;i<params.length;i++)
            {
                pstmt.setObject(i+1,params[i]);
            }
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                T t=clz.newInstance();
                Field[]fields=clz.getDeclaredFields();
                for(Field field:fields)
                {
                    field.setAccessible(true);
                    field.set(t,rs.getObject(field.getName()));
                }
                //System.out.println(t);
                list.add(t);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            close(con,pstmt,rs);
        }
        return list;
    }
    public static void main(String[] args) {
    	int result=0;
    	String sql="select count(*) as count from user";
        Connection con=getConnection();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            rs.next();
            result=rs.getInt("count");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally{
            close(con,pstmt);
        }
        System.out.println(result);
	}
}
