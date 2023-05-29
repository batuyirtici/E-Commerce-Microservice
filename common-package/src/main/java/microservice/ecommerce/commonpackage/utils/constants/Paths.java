package microservice.ecommerce.commonpackage.utils.constants;

import org.apache.kafka.common.protocol.types.Field;

public class Paths {

    public static final String ConfigurationBasePackage = "microservice.ecommerce.commonpackage.configuration";

    public static class Stock
    { public static final String ServiceBasePackage = "microservice.ecommerce.stockservice"; }

    public static class Filter
    { public static final String ServiceBasePackage = "microservice.ecommerce.filterservice"; }

    public static class Sale
    { public static final String ServiceBasePackage = "microservice.ecommerce.saleservice"; }
}