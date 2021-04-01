package compiler_design;

/**
 *
 * @author Anas Ali banat 20172121
 * @author Mohamed zeyad abu hassanien 20174100
 * @author Jameel Emad Matar 20171660
 * 
**/

public class Token{
    
    String name;
    int type;
    int line;
    
    public Token(String name, int type, int line){
        this.name = name;
        this.type = type;
        this.line = line;
    }
    
    public String getName(){
        return name;
    }
    
    public int getType(){
        return type;
    }
    
    public int getline(){
        return line;
    }
}

/**
 *
 * @author Anas Ali banat 20172121
 * @author Mohamed zeyad abu hassanien 20174100
 * @author Jameel Emad Matar 20171660
 * 
**/