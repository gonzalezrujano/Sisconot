package com.corex.sisconot;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class dbf {
    public static void LeerDatos() {
        try {

            // create a DBFReader object
            InputStream inputStream  = new FileInputStream("data/DATOS.DBF"); // take dbf file as program argument
            DBFReader reader = new DBFReader(inputStream);

            // get the field count if you want for some reasons like the following
            //
            int numberOfFields = reader.getFieldCount();

            System.out.println("Nro de Campos: " + numberOfFields);

            // use this count to fetch all field information
            // if required
            //



            for( int i=0; i<numberOfFields; i++) {

                DBFField field = reader.getField( i);

                // do something with it if you want
                // refer the JavaDoc API reference for more details
                //
                System.out.println( field.getName());
            }



            // Now, lets us start reading the rows
            //
            Object []rowObjects;

            while( (rowObjects = reader.nextRecord()) != null) {

                for( int i=0; i<rowObjects.length; i++) {
                    System.out.println( rowObjects[i]);
                    break;
                }
            }

            // By now, we have itereated through all of the rows

            inputStream.close();
        }
        catch( DBFException e) {

            System.out.println( e.getMessage());
        }
        catch( IOException e) {

            System.out.println( e.getMessage());
        }
    }
}
