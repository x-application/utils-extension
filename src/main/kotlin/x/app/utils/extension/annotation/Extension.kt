package x.app.utils.extension.annotation

import kotlin.reflect.KClass

/**
 *   @Project: service-account
 *   @Package: x.app.service.account.extension.annotation
 *   @Author:  Iamee
 *   @Date:    2019-04-30 23:36
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Extension(
        vararg val value: KClass<*>
)
