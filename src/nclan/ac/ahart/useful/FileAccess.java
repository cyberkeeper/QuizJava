package nclan.ac.ahart.useful;

import java.io.*;
import java.util.ArrayList;

/**
 * This class contains some generic methods that could be used in several programs without being edited.
 *
 * @author ahart
 */
public class FileAccess {
    /**
     * Reads in the data from the supplied filename. Uses the getResourceAsStream so that the code works when loading
     * from a JAR or from a normal. An arraylist is created which contains 1 entry for each row found in the file.
     * Exceptions are thrown back to the calling method to deal with.
     *
     * @param filename read in the contents of this file
     * @return Arraylist containing every row that was in the source file
     * @throws Exception Could be of type FileNotFoundException or IOException
     */
    static public ArrayList<String> readData(String filename) throws Exception {
        //for debug, see where Java is looking for files.
        //String path = System.getProperty("user.dir");
        //System.out.println("Current folder: " + path);

        ArrayList rows = new ArrayList();

        try {
            Class FA = FileAccess.class;
            InputStream inStream = FA.getResourceAsStream(filename);
            BufferedReader myBuffer = new BufferedReader(new InputStreamReader(inStream));
            String line;

            //keep reading lines until the line read is null, every line is added as an element to the arraylist
            while ((line = myBuffer.readLine()) != null) {
                rows.add(line);
            }
            myBuffer.close();
        } catch (FileNotFoundException fnfe) {
            throw new Exception("Reading exception: " + fnfe.getMessage());
        } catch (IOException ioe) {
            throw new Exception("Reading exception: " + ioe.getMessage());
        }
        return rows;
    }

    /**
     * Write string data to file.
     *
     * @param filename file name of file where data will be written to
     * @param data     Data to be written to file, data will be written to file as supplied*
     * @throws Exception throws IOException
     */
    static public void writeData(String filename, String data) throws Exception {
        if (filename == null || filename.length() == 0) {
            throw new Exception("No filename supplied");
        } else if (data == null || filename.length() == 0) {
            throw new Exception("No data was supplied");
        } else {
            //got here so we have a filename and some data
            try {
                //set the append parameter in FileWriter to False to make it overwrite any existing file contents.
                FileWriter myWriter = new FileWriter(filename, false);
                BufferedWriter myBuffer = new BufferedWriter(myWriter);
                myBuffer.write(data);
                myBuffer.close();
            } catch (IOException ioe) {
                throw new Exception("Writing exception: " + ioe.getMessage());
            }
        }
    }
}
