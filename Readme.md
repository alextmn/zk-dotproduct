# Java Snarks Example

LibSnark comes with InnerProduct circuit as an example of verifiable computations.

Inner product is an algebraic operation that takes two equal-length sequences of numbers (usually coordinate vectors) and returns a single number.
[read more](https://en.wikipedia.org/wiki/Dot_product)

Apparently with inner product its possible to solve some problems:

For instances if two vectors contain account and lets say amounts, we can hide these accounts and amounts and make them private. The public verification could be proof of correctness and a single number (inner product). 

Example:

```
// account1 is 14, accoun2 is 56, amount is $10
v1 = [1,4,1,0]
v2 = [5,6,1,0]

product = 1*5+4*6+1*1+0*0 = 30
```

Here is the process how to create a verifiable computation:

1. Create a pair of keys for the circuit. They are called proving and verification keys. Proving key necessary to publish a prove and its secret. Verification key is similar to public key, its used to check the correctness of computations along with pubic parameters and its not a secret.
2. Create a proof. We should take our `proving key`, private parameters `(v1,v2)` and the public parameter `PRODUCT`. The result is a proof.
3. Then any verifier can take the `verification key`, the public parameter `PRODUCT` and check that the computation was correct against `v1 and v2` without revealing the vectors.

Here is a test:

```java
// generate keys for the circuit
(prooveKey, verifyKey) =  generate()

long[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
long[] b = {9, 8, 7, 6, 5, 4, 3, 2, 1};

// generate a proof
byte[] proof = createProof(prooveKey, a, b);

// calc product of two vectors
long product = dotProduct(a,b);

// verify
boolean verificationResult =  verify(product,verifyKey, proove);
assertTrue(verificationResult);
```
Running
-------
It uses a native library with circuit implementation. The native libraries are in the lib directory.

The source is here
[https://github.com/alextmn/snarks-dot-product](https://github.com/alextmn/snarks-dot-product)

When running pls specify the path where native library is, e.g:

`java -Djava.library.path=lib/osx_64 -jar zk-dotproduct.jar`



