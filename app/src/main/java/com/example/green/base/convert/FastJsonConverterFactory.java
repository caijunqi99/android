package com.example.green.base.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class FastJsonConverterFactory extends Converter.Factory {
    private ParserConfig mParserConfig = ParserConfig.getGlobalInstance();
    private int featureValues = JSON.DEFAULT_PARSER_FEATURE;
    private SerializeConfig serializeConfig;
    private SerializerFeature[] serializerFeatures = {SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.SkipTransientField,
            SerializerFeature.DisableCircularReferenceDetect};

    public static FastJsonConverterFactory create() {
        return new FastJsonConverterFactory();
    }

    private FastJsonConverterFactory() {
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new FastJsonResponseBodyConverter<>(type, mParserConfig, featureValues);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastJsonRequestBodyConverter<>(serializeConfig, serializerFeatures);
    }

    final class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private SerializeConfig serializeConfig;
        private SerializerFeature[] serializerFeatures;

        FastJsonRequestBodyConverter(SerializeConfig config, SerializerFeature... features) {
            serializeConfig = config;
            serializerFeatures = features;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            byte[] content;
            if (serializeConfig != null) {
                if (serializerFeatures != null) {
                    content = JSON.toJSONBytes(value, serializeConfig, serializerFeatures);
                } else {
                    content = JSON.toJSONBytes(value, serializeConfig);
                }
            } else {
                if (serializerFeatures != null) {
                    content = JSON.toJSONBytes(value, serializerFeatures);
                } else {
                    content = JSON.toJSONBytes(value);
                }
            }
            return RequestBody.create(MEDIA_TYPE, content);
        }
    }

    class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

        private final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];

        private Type mType;

        private ParserConfig config;
        private int featureValues;
        private Feature[] features;

        FastJsonResponseBodyConverter(Type type, ParserConfig config, int featureValues,
                                      Feature... features) {
            mType = type;
            this.config = config;
            this.featureValues = featureValues;
            this.features = features;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T convert(ResponseBody value) throws IOException {
            try {
                if (mType == String.class) {
                    return (T) value.string();
                }
                return JSON.parseObject(value.string(), mType, config, featureValues,
                        features != null ? features : EMPTY_SERIALIZER_FEATURES);

            } finally {
                value.close();
            }
        }
    }
}
