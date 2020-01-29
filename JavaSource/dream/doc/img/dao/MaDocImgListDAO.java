package dream.doc.img.dao;

import java.util.List;

import common.bean.User;
import dream.doc.img.dto.MaDocImgCommonDTO;

/**
 * 첨부파일 - 목록 dao
 * @author  jung7126
 * @version $Id: MaDocImgListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface MaDocImgListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaDocImgListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocImgCommonDTO
     * @return List
     */
    public List findList(MaDocImgCommonDTO maDocImgCommonDTO, User user);
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaDocImgListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteDoc(String id);

    public int deleteDocData(String id);

    public String findTotalCount(MaDocImgCommonDTO maDocImgCommonDTO, User user);
}