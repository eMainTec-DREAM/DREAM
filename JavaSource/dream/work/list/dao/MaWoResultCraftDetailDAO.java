package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과-작업자 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultCraftDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultCraftDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     */
    public MaWoResultCraftDetailDTO findDetail(String wkOrId, String woCraftId, String compNo);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultCraftDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultCraftDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);

    /**
     * 재고확인
     */
    public String validEmp(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser);
    
    /**
     * 작업시간 중복 확인
     */
    public List validTime(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
}