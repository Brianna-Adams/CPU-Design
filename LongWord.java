package CPUDesign;
import java.util.BitSet;

public class LongWord 
{    
    //Data set//
    BitSet word;
    int sum;
    long longVal;
    
    //Bitset Constructor//
    public LongWord()
    {
        this.word = new BitSet(32);
        //Visit each bit and set each one to false/0
        for(int j = 0; j < word.length(); j++)
        {
            this.word.clear(j);
        }
    }
    
    public LongWord(int value)
    {
        this();
        set(value);
    }
    //Accessors and Mutators// 
    
    //Get bit i as a boolean using get(index)
    public boolean getBit(int j)
    {
        //returns true if i != 0 and false if i = 0 
        return this.word.get(j);
    }
    
    //Set bit i (set to true/1)
    public void setBit(int j)
    {
        //i = ~(i & 0) using set(Index)
        this.word.set(j, true);
    }
    
    public void setBit(int j, boolean flag)
    {
        this.word.set(j, flag);
    }
    
    //Clear bit i (reset to false/0) using clear(index)
    public void clearBit(int i)
    {
        //i = i & 0 
        this.word.set(i, false);
    }
    
    //Toggles(flips) bit i
    public void toggleBit(int i)
    {
         this.word.flip(i);
    }
    
    //Set the value of the bits of this long-word (used for testing only)
    public void set(int value)
    {
        for(int j = 0; j < word.length(); j++)
        {
            word.set(value);
        }
    }
    
    //Copies the values of the bits from another long-word into this one
    public LongWord copy(LongWord other) 
    {
        for(int j = 0; j < word.length(); j++)
        {
            this.setBit(j, other.getBit(j));
        }
        return other;
    }
    
    //Returns the value of this long-word as an int
    public int getSigned()
    {
        LongWord _newLongWord = new LongWord();
        int sum = 0;
        for(int j = 31; j >= 0; j--)
        {
            if(this.getBit(j) == true)
            {
                //sum ^= 1 << (j);
               sum ^= 1 << (32 - j); 
            }
            else 
                sum += 0;
        }   
      return (sum/2);
    }
        
    //Returns the value of this long-word as a long
    public long getUnsigned()
    {
        long longVal = 0L;
        for(int j = 0; j < 32; j++)
        {
            if(this.getBit(j) == true)
            {
                longVal ^= 1L << (64 - j);
            }
            else
                longVal += 0;
        }
        return (longVal/2);
    }

    //Left-shift this long-word by 
    //amount bits (padding with 0's), creates a new long word
    public LongWord shiftLeftLogical(int amount) 
    { 
        LongWord sll = new LongWord();
        //sll.copy(this);
        for(int k = 32; k > amount; k--)
        {   
            sll.setBit(k - amount, this.getBit(k));
        }
        for(int k = 32; k > 32 - amount; k--)
        {
            sll.setBit(0);
        }
        
        return sll;
    }
    
    //Right-shift this long-word by
    //amount bits (padding with 0's), creates a new long word
    public LongWord shiftRightLogical(int amount) 
    {
        LongWord srl = new LongWord();
        //srl.copy(this);
        for(int i = 0; i < 32; i++)
        {
            srl.setBit(i + amount, this.getBit(i));
        }
        for(int i = 0; i < amount; i++)
        {
            srl.clearBit(i);
        }
        return srl;
    }
    
    //Right-shift this long-word
    //by amount bits (sign-extending), creates a new long-word
    public LongWord shiftRightArithmetic(int amount) 
    {
        LongWord sra = new LongWord();
        //sra.copy(this);
        for(int j = 0; j < 32; j++)
        {
            sra.setBit(j + amount, this.getBit(j));
        }
        for(int j = 0; j <= amount; j++)
        {
            int index = (32 - amount + j);
            sra.setBit(j, this.getBit(index));
        }
        
        return sra;
    }
    
    //Negate this LongWord, creating another
    public LongWord not()
    {
        LongWord not = new LongWord();
        for(int j = 0; j < 32; j++)
        {
            not.toggleBit(j);
        }
        return not;
    }
        
    //"And" two LongWords, returning a third
    public LongWord and(LongWord other)
    {
        LongWord and = new LongWord();
        and.copy(other);
        for(int j = 0; j < 32; j++)
        {
           if(this.getBit(j) == true && other.getBit(j) == true)
           {
               and.setBit(j);
           }
           else
               and.clearBit(j);
        }
        return and;
    }
        
    //"Or" two LongWords, returning a third
    public LongWord or(LongWord other)
    {
        LongWord or = new LongWord();
        or.copy(other);
        for(int j = 0; j < 32; j++)
        {
            if(this.getBit(j) == false && other.getBit(j) == false)
            {
                or.clearBit(j);
            }
            else
                or.setBit(j);
        }
        return or;
    }
        
    //"Xor" two LongWords, returning a third
    public LongWord xor(LongWord other)
    {
        LongWord xor = new LongWord();
        xor.copy(other);
        for(int j = 0; j < 32; j++)
        {
           if(this.getBit(j) == other.getBit(j))
           {
               xor.clearBit(j);
           }
           else
               xor.setBit(j);
        }
        return xor;
    }

    //Returns true if all bits are 0's in this long-word
    public boolean isZero()
    {
        int sum = 0;
        for(int j = 0; j < 32; j++)
        {
            if(getBit(j) == true)
            {
                sum += 1;
            }
            else if(getBit(j) == false)
            {
                sum += 0;
            }
        }
        if(sum == 0)
            return true;
        else
            return false;

    }
    
    public String convertToHex()
    {
        //Assign integer LongWord to variable
        int decimal = getSigned();
        //Convert integer LongWord to hex
        String hex = Integer.toHexString(decimal);
        return hex;    
    }
    
    @Override
    public String toString()
    { 
        String s = new String();
        for(int i = 0; i < 32; i++)
        {
            if(word.get(i) == true)
            {
                s = s + "1";
            }
            else
            {
                s = s + "0";
            }
        }
        return s + "\t" + "0x" + convertToHex();
    }
}
