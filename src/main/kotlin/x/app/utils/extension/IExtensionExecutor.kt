package x.app.utils.extension

import x.app.utils.extension.annotation.Extension
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 *   @Project: utils-extension
 *   @Package: x.app.utils.extension
 *   @Author:  Iamee
 *   @Date:    2019-04-30 22:25
 */
interface IExtensionExecutor {

    val maps: HashMap<KClass<*>, Any>

    fun add(clz: KClass<*>, extension: Any) {
        if (!maps.containsKey(clz)) maps[clz] = extension
    }

    fun add(extension: Any) {
        extension::class.annotations.forEach {
            if (it.annotationClass.isSubclassOf(Extension::class)) {
                (it as Extension).value.forEach { clz ->
                    this.add(clz, extension)
                }
            }
        }
    }

}

inline fun <reified R> IExtensionExecutor.execute(command: KClass<*>, callback: (extensionPoint: R) -> Unit) {
    maps[command]?.run {
        if (this::class.isSubclassOf(R::class)) callback.invoke(this as @kotlin.ParameterName(name = "extensionPoint") R)
    }
}