package helper;

import java.io.File;
import java.util.Date;

public class BasetestHelper
{
    //pre setup and post setup
    public static String Timestamp()
    {
        Date now=new Date();
        String timestamp=now.toString().replace(":","-");
        return timestamp;
    }

    public static void createfolder(String path)
    {
        File file=new File(path);
        if(!file.exists())
        {
            file.mkdir();
        }
        else
        {
            System.out.println("folder is already created");
        }
    }
}