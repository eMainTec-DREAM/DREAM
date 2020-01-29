package dream.consult.program.btn.dao;

import java.util.List;

import dream.consult.program.btn.dto.MaBtnMngCommonDTO;

/**
 * 버튼 - 목록 dao
 * @author  kim21017
 * @version $Id: MaBtnMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaBtnMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaBtnMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngCommonDTO
     * @return List
     */
    public List findBtnList(MaBtnMngCommonDTO maBtnMngCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaBtnMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteBtn(String id);
}