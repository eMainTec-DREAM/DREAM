package dream.work.rpt.madeptwo.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.madeptwo.dto.MaDeptWoListDTO;

/**
 * 부서별작업분석DAO
 * @author  kim21017
 * @version $Id: MaDeptWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaDeptWoListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaDeptWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maDeptWoListDTO
     * @return List
     */
    public List findDeptWoList(MaDeptWoListDTO maDeptWoListDTO, User user);
    

    public List findCntChart(MaDeptWoListDTO maDeptWoListDTO);
    
    public List findTimeChart(MaDeptWoListDTO maDeptWoListDTO);
}