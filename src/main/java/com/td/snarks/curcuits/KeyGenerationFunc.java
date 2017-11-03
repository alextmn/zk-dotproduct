package com.td.snarks.curcuits;

public interface KeyGenerationFunc {

    void onKeyPair(byte[] pk, byte[]vk);
}
