package dream.conn.rpt.month.log.service;

import java.util.List;

import common.bean.User;
import dream.conn.rpt.month.log.dto.ConnRptMonthLogListDTO;

/**
 * 월별접속현황 service
 * @author  sy.yang
 * @version $Id: ConnRptMonthLogListService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
 * @since   1.0
 */
public interface ConnRptMonthLogListService
{     
    /**
     *  grid find
     * @author  sy.yang
     * @version $Id: ConnRptMonthLogListService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @param connRptMonthLogListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findConnList(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user);    
    /**
     *  chart find
     * @author  sy.yang
     * @version $Id: ConnRptMonthLogListService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @param connRptMonthLogListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findConnChart(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user);    
    /**
     *  usr grid find
     * @author  syyang
     * @version $Id: $
     * @param connRptMonthLogListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findUsrList(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user);  
}
