package com.conde.kun.core.error

import com.conde.kun.core.domain.BaseError

class BusinessErrorException(val error: BaseError) : BaseException()