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

import java.security.PrivateKey;

/**
 * The handle for a private key using the Microsoft Crypto API.
 *
 * @author Stanley Man-Kit Ho
 * @since 1.6
 */
class CPrivateKey extends CKey implements PrivateKey {

    private static final long serialVersionUID = 8113152807912338063L;

    private CPrivateKey(String alg, long hCryptProv, long hCryptKey, int keyLength) {
        super(alg, hCryptProv, hCryptKey, keyLength);
    }

    public static CPrivateKey of(String alg, long hCryptProv, long hCryptKey, int keyLength) {
        return new CPrivateKey(alg, hCryptProv, hCryptKey, keyLength);
    }

    // this key does not support encoding
    public String getFormat()     {
        return null;
    }

    // this key does not support encoding
    public byte[] getEncoded() {
        return null;
    }

    public String toString() {
        return algorithm + "PrivateKey [size=" + keyLength + " bits, type=" +
            getKeyType(handles.hCryptKey) + ", container=" +
            getContainerName(handles.hCryptProv) + "]";
    }

    // This class is not serializable
    private void writeObject(java.io.ObjectOutputStream out)
            throws java.io.IOException {
        throw new java.io.NotSerializableException();
    }
}
