package com.example.springbase

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import kotlin.system.measureTimeMillis

/**
 * 2021.01.03 | gomip | created
 */

@SpringBootApplication(
    scanBasePackages = ["com.example.springbase"]
)

class SpringBaseApplication{
    companion object {
        private val log = LoggerFactory.getLogger(SpringBaseApplication::class.java) as Logger

        @JvmStatic
        fun main(args: Array<String>) {
            try{
                val elapsed = measureTimeMillis {
                    log.info("=============           AppMain START =============")
                    // main start ------------------------------------------------------------------------------------------
                    val app = runApplication<SpringBaseApplication>(*args) {
                        setBannerMode(Banner.Mode.OFF)
                    }

                    // profile check ---------------------------------------------------------------------------------------
                    log.info("=============     Check Profile START =============")
                    val env = app.getBean("environment") as Environment
                    env.activeProfiles.toList().forEach {log.info(it)}
                    log.info("=============       Check Profile End ============= : activate profile count: ${env.activeProfiles.count()}")

                    // loaded bean -----------------------------------------------------------------------------------------
                    log.info("============= Check Loaded Bean START =============")
                    log.info("Loaded Bean : ${app.beanDefinitionNames.toList().sorted().size}")
                    log.info("=============   Check Loaded Bean END =============")
                }
                log.info("=============             AppMain End ============= : $elapsed ms")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
