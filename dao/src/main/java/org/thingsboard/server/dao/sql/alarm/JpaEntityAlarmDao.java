/**
 * Copyright © 2016-2024 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.dao.sql.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.alarm.EntityAlarm;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.DaoUtil;
import org.thingsboard.server.dao.TenantEntityDao;
import org.thingsboard.server.dao.model.sql.EntityAlarmEntity;
import org.thingsboard.server.dao.util.SqlDao;

@Component
@SqlDao
public class JpaEntityAlarmDao implements TenantEntityDao<EntityAlarm> {

    @Autowired
    private EntityAlarmRepository entityAlarmRepository;

    @Override
    public PageData<EntityAlarm> findAllByTenantId(TenantId tenantId, PageLink pageLink) {
        return DaoUtil.toPageData(entityAlarmRepository.findByTenantId(tenantId.getId(), DaoUtil.toPageable(pageLink, "entityId", "alarmId")));
    }

    @Override
    public EntityAlarm save(TenantId tenantId, EntityAlarm entityAlarm) {
        return entityAlarmRepository.save(new EntityAlarmEntity(entityAlarm)).toData();
    }

}