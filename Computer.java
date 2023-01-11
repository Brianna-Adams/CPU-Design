package CPUDesign;

import java.lang.Integer;
import java.util.StringTokenizer;

public class Computer extends Memory
{
    //Declare and initialize variables
    boolean HLT;
    ALU alu;
    Memory memory;
    LongWord[] address_registers;
    LongWord PC;
    LongWord IR;
    LongWord OP1;
    LongWord OP2;
    LongWord OP_code;
    LongWord address_storage;
    LongWord result;
    LongWord branch;

    public Computer()
    {
        HLT = false;
        alu = new ALU();
        memory = new Memory();
        address_registers = new LongWord[16];
        for (int j = 15; j >= 0; j--) 
        {
            address_registers[j] = new LongWord();
        }
        PC = new LongWord();
        IR = new LongWord();
        OP1 = new LongWord();
        OP2 = new LongWord();
        OP_code = new LongWord();
        address_storage = new LongWord();
        result = new LongWord();
        branch = new LongWord();
    }

    /**
     * the run method of Computer class
     * Set the PC start at 0
     * use the preload method to load data into memory(check ReadMe Doc.)
     * run fetch > decode > execute > store > ...
     * stop when HLT is true
     */
    public void run()
    {
        PC.set(0);
        while(HLT == false)
        {
            fetch();
            decode();
            execute();
            store();
        }
    }

    public void fetch()
    {
        IR = memory.read(PC,2);
        PC.set((PC.getSigned() + 2));
    }

    public void decode()
    {
        LongWord longWord = new LongWord();
        longWord.set(0);

        if(IR.getBit(15))
        {
            longWord.set(15);
            OP_code = IR.shiftRightLogical(12).and(longWord);
            address_storage = IR.shiftRightLogical(8).and(longWord);
            OP1 = IR.shiftRightLogical(4).and(longWord);
            OP2 = IR.and(longWord);
        }
        else if(IR.getBit(15) == false)
        {
            //The Halt(HLT) instruction (OP_code 0000)
            if((IR.getBit(14) == false) && (IR.getBit(13) == false) && (IR.getBit(12) == false))
            {
                HLT = true;
            }
            //The Interrupt/Trap(INT) instruction (OP_code 0001)
            else if((IR.getBit(14) == false) && (IR.getBit(13) == false) && (IR.getBit(12)))
            {
                longWord.set(15);
                OP_code = IR.shiftRightLogical(12).and(longWord);
                result = IR.and(longWord);
            }
            //The Move(MOV) instruction (OP_code 0010)
            else if((IR.getBit(14) == false) && (IR.getBit(13)) && (IR.getBit(12) == false))
            {
                longWord.set(15);
                OP_code = IR.shiftRightLogical(12).and(longWord);
                address_storage = IR.shiftRightLogical(8).and(longWord);
                longWord.set(255);
                OP1 = IR.and(longWord);
                if(OP1.getBit(7))
                {
                    for (int i = 0; i < 24; i++) 
                    {
                        OP1.setBit(i + 8);
                    }
                }

            }
            //The Jump(JMP) instruction (OP_code 0011)
            else if((IR.getBit(14) == false) && (IR.getBit(13)) && (IR.getBit(12)))
            {
                longWord.set(15);
                OP_code = IR.shiftRightLogical(12).and(longWord);
                longWord.set(4095);
                result = IR.shiftLeftLogical(1).and(longWord);

            }
            //The Compare(CMP) instruction (OP_code 0100)
            else if((IR.getBit(14) == false) && (IR.getBit(13) == false) && (IR.getBit(12) == false)){//0100
                longWord.set(15);
                OP_code = IR.shiftRightLogical(12).and(longWord);
                OP1 = IR.shiftRightLogical(4).and(longWord);
                OP2 = IR.and(longWord);

            }
            //The Branch(Bxx) instruction (OP_code 0101
            else if((IR.getBit(14)) && (IR.getBit(13) == false) && (IR.getBit(12)))
            {
                longWord.set(15);
                OP_code = IR.shiftRightLogical(12).and(longWord);
                longWord.set(3);
                result = IR.shiftRightLogical(10).and(longWord);
                longWord.set(1023);
                address_storage = IR.shiftLeftLogical(1).and(longWord);
            }
        }
    }

    public void execute()
    {
        if(OP_code.getBit(3))
        {
            OP1.copy(address_registers[OP1.getSigned()]);
            OP2.copy(address_registers[OP2.getSigned()]);
            OP_code.clearBit(3);
            result.copy(alu.operate(OP_code.getSigned(), OP1,OP2));
            OP_code.setBit(3);
        }
        else if(OP_code.getBit(3) == false)
        {
            //The Halt(HLT) instruction (OP_code 0000)
            if((OP_code.getBit(2) == false) && (OP_code.getBit(1) == false) && (OP_code.getBit(0) == false)) 
            {

            }
            //The Interrupt/Trap (INT) instruction(OP_code 0001)
            else if((OP_code.getBit(2) == false) && (OP_code.getBit(1) == false) && (OP_code.getBit(0)))
            {
                if(result.getBit(0) == false)
                {
                    System.out.println("Registers :");
                    for (int i = 0; i < 16; i++) 
                    {
                        System.out.println(i + " -> " + address_registers[i].toString());
                    }
                }
                else if(result.getBit(0))
                {
                    System.out.println(memory.toString());
                }
            }
            //The Move(MOV) instruction
            else if((OP_code.getBit(2) == false) && (OP_code.getBit(1)) && (OP_code.getBit(0) == false))
            {

            }
            //The Jump(JMP) instruction (OP_code 0011)
            else if((OP_code.getBit(2) == false) && (OP_code.getBit(1)) && (OP_code.getBit(0)))
            {
                PC.copy(result);
            }
            //The Compare(CMP) instruction (opcdoe 0100)
            else if((OP_code.getBit(2)) && (OP_code.getBit(1) == false) && (OP_code.getBit(0) == false)) 
            {
                OP1.copy(address_registers[OP1.getSigned()]);
                OP2.copy(address_registers[OP2.getSigned()]);
                alu.operate(4,OP1,OP2);
            }
            //The Branch (Bxx) instruction (OP_code 0101)
            else if((OP_code.getBit(2)) && (OP_code.getBit(1) == false) && (OP_code.getBit(0)))
            {
                if((result.getSigned() == 0) && !((alu.getZF() == true) && (alu.getNF() == false)))
                {
                    PC.copy(address_storage);
                }
                else if((result.getSigned() == 1) && (this.alu.getZF() == false) && (alu.getNF() == true))
                {
                    PC.copy(address_storage);
                }
                else if((result.getSigned() == 2) && (alu.getZF()) && (alu.getNF() == false))
                {
                    PC.copy(address_storage);
                }
                else if((result.getSigned() == 3) && (((alu.getZF() == true) && (alu.getNF() == false)) || ((alu.getZF() == false) && (alu.getNF() == true))))
                {
                    PC.copy(address_storage);
                }
            }
        }
    }

    public void store()
    {
        if(OP_code.getBit(3))
        {
            address_registers[address_storage.getSigned()].copy(result);
        }
        else if(OP_code.getBit(3) == false)
        {
            //The Halt(HLT) instruction (OP_code 0000)
            if((OP_code.getBit(2) == false) && (OP_code.getBit(1) == false) && (OP_code.getBit(0) == false)) 
            {

            }
            //The Interrupt/Trap(INT) instruction (OP_code 0001)
            else if((OP_code.getBit(2) == false) && (OP_code.getBit(1) == false) && (OP_code.getBit(0)))
            {

            }
            //The Move(MOV) instruction (OP_code 0010)
            else if((OP_code.getBit(2) == false) && (OP_code.getBit(1)) && (OP_code.getBit(0) == false))
            {
                address_registers[address_storage.getSigned()].copy(OP1);

            }
            //The Jump(JMP) instruction (OP_code 0011)
            else if((OP_code.getBit(2) == false) && (OP_code.getBit(1)) && (OP_code.getBit(0)))
            {

            }
            //The Compate(CMP) instruction (OP_code 0100)
            else if((OP_code.getBit(2)) && (OP_code.getBit(1) == false) && (OP_code.getBit(0) == false)) 
            {
                System.out.println("NF: " + alu.getNF() + "ZF: " + alu.getZF());
            }
            //The Branch(Bxx) instructions (OP_code 0101)
            else if((OP_code.getBit(2)) && (OP_code.getBit(1) == false) && (OP_code.getBit(0)))
            {
               
            }
        }
    }
        
    public void preload(String[] arr)
    {
        
    }
    
}  

