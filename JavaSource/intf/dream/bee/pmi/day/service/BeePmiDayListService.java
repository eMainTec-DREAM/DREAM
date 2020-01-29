package intf.dream.bee.pmi.day.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.pmi.day.dto.BeePmiDayCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeePmiDayListService
{     
    public List findPmiDayList(BeePmiDayCommonDTO beePmiDayCommonDTO, Map map) throws Exception;
    public List findPmiDayCount(BeePmiDayCommonDTO beePmiDayCommonDTO, Map map) throws Exception;
    public List findPointList(Map map) throws Exception;
    public List findPointHistList(Map map) throws Exception;
    public List findWoPointCount(Map map) throws Exception;
    public List findStatus(Map map) throws Exception;

    public int insertPoint(List list) throws Exception;
    public int updatePoint(List list) throws Exception;
}
