package org.gdgcd.demo.service;

import java.util.List;

/**
 * Created by yqin on 10/28/15.
 */
public interface Page<T> {
    List<T> getContentList();
}
