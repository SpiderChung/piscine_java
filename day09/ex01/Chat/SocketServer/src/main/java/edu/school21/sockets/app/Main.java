package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        Args arguments = new Args();
        try {
            JCommander.newBuilder()
                    .addObject(arguments)
                    .build()
                    .parse(args);
        } catch (ParameterException e) {
            System.err.println("Specify the server port using --port=");
            System.exit(-1);
        }

        try {
            ApplicationContext context = new AnnotationConfigApplicationContext("edu.school21.sockets");
            Server server = context.getBean(Server.class);
            server.start(arguments.port);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Parameters(separators = "=")
    static class Args {
        @Parameter(
                names = "--port",
                required = true
        )
        private int port;
    }
}


