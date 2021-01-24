package me.kingcjy.order.application;

import me.kingcjy.order.domain.OrderCode;

import java.util.Random;
import java.util.UUID;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
public class OrderCodeGenerator {
    private static UUID pureGeneratorUUID(long randomSeed) {
        Random random = new Random(randomSeed);
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);
        randomBytes[6] &= 0x0f;  /* clear version        */
        randomBytes[6] |= 0x40;  /* set to version 4     */
        randomBytes[8] &= 0x3f;  /* clear variant        */
        randomBytes[8] |= 0x80;  /* set to IETF variant  */
        long msb = 0;
        long lsb = 0;
        for (int i = 0; i < 8; i++)
            msb = (msb << 8) | (randomBytes[i] & 0xff);
        for (int i = 8; i < 16; i++)
            lsb = (lsb << 8) | (randomBytes[i] & 0xff);
        return new UUID(msb, lsb);
    }

    public static OrderCode generateOrderCode(long randomSeed) {
        return new OrderCode(pureGeneratorUUID(randomSeed).toString());
    }
}
