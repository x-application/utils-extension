package x.app.utils.extension.point

import org.axonframework.modelling.command.Repository
import x.app.common.AbstractCommand

/**
 *   @Project: common-base
 *   @Package: x.app.common
 *   @Author:  Iamee
 *   @Date:    2019-04-30 23:54
 */
interface ICommandExtensionPoint<C : AbstractCommand<*>, T> {

    fun before(repository: Repository<T>, command: C) {}

    fun after(repository: Repository<T>, command: C) {}
    //MessageHandlerInterceptor
}