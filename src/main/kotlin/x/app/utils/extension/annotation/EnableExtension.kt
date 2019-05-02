package x.app.utils.extension.annotation

import org.springframework.context.annotation.Import
import x.app.utils.extension.ExtensionAutoConfiguration

/**
 *   @Project: utils-extension
 *   @Package: x.app.utils.extension.annotation
 *   @Author:  Iamee
 *   @Date:    2019-05-02 23:37
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(ExtensionAutoConfiguration::class)
annotation class EnableExtension