package dream.conn.rpt.month.log.dao;

import java.util.List;

import common.bean.User;
import dream.conn.rpt.month.log.dto.ConnRptMonthLogListDTO;

/**
 * 접속현황 DAO
 * @author  sy.yang
 * @version $Id:  $
 * @since   1.0
 */
public interface ConnRptMonthLogListDAO
{
    /**
     * grid find
     * @author  sy.yang
     * @version $Id:  $
     * @since   1.0
     * 
     * @param connRptMonthLogListDTO
     * @return List
     */
    public List findConnList(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user);
    
    /**
     * chart find
     * @author  sy.yang
     * @version $Id: $
     * @since   1.0
     * 
     * @param connRptMonthLogListDTO
     * @return List
     */
    public List findConnChart(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user);

    /**
     * grid find
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param connRptMonthLogListDTO
     * @return List
     */
    public List findUsrList(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user);
}