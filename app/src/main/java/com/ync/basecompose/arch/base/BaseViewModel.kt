package com.ync.basecompose.arch.base

import androidx.lifecycle.ViewModel
import com.ync.basecompose.arch.extentions.LoadingAware
import com.ync.basecompose.arch.extentions.ViewErrorAware

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/2/22.
 */
open class BaseViewModel : ViewModel(), ViewErrorAware, LoadingAware