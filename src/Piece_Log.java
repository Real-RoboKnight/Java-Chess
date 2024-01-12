import java.util.ArrayList;
import java.util.stream.Collectors;

public class Piece_Log {
  private final ArrayList<Log> logs = new ArrayList<>();
  
  public record Log(Board.Piece piece, Board.Location startLocation, Board.Location endLocation) {
    @Override
    public String toString () {
      return piece.getName() + ' ' + piece.toString() + " | " + startLocation.toString() + " -> " + endLocation.toString();
    }
  }
    public  void addLog (Log log) {logs.add(log);}
    
    public void addLog (Board.Piece piece, Board.Location startLocation, Board.Location endLocation) {logs.add(new Log(piece, startLocation, endLocation));}
  
  @Override
  public String toString () {
    return logs.stream().map(Log::toString).collect(Collectors.joining("\n"));
  }
}
