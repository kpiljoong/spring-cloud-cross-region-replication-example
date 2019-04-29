package com.piljoong.cacheflow.producer.cache;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(XMemcachedProperties.class)
public class XmemcachedAutoConfiguration {

    @Autowired
    private XMemcachedProperties properties;

    public MemcachedClient createClient() throws IOException {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(properties.getServers()));

        builder.setConnectionPoolSize(properties.getPoolSize());
        builder.setFailureMode(true);
        builder.setSessionLocator(new KetamaMemcachedSessionLocator());
        builder.setCommandFactory(new BinaryCommandFactory());
        builder.setTranscoder(new SerializingTranscoder());
        builder.getTranscoder().setCompressionThreshold(1024);

        MemcachedClient client = builder.build();
        client.setConnectTimeout(properties.getConnectionTimeout());
        client.setOpTimeout(properties.getOpTimeout());
        client.setPrimitiveAsString(true);
        return client;
    }

    @Bean
    public XMemcachedManager createManager() throws IOException {
        return new XMemcachedManager(createClient());
    }
}
