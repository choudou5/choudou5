/*
 * Copyright 1999-2101 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.choudou5.rpc.dubbo.service;


import com.choudou5.rpc.dubbo.domain.OverrideEntity;

import java.util.List;


/**
 * @author tony.chenl
 */
public interface OverrideService {
    
    List<OverrideEntity> findByService(String service);
    
    List<OverrideEntity> findByAddress(String address);
    
    List<OverrideEntity> findByServiceAndAddress(String service, String address);
    
    List<OverrideEntity> findByApplication(String application);

    List<OverrideEntity> findByServiceAndApplication(String service, String application);
    
    List<OverrideEntity> findAll();

    OverrideEntity findById(Long id);
    
}
