package CPUDesign; 

public class TestLongWord extends LongWord
{
    public static void runTests()
    {
        LongWord a = new LongWord();
        a.setBit(7);
        a.setBit(8);
        a.setBit(19);
        a.setBit(20);
        a.setBit(29);
        a.setBit(30);
        System.out.println("LongWord A: " + a.toString());  
        System.out.println("LongWord A as an int: " + a.getSigned());
        System.out.println("LongWord A as a long: " + a.getUnsigned());
        LongWord b = new LongWord();
        b.setBit(5);
        b.setBit(15);
        b.toggleBit(20);
        b.toggleBit(21);
        b.toggleBit(25);
        System.out.println("LongWord B: " + b.toString());
        System.out.println("LongWord B as an int: " + b.getSigned());
        System.out.println("LongWord B as a long: " + b.getUnsigned());
        System.out.println("Let's left shift this LongWord by 5 bits: " + b.shiftLeftLogical(5));
        System.out.println("Let's now right shift this LongWord by 5 bits: " + b.shiftRightLogical(5));
        System.out.println("Let's now arithmetically right shift this LongWord by 5 bits: " + b.shiftRightArithmetic(5));
        System.out.println("Let's and LongWord A and B: " + a.and(b).toString());
        System.out.println("Let's or LongWord A and B: " + a.or(b).toString());
        System.out.println("Let's xor LongWord A and B: " + a.xor(b).toString());
        System.out.println(a.copy(b).toString());
        System.out.println(a.toString());
    }
    
    public static void main(String[] args)
     {
         runTests();
     }
}