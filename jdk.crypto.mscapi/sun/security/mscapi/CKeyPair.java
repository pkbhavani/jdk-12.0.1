/*
 * Copyright (c) 2005, 2018, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package sun.security.mscapi;

/**
 * The handle for an RSA public/private keypair using the Microsoft Crypto API.
 *
 * @since 1.6
 */
class CKeyPair {

    private final CPrivateKey privateKey;

    private final CPublicKey publicKey;

    CKeyPair(String alg, long hCryptProv, long hCryptKey, int keyLength) {
        privateKey = CPrivateKey.of(alg, hCryptProv, hCryptKey, keyLength);
        publicKey = CPublicKey.of(alg, hCryptProv, hCryptKey, keyLength);
    }

    public CPrivateKey getPrivate() {
        return privateKey;
    }

    public CPublicKey getPublic() {
        return publicKey;
    }
}
