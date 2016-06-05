import java.io.*;
import java.util.Random;

class abcgen {
  public static void main(String[] args) {
    try {
      PrintWriter output = new PrintWriter("temp.txt");



          Random r = new Random();

          String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
          for (int i = 0; i < 1000000; i++) {
              output.print(alphabet.charAt(r.nextInt(alphabet.length())) + " ");
          }
          output.close();
    } catch(IOException e) {

    }
  }
}
