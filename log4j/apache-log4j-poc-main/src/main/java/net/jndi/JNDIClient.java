package net.jndi;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;
import java.util.Map;

public class JNDIClient {

    public static void main(String[] args) {

        Map<String, Object> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:7389/dc=nanquanyuhao,dc=net");

        DirContext ctx = null;
        try {
            ctx = new InitialDirContext((Hashtable<?, ?>) env);
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            searchControls.setCountLimit(10);
            NamingEnumeration<SearchResult> namingEnumeration =
                    ctx.search("", "(uid=*)", new Object[]{}, searchControls);

            // 通过名称查找远程对象，假设远程服务器已经将一个远程对象与名称绑定
            ctx.lookup("ldap://localhost:7389/ou=employees,dc=nanquanyuhao,dc=net");
            while (namingEnumeration.hasMore()) {
                SearchResult sr = namingEnumeration.next();
                System.out.println("DN: " + sr.getName());
                System.out.println(sr.getAttributes().get("uid"));
            }
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
