package x.app.utils.extension.annotation

import org.springframework.stereotype.Component
import kotlin.reflect.KClass

/**
 *   @Project: utils-extension
 *   @Package: x.app.utils.extension.annotation
 *   @Author:  Iamee
 *   @Date:    2019-04-30 23:36
 */
@Component
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Extension(
        vararg val value: KClass<*>
)
