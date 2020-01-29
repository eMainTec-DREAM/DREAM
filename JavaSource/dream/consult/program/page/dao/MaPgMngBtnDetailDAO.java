package dream.consult.program.page.dao;

import dream.consult.program.page.dto.MaPgMngBtnDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 버튼 상세 dao
 * @author  kim21017
 * @version $Id: MaPgMngBtnDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngBtnDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngBtnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgBtnId
     * @return
     */
    public MaPgMngBtnDetailDTO findDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngBtnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngBtnDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int updateDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngBtnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngBtnDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int insertDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO);
}