package compiler_design;

/**
 *
 * @author Anas Ali banat 20172121
 * @author Mohamed zeyad abu hassanien 20174100
 * @author Jameel Emad Matar 20171660
 * 
**/

public class Second{
    
    int i=-1;
    Token gui;
    
    public void nextToken(){
        i++;
    }

    public void Type(){
        if(match("array",1)){
            nextToken();
            match("[",1);
            match("",3);
            match("..",1);
            match("",3);
            match("]",1);
            match("of",1);
            standrd_Type();
        }
    }

    public void standrd_Type(){
        match("integer",1);
        match("real",1);
    }
    
    /**
    *
    * @author Anas Ali banat 20172121
    * @author Mohamed zeyad abu hassanien 20174100
    * @author Jameel Emad Matar 20171660
    * 
   **/
    
    public void prog(){
        match("program",0);
        identifierlist();
        match(";",0);
        declarations();
        subprogram_declarations();
        compound_statment();
        match(".",0);
    }

    public void identifierlist(){
        match("",2);
        if(match(",",1))
        {
            nextToken();
            identifierlist();
        }
    }

    public void declarations(){
        if(match("var",1)){
            nextToken();
            identifierlist();
            match(":",1);
            Type();
            match(";",1);
            declarations();
        }
    }
    
    /**
    *
    * @author Anas Ali banat 20172121
    * @author Mohamed zeyad abu hassanien 20174100
    * @author Jameel Emad Matar 20171660
    * 
   **/
    
    public void subprogram_declarations(){
        if(subprogram_declaration()){
            subprogram_declarations();}
    }

    public boolean subprogram_declaration(){
        if(subprogram_head()){
            declarations();
            compound_statment();
            return true;
        }
        return false;
    }

    public boolean  subprogram_head(){
        if(match("function",1)){
            nextToken();
            match("",2);
            arguments();
            match(":",2);
            standrd_Type();
            match(";",1);
            return true;
        }
        else if(match("procedure",1)){
            nextToken();
            match("",2);
            arguments();
            match(";",1);
            return true;
        }
        return false;
    }
    
    /**
    *
    * @author Anas Ali banat 20172121
    * @author Mohamed zeyad abu hassanien 20174100
    * @author Jameel Emad Matar 20171660
    * 
   **/

    public void arguments(){
        if(match("(",1)){
            nextToken();
            prameter_list();
            match(")",1);
        }
    }

    public void prameter_list(){
        identifierlist();
        match(":",1);
        Type();
        match(";",1);
        if(match(";",1)){
            nextToken();
            prameter_list();
        }
    }

    public boolean  compound_statment(){
        boolean b=true;
        if(match("begin",1)){
            nextToken();
        }else{
            b=false;
        }
        optional_statment();
        match("end",1);
        return b;
    }

    public void optional_statment(){
        statment_List();
    }
    
    /**
    *
    * @author Anas Ali banat 20172121
    * @author Mohamed zeyad abu hassanien 20174100
    * @author Jameel Emad Matar 20171660
     * @return 
    * 
   **/

    public boolean statment_List(){
        if(statment()){
            if(match(";",1)){
                nextToken();
                statment_List();
            }
            return true;
        }
        return false;
    }

    public boolean statment(){
        if(variable()){
            match(":=",1);
            expression();
            return true;
        }
        else if(compound_statment()||procedure_statment())
            return true;
        else if(match("if",1)){
            nextToken();
            expression();
            match("then",1);
            statment();
            match("else",1);
            statment();
            return true;
        }
        else if(match("while",1)){
            nextToken();
            expression();
            match("do",1);
            statment();
            return true;
        }
        return false;
    }
    
    /**
    *
    * @author Anas Ali banat 20172121
    * @author Mohamed zeyad abu hassanien 20174100
    * @author Jameel Emad Matar 20171660
     * @return 
    * 
   **/

    public boolean variable(){
        if(match("",2)){
            nextToken();
            if(match("[",1)){
                nextToken();
                expression();
                match("[",1);
            }
            return true;
        }
        return false;
    }

    public boolean procedure_statment(){
        if(match("",2)){
            nextToken();
            if(match("(",1)){
                nextToken();
                expression_List();
                match(")",1);
            }
            return true;
        }
        return false;
    }

    public void expression(){
        simple_expression();
        if(match(">",1)||match("<",1)||match("<=",1)||match(">=",1)||match("<>",1)||match("=",1)){
            nextToken();
            simple_expression();
        }
    }

    public void expression_List(){
        expression();
        if(match(",",1)){
            nextToken();
            expression_List();
        }
    }
    
    /**
    *
    * @author Anas Ali banat 20172121
    * @author Mohamed zeyad abu hassanien 20174100
    * @author Jameel Emad Matar 20171660
    * 
   **/
    
    public void simple_expression(){
        sign();
        term();
        if(match("+",1)||match("-",1)||match("or",1)){
            nextToken();
            simple_expression();
        }
    }
    
    public boolean term(){
        if(factor()){
            if(match("*",1)||match("/",1)||match("div",1)|match("mod",1)||match("and",1)){
                nextToken();
                term();
            }
            return true;
        }
        return false;
    }

    public boolean factor(){
        if(match("id",1)){
            nextToken();
            if(match("(",1)){
                nextToken();
                expression_List();
                match(")",1);
                return true;
            }    
        }else if(match("",3)){
            nextToken();
            return true;
        }else if(match("(",1)){
            nextToken();
            expression_List();
            match(")",1);
            return true;
        }else if(match("not",1)){
            nextToken();
            factor();
            return true;
        }
        return false;
    }
    
    /**
     *
     * @author Anas Ali banat 20172121
     * @author Mohamed zeyad abu hassanien 20174100
     * @author Jameel Emad Matar 20171660
     * 
    **/

    public void sign(){
        if(match("+",1)||match("-",1)){
            nextToken();
        }
    }

    public boolean match(String s,int t){
        if((t==0&&gui.getType()==t&&(s == null ? gui.getName() == null : s.equals(gui.getName())))||(t==gui.getType()&&t!=0)){
            nextToken();
            return true;
        }
        else{
            System.out.println("This Error is Expected");
            nextToken();
            return false;
        }
    }
}

/**
 *
 * @author Anas Ali banat 20172121
 * @author Mohamed zeyad abu hassanien 20174100
 * @author Jameel Emad Matar 20171660
 * 
**/