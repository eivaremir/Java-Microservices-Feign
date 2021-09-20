package com.account.saving;

import org.deposit.DepositsMysqlConnection;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




@RestController
public class controller {

    @GetMapping(path="/saving",produces= MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> getCountries2() {

        List<Map> accounts = new ArrayList<Map>();

        System.out.println("connecting to bd from saving accounts microservice");
        DepositsMysqlConnection depositbd = new DepositsMysqlConnection();
        ResultSet rs = null;
        try{
            Statement stmt = depositbd.conn.createStatement();
            rs = stmt.executeQuery("CALL `deposits`.`GET_SAVING_ACCOUNTS_LIST`();");

            while(rs.next()){
                JSONObject json = new JSONObject();
                json.put("accountNumber",rs.getString("accountNumber"));
                json.put("name",rs.getString("name"));
                json.put("balance",rs.getString("balance"));
                json.put("id",rs.getString("id"));
                json.put("isChristmasAccount",rs.getString("isChristmasAccount"));
                //System.out.println(rs.getString("accountNumber"));
                //System.out.println(json);
                accounts.add(json.toMap());
            }
            //System.out.println(accounts);
        }catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


        return new ResponseEntity<Object>(accounts, HttpStatus.OK);
    }



}
