package org.tcs.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public interface AssertHelper {
    Logger LOGGER = LogManager.getLogger(AssertHelper.class);

    SoftAssert softAssert = new SoftAssert();

    // ===== Hard Asserts =====

    static void assertEquals(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
            LOGGER.info("✅ [ASSERT PASSED] Expected: [{}], Actual: [{}]", expected, actual);
        } catch (AssertionError e) {
            LOGGER.error("❌ [ASSERT FAILED] Expected: [{}], Actual: [{}]", expected, actual, e);
            throw e; // rethrow so TestNG marks test failed
        }
    }

    static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            LOGGER.info("✅ [ASSERT PASSED]");
        } catch (AssertionError e) {
            LOGGER.error("❌ [ASSERT FAILED]", e);
            throw e;
        }
    }

    static void assertFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
            LOGGER.info("✅ [ASSERT PASSED] {}", message);
        } catch (AssertionError e) {
            LOGGER.error("❌ [ASSERT FAILED] {}", message, e);
            throw e;
        }
    }

    // ===== Soft Asserts =====

    static void verifyEquals(String actual, String expected, String message) {
        try {
            softAssert.assertEquals(actual, expected, message);
            LOGGER.info("✅ [VERIFY PASSED] {} | Expected: [{}], Actual: [{}]", message, expected, actual);
        } catch (AssertionError e) {
            LOGGER.warn("⚠️ [VERIFY FAILED] {} | Expected: [{}], Actual: [{}]", message, expected, actual, e);
        }
    }

    static void verifyTrue(boolean condition, String message) {
        try {
            softAssert.assertTrue(condition, message);
            LOGGER.info("✅ [VERIFY PASSED] {}", message);
        } catch (AssertionError e) {
            LOGGER.warn("⚠️ [VERIFY FAILED] {}", message, e);
        }
    }

    static void verifyFalse(boolean condition, String message) {
        try {
            softAssert.assertFalse(condition, message);
            LOGGER.info("✅ [VERIFY PASSED] {}", message);
        } catch (AssertionError e) {
            LOGGER.warn("⚠️ [VERIFY FAILED] {}", message, e);
        }
    }

    static void verifyAll() {
        try {
            softAssert.assertAll();
            LOGGER.info("✅ All soft assertions passed.");
        } catch (AssertionError e) {
            LOGGER.error("❌ One or more soft assertions failed. Check logs above.", e);
            throw e;
        }
    }
}
