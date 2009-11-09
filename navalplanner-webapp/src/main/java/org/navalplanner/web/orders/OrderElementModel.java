/*
 * This file is part of ###PROJECT_NAME###
 *
 * Copyright (C) 2009 Fundación para o Fomento da Calidade Industrial e
 *                    Desenvolvemento Tecnolóxico de Galicia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.navalplanner.web.orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.navalplanner.business.orders.daos.IOrderElementDAO;
import org.navalplanner.business.orders.entities.HoursGroup;
import org.navalplanner.business.orders.entities.OrderElement;
import org.navalplanner.business.resources.daos.ICriterionTypeDAO;
import org.navalplanner.business.resources.entities.Criterion;
import org.navalplanner.business.resources.entities.CriterionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderElementModel implements IOrderElementModel {

    private OrderElement orderElement;

    private OrderModel order;

    @Autowired
    private IOrderElementDAO orderElementDAO;

    @Autowired
    private ICriterionTypeDAO criterionTypeDao;

    @Autowired
    private ICriterionTypeDAO criterionTypeDAO;

    private Map<String, CriterionType> mapCriterionTypes = new HashMap<String, CriterionType>();

    @Override
    public OrderElement getOrderElement() {
        return orderElement;
    }

    @Override
    public IOrderModel getOrderModel() {
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public void setCurrent(OrderElement orderElement, OrderModel order) {
        orderElementDAO.reattach(orderElement);

        for (HoursGroup hoursGroup : orderElement.getHoursGroups()) {
            hoursGroup.getCriterionRequirements().size();
            hoursGroup.getCriterions().size();
        }

        this.orderElement = orderElement;
        this.order = order;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CriterionType> getCriterionTypes() {
        List<CriterionType> result = new ArrayList<CriterionType>();

        if (mapCriterionTypes.isEmpty()) {
            loadCriterionTypes();
        }
        result.addAll(mapCriterionTypes.values());

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public CriterionType getCriterionTypeByName(String name) {
        if (mapCriterionTypes.isEmpty()) {
            loadCriterionTypes();
        }

        return mapCriterionTypes.get(name);
    }

    private void loadCriterionTypes() {
        for (CriterionType criterionType : criterionTypeDAO.getCriterionTypes()) {
            criterionType.getCriterions().size();
            mapCriterionTypes.put(criterionType.getName(), criterionType);
        }
    }

    @Override
    public List<Criterion> getCriterionsFor(CriterionType type) {
        return (List<Criterion>) order.getCriterionsFor(type);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Criterion> getCriterionsHoursGroup(HoursGroup hoursGroup) {
        return hoursGroup.getCriterions();
    }

    @Override
    @Transactional(readOnly = true)
    public CriterionType getCriterionType(Criterion criterion) {
        CriterionType criterionType = criterion.getType();
        criterionTypeDao.reattach(criterionType);
        criterionType.getName();
        return criterionType;
    }

    @Override
    public void confirmCancel() {

    }

    @Override
    @Transactional
    public void confirmSave() {
        orderElementDAO.save(orderElement);
    }

}
