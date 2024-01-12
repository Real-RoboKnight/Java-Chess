import java.util.Objects;
import java.util.Scanner;

public class Main {
  public static void main (String[] args) {
    
//    System.out.println(new Board.Location(1, 1));
//
//    Board a = new Board();
//    System.out.println(a);
//
//    a.setPiece(new Board.Location(1, 1), Board.Piece.blackQueen);
//    System.out.println(a);
//
//    a.setPiece(new Board.Location(2, "e"), null);
//    a.setPiece(new Board.Location(4, "e"), Board.Piece.whitePawn);
//    System.out.println(a);
//
//    System.out.println(a.toString(true));
//    System.out.println("\n\n\n");
    
    System.out.println("Welcome to Chess!!");
    Board chessBoard = new Board();
    Piece_Log pieceLog = new Piece_Log();
    Scanner scanner = new Scanner(System.in);
    boolean whiteToMove = true;
    while (true) {
      try {
        System.out.println(String_Concatenater.concatenater(chessBoard.toString(!whiteToMove), pieceLog.toString()));
        Board.Location startLocation;
        Board.Location endLocation;
        Board.Piece piece;
        
//        Start Location
        System.out.println((whiteToMove ? "White" : "Black") + " to move. Which piece would you like to move? (enter a letter and number)");
        String input = scanner.next();
        
//        Input validation checking
        if (input.length() != 2) throw new IllegalArgumentException("Input needs two, and only two characters");
        if (Character.isDigit(input.charAt(0)) == Character.isDigit(input.charAt(1)))
          throw new IllegalArgumentException("One, and only one of the characters need to be a digit");
        if (Character.isAlphabetic(input.charAt(0)) == Character.isAlphabetic(input.charAt(1)))
          throw new IllegalArgumentException("One, and only one of the characters need to be an alphabetic character");
        
        if (Character.isDigit(input.charAt(0)))
          startLocation = new Board.Location(Character.getNumericValue(input.charAt(0)), input.charAt(1));
        else startLocation = new Board.Location(Character.getNumericValue(input.charAt(1)), input.charAt(0));
        
//        Very owner of piece
        Board.Color color = chessBoard.getColor(startLocation);
        if (color == Board.Color.none) {
          throw new IllegalAccessException(startLocation + " is a blank spot, pick a piece you own.");
        } else if (whiteToMove && color == Board.Color.black) {
          throw new IllegalAccessException(startLocation + " is a black spot, pick a piece you own.");
        } else if (!whiteToMove && color == Board.Color.white) {
          throw new IllegalAccessException(startLocation + " is a white spot, pick a piece you own.");
        }
        piece = chessBoard.getPiece(startLocation);
        
//        End Location
        System.out.println("Where would you like to move? (enter a letter and number)");
        input = scanner.next();

//        Input validation checking
        if (input.length() != 2) throw new IllegalArgumentException("Input needs two, and only two characters");
        if (Character.isDigit(input.charAt(0)) == Character.isDigit(input.charAt(1)))
          throw new IllegalArgumentException("One, and only one of the characters need to be a digit");
        if (Character.isAlphabetic(input.charAt(0)) == Character.isAlphabetic(input.charAt(1)))
          throw new IllegalArgumentException("One, and only one of the characters need to be an alphabetic character");
        
        if (Character.isDigit(input.charAt(0)))
          endLocation = new Board.Location(Character.getNumericValue(input.charAt(0)), input.charAt(1));
        else endLocation = new Board.Location(Character.getNumericValue(input.charAt(1)), input.charAt(0));
        
        
        if (whiteToMove && chessBoard.getColor(endLocation) == Board.Color.white || !whiteToMove && chessBoard.getColor(endLocation) == Board.Color.black)
          throw new IllegalAccessException(endLocation + " is a piece you own (" + chessBoard.getPiece(endLocation) + " - " + chessBoard.getPiece(endLocation).name() + ')');
        
        
//        System.out.println(piece.toString() + startLocation + " -> " + endLocation);
        pieceLog.addLog(piece, startLocation, endLocation);
//        System.out.println(pieceLog.toString());
        chessBoard.setPiece(endLocation, piece);
        chessBoard.setPiece(startLocation, null);
        
        whiteToMove = !whiteToMove;
        
        
      } catch (IllegalArgumentException e) {
        System.out.println("Incorrect notation :: " + e.getMessage());
        continue;
      } catch (IllegalAccessException e) {
        System.out.println("Incorrect selection :: " + e.getMessage());
        continue;
      }
    }
  }
}