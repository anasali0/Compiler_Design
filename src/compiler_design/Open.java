package compiler_design;

/**
 *
 * @author Anas Ali banat 20172121
 * @author Mohamed zeyad abu hassanien 20174100
 * @author Jameel Emad Matar 20171660
 * 
**/

import java.io.*;
import java.io.IOException;
import javax.swing.JFileChooser;

class Open{
    ObjectInputStream input;
    public File openFile(){
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        return file;
    }
    
    /**
    *
    * @author Anas Ali banat 20172121
    * @author Mohamed zeyad abu hassanien 20174100
    * @author Jameel Emad Matar 20171660
    * 
   **/
    
    @SuppressWarnings("CallToPrintStackTrace")
    public String getContents(File file){
        StringBuilder contents = new StringBuilder();
        try{
            try(@SuppressWarnings("LocalVariableHidesMemberVariable")
                BufferedReader input = new BufferedReader(new FileReader(file))) {
                String line = null;
                while(( line = input.readLine()) != null){
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            }  
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return contents.toString();
    }
}
/**
 *
 * @author Anas Ali banat 20172121
 * @author Mohamed zeyad abu hassanien 20174100
 * @author Jameel Emad Matar 20171660
 * 
**/
