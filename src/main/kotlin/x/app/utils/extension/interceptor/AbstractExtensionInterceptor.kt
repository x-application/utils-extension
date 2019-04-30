package x.app.utils.extension.interceptor

import org.axonframework.commandhandling.CommandMessage
import org.axonframework.messaging.InterceptorChain
import org.axonframework.messaging.MessageHandlerInterceptor
import org.axonframework.messaging.unitofwork.UnitOfWork
import org.axonframework.modelling.command.Repository
import x.app.common.AbstractCommand
import x.app.common.ICommandExtensionPoint
import x.app.utils.extension.IExtensionExecutor
import x.app.utils.extension.execute

/**
 *   @Project: utils-extension
 *   @Package: x.app.utils.extension.interceptor
 *   @Author:  Iamee
 *   @Date:    2019-05-01 0:03
 */
abstract class AbstractExtensionInterceptor<T>(
        private val repository: Repository<T>,
        private val executor: IExtensionExecutor
) : MessageHandlerInterceptor<CommandMessage<Any>> {

    override fun handle(unitOfWork: UnitOfWork<out CommandMessage<Any>>, interceptorChain: InterceptorChain): Any {
        val command = unitOfWork.message.payload
        var point: ICommandExtensionPoint<AbstractCommand<*>, T>? = null
        if (command is AbstractCommand<*>) {
            executor.execute<ICommandExtensionPoint<AbstractCommand<*>, T>>(command = command::class) {
                point = it
            }
        }
        point?.run {
            this.before(repository = repository, command = command as AbstractCommand<*>)
        }
        val result = interceptorChain.proceed()
        point?.run {
            this.after(repository = repository, command = command as AbstractCommand<*>)
        }
        return result
    }

}