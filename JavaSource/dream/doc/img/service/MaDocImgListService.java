package dream.doc.img.service;

import java.util.List;

import common.bean.User;
import dream.doc.img.dto.MaDocImgCommonDTO;


/**
 * 사진파일 - 목록 service
 * @author  jung7126
 * @version $Id: MaDocImgListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaDocImgListService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: MaDocImgListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maDocImgCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaDocImgCommonDTO maDocImgCommonDTO, User user);    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaDocImgListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param maDocImgCommonDTO 
     * @param user 
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, MaDocImgCommonDTO maDocImgCommonDTO, User user) throws Exception;

    public String findTotalCount(MaDocImgCommonDTO maDocImgCommonDTO, User user);
    
}
