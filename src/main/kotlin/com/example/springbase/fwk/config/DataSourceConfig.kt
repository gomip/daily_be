package com.example.springbase.fwk.config

import ch.qos.logback.classic.Logger
import com.zaxxer.hikari.HikariDataSource
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

/**
 * 21.01.05 | gomip | created
 */

@Configuration
class DataSourceConfig {
    companion object {
        val log = LoggerFactory.getLogger(DataSourceConfig::class.java) as Logger
    }

    @Primary
    @Bean(name = ["dataSource"])
    fun dataSource(
            @Value("\${spring.datasource.url}") url: String,
            @Value("\${spring.datasource.username}") username: String,
            @Value("\${spring.datasource.password}") password: String,
            @Value("\${spring.datasource.driver-class-name}") driverClassName: String,
    ) : DataSource {
        log.info("DataSource Config Start")
        val hikariDs = HikariDataSource()
        hikariDs.jdbcUrl = url
        hikariDs.username = username
        hikariDs.password = password
        hikariDs.driverClassName = driverClassName
        log.info("DataSource Config End")
        return hikariDs
    }

}