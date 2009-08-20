package org.navalplanner.business.calendars.daos;

import java.util.List;

import org.navalplanner.business.calendars.entities.BaseCalendar;
import org.navalplanner.business.common.daos.IGenericDAO;

public interface IBaseCalendarDAO extends IGenericDAO<BaseCalendar, Long> {

    List<BaseCalendar> getBaseCalendars();

    List<BaseCalendar> findByParent(BaseCalendar baseCalendar);

    List<BaseCalendar> findLastVersions();

}
