package com.undertow;

import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import static io.undertow.servlet.Servlets.servlet;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.jsp.JspServletBuilder;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import java.io.File;

import javax.servlet.ServletException;

public class ServletServer {
	
	public static final String MYAPP = "/myapp";

    public static void main(final String[] args) {
        try {

            DeploymentInfo servletBuilder = deployment()
                    .setClassLoader(ServletServer.class.getClassLoader())
                    .setContextPath(MYAPP)
                    .setDeploymentName("test.war")
                    .setResourceManager(new FileResourceManager(new File("src/main/webapp"), 1024)) 
                    .addServlets(
                            servlet("memory", MemoryLeak.class)
                                    .addMapping("/memoryLeak"),
                            servlet("userinfo", UserInfo.class)
                                    .addMapping("/userinfo"),
                            servlet("MyServlet", MessageServlet.class)
                                    .addInitParam("message", "Hello INTI")
                                    .addMapping("/myservlet")
                            ,
                            JspServletBuilder.createServlet("Default Jsp Servlet", "*.jsp")

                    		);

            DeploymentManager manager = defaultContainer().addDeployment(servletBuilder);
            manager.deploy();

            HttpHandler servletHandler = manager.start();
            PathHandler path = Handlers.path(Handlers.redirect(MYAPP))
                    .addPrefixPath(MYAPP, servletHandler);
            Undertow server = Undertow.builder()
                    .addHttpListener(8080, "localhost")
                    .setHandler(path)
                    .build();
            server.start();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

}
