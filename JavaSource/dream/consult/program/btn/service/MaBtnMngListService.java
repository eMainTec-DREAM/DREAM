package dream.consult.program.btn.service;

import java.util.List;

import dream.consult.program.btn.dto.MaBtnMngCommonDTO;

/**
 * 버튼 - 목록 service
 * @author  kim21017
 * @version $Id: MaBtnMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaBtnMngListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaBtnMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maBtnMngCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findBtnList(MaBtnMngCommonDTO maBtnMngCommonDTO);    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaBtnMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteBtn(String[] deleteRows) throws Exception;
}
