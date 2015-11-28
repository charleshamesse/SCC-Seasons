import java.io.*;

public class ReadCVS {
    public static void main(String[] args) {

    	writeSeason("Winter", 0);
    	writeSeason("Spring", 1);
    	writeSeason("Summer", 2);
    	writeSeason("Autumn", 3);
    

    }
    
    public static void writeSeason(String sname, int column) {
PrintWriter writer;
    	
    	try {
    		String name = sname;
			writer = initWriter(name);
	    	writer.print(getPart("header.html"));
	    	
	    	writer.print(run(column));
	    	
	    	writer.print(getPart("body.html"));
	    	
	    	writer.print(name);
	    	
	    	writer.print(getPart("footer.html"));
	    	
	    	writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static PrintWriter initWriter(String name) throws FileNotFoundException, UnsupportedEncodingException {
  		PrintWriter writer = new PrintWriter("dist/De-rien-" + name + ".html", "UTF-8");
		return writer;
    }
    public static String run(int column) {

      String csvFile = "Merci-Charlie.csv";
      String data = "['City',   'Temperature'],";
      BufferedReader br = null;
      String line = "";
      String cvsSplitBy = ";";

      try {

        br = new BufferedReader(new FileReader(csvFile));
        int k = 0;
        while ((line = br.readLine()) != null) {
        	if(k > 0) {
          // use comma as separator
          String[] values = line.split(cvsSplitBy);

          System.out.println("Country:" + values[0]
          + " , city: " + values[1] + "");
          
          data += "['" + values[1] + "',  " + values[2+column].replaceAll(",",".") + "], \n";
        	}
          k++;

        }

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (br != null) {
          try {
            br.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      return data;
    }
    
    private static String getPart(String path) {
    	String part = "";
    	BufferedReader br = null;
        String line = "";

        try {

          br = new BufferedReader(new FileReader("assets/" + path));
          while ((line = br.readLine()) != null) {
        	 	part += line + "\n";
          }

        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          if (br != null) {
            try {
              br.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
        
        return part;
    	
    }
 
}
