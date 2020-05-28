package com.fd.useradmin.sentinel;

import feign.Contract;
import feign.MethodMetadata;

import java.util.List;

public class MySentinelContract implements Contract {
    @Override
    public List<MethodMetadata> parseAndValidateMetadata(Class<?> targetType) {
        return null;
    }
}
