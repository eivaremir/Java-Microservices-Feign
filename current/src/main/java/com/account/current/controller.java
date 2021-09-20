package com.account.current;


import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.deposit.DepositsMysqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@RestController
public class controller {

    @GetMapping(path="/current",produces= MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> getCountries2() {

        List<Map> accounts = new ArrayList<Map>();

        System.out.println("connecting to bd from current accounts microservice");
        DepositsMysqlConnection depositbd = new DepositsMysqlConnection();
        ResultSet rs = null;
        try{
            Statement stmt = depositbd.conn.createStatement();
            rs = stmt.executeQuery("CALL `deposits`.`GET_CURRENT_ACCOUNTS_LIST`();");

            while(rs.next()){
                JSONObject json = new JSONObject();
                json.put("accountNumber",rs.getString("accountNumber"));
                json.put("name",rs.getString("name"));
                json.put("balance",rs.getString("balance"));
                json.put("id",rs.getString("id"));
                json.put("isMoneyMarket",rs.getString("isMoneyMarket"));
                //System.out.println(rs.getString("accountNumber"));
                //System.out.println(json);
                accounts.add(json.toMap());
            }
            System.out.println(accounts);
        }catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


        return new ResponseEntity<Object>(accounts, HttpStatus.OK);
    }



}
