/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
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
 */


package org.graalvm.compiler.replacements.test;

import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.junit.Test;

import jdk.vm.ci.code.InstalledCode;
import jdk.vm.ci.code.InvalidInstalledCodeException;
import jdk.vm.ci.meta.ResolvedJavaMethod;

public class IntegerExactExceptionTest extends GraalCompilerTest {

    static int intCounter = 32;

    public void testIntegerExactOverflowSnippet(int input) {
        try {
            intCounter = Math.addExact(intCounter, input);
        } catch (ArithmeticException e) {
            intCounter = intCounter / 2;
        }
    }

    @Test
    public void testIntegerExact() throws InvalidInstalledCodeException {
        ResolvedJavaMethod method = getResolvedJavaMethod("testIntegerExactOverflowSnippet");
        InstalledCode code = getCode(method);
        code.executeVarargs(this, Integer.MAX_VALUE);

        if (!code.isValid()) {
            code = getCode(method);
            code.executeVarargs(this, Integer.MAX_VALUE);
            assertTrue(code.isValid());
        }
    }

    public void testIntegerExactOverflowWithoutHandlerSnippetW(int input) {
        try {
            intCounter = Math.addExact(intCounter, input);
        } finally {
            intCounter = intCounter / 2;
        }
    }

    @Test
    public void testIntegerExactWithoutHandler() throws InvalidInstalledCodeException {
        ResolvedJavaMethod method = getResolvedJavaMethod("testIntegerExactOverflowWithoutHandlerSnippetW");
        InstalledCode code = getCode(method);

        try {
            code.executeVarargs(this, Integer.MAX_VALUE);
        } catch (ArithmeticException e) {
            // An ArithmeticException is expected to be thrown.
        }

        if (!code.isValid()) {
            code = getCode(method);
            try {
                code.executeVarargs(this, Integer.MAX_VALUE);
            } catch (ArithmeticException e) {
                // An ArithmeticException is expected to be thrown.
            }
            assertTrue(code.isValid());
        }
    }

    public void testIntegerExactOverflowWithoutUse1(int input) {
        Math.addExact(intCounter, input);
    }

    public void testIntegerExactOverflowWithoutUse2(int input, boolean cond) {
        if (cond) {
            Math.addExact(intCounter, input);
        } else {
            intCounter = Math.addExact(intCounter, input);
        }
    }

    public void testIntegerExactOverflowWithoutUse3() {
        Math.addExact(Integer.MAX_VALUE, 1);
    }

    @Test
    public void testIntegerExactWithoutUse1() throws InvalidInstalledCodeException {
        ResolvedJavaMethod method = getResolvedJavaMethod("testIntegerExactOverflowWithoutUse1");
        InstalledCode code = getCode(method);

        boolean gotException = false;
        try {
            code.executeVarargs(this, Integer.MAX_VALUE);
        } catch (ArithmeticException e) {
            gotException = true;
        }
        assertTrue(gotException);
    }

    @Test
    public void testIntegerExactWithoutUse2() throws InvalidInstalledCodeException {
        ResolvedJavaMethod method = getResolvedJavaMethod("testIntegerExactOverflowWithoutUse2");
        InstalledCode code = getCode(method);

        boolean gotException = false;
        try {
            code.executeVarargs(this, Integer.MAX_VALUE, true);
        } catch (ArithmeticException e) {
            gotException = true;
        }
        assertTrue(gotException);
    }

    @Test
    public void testIntegerExactWithoutUse3() throws InvalidInstalledCodeException {
        ResolvedJavaMethod method = getResolvedJavaMethod("testIntegerExactOverflowWithoutUse3");
        InstalledCode code = getCode(method);

        boolean gotException = false;
        try {
            code.executeVarargs(this);
        } catch (ArithmeticException e) {
            gotException = true;
        }
        assertTrue(gotException);
    }

    static long longCounter = 10;

    public void testLongExactOverflowSnippet(long input) {
        try {
            longCounter = Math.addExact(longCounter, input);
        } catch (ArithmeticException e) {
            longCounter = longCounter / 2;
        }
    }

    @Test
    public void testLongExact() throws InvalidInstalledCodeException {
        ResolvedJavaMethod method = getResolvedJavaMethod("testLongExactOverflowSnippet");
        InstalledCode code = getCode(method);
        code.executeVarargs(this, Long.MAX_VALUE);

        if (!code.isValid()) {
            code = getCode(method);
            code.executeVarargs(this, Long.MAX_VALUE);
            assertTrue(code.isValid());
        }
    }

    public void testLongExactWithoutHandlerSnippet(long input) {
        try {
            longCounter = Math.addExact(longCounter, input);
        } finally {
            longCounter = longCounter / 2;
        }
    }

    @Test
    public void testLongExactWithoutHandler() throws InvalidInstalledCodeException {
        ResolvedJavaMethod method = getResolvedJavaMethod("testLongExactWithoutHandlerSnippet");
        InstalledCode code = getCode(method);

        try {
            code.executeVarargs(this, Long.MAX_VALUE);
        } catch (ArithmeticException e) {
            // An ArithmeticException is expected to be thrown.
        }

        if (!code.isValid()) {
            code = getCode(method);
            try {
                code.executeVarargs(this, Long.MAX_VALUE);
            } catch (ArithmeticException e) {
                // An ArithmeticException is expected to be thrown.
            }
            assertTrue(code.isValid());
        }
    }

    public void testLongExactOverflowWithoutUse1(long input) {
        Math.addExact(longCounter, input);
    }

    public void testLongExactOverflowWithoutUse2(long input, boolean cond) {
        if (cond) {
            Math.addExact(longCounter, input);
        } else {
            longCounter = Math.addExact(longCounter, input);
        }
    }

    @Test
    public void testLongExactWithoutUse1() throws InvalidInstalledCodeException {
        ResolvedJavaMethod method = getResolvedJavaMethod("testLongExactOverflowWithoutUse1");
        InstalledCode code = getCode(method);

        boolean gotException = false;
        try {
            code.executeVarargs(this, Long.MAX_VALUE);
        } catch (ArithmeticException e) {
            gotException = true;
        }
        assertTrue(gotException);
    }

    @Test
    public void testLongExactWithoutUse2() throws InvalidInstalledCodeException {
        ResolvedJavaMethod method = getResolvedJavaMethod("testLongExactOverflowWithoutUse2");
        InstalledCode code = getCode(method);

        boolean gotException = false;
        try {
            code.executeVarargs(this, Long.MAX_VALUE, true);
        } catch (ArithmeticException e) {
            gotException = true;
        }
        assertTrue(gotException);
    }
}
