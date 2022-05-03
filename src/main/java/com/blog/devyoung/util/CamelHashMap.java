package com.blog.devyoung.util;

import java.util.HashMap;

import org.springframework.jdbc.support.JdbcUtils;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class CamelHashMap extends HashMap<String, Object> {

	@Override
    public Object put(String key, Object value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(key), value);
    }
	
}
