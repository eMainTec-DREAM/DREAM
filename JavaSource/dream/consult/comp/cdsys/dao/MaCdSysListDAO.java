package dream.consult.comp.cdsys.dao;

import java.util.List;

import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 - 목록 dao
 * @author  kim21017
 * @version $Id: MaCdSysListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaCdSysListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaCdSysListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCommonDTO
     * @return List
     */
    public List findListTypeList(MaCdSysCommonDTO maCdSysCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaCdSysListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteListType(String id);
}