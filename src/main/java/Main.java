import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import org.apache.http.HttpException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws HttpException, IOException {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Movie title:");
        String title = scanner.nextLine();
        System.out.println("Enter rating (1-5)");
        int rating = scanner.nextInt();

        GenerateContentResponse description = client.models.generateContent("gemini-2.0-flash-001", title, null);

        System.out.println(description.text());
    }
}
