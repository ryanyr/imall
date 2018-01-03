package com.czwx.imall.system.service;

import com.czwx.imall.system.domain.SysPerm;
import java.util.List;

public interface SysPermService {

    List<SysPerm> queryPermInfo(Long userId);

}
