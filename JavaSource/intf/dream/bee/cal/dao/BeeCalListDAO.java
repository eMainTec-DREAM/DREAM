package intf.dream.bee.cal.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.cal.dto.BeeCalCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeCalListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findCalList(BeeCalCommonDTO beeCalCommonDTO, Map map) throws Exception;
    public List findCalCount(BeeCalCommonDTO beeCalCommonDTO, Map map) throws Exception;
    public int deleteCal(Map map) throws Exception;
    public int insertCal(Map map) throws Exception;
    public int updateCal(Map map) throws Exception;
    
    public int deleteWoCalib(Map map) throws Exception;
    public int insertWoCalib(Map map) throws Exception;
    public int updateWoCalib(Map map) throws Exception;

    public List findCalEtcValueList(Map map) throws Exception;
    public int deleteCalEtcValueList(Map map) throws Exception;
    public int insertCalEtcValueList(Map map) throws Exception;
    public int updateCalEtcValueList(Map map) throws Exception;

    public List findCalStdEqList(Map map) throws Exception;
    public int deleteCalStdEqList(Map map) throws Exception;
    public int insertCalStdEqList(Map map) throws Exception;
    public int updateCalStdEqList(Map map) throws Exception;
    public int updateCalStdEqNextDateList(Map map) throws Exception;

    public List findCalGnlValueList(Map map) throws Exception;
    public int deleteCalGnlValueList(Map map) throws Exception;
    public int insertCalGnlValueList(Map map) throws Exception;
    public int updateCalGnlValueList(Map map) throws Exception;

    public List findCalSclValueList(Map map) throws Exception;
    public int deleteCalSclValueList(Map map) throws Exception;
    public int insertCalSclValueList(Map map) throws Exception;
    public int updateCalSclValueList(Map map) throws Exception;
}