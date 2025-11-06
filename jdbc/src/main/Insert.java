package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {

    /* 定数 */
    /** ドライバーのクラス名 */
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    /** ・JDMC接続先情報 */
    private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/jdbc_db";
    /** ・ユーザー名 */
    private static final String USER = "yuki0302";
    /** ・パスワード */
    private static final String PASS = "postgres";

    public static void main(String[] args) {

        /* 使用する変数を宣言 */
        /* ResultSetはデータを取得してきた際に使用するため、SELECT以外では使用しない */
        Connection connection = null;
        Statement statement = null;

        /* SQLの実行 */
        try {
            Class.forName(POSTGRES_DRIVER);
            connection = DriverManager.getConnection(JDBC_CONNECTION,USER,PASS);
            statement = connection.createStatement();

            String SQL = "INSERT INTO SHOHIN_TB( SHOHIN_ID, SHOHIN_NAME, TANKA) VALUES('021','SHOHIN021',2100) ";
            statement.executeUpdate(SQL);

        /* エラーが発生した際に実行する処理 */
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