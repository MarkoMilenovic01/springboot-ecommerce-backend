package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.Country;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.aspectj.lang.reflect.DeclareAnnotation.Kind.Type;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    @Autowired
    private EntityManager entityManager;


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.PUT};

        config.getExposureConfiguration().forDomainType(Product.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
        config.getExposureConfiguration().forDomainType(ProductCategory.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
        config.getExposureConfiguration().forDomainType(Country.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
        config.getExposureConfiguration().forDomainType(State.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));

        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(jakarta.persistence.metamodel.Type::getJavaType).toArray(Class[]::new));



    }
}
