/* Nuke2.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read a string from the keyboard
 *   and prints the same string, with its second character removed.
 */

class Nuke2 {


  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    inputLine = keyboard.readLine();

    String outputLine = inputLine.replace(inputLine.substring(1,2),""); /*用空字符替换第二个字符*/
    
    System.out.print(outputLine);/*输出时_不_加换行符*/
    //System.out.print(outputLine + System.getProperty("line.separator"));/*输出时加换行符*/
    System.out.flush();        /* Make sure the line is printed immediately. */

  

  }
}
