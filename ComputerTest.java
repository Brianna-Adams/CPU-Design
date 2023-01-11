package CPUDesign;


/**
 * Class to test our computer object.
 *
 * @Brianna Adams
 * 
 */
public class ComputerTest
{
    public static void runTest()
    {
        Computer briannaadamsmacbook = new Computer();
        String[] preload_test = new String [5];
        preload_test[0] = "1110 1110 1110 0100";
        preload_test[1] = "1010 0010 1100 1110";
        preload_test[2] = "0010 0010 0011 1010";
        preload_test[3] = "1100 0100 0000 1000";
        preload_test[4] = "0010 0100 0100 0011";
        briannaadamsmacbook.preload(preload_test);
        briannaadamsmacbook.run();
        
        Computer briannaadamsPC = new Computer();
        String[] preload_test_2 = new String [5];
        preload_test_2[0] = "0000 0001 0000 0001";
        preload_test_2[1] = "1000 0000 1000 0000";
        preload_test_2[2] = "1101 1100 0000 1000";
        preload_test_2[3] = "0000 1000 0100 0010";
        preload_test_2[4] = "1111 0111 0101 0100";
        briannaadamsPC.preload(preload_test_2);
        briannaadamsPC.run();
        
        Computer briannaadamsIPAD = new Computer();
        String[] preload_test_3 = new String[5];
        preload_test_3[0] = "0010 0100 1001 0010";
        preload_test_3[1] = "0000 0010 0100 0000";
        preload_test_3[2] = "1000 0000 0100 0001";
        preload_test_3[3] = "0000 1000 1000 0010";
        preload_test_3[4] = "1000 1000 0010 0010";
        briannaadamsIPAD.preload(preload_test_3);
        briannaadamsIPAD.run();
        
    }
    public static void main(String[] args)
    {
        runTest();
    }
}
