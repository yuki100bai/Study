package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {

    /* 定数 */
    /** ドライバーのクラス名 */
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    /** ・JDMC接続先情報 */
    private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/jdbc_db";
    /** ・ユーザー名 */
    private static final String USER = "postgres";
    /** ・パスワード */
    private static final String PASS = "postgres";

    public static void main(String[] args) {

        /* 事前に使用する変数を宣言 */
        /* INSERTと同様、使用しないためResultSetは不要です。 */
        Connection connection = null;
        Statement statement = null;

        /* SQL実行 */
        try {
            Class.forName(POSTGRES_DRIVER);
            connection = DriverManager.getConnection(JDBC_CONNECTION,USER,PASS);
            statement = connection.createStatement();

            String SQL = "update shohin_tb set SHOHIN_NAME = '商品21' where SHOHIN_ID = '021' ";
            statement.executeUpdate(SQL);

        /* エラーが起きた際の処理 */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        /* SQL実行後に実行する処理 */
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
          
        }
    }
}
