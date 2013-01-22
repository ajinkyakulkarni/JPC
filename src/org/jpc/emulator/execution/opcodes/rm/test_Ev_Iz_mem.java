package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class test_Ev_Iz_mem extends Executable
{
    final Pointer op1;
    final int immz;
    final int size;

    public test_Ev_Iz_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        size = parent.opr_mode;
        op1 = new Pointer(parent.operand[0], parent.adr_mode);
        immz = (int)parent.operand[1].lval;
    }

    public Branch execute(Processor cpu)
    {
        if (size == 16)
        {
        cpu.of = cpu.af = cpu.cf = false;
        cpu.flagResult = (short)(op1.get16(cpu) & immz);
        cpu.flagStatus = SZP;
        }
        else if (size == 32)
        {
        cpu.of = cpu.af = cpu.cf = false;
        cpu.flagResult = (op1.get32(cpu) & immz);
        cpu.flagStatus = SZP;
        }        else throw new IllegalStateException("Unknown size "+size);
        return Branch.None;
    }

    public boolean isBranch()
    {
        return false;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}