package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: Tod
 * @Description:
 * @Date: Created in 2019/11/27 17:43
 * @Version: 1.0
 */
public class JDBCUtil {
    private static DataSource ds;

    public static DataSource getDataSource() {
        return ds;
    }

    static {
        try {
            // 加载配置
            Properties properties = new Properties();
            // 类加载器获取到src路径
            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("utils/druid.properties");
            properties.load(is);
            // 获取连接池对象
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = JDBCUtil.getConnection();
        System.out.println(conn);

    }

    /***
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /***
     * 释放连接
     * @param stmt
     * @param conn
     */
    public static void close(Statement stmt,Connection conn){
        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                // 归还连接
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * 释放连接
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                // 归还连接
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
