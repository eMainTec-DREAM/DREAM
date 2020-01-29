package intf.dream.bee.cal.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.cal.dto.BeeCalCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeCalListService
{     
    public List findCalList(BeeCalCommonDTO beeCalCommonDTO, Map map) throws Exception;
    public List findCalCount(BeeCalCommonDTO beeCalCommonDTO, Map map) throws Exception;
    public int deleteCal(List list) throws Exception;
    public int insertCal(List list) throws Exception;
    public int updateCal(List list) throws Exception;
    
    public List findCalEtcValueList(Map map) throws Exception;
    public int deleteCalEtcValueList(List list) throws Exception;
    public int insertCalEtcValueList(List list) throws Exception;
    public int updateCalEtcValueList(List list) throws Exception;
    
    public List findCalStdEqList(Map map) throws Exception;
    public int deleteCalStdEqList(List list) throws Exception;
    public int insertCalStdEqList(List list) throws Exception;
    public int updateCalStdEqList(List list) throws Exception;
    
    public List findCalGnlValueList(Map map) throws Exception;
    public int deleteCalGnlValueList(List list) throws Exception;
    public int insertCalGnlValueList(List list) throws Exception;
    public int updateCalGnlValueList(List list) throws Exception;
    
    public List findCalSclValueList(Map map) throws Exception;
    public int deleteCalSclValueList(List list) throws Exception;
    public int insertCalSclValueList(List list) throws Exception;
    public int updateCalSclValueList(List list) throws Exception;
}
