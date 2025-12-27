package constant;

import utils.ConfigReader;

public class Endpoints
{
    //Read properties file
    public static final String url= ConfigReader.PropertyReaderMethod("src/test/resources/config/Config_qa.properties","baseUrl");
    public static final String path_url_Login_API=ConfigReader.PropertyReaderMethod("src/test/resources/config/Config_qa.properties","LoginAPIURL");
    public static final String path_url_AddNewProduct_API=ConfigReader.PropertyReaderMethod("src/test/resources/config/Config_qa.properties","AddNewProductAPIURL");
    public static final String path_url_Update_product_API=ConfigReader.PropertyReaderMethod("src/test/resources/config/Config_qa.properties","UpdateproductAPI");
    public static final String path_url_Get_single_product_API =ConfigReader.PropertyReaderMethod("src/test/resources/config/Config_qa.properties","GetsingleproductAPI");
    public static final String path_url_Get_all_productsAPI_API=ConfigReader.PropertyReaderMethod("src/test/resources/config/Config_qa.properties","GetallproductsAPI");
    public static final String path_url_Delete_product_API =ConfigReader.PropertyReaderMethod("src/test/resources/config/Config_qa.properties","DeleteproductAPI");
}