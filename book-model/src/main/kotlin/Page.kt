package org.gdgcd.demo.service

public trait Page<T> {
    public fun getContentList(): List<T>
}
