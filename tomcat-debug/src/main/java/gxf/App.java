package gxf;

/**
 * Hello world!
 *
 */
import gxf.servlert.HelloWorldServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;

public class App
{
    public static void main( String[] args ){
       startTomcat(8088, "/wanli");
    }

    private static void startTomcat(int port, String contentPath) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        //上下文
        StandardContext standardContext = new StandardContext();
        standardContext.setPath(contentPath);
        standardContext.addLifecycleListener(new FixContextListener());
        tomcat.getHost().addChild(standardContext);

        //注册servlet
        final String helloServletName = "HelloWorldServlet";
        tomcat.addServlet(contentPath, helloServletName, new HelloWorldServlet());
        standardContext.addServletMappingDecoded("/HelloWorldServlet", helloServletName);
        try {
            tomcat.start();
            System.out.println("#### start on " + port);
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer().await();

    }

}
