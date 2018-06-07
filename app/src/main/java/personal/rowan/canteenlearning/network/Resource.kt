package personal.rowan.canteenlearning.network

/**
 * Created by Rowan Hall
 */
class Resource<T>(val progress: Boolean = false, private val resourceData: T? = null, private val error: Throwable? = null) {

    fun hasData(): Boolean {
        return resourceData != null
    }

    fun data(): T {
        return resourceData!!
    }

    fun hasError(): Boolean {
        return error != null
    }

    fun error(): Throwable {
        return error!!
    }

    companion object {
        fun <T> starting(): Resource<T> {
            return Resource(false, null, null)
        }

        fun <T> progress(resource: Resource<T>): Resource<T> {
            return Resource(true, resource.resourceData, resource.error)
        }

        fun <T> success(resourceData: T): Resource<T> {
            return Resource(false, resourceData, null)
        }

        fun <T> failure(resource: Resource<T>, e: Throwable): Resource<T> {
            return Resource(false, resource.resourceData, e)
        }
    }

}