package CPUDesign;

/**
 * The memory class is a byte-array abstration of the main memory.
 * It simulates read-write functionalities.
 *
 * @author Brianna Adams
 */
public class Memory
{
    public int memory_size = 256;
    public byte[] memory;
    
    /*
     * Constructor method to set all bits in the byte-array to zero.
     */
    public Memory()
    {
        memory = new byte[memory_size];
        for(int i = 0; i < memory_size;i++)
        {
            memory[i] = 0;
        }
    }
    
    /*
     * Accessor method for accessing up to a maximum of 4 bytes of 
     * data starting from a given memory address.  
     * The return value of the accessor is a LongWord that can be stored in a 
     * CPU register.
     */
    public LongWord read(LongWord address, int numBytes)
    {
        LongWord read = new LongWord();
        int index = address.getSigned();
        
        //Error handling for bit parameters that exceed 4 bytes
        //or dip below 1
        if(numBytes > 4 || numBytes <= 0)
        {
            System.out.println("Invalid number of bytes");
        }
        //Error handling for indexing that may exceed memory size
        //of 256 bits.
        else if(index > 255 || index < 0)
        {
            System.out.println("Invalid memory index");
        }
        for(int j = 0; j < numBytes; j++)
        {
            read.set(memory[index + j]); 
            read.shiftLeftLogical(8);
            read.set(memory[(int)address.getUnsigned() + j]);
        }
        return read;
    }
        
    
    /*
     * Mutator method for writing to memory up to a maximum of 4 bytes
     * starting from a given memory address.
     */
    public void write(LongWord address, LongWord word, int numBytes)
    {
        LongWord write = new LongWord();
        //int index = address.getSigned();
        
        if(numBytes > 4 || numBytes <= 0)
        {
            System.out.println("Invalid number of bytes");
        }
        else if(address.getSigned() > 255 || address.getSigned() < 0)
        {
            System.out.println("Invalid memory index");
        }
        for(int j = numBytes-1; j >= 0; j--)
        {
            memory[address.getSigned() + j] = (byte)word.shiftRightLogical((numBytes-j-1) * 8).getUnsigned();
        }
    }
}