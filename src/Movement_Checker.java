public class Movement_Checker {
  Board board;
  
  private Movement_Checker setBoard (Board board) {
    this.board = board;
    return this;
  }
  
  public Movement_Checker (Board board) {
    this.board = board;
  }
  
  private boolean intersectionChecker (Board.Location[] locations) {
    for (Board.Location location : locations) {
      Board.Piece piece = board.getPiece(location);
      if (!(piece == Board.Piece.whiteNONE || piece == Board.Piece.blackNONE)) return false;
    }
    return true;
  }
  
}
