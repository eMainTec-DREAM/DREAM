package dream.work.rpt.mabmeqlist.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabmeqlist.dto.MaBmEqListDTO;

/**
 * 설비별고장분석DAO
 * @author  kim21017
 * @version $Id: MaBmEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaBmEqListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaBmEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmEqListDTO
     * @return List
     */
    public List findBmEqList(MaBmEqListDTO maBmEqListDTO, User user);
    
}