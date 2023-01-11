package CPUDesign;
import java.util.BitSet;

public class TestALU
{
    public static void main(String [] args)
    {
        runTests();
    }
    
    public static void runTests()
    {
        ALU test = new ALU();
        LongWord a = new LongWord();
        LongWord b = new LongWord();
        LongWord c = new LongWord();
        LongWord d = new LongWord();
        
        for(int j = 1; j < 32; j += 4)
        {
            a.setBit(j);
            for(int k = 0; k < 32; k += 6)
            {
                b.setBit(k);  
            }
        }
        
        System.out.println(a.toString());
        System.out.println(b.toString());
        
        ////////////////////////
        System.out.println("\r\n ALU Code 0: AND \r\n" + a.toString() + " \r\n" + b.toString());
        c = test.operate(0, a, b);
        test.operate(0, a, b);
        System.out.println("Result: " + c.toString() + "\r\n" + test.toString());
        ////////////////////////
        System.out.println("\r\n ALU Code 1: OR \r\n" + a.toString() + " \r\n" + b.toString());
        c = test.operate(1, a, b);
        test.operate(1, a, b);
        System.out.println("Result: " + c.toString() + "\r\n" + test.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 2: XOR \r\n" + a.toString() + " \r\n" + b.toString());
        c = test.operate(2, a, b);
        test.operate(2, a, b);
        System.out.println("Result: " + c.toString() + "\r\n" + test.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 3: ADD \r\n" + a.toString() + " \r\n" + b.toString());
        c = test.operate(3, a, b);
        test.operate(3, a, b);
        System.out.println("Result: " + c.toString() + "\r\n" + test.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 4: SUB \r\n" + a.toString() + " \r\n" + b.toString());
        c = test.operate(4, a, b);
        test.operate(4, a, b);
        System.out.println("Result: " + c.toString() + "\r\n" + test.toString());
        //////////////////////
        d.setBit(29);
        System.out.println("\r\n ALU Code 5: SLL \r\n" + a.toString() + " \r\n" + d.toString());
        c = test.operate(5, a, d);
        test.operate(5, a, d);
        System.out.println("Result: " + c.toString() + "\r\n" + test.toString());
        //////////////////////
        System.out.println("\r\n ALU Code 6: SRL \r\n" + a.toString() + " \r\n" + d.toString());
        c = test.operate(6, a, d);
        test.operate(6, a, d);
        System.out.println("Result: " + c.toString() + "\r\n" + test.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 7: SRA \r\n" + a.toString() + " \r\n" + d.toString());
        test.operate(7, a, d);
        System.out.println("Result: " + c.toString() + "\r\n" + test.toString());
        
        /*********************************************************************************/
        
        ALU test_2 = new ALU();
        LongWord e = new LongWord();
        LongWord f = new LongWord();
        LongWord g = new LongWord();
        LongWord h = new LongWord();
        
        for(int j = 0; j < 32; j += 2)
        {
            e.setBit(j);
            for(int k = 0; k < 32; k += 8)
            {
                f.setBit(k);  
            }
        }
        System.out.println(e.toString());
        System.out.println(f.toString());
        
        ////////////////////////
        System.out.println("\r\n ALU Code 0: AND \r\n" + e.toString() + " \r\n" + f.toString());
        g = test.operate(0, e, f);
        test_2.operate(0, e, f);
        System.out.println("Result: " + g.toString() + "\r\n" + test_2.toString());
        ////////////////////////
        System.out.println("\r\n ALU Code 1: OR \r\n" + e.toString() + " \r\n" + f.toString());
        g = test.operate(1, e, f);
        test_2.operate(1, e, f);
        System.out.println("Result: " + g.toString() + "\r\n" + test_2.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 2: XOR \r\n" + e.toString() + " \r\n" + f.toString());
        g = test.operate(2, e, f);
        test_2.operate(2, e, f);
        System.out.println("Result: " + g.toString() + "\r\n" + test_2.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 3: ADD \r\n" + e.toString() + " \r\n" + f.toString());
        g = test.operate(3, e, f);
        test_2.operate(3, e, f);
        System.out.println("Result: " + g.toString() + "\r\n" + test_2.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 4: SUB \r\n" + e.toString() + " \r\n" + f.toString());
        g = test.operate(4, e, f);
        test_2.operate(4, e, f);
        System.out.println("Result: " + g.toString() + "\r\n" + test_2.toString());
        //////////////////////
        h.setBit(28);
        System.out.println("\r\n ALU Code 5: SLL \r\n" + e.toString() + " \r\n" + h.toString());
        g = test.operate(5, e, h);
        test_2.operate(5, e, h);
        System.out.println("Result: " + g.toString() + "\r\n" + test_2.toString());
        //////////////////////
        System.out.println("\r\n ALU Code 6: SRL \r\n" + e.toString() + " \r\n" + h.toString());
        g = test.operate(6, e, h);
        test_2.operate(6, e, h);
        System.out.println("Result: " + g.toString() + "\r\n" + test_2.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 7: SRA \r\n" + e.toString() + " \r\n" + h.toString());
        g = test.operate(7, e, h);
        test_2.operate(7, e, h);
        System.out.println("Result: " + g.toString() + "\r\n" + test_2.toString());
        
        /*********************************************************************************/
        
        ALU test_3 = new ALU();
        LongWord i = new LongWord();
        LongWord j = new LongWord();
        LongWord k = new LongWord();
        LongWord l = new LongWord();
        
        for(int m = 0; m < 32; m += 3)
        {
            i.setBit(m);
            for(int n = 0; n < 32; n += 12)
            {
                j.setBit(n);  
            }
        }
        
        System.out.println(i.toString());
        System.out.println(j.toString());
        ////////////////////////
        System.out.println("\r\n ALU Code 0: AND \r\n" + i.toString() + " \r\n" + j.toString());
        k = test.operate(0, i, j);
        test_3.operate(0, i, j);
        System.out.println("Result: " + k.toString() + "\r\n" + test_3.toString());
        ////////////////////////
        System.out.println("\r\n ALU Code 1: OR \r\n" + i.toString() + " \r\n" + j.toString());
        k = test.operate(1, i, j);
        test_3.operate(1, i, j);
        System.out.println("Result: " + k.toString() + "\r\n" + test_3.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 2: XOR \r\n" + i.toString() + " \r\n" + j.toString());
        k = test.operate(2, i, j);
        test_3.operate(2, i, j);
        System.out.println("Result: " + k.toString() + "\r\n" + test_3.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 3: ADD \r\n" + i.toString() + " \r\n" + j.toString());
        k = test.operate(3, i, j);
        test_3.operate(3, i, j);
        System.out.println("Result: " + k.toString() + "\r\n" + test_3.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 4: SUB \r\n" + i.toString() + " \r\n" + j.toString());
        k = test.operate(4, i, j);
        test_3.operate(4, i, j);
        System.out.println("Result: " + k.toString() + "\r\n" + test_3.toString());
        //////////////////////
        l.setBit(28);
        System.out.println("\r\n ALU Code 5: SLL \r\n" + i.toString() + " \r\n" + l.toString());
        k = test.operate(5, i, l);
        test_3.operate(5, i, l);
        System.out.println("Result: " + k.toString() + "\r\n" + test_3.toString());
        //////////////////////
        System.out.println("\r\n ALU Code 6: SRL \r\n" + i.toString() + " \r\n" + l.toString());
        k = test.operate(6, i, l);
        test_3.operate(6, i, l);
        System.out.println("\r\n Result: " + k.toString() + "\r\n" + test_3.toString());
        ///////////////////////
        System.out.println("\r\n ALU Code 7: SRA \r\n" + i.toString() + " \r\n" + l.toString());
        k = test.operate(7, i, l);
        test_3.operate(7, i, l);
        System.out.println("Result: " + k.toString() + "\r\n" + test_3.toString());
    }
}