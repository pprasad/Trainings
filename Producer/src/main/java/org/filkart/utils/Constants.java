package org.filkart.utils;

public abstract class Constants {

    public static String TOPIC_NAME = "FlipkartOrdersTopic";
    public static String CONFIG_PATH = "Producer/src/main/resources/producer.properties";
    public static String BOOK_SERIALIZER = "org.filkart.serializers.BookSerializer";
    public static String MOBILE_SERIALIZER = "org.filkart.serializers.MobileSerializer";
    public static String CAR_SERIALIZER = "org.filkart.serializers.CarSerializer";
    public static String BOOK_DESERIALIZER = "org.filkart.serializers.BookDeserializer";
    public static String MOBILE_DESERIALIZER = "org.filkart.serializers.CarDeserializer";
    public static String CAR_DESERIALIZER = "org.filkart.serializers.MobileDeserializer";

    public enum ORDER_TYPE {
        BOOK("book.purchase"),MOBILE("mobile.purchase"),CAR("car.purchase");
        String value;
        ORDER_TYPE(String value) {
            this.value =value;
        }
        public String getValue() {
            return value;
        }
    }


}
