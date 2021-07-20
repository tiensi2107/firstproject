package com.ntsi.gpxgateway.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ntsi.gpxgateway.util.TimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class GPXGson {
    private static final Logger logger = LogManager.getLogger(GPXGson.class);

    public enum GsonAdapter{
        PROXY,
        SUPPRESS,
        GENERIC_ENUM,
        ISO_8601_NO_MILLI,
        SERIALIZE_NULL,
    }
    private static ConcurrentHashMap<Set<GsonAdapter>, Gson> gsonMap = new ConcurrentHashMap<>();
    
    public static Gson getGson(GsonAdapter... gsonAdapters) {
        Set<GsonAdapter> adapterSet = Set.of(gsonAdapters);
        if (gsonMap.containsKey(adapterSet)) {
            return gsonMap.get(adapterSet);
        } else {
            GsonBuilder builder = new GsonBuilder();
            for (GsonAdapter adapter : adapterSet) {
                appenBuilder(adapter,builder);
            }
            Gson gson = builder.create();
            gsonMap.put(adapterSet,gson);
            logger.info("Gson map size {}",gsonMap.size());
            return gson;
        }

    }
    public static GsonBuilder getBuilder(GsonAdapter...gsonAdapters){
            GsonBuilder builder = new GsonBuilder();
            Set<GsonAdapter> adapterSet = Set.of(gsonAdapters);
            for(GsonAdapter adapter : adapterSet){
                appenBuilder(adapter,builder);
            }
            return builder;
        }
    public static void appenBuilder(GsonAdapter adapter, GsonBuilder builder){
        switch (adapter){
            case PROXY:
                builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
                break;
            case SUPPRESS:
                builder.setExclusionStrategies(new IncludeWithoutSuppressStrategy());
                break;
            case GENERIC_ENUM:
                builder.registerTypeAdapterFactory(new GenericEnumAdapterFactory());
                break;
            case ISO_8601_NO_MILLI:
                builder.registerTypeAdapter(LocalDateTime.class,
                                            new GsonZdtToLdtUTCDeserializer(TimeUtil.CF.ISO_8601_NO_MILLI.getParser()))
                                            .registerTypeAdapter(LocalDateTime.class,
                                                    new GsonZdtToLdtUTCDeserializer(TimeUtil.CF.ISO_8601_NO_MILLI.getPrinter()));
                break;
            case SERIALIZE_NULL:
                builder.serializeNulls();
                break;
            default:
                break;
        }
    }
}
