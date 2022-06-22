package org.service;

import io.quarkus.cache.CacheResult;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigInteger;
import java.util.ArrayList;

@ApplicationScoped
public class AlticciService {
    @CacheResult(cacheName = "alticci-cache")
    public BigInteger getAlticciSeqByIndex(int n) throws IllegalArgumentException {
        if (n == 0) {
            return BigInteger.valueOf(0);
        } else if (n == 1 || n == 2) {
            return BigInteger.valueOf(1);
        } else if (n >= 3) {
            return getAlticciSeqByIndex(n - 3).add(getAlticciSeqByIndex(n - 2));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public ArrayList getAlticciSeqArray(int start, int end) throws IllegalArgumentException {
        ArrayList resposta = new ArrayList();
        if (start >=0 && end >=0 && start <= end) {
            for (int i = start; i <= end; i++){
                resposta.add(getAlticciSeqByIndex(i));
            }
            return resposta;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
