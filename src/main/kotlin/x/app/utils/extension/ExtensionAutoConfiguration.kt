package x.app.utils.extension

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import x.app.utils.extension.annotation.Extension
import kotlin.reflect.KClass

/**
 *   @Project: utils-extension
 *   @Package: x.app.utils.extension
 *   @Author:  Iamee
 *   @Date:    2019-05-02 23:41
 */
class ExtensionAutoConfiguration {

    @Autowired
    lateinit var context: ApplicationContext

    @Bean
    fun executor(): IExtensionExecutor {
        return object : IExtensionExecutor {
            override val maps: HashMap<KClass<*>, Any> = HashMap()
        }.apply {
            context.getBeansWithAnnotation(Extension::class.java).forEach { (_, extension) ->
                this.add(extension = extension)
            }
        }
    }

}