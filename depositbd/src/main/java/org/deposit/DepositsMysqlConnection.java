package org.deposit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DepositsMysqlConnection {

    public Connection conn = null;

    public DepositsMysqlConnection(){


        try {

            this.conn = DriverManager.getConnection("jdbc:mysql://deposit-account.crltg6hajdp9.us-east-1.rds.amazonaws.com?" +
                    "user=admin&password=12345678");
            System.out.println("Succesfully connected to deposit bd");

        } catch (
                SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


    }


}
