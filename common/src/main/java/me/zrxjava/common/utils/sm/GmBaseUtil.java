package me.zrxjava.common.utils.sm;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * @author void
 */
public class GmBaseUtil {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
}
