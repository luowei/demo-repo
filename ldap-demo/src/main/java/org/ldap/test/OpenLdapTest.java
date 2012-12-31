package org.ldap.test;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-8
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public class OpenLdapTest {



        public static void main(String[] args) {
            String root = "dc=maxcrc,dc=com"; // root
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost/" + root);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "cn=Manager,dc=maxcrc,dc=com");
            env.put(Context.SECURITY_CREDENTIALS, "secret");
            DirContext ctx = null;
            try {
                ctx = new InitialDirContext(env);
                System.out.println("认证成功");
            } catch (javax.naming.AuthenticationException e) {
                e.printStackTrace();
                System.out.println("认证失败");
            } catch (Exception e) {
                System.out.println("认证出错：");
                e.printStackTrace();
            }

            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException e) {
                    // ignore
                }
            }
        }

}
