package x.app.utils.extension

import org.reflections.Reflections
import x.app.utils.extension.annotation.Extension
import kotlin.reflect.KClass

/**
 *   @Project: service-account
 *   @Package: x.app.service.account.extension
 *   @Author:  Iamee
 *   @Date:    2019-04-30 23:37
 */
class DefaultExtensionExecutor : IExtensionExecutor {

    override val maps: HashMap<KClass<*>, Any> = HashMap()

    companion object {

        fun createExecutor(basePackage: String): DefaultExtensionExecutor {
            val reflections = Reflections(basePackage)
            val executor = DefaultExtensionExecutor()
            val cls = reflections.getTypesAnnotatedWith(Extension::class.java)
            cls.forEach {
                executor.add(it.newInstance())
            }
            return executor
        }

    }

}
