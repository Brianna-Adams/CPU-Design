package CPUDesign;
import java.util.BitSet;

class ALU
{  
    //Declare and initialize boolean flags
    boolean ZF = false;
    boolean NF = false;
    boolean CF = false;
    boolean OF = false;
    boolean cin = false;
    
    //ALU constructor 
    public ALU()
    {
        this.ZF = false;
        this.NF = false;
        this.CF = false;
        this.OF = false;
    }
    
    //Accessor method for the zero flag (ZF)
    public boolean getZF()
    {
        return this.ZF;   
    }
    
    //Accessor method for the negative flag(NF)
    public boolean getNF()
    {
        return this.NF;
    }
    
    //Accessor method for the carry-out flag(CF)
    public boolean getCF()
    {
       return this.CF; 
    }
    
    //Accessor method for the overflow flag(OF)
    public boolean getOF()
    {
        return this.OF;
    }
    
    //Mutator to perform ALU operations based on control codes
    public LongWord operate(int code, LongWord OP1, LongWord OP2)
    {
        switch(code)
        {
            case 0: return and(OP1, OP2);
            case 1: return or(OP1, OP2);
            case 2: return xor(OP1, OP2);
            case 3: return add(OP1, OP2);
            case 4: return sub(OP1, OP2);
            case 5: return sll(OP1, OP2);
            case 6: return srl(OP1, OP2);
            case 7: return sra(OP1, OP2);
            default: return null;
        }
    }
    
    //Implementation of ripply carry adder for the execution of both addition and subtraction
    //of long words
    private LongWord rippleCarryAdd(LongWord a, LongWord b, boolean cin)
    {
      CF = false;
      NF = false;
      OF = false;
      //Declare and initialize longword sum/difference
      LongWord c = new LongWord();
      //If cin is true, perform subtraction operations
            //Else if cin is false, perform addition operations
      if(cin == true)
      {
          for(int j = 0; j < 32; j++)
          {
              //Negate the subtrahend and add to the minuhend
              b.toggleBit(j);
              //if A = 0 and B = 0 --> carryOut = 0 --> diff = 0
              //if A = 1 and B = 1 --> carryOut = 0 --> diff = 0
              //if A = 0 and B = 1 --> carryOut = 1 --> diff = 1
              //if A = 1 and B = 0 --> carryOut = 0 --> diff = 1
          }
          c = rippleCarryAdd(a, b, false);
      }
      else if(cin == false)
      {
          for(int j = 31; j >= 0; j--)
          {
              //if A = 0 and B = 0 --> carryOut = 0 --> sum = 0
              //if A = 1 and B = 1 --> carryOut = 1 --> sum = 0
              //if A = 0 and B = 1 --> carryOut = 0 --> sum = 1
              //if A = 1 and B = 0 --> carryOut = 0 --> sum = 1
              if(a.getBit(j) == false && b.getBit(j) == false && CF == false)
              {
                  CF = false;
                  c.clearBit(j);
              }
              else if(a.getBit(j) == false && b.getBit(j) == false && CF == true)
              {
                  CF = false;
                  c.setBit(j);
              }
              else if(a.getBit(j) == true && b.getBit(j) == true && CF == false)
              {
                  CF = true;
                  c.clearBit(j);
              }
              else if(a.getBit(j) == true && b.getBit(j) == true && CF == true)
              {
                  CF = true;
                  c.setBit(j);
              }
              else if(a.getBit(j) == true && b.getBit(j) == false && CF == false)
              {
                  CF = false;
                  c.setBit(j);
              }
              else if(a.getBit(j) == true && b.getBit(j) == false && CF == true)
              {
                  CF = true;
                  c.clearBit(j);
              }
              else if(a.getBit(j) == false && b.getBit(j) == true && CF == false)
              {
                  CF = false;
                  c.setBit(j);
              }
              else if(a.getBit(j) == false && b.getBit(j) == true && CF == true)
              {
                  CF = true;
                  c.clearBit(j);
              }
              else if(j == 0)
              {
                  if((a.getBit(0) == true) && (b.getBit(0) == true) && CF == true)
                  {
                      c.setBit(0);
                      CF = true;
                      NF = true;
                      OF = false;
                  }
                  else if((a.getBit(0) == true) && (b.getBit(0) == true) && CF == false)
                  {
                      c.clearBit(0);
                      CF = true;
                      NF = false;
                      OF = true;
                  }
                  else if((a.getBit(0) == true) && (b.getBit(0) == false) && CF == true)
                  {
                      c.clearBit(0);
                      CF = true;
                      NF = false;
                      OF = false;
                  }
                  else if((a.getBit(0) == true) && (b.getBit(0) == false) && CF == false)
                  {
                      c.setBit(0);
                      CF = false;
                      NF = true;
                      OF = false;
                  }
                  else if((a.getBit(0) == false) && (b.getBit(0) == true) && CF == true)
                  {
                      c.clearBit(0);
                      CF = true;
                      NF = false;
                      OF = false;
                  }
                  else if((a.getBit(0) == false) && (b.getBit(0) == true) && CF == false)
                  {
                      c.setBit(0);
                      CF = false;
                      NF = true;
                      OF = false;
                  }
                  else if((a.getBit(0) == false) && (b.getBit(0) == false) && CF == true)
                  {
                      c.setBit(0);
                      CF = false;
                      NF = true;
                      OF = true;
                  }
                  else if((a.getBit(0) == false) && (b.getBit(0) == false) && CF == false)
                  {
                      c.clearBit(0);
                      CF = false;
                      NF = false;
                      OF = false;
                  }
                  
              }
          }
      }
      return c;
    }
        
    //Method to perform an AND operation on two long words,returning a new long word
    private LongWord and(LongWord OP1, LongWord OP2)
    {
        LongWord OP3 = new LongWord();
        OP3 = OP1.and(OP2);
        ZF = OP3.isZero();
        NF = OP3.getBit(0);
        CF = false;
        OF = false;
        return OP3;
    }
    
    //Method to perform an OR operation on two long words, returning a new long word
    private LongWord or(LongWord OP1, LongWord OP2)
    {
        LongWord OP3 = new LongWord();
        OP3 = OP1.or(OP2);
        ZF = OP3.isZero();
        NF = OP3.getBit(0);
        CF = false;
        OF = false;
        return OP3;
    }
    
    //Method to perform an XOR operation on two long words, returning a new long word
    private LongWord xor(LongWord OP1, LongWord OP2)
    {
        LongWord OP3 = new LongWord();
        OP3 = OP1.xor(OP2);
        ZF = OP3.isZero();
        NF = OP3.getBit(0);
        CF = false;
        OF = false;
        return OP3;
    }
        
    //Method to perform addition on two long words, returning the sum as a new long word
    private LongWord add(LongWord OP1, LongWord OP2)
    {
        LongWord OP3 = new LongWord();
        OP3 = rippleCarryAdd(OP1, OP2,false);
        ZF = OP3.isZero();
        CF = getCF();
        OF = getOF();
        NF = getNF();
        return OP3;
    }
    
    //Method to perform subtraction on two long words, returning the difference as a new long word
    private LongWord sub(LongWord OP1, LongWord OP2)
    {
        LongWord OP3 = new LongWord();
        OP3 = rippleCarryAdd(OP1, OP2,true);
        ZF = OP3.isZero();
        CF = getCF();
        OF = getOF();
        NF = getNF();
        return OP3;
    }
    
    //Method to perform a shift left logical on a long word
    private LongWord sll(LongWord OP1, LongWord amount)
    {
        LongWord OP3 = new LongWord();
        OP3 = OP3.copy(OP1);
        OP3 = OP3.shiftLeftLogical(amount.getSigned());
        NF = OP3.getBit(0);
        ZF = OP3.isZero();
        CF = false;
        if(amount.getSigned() == 1 && (OP1.getBit(30) != OP3.getBit(30)))
            OF = true;
        else
            OF = false;
        return OP3;
    }
    
    //Method to perform a shift right logical on a long word
    private LongWord srl(LongWord OP1, LongWord amount)
    {
        LongWord OP3 = new LongWord();
        OP3 = OP3.copy(OP1);
        OP3 = OP3.shiftRightLogical(amount.getSigned());
        ZF = OP3.isZero();
        NF = false;
        CF = false;
        OF = false;
        return OP3;
    }
    
    //Method to perform a shift right arithmetic on a long word
    private LongWord sra(LongWord OP1, LongWord amount)
    {
        LongWord OP3 = new LongWord();
        OP3 = OP3.copy(OP1);
        OP3 = OP3.shiftRightArithmetic(amount.getSigned());
        NF = OP3.getBit(0);
        ZF = OP3.isZero();
        CF = false;
        OF = false;
        return OP3;
    }
    
    //@Override toString method to publish the status of flags
    public String toString()
    {
        return "The zero flag: " + getZF() + "\r\n The negative flag: " + getNF() 
            + "\r\n The carry-out flag: " + getCF() + "\r\n The overflow flag: " + getOF();
    }
}