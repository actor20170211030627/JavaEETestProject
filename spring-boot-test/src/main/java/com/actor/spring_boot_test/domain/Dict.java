package com.actor.spring_boot_test.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * dict
 * @author 
 */
@Data
public class Dict implements Serializable {
    /**
     * 字典id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private Integer value;

    /**
     * 类型
     */
    private String type;

    private static final long serialVersionUID = 1L;
}