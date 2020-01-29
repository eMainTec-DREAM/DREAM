package dream.part.rpt.mawopthist.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mawopthist.dto.MaWoPtHistCommonDTO;
import dream.part.rpt.mawopthist.dto.MaWoPtHistListDTO;
import dream.part.rpt.mawopthist.form.MaWoPtHistListForm;

/**
 * 부품사용이력 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaWoPtHistListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoPtHistCommonDTO
     * @return List
     */
    public List findList(MaWoPtHistCommonDTO maWoPtHistCommonDTO, User loginUser);
    
    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return
     */
    public int deleteParts(MaWoPtHistListDTO maWoPtHistListDTO, User loginUser);
    
    /**
     * 자재 거래처 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoPtHistListDTO
     * @param loginUser
     * @return
     */
    public int deletePtVendor(MaWoPtHistListDTO maWoPtHistListDTO, User loginUser);
    
    /**
     * 자재 첨부파일 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoPtHistListDTO
     * @param loginUser
     * @return
     */
    public int deleteObjDoc(MaWoPtHistListDTO maWoPtHistListDTO, User loginUser);

    /**
     * req hdr create
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param partId
     * @param user
     * @return
     */
    public int reqHdrPtBuy(MaWoPtHistListForm maWoPtHistListForm, String partId, User user);

    /**
     * req dtl create
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param partId
     * @param user
     * @return
     */
    public int reqDtlPtBuy(MaWoPtHistListForm maWoPtHistListForm, String partId, User user, String partGrade);
    

    public String findTotalCount(MaWoPtHistCommonDTO maWoPtHistCommonDTO, User user);
}