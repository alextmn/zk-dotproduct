package com.td.snarks.curcuits;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZkDotProductTest {

    private byte[] prooveKey, verifyKey;

    private final int VectorSize = 9;


    @Before
    public void generateCurcuitKeyPair() {

        new ZkDotProduct().generate(VectorSize, (pk, vk) -> {
            prooveKey = pk;
            verifyKey = vk;
        });

        assertNotNull(prooveKey);
        assertNotNull(verifyKey);
    }

    @Test
    public void testProof() {
        long[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        long[] b = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        // generate a proof
        byte[] proof = new ZkDotProduct().createProof(prooveKey, a, b);

        // calc a product of two vectors
        long product = new ZkDotProduct().dotProduct(a,b);

        // verify
        boolean verificationResult =  new ZkDotProduct().verify(product,verifyKey, proof);
        assertTrue(verificationResult);

        // verify with not matching public param
        verificationResult =  new ZkDotProduct().verify(product + 1,verifyKey, proof);
        assertFalse(verificationResult);
    }
}
