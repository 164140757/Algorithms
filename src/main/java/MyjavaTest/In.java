package MyjavaTest;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class In{
  private Scanner scanner;//declare
  // assume Unicode UTF-8 encoding
  private static final String CHARSET_NAME = "UTF-8";
  //assume language = English, country = US
  private static final Locale LOCALE = Locale.US;
  //Initializes an input stream from standard input
  public In(){
    scanner =new Scanner(new BufferedInputStream(System.in),CHARSET_NAME);
    scanner.useLocale(LOCALE);
  }
  In(String name){
    if(name == null)throw new IllegalArgumentException("argument is null");
    try{
      //local file System
      File file = new File(name);
      if(file.exists()){
        //warp with BufferedInputStream 
        FileInputStream fis = new FileInputStream(file);
        scanner = new Scanner(new BufferedInputStream(fis),CHARSET_NAME);
        scanner.useLocale(LOCALE);
        return;
      }
      else throw new IllegalArgumentException("File does not exist");
    }
    catch(IOException ioe){
      throw new IllegalArgumentException("Could not open "+name,ioe);
    }
  }
  public int readInt(){
    try{
      return scanner.nextInt();
    }
    catch(InputMismatchException e){
      String token = scanner.next();
      throw new InputMismatchException("attempt to read an 'int' value from the input stream,"+"but the next token is \""+token+"\"");
    }
    catch(NoSuchElementException e){
      throw new NoSuchElementException("attempts to read an 'int' value from the input stream,"+"but no more tokens are available");
    }
  }
  
  public double readDouble() {
    try {
      return scanner.nextDouble();
    } catch (InputMismatchException e) {
      String token = scanner.next();
      throw new InputMismatchException(
          "attempt to read an 'int' value from the input stream," + "but the next token is \"" + token + "\"");
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException(
          "attempts to read an 'int' value from the input stream," + "but no more tokens are available");
    }
  }

}