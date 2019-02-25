package com.example.backservice.domain;

import java.util.Arrays;

public enum  EntityCodEnum {
    SALEMAN("001") ,
    CUSTOMER("002") ,
    SALE("003") ;

    EntityCodEnum(String id) {
        this.id = id;
    }

    private String id;

    public String getId() {
        return id;
    }

    public static EntityCodEnum valueEnum(String id){
        return Arrays.stream(EntityCodEnum.values()).filter(
                e -> e.getId().equals(id))
                .findFirst()
                .get();
    }
}
