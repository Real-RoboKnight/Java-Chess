public class Board {
  public record Location(int row, int column) {
    public Location (int row, int column) {
      if (row < 1 || row > 8)
        throw new IllegalArgumentException("row:" + row + " is not within the allowed range of 1 - 8");
      if (column < 1 || column > 8)
        throw new IllegalArgumentException("column:" + (char)('@' + column) + " is not within the allowed range of A - H");
      this.row = row - 1;
      this.column = column - 1;
    }
    
    public Location (int row, char column) {
      this(row, String.valueOf(column));
    }
    
    public Location (int row, String column) {
      this(row, column.toLowerCase().charAt(0) - 'a' + 1);
    }
    
    @Override
    public String toString () {
      return ((char)((char)column + 'A')) + String.valueOf(row + 1);
    }
  }
  
  public enum Color {white, black, none}
  
  public enum Piece {
/*    whitePawn("♙"), whiteBishop("♗"), whiteKnight("♘"), //
    whiteRook("♖"), whiteQueen("♕"), whiteKing("♔"), //
    blackPawn("♟"), blackBishop("♝"), blackKnight("♞"), //
    blackRook("♜"), blackQueen("♛"), blackKing("♚"), //
    whiteNONE("⬜"), blackNONE("⬛"); */
    
    whitePawn("♟"), whiteBishop("♝"), whiteKnight("♞"), //
    whiteRook("♜"), whiteQueen("♛"), whiteKing("♚"), //
    blackPawn("♙"), blackBishop("♗"), blackKnight("♘"), //
    blackRook("♖"), blackQueen("♕"), blackKing("♔"), //
    whiteNONE("⬜"), blackNONE("⬛");
    private final String print;
    
    public String getName () {
      StringBuilder stringBuilder = new StringBuilder();
      for (String str : name().split("(?=\\p{Upper})")) {
        stringBuilder.append(str.substring(0, 1).toUpperCase()).append(str.substring(1)).append(' ');
      }
      return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
    @Override
    public String toString () {return print;}
    
    Piece (String print) {
      this.print = print;
    }
  }
  
  private Piece[][] board = { //Board Initialization
      {Piece.blackRook, Piece.blackKnight, Piece.blackBishop, Piece.blackQueen, Piece.blackKing, Piece.blackBishop, Piece.blackKnight, Piece.blackRook}, //row 1
      {Piece.blackPawn, Piece.blackPawn, Piece.blackPawn, Piece.blackPawn, Piece.blackPawn, Piece.blackPawn, Piece.blackPawn, Piece.blackPawn}, //row 2
      {Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE}, //row 3
      {Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE}, //row 4
      {Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE}, //row 5
      {Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE, Piece.whiteNONE, Piece.blackNONE}, //row 6
      {Piece.whitePawn, Piece.whitePawn, Piece.whitePawn, Piece.whitePawn, Piece.whitePawn, Piece.whitePawn, Piece.whitePawn, Piece.whitePawn}, //row 7
      {Piece.whiteRook, Piece.whiteKnight, Piece.whiteBishop, Piece.whiteQueen, Piece.whiteKing, Piece.whiteBishop, Piece.whiteKnight, Piece.whiteRook}  //row 8
  };
  
  @Override
  public String toString () {
    return this.toString(false);
  }
  
  public String toString (boolean flip) {
    StringBuilder out = new StringBuilder(" \tA\tB\tC\tD\tE\tF\tG\tH\t \n");
    if (!flip) for (int i = 1; i <= 8; i++) {
      out.append(9-i).append('\t');
      for (Piece piece : board[i - 1]) {
        out.append(piece).append('\t');
      }
      out.append(9-i).append('\n');
    }
    else for (int i = 8; i >= 1; i--) {
      out.append(9-i).append('\t');
      for (Piece piece : board[i - 1]) {
        out.append(piece).append('\t');
      }
      out.append(9-i).append('\n');
    }
    out.append(" \tA\tB\tC\tD\tE\tF\tG\tH\t \n");
    return out.toString();
    
    
  }
  
  private Piece getBackground (Location location) {
    return (location.row % 2 != location.column % 2) ? Piece.blackNONE : Piece.whiteNONE;
  }
  
  public void setPiece (Location location, Piece piece) {
    if (piece == null) piece = getBackground(location);
    board[7- location.row][location.column] = piece;
  }
  
  public Piece getPiece (Location location) {
    return board[7- location.row][location.column];
  }
  
  public Color getColor (Location location) {
    return switch (getPiece(location)) {
      case blackNONE, whiteNONE -> Color.none;
      case whiteBishop, whiteKing, whiteKnight, whitePawn, whiteQueen, whiteRook -> Color.white;
      case blackBishop, blackKing, blackKnight, blackPawn, blackQueen, blackRook -> Color.black;
    };
  }
  
  public Board (Piece[][] board) {
    this.board = board;
  }
  
  public Board () {
  }
}
