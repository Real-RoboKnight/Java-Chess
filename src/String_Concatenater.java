public class String_Concatenater {
  public static String concatenater (String leftStr, String rightStr) { return concatenater( leftStr.split("\n"), rightStr.split("\n"));}
  public static String concatenater (String[] leftStr, String[] rightStr) {
    StringBuilder out = new StringBuilder();
    int wait = rightStr.length - leftStr.length;
    
    for (int i = 0; i < wait; i++) {
      out.append(" \t \t \t \t \t \t \t \t \t \t\t").append(rightStr[i]).append('\n');
    }
    for (int i = 0; i < leftStr.length; i++) {
      out.append(leftStr[i]).append("\t\t");
      if (i + wait >= 0) out.append(rightStr[i + wait]);
      out.append('\n');
    }
    
    return out.toString();
  }
}
