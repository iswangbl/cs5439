package net.jndi;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldif.LDIFException;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LDAPSeriServer {

    private static final String LDAP_BASE = "dc=nanquanyuhao,dc=net";

    public static void main(String[] args) {
        int port = 7389;

        try {
            InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig(LDAP_BASE);
            config.setListenerConfigs(new InMemoryListenerConfig(
                    "lister",
                    InetAddress.getByName("0.0.0.0"),
                    port,
                    ServerSocketFactory.getDefault(),
                    SocketFactory.getDefault(),
                    (SSLSocketFactory) SSLSocketFactory.getDefault()
            ));
            config.setSchema(null);
            config.setEnforceAttributeSyntaxCompliance(false);
            config.setEnforceSingleStructuralObjectClass(false);

            InMemoryDirectoryServer ds = new InMemoryDirectoryServer(config);
            ds.add("dn: " + "dc=nanquanyuhao,dc=net", "objectClass: top", "objectClass: domain");
            ds.add("dn: " + "ou=employees,dc=nanquanyuhao,dc=net", "objectClass: organizationlUnit", "objectClass: top");
            ds.add("dn: " + "uid=wuya,ou=employees,dc=nanquanyuhao,dc=net", "objectClass: ExportObject");

            System.out.println("Listening in 0.0.0.0:" + port);
            ds.startListening();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (LDIFException e) {
            e.printStackTrace();
        } catch (LDAPException e) {
            e.printStackTrace();
        }

    }
}
