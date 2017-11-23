import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Setup
 */
public class fileHandling {
    ArrayList<String> addrFiles;
    
    /*
    * This constructor initialises an arraylst and stores files that end in .addr
    * which are in the current directory
    */
    
    public fileHandling()
    {
        addrFiles = new ArrayList <String> ();
        
        File dir = new File(".");
        
        for(File file:dir.listFiles())
        {
            String filename = file.getName();
            
            if(filename.endsWith(".addr"))
            {
                addrFiles.add(filename);
            }
        }
    }
    
    /*
    * This method finds the file in the arraylist
    */
    
    public String findFile(String position)
    {
        int index = Integer.parseInt(position.substring(1, 2));
        
        String file = addrFiles.get(index);
        
        return file;
    }
    
    /*
    * This method displays the arraylist
    */
    
    public String toString()
    {
        int counter = 0;
        String listOfFiles = "";
        
        for(String files: addrFiles)
        {
            listOfFiles += "["+counter+"] " + files + "\n";
            counter = counter + 1;
        }
        
        return listOfFiles;
    }
    
    /*
    * These two method below write useful outputs to a file and then open the file
    */
    
    public void printCacheOutput1(simulator s, String file) throws FileNotFoundException, IOException
    {
        File output = new File("results.txt");
        
        PrintWriter pw = new PrintWriter(output);
        
        String [] l1 = s.getl1Cache();
        
        String [] addresses1 = s.getL1Addresses();
        
        String [] performance = s.getL1Performance();
        
        int [] count = s.getL1Count();
        
        String heading00 = String.format("%67s",file);
        
        String heading0 = String.format("%67s","l1Cache");
        
        String heading1 = String.format("%-20s","Index");
        String heading2 = String.format("%-40s","Tag");
        String heading3 = String.format("%-30s","Hexadecimal Address @ end");
        String heading4 = String.format("%-25s", "Number of times index stored @");
        
        pw.println(heading00);
        pw.println("\n");
        pw.println(heading0);
        pw.println("\n");
        pw.println(heading1 + heading2 + heading3 + heading4);
        
        /* prints out cache block number(in binary) + tag + last hex address stored 
           + number of times the specific block in cache memory was accessed       */
        
        for(int i = 0; i < l1.length; i++)
        {
            String text1 = String.format("%-20s","[" + Integer.toBinaryString(i) + "] ");
            String text2 = String.format("%-40s",l1[i]);
            String text3 = String.format("%-30s", addresses1[i]);
            String text4 = String.format("%-25s", count[i]);
            pw.println(text1 + text2 + text3 + text4);
        }
        
        pw.println("\n");
        //prints out hits, misses + hit percenatge
        
        for(int i = 0; i < performance.length;i++)
        {
            pw.println(performance[i]);
        }
        
        pw.println(s.getL1CPI());
        pw.println(s.getCycles()+"\n");
        
        pw.flush();
        pw.close();
        
        //Desktop.getDesktop().edit(output);
    }
    
    public void printCacheOutput2(simulator s, String file) throws FileNotFoundException, IOException
    {
        File output = new File("results.txt");
        
        PrintWriter pw = new PrintWriter(output);
        
        String [] l1 = s.getl1Cache();
        String [] l2 = s.getl2Cache();
        
        String [] addresses1 = s.getL1Addresses();
        String [] addresses2 = s.getL2Addresses();
        
        String [] performance1 = s.getL1Performance();
        String [] performance2 = s.getL2Performance();
        
        int [] count1 = s.getL1Count();
        int [] count2 = s.getL2Count();
        
        String heading00 = String.format("%67s",file);
        
        String heading0 = String.format("%67s","l1Cache");
        
        String heading01 = String.format("%67s","l2Cache");
        
        String heading1 = String.format("%-20s","Index");
        String heading2 = String.format("%-40s","Tag");
        String heading3 = String.format("%-30s","Hexadecimal Address @ end");
        String heading4 = String.format("%-25s", "Number of times index stored @");
        
        pw.println(heading00);
        pw.println("\n");
        pw.println(heading0);
        pw.println("\n");
        pw.println(heading1 + heading2 + heading3 + heading4);
        
        /* prints out cache block number(in binary) + tag + last hex address stored 
           + number of times the specific block in cache memory was accessed       */
        
        for(int i = 0; i < l1.length; i++)
        {
            String text1 = String.format("%-20s","[" + Integer.toBinaryString(i) + "] ");
            String text2 = String.format("%-40s", l1[i]);
            String text3 = String.format("%-30s", addresses1[i]);
            String text4 = String.format("%-25s", count1[i]);
            pw.println(text1 + text2 + text3 + text4);
        }
        
        pw.println("\n");
        
        //prints out hits, misses + hit percenatge
        
        for(int i = 0; i < performance1.length;i++)
        {
            pw.println(performance1[i]);
        }
        
        pw.println(s.getL1CPI());
        
        pw.println(heading01);
        pw.println("\n");
        pw.println(heading1 + heading2 + heading3 + heading4);
        
        
        /* prints out cache block number(in binary) + tag + last hex address stored 
           + number of times the specific block in cache memory was accessed       */
        
        for(int i = 0; i < l2.length; i++)
        {
            String text1 = String.format("%-20s","[" + Integer.toBinaryString(i) + "] ");
            String text2 = String.format("%-40s", l2[i]);
            String text3 = String.format("%-30s", addresses2[i]);
            String text4 = String.format("%-25s", count2[i]);
            pw.println(text1 + text2 + text3 + text4);
        }
        
        pw.println("\n");
        
        //prints out hits, misses + hit percenatge
        
        for(int i = 0; i < performance2.length; i++)
        {
            pw.println(performance2[i]);
        }
        
        pw.println(s.getL2CPI());
        
        pw.println(s.getCycles());
        
        pw.println("\n" + s.getPerformanceRatio());
        
        pw.flush();
        pw.close();
        
        //Desktop.getDesktop().edit(output);
    }
}
