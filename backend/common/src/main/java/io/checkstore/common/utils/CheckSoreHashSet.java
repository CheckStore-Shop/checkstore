package io.checkstore.common.utils;

import io.checkstore.common.entity.Customer;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

public class CheckSoreHashSet<E> extends HashSet<E> {
    @Serial
    private static final long serialVersionUID = -23456691722L;
    private final long limit;

    public CheckSoreHashSet(){
        this.limit = 1L;
    }

    public CheckSoreHashSet(long limit){
        this.limit = limit;
    }


    @Override
    public boolean add(E object) {
        if (this.size() > limit) return false;
        return super.add(object);
    }
}
