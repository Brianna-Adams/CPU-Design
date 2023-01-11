package CPUDesign;


public class MemoryTest 
{
    public static void main(String [] args)
    {
        runTest();
    }
    
    public static void runTest()
    {
        Memory memory = new Memory();
        LongWord address = new LongWord();
        LongWord word = new LongWord();
        LongWord output = new LongWord();
            
        address.toggleBit(28);
        word.toggleBit(13);
        memory.write(address, word, 2);
        System.out.println("-> LongWord: " + word + " -> Address: " 
            + address.getSigned());
        output = memory.read(address, 2);
        System.out.println("-> Address: " + address.getSigned() + "-> LongWord: " 
            + output);
            
        LongWord address_2 = new LongWord();
        LongWord word_2 = new LongWord();
        LongWord output_2 = new LongWord();
        
        address_2.setBit(30); 
        word_2.toggleBit(8);
        memory.write(address_2, word_2, 3);
        System.out.println("-> LongWord: " + word_2 + " -> Address: " 
            + address_2.getSigned());
        output_2 = memory.read(address, 3);
        System.out.println("-> Address: " + address_2.getSigned() + "-> LongWord: " 
            + output_2);

        LongWord address_3 = new LongWord();
        LongWord word_3 = new LongWord();
        LongWord output_3 = new LongWord();
        
        address_3.toggleBit(27); 
        word_3.toggleBit(10);
        memory.write(address_3, word_3, 1);
        System.out.println("-> LongWord: " + word_3 + " -> Address: " 
            + address_3.getSigned());
        output_3 = memory.read(address, 1);
        System.out.println("-> Address: " + address_3.getSigned() + "-> LongWord: " 
            + output_3);
        
    }

}