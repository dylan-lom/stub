/*
 * Regular expressions
 * ====================
 * mods: (?<mods>(?:(?:static|final|public|private)\s*)+)
 * type: (?<type>.*)
 * name: (?<name>\s\w+)
 * args: \((?<args>[^)]*)\)
 */
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Stub {

  public static class Signature {
    public static final String signatureRe = 
      "\\s*(?<mods>(?:(?:abstract|final|public|private|protected|static)\\s*)+)(?<type>.*)(?<name>\\s\\w+)\\((?<args>[^)]*)\\);?";

    private String signature;
    private Matcher matcher;

    public Signature(String signature) {
      this.signature = signature;

      Pattern p = Pattern.compile(signatureRe);
      this.matcher = p.matcher(signature);
    }

    public String toString() {
      return this.signature;
    }

    public boolean matches() {
      return this.matcher.matches();
    }

    public String getModifiers() {
      if (!this.matches()) return null;
      return this.matcher.group("mods").trim();
    }

    public String getType() {
      if (!this.matches()) return null;
      return this.matcher.group("type").trim();
    }

    public String getTypeDefault() {
      if (!this.matches()) return null;
      String type = this.getType();
      
      if (type.equals("byte") || type.equals("short") || type.equals("int")) return "0";
      if (type.equals("long")) return "0L";
      if (type.equals("float")) return "0.0f";
      if (type.equals("double")) return "0.0d";
      if (type.equals("char")) return "'\u0000'";
      if (type.equals("boolean")) return "false";
      return "null";
    }

    public String getName() {
      if (!this.matches()) return null;
      return this.matcher.group("name").trim();
    }

    public String getArgs() {
      if (!this.matches()) return null;
      return this.matcher.group("args").trim();
    }
  } // END SIGNATURE CLASS

  public static void printStub(String signature) {
    Signature sig = new Signature(signature);
    if (!sig.matches()) {
      System.err.printf("ERROR: Unable to parse sig: %s\n", sig.toString());
      return;
    }

    System.out.printf("%s %s %s(%s) {\n", sig.getModifiers(), sig.getType(), sig.getName(), sig.getArgs());
    System.out.printf("  assert false : \"TODO: Implement %s\";\n", sig.getName());
    System.out.printf("  return %s\n", sig.getTypeDefault());
    System.out.printf("}\n");
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    while (sc.hasNext()) {
      printStub(sc.next());
    }
  }
}

