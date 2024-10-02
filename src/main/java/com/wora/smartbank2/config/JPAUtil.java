package com.wora.smartbank2.config;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private JPAUtil(){}

    public static EntityManagerFactory entityManagerFactory(){
        if (ENTITY_MANAGER_FACTORY == null){
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("simulation");
        }
        return ENTITY_MANAGER_FACTORY;
    }

    public static void closeEntityManagerFactory() {
        if (ENTITY_MANAGER_FACTORY != null) {
            ENTITY_MANAGER_FACTORY.close();
        }
    }

}
