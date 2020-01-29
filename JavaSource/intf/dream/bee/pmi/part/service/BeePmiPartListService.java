package intf.dream.bee.pmi.part.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.pmi.part.dto.BeePmiPartCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeePmiPartListService
{     
    public List findPmiPartList(BeePmiPartCommonDTO beePmiPartCommonDTO, Map map) throws Exception;
    public List findPmiPartCount(BeePmiPartCommonDTO beePmiPartCommonDTO, Map map) throws Exception;
    public List findPointList(Map map) throws Exception;
    public List findPointHistList(Map map) throws Exception;
    public List findWoPointCount(Map map) throws Exception;
    public List findStatus(Map map) throws Exception;

    public int insertPoint(List list) throws Exception;
    public int updatePoint(List list) throws Exception;
}
