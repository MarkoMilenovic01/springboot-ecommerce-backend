package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.aspectj.lang.reflect.DeclareAnnotation.Kind.Type;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    @Autowired
    private EntityManager entityManager;


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.PUT};

        config.getExposureConfiguration().forDomainType(Product.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
        config.getExposureConfiguration().forDomainType(ProductCategory.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
        config.getExposureConfiguration().forDomainType(Country.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
        config.getExposureConfiguration().forDomainType(State.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
        config.getExposureConfiguration().forDomainType(Order.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));

        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(jakarta.persistence.metamodel.Type::getJavaType).toArray(Class[]::new));

        cors.addMapping((config.getBasePath() + "/**")).allowedOrigins(theAllowedOrigins);

    }
}
