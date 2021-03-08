package com.example.springbase.fwk.config

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * 21.01.05 | gomip | created
 */

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.example.springbase.repo.jpa"],
    entityManagerFactoryRef = "entityMangerFactory",
    transactionManagerRef = "transactionManger"
)
class JpaConfig(
    @Value("\${spring.jpa.hibernate.ddl-auto}") val ddlAuto: String,
    @Value("\${spring.jpa.hibernate.format_sql}") val formatSql: String
) {
    companion object {
        val log = LoggerFactory.getLogger(JpaConfig::class.java) as Logger
    }

    @Primary
    @Bean(name = ["entityManagerFactory"])
    fun entityMangerFactory(@Qualifier("dataSource")ds : DataSource) : EntityManagerFactory {
        log.info("===== EntityManagerFactory Start =====")
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setDatabase(Database.POSTGRESQL)

        val properties = hashMapOf<String, Any>()
        properties["hibernate.physical_naming_strategy"] = "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy"
        properties["hibernate.format_sql"] = formatSql
        properties["hibernate.ddl-auto"] = ddlAuto
        properties["use_sql_comments"] = true
        properties["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQL95Dialect"
        properties["hibernate.hbm2ddl.auto"] = "none"

        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.dataSource = ds
        entityManagerFactoryBean.setPackagesToScan("com.example.springbase.model.entity")
        entityManagerFactoryBean.jpaVendorAdapter = vendorAdapter
        entityManagerFactoryBean.setJpaPropertyMap(properties)
        entityManagerFactoryBean.afterPropertiesSet()

        log.info("=====   EntityManagerFactory End =====")
        return entityManagerFactoryBean.`object`!!
    }

    @Bean(name = ["transactionManager"])
    fun transactionManager(@Qualifier("entityManagerFactory") entityManagerFactory: EntityManagerFactory) : JpaTransactionManager {
        log.info("===== TransactionManger Start =====")
        val res = JpaTransactionManager(entityManagerFactory)
        log.info("=====   TransactionManger End =====")
        return res
    }
}