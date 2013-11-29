package ru.regenix.jphp.runtime.memory;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import ru.regenix.jphp.runtime.memory.support.Memory;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReferenceMemoryTest {

    @Test
    public void testValueOf(){
        Memory memory = ReferenceMemory.valueOf(Memory.TRUE);
        Assert.assertTrue(memory instanceof ReferenceMemory);
        Assert.assertFalse(memory.isImmutable());
        Assert.assertNotNull(memory.toImmutable());
        Assert.assertTrue(memory.toImmutable().isImmutable());
        Assert.assertTrue(memory.toImmutable() instanceof TrueMemory);
    }

    @Test
    public void testNew(){
        Memory memory = new ReferenceMemory(Memory.FALSE);
        Assert.assertEquals(Memory.FALSE, memory.toImmutable());
    }

    @Test
    public void testAssign(){
        Memory memory = new ReferenceMemory();

        memory.assign(true);
        Assert.assertEquals(Memory.TRUE, memory.toImmutable());
        Assert.assertTrue(memory.toBoolean());

        memory.assign(false);
        Assert.assertEquals(Memory.FALSE, memory.toImmutable());
        Assert.assertFalse(memory.toBoolean());

        memory.assign(12);
        Assert.assertTrue(memory.toImmutable() instanceof LongMemory);
        Assert.assertEquals(12, memory.toLong());

        memory.assign(12.3);
        Assert.assertTrue(memory.toImmutable() instanceof DoubleMemory);
        Assert.assertEquals(12.3, memory.toDouble(), 0.000001);

        memory.assign("foobar");
        Assert.assertTrue(memory.toImmutable() instanceof StringMemory);
        Assert.assertEquals("foobar", memory.toString());

        memory.assign(new ReferenceMemory(new StringMemory("foobar")));
        Assert.assertTrue(memory.toImmutable() instanceof StringMemory);
        Assert.assertEquals("foobar", memory.toString());
    }

    @Test
    public void testAssignRef(){
        Memory memory = new ReferenceMemory();
        ReferenceMemory ref = new ReferenceMemory(Memory.TRUE);

        memory.assignRef(ref);
        Assert.assertEquals(Memory.TRUE, memory.toImmutable());

        memory.assign(false);
        Assert.assertEquals(Memory.FALSE, ref.toImmutable());

        Memory memory2 = new ReferenceMemory();
        memory2.assignRef(memory);
        Assert.assertEquals(Memory.FALSE, memory.toImmutable());

        memory2.assign(12);
        Assert.assertEquals(12, memory.toLong());
        Assert.assertEquals(12, ref.toLong());
    }

    @Test
    public void testConcatAssign(){
        Memory memory = new ReferenceMemory(new StringMemory("foo"));
        memory.concatAssign("bar");

        Assert.assertTrue(memory.toImmutable() instanceof StringMemory);
        Assert.assertNotNull(((StringMemory) memory.toImmutable()).builder);
        Assert.assertNull(((StringMemory) memory.toImmutable()).value);

        memory.concatAssign("foo");
        Assert.assertNull(((StringMemory)memory.toImmutable()).value);
        Assert.assertEquals("foobarfoo", memory.toString());
        Assert.assertNotNull(((StringMemory) memory.toImmutable()).value);

        Memory memory2 = new ReferenceMemory();
        memory2.assignRef(memory);
        memory2.concatAssign("bar");
        Assert.assertNotNull(((StringMemory) memory.toImmutable()).builder);
        Assert.assertEquals("foobarfoobar", memory2.toString());
    }
}