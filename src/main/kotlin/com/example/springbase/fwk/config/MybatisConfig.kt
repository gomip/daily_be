package com.example.springbase.fwk.config

import ch.qos.logback.classic.Logger
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.mybatis.spring.annotation.MapperScan
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import java.io.IOException
import javax.sql.DataSource

/**
 * 21.01.05 | gomip | created
 */

@Configuration
@MapperScan(
        basePackages = ["com.example.springbase.repo.mybatis"],
        sqlSessionFactoryRef = "sqlSessionFactory",
        sqlSessionTemplateRef = "sqlSessionTemplate"
)
class MybatisConfig {
    companion object {
        private val log = LoggerFactory.getLogger(MybatisConfig::class.java) as Logger
    }

    /*
    VFS = Virtual File System
    실제 파일 시스템 위 , 응용 프로그램 계층에 추상화된 가상 파일시스템
     */
    @Bean(name = ["sqlSessionFactory"])
    @Throws(IOException::class)
    fun sqlSessionFactory(@Qualifier("dataSource") ds: DataSource) : SqlSessionFactory {
        log.info("===== MybatisConfig Start =====")
        val pathResolver = PathMatchingResourcePatternResolver()
        val sqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactoryBean.setDataSource(ds)
        sqlSessionFactoryBean.setConfigLocation(pathResolver.getResource("classpath:META-INF/mybatis/mybatis-config.xml"))
        sqlSessionFactoryBean.setMapperLocations(*pathResolver.getResources("classpath:META-INF/mybatis/mapper/**/*.xml"))
        sqlSessionFactoryBean.vfs = SpringBootVFS::class.java
        log.info("=====   MybatisConfig End =====")
        return sqlSessionFactoryBean.`object`!!
    }

    @Bean(name = ["sqlSessionTemplate"])
    fun sqlSessionTemplate(@Qualifier("sqlSessionFactory") sqlSessionFactory: SqlSessionFactory) : SqlSessionTemplate {
        log.info("===== sqlSessionTemplate Start =====")
        val res = SqlSessionTemplate(sqlSessionFactory)
        log.info("=====   sqlSessionTemplate End =====")
        return res
    }
}