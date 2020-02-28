package com.conde.kun.core.error

import java.io.IOException

/**
 * Base exception for all the request
 */
open class BaseException(message: String? = null): IOException(message)

