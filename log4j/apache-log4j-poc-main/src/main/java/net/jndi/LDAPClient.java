package net.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LDAPClient {

    public static void main(String[] args) {

        Context ctx = null;
        Object obj = null;
        try {
            ctx = new InitialContext();
            obj = ctx.lookup("ldap://localhost:7389/uid=wuya,ou=employees,dc=nanquanyuhao,dc=net");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
