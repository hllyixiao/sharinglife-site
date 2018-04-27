package cn.com.sharinglife.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author hell
 * @date 2018/4/27
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "cn.com.sharinglife.elasticsearch.repository")
@ConditionalOnProperty(name = "sl.elasticsearch.enable", havingValue = "true")
public class ESConfiguration {

    /**
     * es集群地址
     */
    @Value("${sl.elasticsearch.ip}")
    private String hostName;
    /**
     * 端口
     */
    @Value("${sl.elasticsearch.port}")
    private String port;
    /**
     * 集群名称
     */
    @Value("${sl.elasticsearch.cluster.name}")
    private String clusterName;

    /**
     * 连接池
     */
    @Value("${sl.elasticsearch.pool}")
    private String poolSize;


    @Primary
    @Bean(name = "primaryElasticSearChClient")
    public Client primaryElasticSearChClient() throws Exception {
        // 配置信息、增加线程池个数，暂时设为5
        Settings esSetting = Settings.builder()
                .put("cluster.name", clusterName)
                .build();

        return TransportClient.builder()
                .settings(esSetting)
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port)));

    }

    @Primary
    @Bean(name = "primaryElasticSearchTemplate")
    public ElasticsearchOperations primaryElasticSearchTemplate(
            @Qualifier("primaryElasticSearChClient") Client primaryElasticSearChClient) throws Exception {
        return new ElasticsearchTemplate(primaryElasticSearChClient);
    }
}
