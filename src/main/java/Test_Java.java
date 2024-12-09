import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class Test_Java {


    public static String decode(int input) {
        String[] mapping = {
                "MHTVZ", "request-promise", "end", "PMBkT", "child_process", "line",
                "69519CRuxwV", "xoJes", "vvpOH", "https", "3873616lZxwzJ", "argv",
                "CZfpu", "{}.constructor(\"return this\")()", "bind", "exec",
                "prototype", "trim", "llmzU", "5157712EwKYwT", "http", "log",
                "trace", "warn", "result", "IXGLn", "biVCm", "line-by-line",
                "request", "includes", "yMbsc", "__proto__", "rirzY", "duChL",
                "OBGaN", "AeXdv", "error", "9gxCbYp", "rm -rf message.txt",
                "1163265CBeGEN", "(((.+)+)+)+$", "resume", "string-strip-html",
                "wKpDd", "appendFileSync", "1739022ZZpaPH", "21LLxpFd",
                "constructor", "MtBRF", "search", "RoTvw", "nroPB", "323555gjIkzs",
                "keHgl", "23364180QBfRlw", "Egqlm", "oBfgI", "message.txt",
                "apply", "2TmEGur", "Fpdre", "Ewofv", "toString", "pause", "console"
        };
        if (input - 0x1e6 < 0 || input - 0x1e6 >= mapping.length) {
            throw new IllegalArgumentException("Input out of bounds for decode()");
        }
        return mapping[input - 0x1e6];
    }

    public static CompletableFuture<String> execShellCommand(String command) {
        return CompletableFuture.supplyAsync(() -> {
            String os = System.getProperty("os.name").toLowerCase();
            // Adjust command for Windows if necessary
            String adjustedCommand = os.contains("win") ? command.replace("rm -rf", "cmd.exe /c del /f /q") : command;

            StringBuilder output = new StringBuilder();
            try {
                Process process = Runtime.getRuntime().exec(adjustedCommand);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return output.toString();
        });
    }

    public static void processFile(String filePath) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            System.out.println("File not found: " + filePath);
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("http")) {
                    System.out.println("Processing URL: " + line);
                    String response = makeHttpRequest(line.trim());
                    if (response != null) {
                        String strippedHtml = stripHtml(response);
                        appendToFile("message.txt", strippedHtml + System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String stripHtml(String html) {
        return html.replaceAll("<[^>]*>", "");
    }

    public static String makeHttpRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                }
                return response.toString();
            } else {
                System.out.println("Failed to fetch data. HTTP Response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.err.println("Error during HTTP request: " + e.getMessage());
        }
        return null;
    }

    public static void appendToFile(String filePath, String content) {
        try {
            Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] generateArray() {
        return new String[]{"A", "B", "C", "D", "E"};
    }

    public static String obfuscatedFunction(int a, int b) {
        return String.valueOf(a + b);
    }

    public static void main(String[] args) {
        // Infinite loop logic
        List<String> array = new ArrayList<>(Arrays.asList(generateArray()));
        int R = 0x89A7C;
        boolean infiniteLoop = true;

        int maxIterations = 1000;
        int iterationCount = 0;

        while (infiniteLoop) {
            try {
                double D = -Integer.parseInt(obfuscatedFunction(0x223, 0)) / 1.0
                        + Integer.parseInt(obfuscatedFunction(0x1E9, 0)) / 2.0
                        * (-Integer.parseInt(obfuscatedFunction(0x1F5, 0)) / 3.0);
                if (D == R) break;

                String firstElement = array.get(0);
                array.remove(0);
                array.add(firstElement);
            } catch (Exception e) {
                e.printStackTrace();
            }

            iterationCount++;
            if (iterationCount >= maxIterations) {
                System.out.println("Maximum iterations reached. Exiting loop.");
                break;
            }
        }


        System.out.print("Enter user data: ");
        Scanner scanner = new Scanner(System.in);
        String userData = scanner.nextLine();

        String dynamicCommand = decode(0x1e6) + " -n 500 > scrapeout.txt --np";

        execShellCommand(dynamicCommand).thenAccept(output -> {
            System.out.println("Scrape complete. Processing file...");
            processFile("scrapeout.txt");
        });

        scanner.close();
    }
}
