/**
 *
 * @author sctmic012
 */

public class cache {
    
    private final int bytesPerBlock;
    private final int blockSize;
    
    private final String [] blocks;
    private final String [] addresses;
    private final int [] counter;
    private final int bytesPerLine = 2;
    
    public cache(int blockSize, int bytesPerBlock)
    {
        this.bytesPerBlock = bytesPerBlock;
        this.blockSize = blockSize;
        
        blocks = new String[blockSize];
        addresses = new String[blockSize];
        counter = new int[blockSize];
        
        for(int i = 0; i < blockSize; i++)
        {
            blocks[i] = "";
            addresses[i] = "";
            counter[i] = 0;
        }
    }
    
    /**
     * This method checks if the tag is in the cache block,
     * if it is the method returns true and ends,
     * if it is not then the method stores the address in cache and returns false
    */
    
    public boolean inCache(String hexAddress)
    {
        // converts hexidecimal address to decimal
        int decimalAddress = Integer.parseInt(hexAddress,16);
        
        int blockNumber = getIndex(decimalAddress);
        String tag = generateTag(decimalAddress);
        
        if(blocks[blockNumber].equals(tag))
        {
            return true;
        } 
        else
        {
            readInTag(tag,blockNumber);
            storeHexAddress(blockNumber,hexAddress);
            return false;
        }
    }
    
    /**
     * This method stores the tag at a particular block   
    */
    
    private void readInTag(String tag, int blockNumber)
    {   
        blocks[blockNumber] = tag;
        
        // increments the amount of times the spcific block has been accessed
        
        counter[blockNumber] += 1;
    }
    
    /**
     * Method just stores the hex address, this is used later to output the last
     * hex address to a file, this is a "quality of " method & does not have
     * a tangible effect on the simulation
    */
    
    private void storeHexAddress(int blockNumber, String hexAddress)
    {
        addresses[blockNumber] = hexAddress;
    }
    
    /**
     * This method gets the index where it expects the tag to be stored
    */
    
    private int getIndex(int decimalAddress)
    {
        int hexAddress = (int) Math.round(decimalAddress / bytesPerBlock);
        int blockNumber = hexAddress % blockSize;
        
        return blockNumber;
    }
    
    /**
     * This method generates the tag associated with the data,
     * the tag is what will be stored in the array
     */
    
    private String generateTag(int decimalAddress)
    {
        String binary = Integer.toBinaryString(decimalAddress);
        
        int binaryLength = binary.length();
        
        if(binaryLength < 16)
        {
            binary = addPadding(binary, binaryLength);
        }
        
        int offset = (int) (Math.log(bytesPerLine) / Math.log(2));
        int index = (int) (Math.log(blockSize / bytesPerLine) / Math.log(2));
        
        int tagEnd = binary.length() - (offset + index); 
        String tag = binary.substring(0, tagEnd);
        
        return tag;
    }
    
    
    /**
     * If the binary number is too small,
     * this method adds padding to the beginning of the number to generate a tag
     */
    
    private String addPadding(String binary, int binaryLength)
    {
        int noOfPadding = 16 - binaryLength;
        String paddedZeros = "";
        
        for(int i = 0; i < noOfPadding; i++)
        {
            paddedZeros = paddedZeros + "0";
        }
        
        return paddedZeros + binary;
    }
    
    /**
    * Methods below used to generate output to write to a file later on
    */
    
    public String[] ToString()
    {
        return blocks;
    }
    
    public String [] Addresses()
    {
        return addresses;
    }
    
    public int [] count()
    {
        return counter;
    }
}
