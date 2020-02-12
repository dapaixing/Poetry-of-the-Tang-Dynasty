//package lab;
//
//import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//
//import javax.sql.DataSource;
//import java.sql.*;
//
//public class 插入诗词Demo {
//    public static void main(String[] args) throws SQLException {
//        String 朝代 = "唐代";
//        String 作者 = "白居易";
//        String 标题 = "问刘十九";
//        String 正文 = "绿蚁新醅酒，红泥小火炉。晚来天欲雪，能饮一杯无？";
//
////        Class.forName("com.mysql.jdbc.Driver");
////        String url = "jdbc:mysql://127.0.0.1/tangshi?useSSL=false&characterEncoding=utf8";
////        Connection connection = DriverManager.getConnection(url, "root", "root");
////        System.out.println(connection);
//
////        DataSource dataSource1 = new MysqlDataSource();
//        //带有连接池，好处参考线程池
//        MysqlConnectionPoolDataSource dataSource2 = new MysqlConnectionPoolDataSource();
//        dataSource2.setServerName("127.0.0.1");
//        dataSource2.setPort(3306);
//        dataSource2.setUser("root");
//        dataSource2.setPassword("root");
//        dataSource2.setDatabaseName("tangshi");
//        dataSource2.setUseSSL(false);
//        dataSource2.setCharacterEncoding("UTF8");
//
//
//        try(Connection connection = dataSource2.getConnection()){
//            String sql = "INSERT INTO tangshi(sha256,dynasty,title,author,content,words)VALUES(?,?,?,?,?,?)";
//
//            try (PreparedStatement statement = connection.prepareStatement(sql);){
//                statement.setString(1,"sha256");
//                statement.setString(2,朝代);
//                statement.setString(3,标题);
//                statement.setString(4,作者);
//                statement.setString(5,正文);
//                statement.setString(6,"");
//
//                statement.executeUpdate();
//            }
//        }
//    }
//}
