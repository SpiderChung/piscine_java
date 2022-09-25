package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.client.Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Args arguments = new Args();
        try {
            JCommander.newBuilder()
                    .addObject(arguments)
                    .build()
                    .parse(args);
        } catch (ParameterException e) {
            System.err.println("Specify the server port using --server-port=");
            System.exit(-1);
        }

        try {
            int port = Integer.parseInt(args[0].substring(14));
            Client client = new Client("127.0.0.1", port);
            client.start();
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @Parameters(separators = "=")
    static class Args {
        @Parameter(
                names = "--server-port",
                required = true
        )
        private int port;
    }
}
