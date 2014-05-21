/* ***** BEGIN LICENSE BLOCK *****
 * 
 * JLargeArrays
 * Copyright (C) 2013 onward University of Warsaw, ICM
 *
 * This file is part of GNU Classpath.
 *
 * GNU Classpath is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * GNU Classpath is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GNU Classpath; see the file COPYING.  If not, write to the 
 * University of Warsaw, Interdisciplinary Centre for Mathematical and 
 * Computational Modelling, Pawinskiego 5a, 02-106 Warsaw, Poland. 
 * 
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 * 
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under
 * terms of your choice, provided that you also meet, for each linked
 * independent module, the terms and conditions of the license of that
 * module.  An independent module is a module which is not derived from
 * or based on this library.  If you modify this library, you may extend
 * this exception to your version of the library, but you are not
 * obligated to do so.  If you do not wish to do so, delete this
 * exception statement from your version. 
 * 
 * ***** END LICENSE BLOCK ***** */
package pl.edu.icm.jlargearrays;

/**
 * The base class for all large arrays. All implementations of this abstract
 * class can store up to 2<SUP>63</SUP> elements of primitive data types.
 *
 * @author Piotr Wendykier (p.wendykier@icm.edu.pl)
 */
public abstract class LargeArray implements java.io.Serializable, Cloneable
{

    private static final long serialVersionUID = 7921589398878016801L;
    protected LargeArrayType type;
    protected long length;
    protected long sizeof;
    protected long ptr = 0;
    
    /**
     * Largest array size for which a regular 1D Java array is used to store the
     * data.
     */
    protected static int LARGEST_32BIT_INDEX = 1073741824; //2^30;

    /**
     * Returns the length of an array.
     *
     * @return the length of an array
     */
    public long length()
    {
        return length;
    }

    /**
     * Returns the type of an array.
     *
     * @return the type of an array
     */
    public LargeArrayType getType()
    {
        return type;
    }

    /**
     * Returns a value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i an index
     * @return a value at index i.
     */
    public abstract Object get(long i);

    /**
     * Returns a value at index i. Array bounds are checked.
     *
     * @param i an index
     * @return a value at index i.
     */
    public Object get_safe(long i)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        return get(i);
    }

    /**
     * Returns a value at index i. Array bounds are not checked. If isLarge() returns false for a given array or the index argument is invalid, then calling
     * this method will cause JVM crash.
     *
     * @param i index
     * @return a value at index i. The type of returned value is the same as the type of this array.
     */
    public abstract Object getFromNative(long i);

    /**
     * Returns a boolean value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i an index
     * @return a boolean value at index i.
     */
    public abstract boolean getBoolean(long i);

    /**
     * Returns a boolean value at index i. Array bounds are checked.
     *
     * @param i an index
     * @return a boolean value at index i.
     */
    public boolean getBoolean_safe(long i)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        return getBoolean(i);
    }

    /**
     * Returns a byte value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i an index
     * @return a value at index i.
     */
    public abstract byte getByte(long i);

    /**
     * Returns a byte value at index i. Array bounds are checked.
     *
     * @param i an index
     * @return a value at index i.
     */
    public byte getByte_safe(long i)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        return getByte(i);
    }

    /**
     * Returns a short value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i an index
     * @return a value at index i.
     */
    public abstract short getShort(long i);

    /**
     * Returns a short value at index i. Array bounds are checked.
     *
     * @param i an index
     * @return a value at index i.
     */
    public short getShort_safe(long i)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        return getShort(i);
    }

    /**
     * Returns an int value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i an index
     * @return a value at index i.
     */
    public abstract int getInt(long i);

    /**
     * Returns an int value at index i. Array bounds are checked.
     *
     * @param i an index
     * @return a value at index i.
     */
    public int getInt_safe(long i)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        return getInt(i);
    }

    /**
     * Returns a long value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i an index
     * @return a value at index i.
     */
    public abstract long getLong(long i);

    /**
     * Returns a long value at index i. Array bounds are checked.
     *
     * @param i an index
     * @return a value at index i.
     */
    public long getLong_safe(long i)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        return getLong(i);
    }

    /**
     * Returns a float value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i an index
     * @return a value at index i.
     */
    public abstract float getFloat(long i);

    /**
     * Returns a float value at index i. Array bounds are checked.
     *
     * @param i an index
     * @return a value at index i.
     */
    public float getFloat_safe(long i)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        return getFloat(i);
    }

    /**
     * Returns a double value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i an index
     * @return a value at index i.
     */
    public abstract double getDouble(long i);

    /**
     * Returns a double value at index i. Array bounds are checked.
     *
     * @param i an index
     * @return a value at index i.
     */
    public double getDouble_safe(long i)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        return getDouble(i);
    }

    /**
     * If the size of the array is smaller than LARGEST_32BIT_INDEX, then this
     * method returns a reference to the internal data array. Otherwise, it
     * returns null.
     *
     * @return reference to the internal data array or null
     */
    public abstract Object getData();

    /**
     * If the size of the array is smaller than LARGEST_32BIT_INDEX, then this
     * method returns boolean data. Otherwise, it returns null.
     *
     * @return an array containing the elements of the list or null
     */
    public abstract boolean[] getBooleanData();

    /**
     * If (endPos - startPos) / step is smaller than LARGEST_32BIT_INDEX, then
     * this method returns selected elments of an array. Otherwise, it returns
     * null. If (endPos - startPos) / step is smaller or equal to a.length, it
     * is returned therein. Otherwise, a new array is allocated and returned.
     * Array bounds are checked.
     *
     * @param a the array into which the elements are to be stored, if it is big
     * enough; otherwise, a new array of is allocated for this purpose.
     * @param startPos starting position (included)
     * @param endPos ending position (excluded)
     * @param step step size
     * @return an array containing the elements of the list or null
     */
    public abstract boolean[] getBooleanData(boolean[] a, long startPos, long endPos, long step);

    /**
     * If the size of the array is smaller than LARGEST_32BIT_INDEX, then this
     * method returns byte data. Otherwise, it returns null.
     *
     * @return an array containing the elements of the list or null
     */
    public abstract byte[] getByteData();

    /**
     * If (endPos - startPos) / step is smaller than LARGEST_32BIT_INDEX, then
     * this method returns selected elments of an array. Otherwise, it returns
     * null. If (endPos - startPos) / step is smaller or equal to a.length, it
     * is returned therein. Otherwise, a new array is allocated and returned.
     * Array bounds are checked.
     *
     * @param a the array into which the elements are to be stored, if it is big
     * enough; otherwise, a new array of is allocated for this purpose.
     * @param startPos starting position (included)
     * @param endPos ending position (excluded)
     * @param step step size
     * @return an array containing the elements of the list or null
     */
    public abstract byte[] getByteData(byte[] a, long startPos, long endPos, long step);

    /**
     * If the size of the array is smaller than LARGEST_32BIT_INDEX, then this
     * method returns short data. Otherwise, it returns null.
     *
     * @return an array containing the elements of the list or null
     */
    public abstract short[] getShortData();

    /**
     * If (endPos - startPos) / step is smaller than LARGEST_32BIT_INDEX, then
     * this method returns selected elments of an array. Otherwise, it returns
     * null. If (endPos - startPos) / step is smaller or equal to a.length, it
     * is returned therein. Otherwise, a new array is allocated and returned.
     * Array bounds are checked.
     *
     * @param a the array into which the elements are to be stored, if it is big
     * enough; otherwise, a new array of is allocated for this purpose.
     * @param startPos starting position (included)
     * @param endPos ending position (excluded)
     * @param step step size
     * @return an array containing the elements of the list or null
     */
    public abstract short[] getShortData(short[] a, long startPos, long endPos, long step);

    /**
     * If the size of the array is smaller than LARGEST_32BIT_INDEX, then this
     * method returns int data. Otherwise, it returns null.
     *
     * @return an array containing the elements of the list or null
     */
    public abstract int[] getIntData();

    /**
     * If (endPos - startPos) / step is smaller than LARGEST_32BIT_INDEX, then
     * this method returns selected elments of an array. Otherwise, it returns
     * null. If (endPos - startPos) / step is smaller or equal to a.length, it
     * is returned therein. Otherwise, a new array is allocated and returned.
     * Array bounds are checked.
     *
     * @param a the array into which the elements are to be stored, if it is big
     * enough; otherwise, a new array of is allocated for this purpose.
     * @param startPos starting position (included)
     * @param endPos ending position (excluded)
     * @param step step size
     * @return an array containing the elements of the list or null
     */
    public abstract int[] getIntData(int[] a, long startPos, long endPos, long step);

    /**
     * If the size of the array is smaller than LARGEST_32BIT_INDEX, then this
     * method returns long data. Otherwise, it returns null.
     *
     * @return an array containing the elements of the list or null
     */
    public abstract long[] getLongData();

    /**
     * If (endPos - startPos) / step is smaller than LARGEST_32BIT_INDEX, then
     * this method returns selected elments of an array. Otherwise, it returns
     * null. If (endPos - startPos) / step is smaller or equal to a.length, it
     * is returned therein. Otherwise, a new array is allocated and returned.
     * Array bounds are checked.
     *
     * @param a the array into which the elements are to be stored, if it is big
     * enough; otherwise, a new array of is allocated for this purpose.
     * @param startPos starting position (included)
     * @param endPos ending position (excluded)
     * @param step step size
     * @return an array containing the elements of the list or null
     */
    public abstract long[] getLongData(long[] a, long startPos, long endPos, long step);

    /**
     * If the size of the array is smaller than LARGEST_32BIT_INDEX, then this
     * method returns float data. Otherwise, it returns null.
     *
     * @return an array containing the elements of the list or null
     */
    public abstract float[] getFloatData();

    /**
     * If (endPos - startPos) / step is smaller than LARGEST_32BIT_INDEX, then
     * this method returns selected elments of an array. Otherwise, it returns
     * null. If (endPos - startPos) / step is smaller or equal to a.length, it
     * is returned therein. Otherwise, a new array is allocated and returned.
     * Array bounds are checked.
     *
     * @param a the array into which the elements are to be stored, if it is big
     * enough; otherwise, a new array of is allocated for this purpose.
     * @param startPos starting position (included)
     * @param endPos ending position (excluded)
     * @param step step size
     * @return an array containing the elements of the list or null
     */
    public abstract float[] getFloatData(float[] a, long startPos, long endPos, long step);

    /**
     * If the size of the array is smaller than LARGEST_32BIT_INDEX, then this
     * method returns double data. Otherwise, it returns null.
     *
     * @return an array containing the elements of the list or null
     */
    public abstract double[] getDoubleData();

    /**
     * If (endPos - startPos) / step is smaller than LARGEST_32BIT_INDEX, then
     * this method returns selected elments of an array. Otherwise, it returns
     * null. If (endPos - startPos) / step is smaller or equal to a.length, it
     * is returned therein. Otherwise, a new array is allocated and returned.
     * Array bounds are checked.
     *
     * @param a the array into which the elements are to be stored, if it is big
     * enough; otherwise, a new array of is allocated for this purpose.
     * @param startPos starting position (included)
     * @param endPos ending position (excluded)
     * @param step step size
     * @return an array containing the elements of the list or null
     */
    public abstract double[] getDoubleData(double[] a, long startPos, long endPos, long step);

    /**
     * Sets a value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i index
     * @param value value to set
     */
    public void set(long i, Object value)
    {
        if (value instanceof Boolean) {
            setBoolean(i, (Boolean) value);
        } else if (value instanceof Byte) {
            setByte(i, (Byte) value);
        } else if (value instanceof Short) {
            setShort(i, (Short) value);
        } else if (value instanceof Integer) {
            setInt(i, (Integer) value);
        } else if (value instanceof Long) {
            setLong(i, (Long) value);
        } else if (value instanceof Float) {
            setFloat(i, (Float) value);
        } else if (value instanceof Double) {
            setDouble(i, (Double) value);
        } else if (value instanceof Double) {
            throw new IllegalArgumentException("Unsupported type.");
        }
    }

    /**
     * Sets a value at index i. Array bounds are not checked. If isLarge() returns false for a given array or the index argument is invalid, then calling this
     * method will cause JVM crash.
     *
     * @param i index
     * @param value value to set
     * @throws ClassCastException if the type of value argument is different than the type of the array
     */
    public abstract void setToNative(long i, Object value);

    /**
     * Sets a value at index i. Array bounds are checked.
     *
     * @param i index
     * @param value value to set
     */
    public void set_safe(long i, Object value)
    {
        if (value instanceof Boolean) {
            setBoolean_safe(i, (Boolean) value);
        } else if (value instanceof Byte) {
            setByte_safe(i, (Byte) value);
        } else if (value instanceof Short) {
            setShort_safe(i, (Short) value);
        } else if (value instanceof Integer) {
            setInt_safe(i, (Integer) value);
        } else if (value instanceof Long) {
            setLong_safe(i, (Long) value);
        } else if (value instanceof Float) {
            setFloat_safe(i, (Float) value);
        } else if (value instanceof Double) {
            setDouble_safe(i, (Double) value);
        } else if (value instanceof Double) {
            throw new IllegalArgumentException("Unsupported type.");
        }
    }

    /**
     * Sets a boolean value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i index
     * @param value value to set
     */
    public abstract void setBoolean(long i, boolean value);

    /**
     * Sets a boolean value at index i. Array bounds are checked.
     *
     * @param i index
     * @param value value to set
     */
    public void setBoolean_safe(long i, boolean value)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        setBoolean(i, value);
    }

    /**
     * Sets a byte value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i index
     * @param value value to set
     */
    public abstract void setByte(long i, byte value);

    /**
     * Sets a byte value at index i. Array bounds are checked.
     *
     * @param i index
     * @param value value to set
     */
    public void setByte_safe(long i, byte value)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        setByte(i, value);
    }

    /**
     * Sets a short value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i index
     * @param value value to set
     */
    public abstract void setShort(long i, short value);

    /**
     * Sets a short value at index i. Array bounds are checked.
     *
     * @param i index
     * @param value value to set
     */
    public void setShort_safe(long i, short value)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        setShort(i, value);
    }

    /**
     * Sets an int value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i index
     * @param value value to set
     */
    public abstract void setInt(long i, int value);

    /**
     * Sets an int value at index i. Array bounds are checked.
     *
     * @param i index
     * @param value value to set
     */
    public void setInt_safe(long i, int value)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        setInt(i, value);
    }

    /**
     * Sets a long value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i index
     * @param value value to set
     */
    public abstract void setLong(long i, long value);

    /**
     * Sets a long value at index i. Array bounds are checked.
     *
     * @param i index
     * @param value value to set
     */
    public void setLong_safe(long i, long value)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        setLong(i, value);
    }

    /**
     * Sets a float value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i index
     * @param value value to set
     */
    public abstract void setFloat(long i, float value);

    /**
     * Sets a float value at index i. Array bounds are checked.
     *
     * @param i index
     * @param value value to set
     */
    public void setFloat_safe(long i, float value)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        setFloat(i, value);
    }

    /**
     * Sets a double value at index i. Array bounds are not checked. Calling this method with invalid index argument will cause JVM crash.
     *
     * @param i index
     * @param value value to set
     */
    public abstract void setDouble(long i, double value);

    /**
     * Sets a double value at index i. Array bounds are checked.
     *
     * @param i index
     * @param value value to set
     */
    public void setDouble_safe(long i, double value)
    {
        if (i < 0 || i >= length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(i));
        }
        setDouble(i, value);
    }

    /**
     * Returns true if the size od an array is larger than LARGEST_32BIT_INDEX.
     *
     * @return true if the size od an array is larger than LARGEST_32BIT_INDEX,
     * false otherwise.
     */
    public boolean isLarge()
    {
        return ptr != 0;
    }

    /**
     * Sets the maximal size of a 32-bit array. For arrays of the size larger
     * than index, the data is stored in the memory allocated by
     * sun.misc.Unsafe.allocateMemory().
     *
     * @param index the maximal size of a 32-bit array.
     */
    public static void setMaxSizeOf32bitArray(int index)
    {
        if (index < 0) {
            throw new IllegalArgumentException("index cannot be negative");
        }
        LARGEST_32BIT_INDEX = index;
    }

    /**
     * Returns the maximal size of a 32-bit array.
     *
     * @return the maximal size of a 32-bit array.
     */
    public static int getMaxSizeOf32bitArray()
    {
        return LARGEST_32BIT_INDEX;
    }

    @Override
    public Object clone()
    {
        try {
            return super.clone();
        } catch (CloneNotSupportedException exc) {
            throw new InternalError(); // should never happen
        }
    }

    /**
     * Memory deallocator.
     */
    protected static class Deallocator implements Runnable
    {

        private long ptr;
        private final long length;
        private final long sizeof;

        public Deallocator(long ptr, long length, long sizeof)
        {
            this.ptr = ptr;
            this.length = length;
            this.sizeof = sizeof;
        }

        @Override
        public void run()
        {
            if (ptr != 0) {
                Utilities.UNSAFE.freeMemory(ptr);
                ptr = 0;
                MemoryCounter.decreaseCounter(length * sizeof);
            }
        }
    }

    /**
     * Initializes allocated native memory to zero.
     */
    protected void zeroNativeMemory(long size)
    {
        if (ptr != 0) {
            int nthreads = Runtime.getRuntime().availableProcessors();
            if (nthreads <= 2) {
                Utilities.UNSAFE.setMemory(ptr, size * sizeof, (byte) 0);
            } else {
                long k = size / nthreads;
                Thread[] threads = new Thread[nthreads];
                final long ptrf = ptr;
                for (int j = 0; j < nthreads; j++) {
                    final long firstIdx = j * k;
                    final long lastIdx = (j == nthreads - 1) ? size : firstIdx + k;
                    threads[j] = new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            for (long k = firstIdx; k < lastIdx; k++) {
                                Utilities.UNSAFE.putByte(ptrf + sizeof * k, (byte) 0);
                            }
                        }
                    });
                    threads[j].start();
                }
                try {
                    for (int j = 0; j < nthreads; j++) {
                        threads[j].join();
                        threads[j] = null;
                    }
                } catch (InterruptedException ex) {
                    Utilities.UNSAFE.setMemory(ptr, size * sizeof, (byte) 0);
                }
            }
        }
    }
}
