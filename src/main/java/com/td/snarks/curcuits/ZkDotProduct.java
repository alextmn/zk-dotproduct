package com.td.snarks.curcuits;

import java.util.stream.IntStream;

public class ZkDotProduct {
    static {
        System.loadLibrary(
                "SnarksDotProductLib");
    }

    public native void generate(int size, KeyGenerationFunc cb);

    public native byte[] createProof(byte[] pk, long[] a, long[] b);

    public native boolean verify(long product, byte[] vk, byte[] proof);

    public long dotProduct(long[] a, long[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays have different size");
        }
        long sum = IntStream.range(0, a.length)
                .mapToLong(i -> a[i] * b[i])
                .sum();
        return sum;
    }

    public static ZkDotProduct newThreadSafeInstance() {
        return new ZkDotProduct(){
            public  void generate(int size, KeyGenerationFunc cb){
                synchronized (ZkDotProduct.class) {
                    super.generate(size, cb);
                }
            }

            public  byte[] createProof(byte[] pk, long[] a, long[] b){
                synchronized (ZkDotProduct.class) {
                    return super.createProof(pk, a, b);
                }
            }

            public  boolean verify(long product, byte[] vk, byte[] proof){
                synchronized (ZkDotProduct.class) {
                    return super.verify(product, vk, proof);
                }
            }
        };
    }
}
