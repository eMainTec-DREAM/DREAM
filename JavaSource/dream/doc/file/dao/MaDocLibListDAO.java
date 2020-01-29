package dream.doc.file.dao;

import java.util.List;

import common.bean.User;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * 첨부파일 - 목록 dao
 * @author  jung7126
 * @version $Id: MaDocLibListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface MaDocLibListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaDocLibListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocLibCommonDTO
     * @return List
     */
    public List findList(MaDocLibCommonDTO maDocLibCommonDTO, User user);
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaDocLibListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteDoc(String id, User user);

    public int deleteDocData(String id, User user);

    public int deleteObjDoc(String id, String objectId, String objectType, User user);

	/**
	 * Link Exist Doc
	 * @param maDocLibCommonDTO
	 */
	public void linkDoc(MaDocLibCommonDTO maDocLibCommonDTO);
	
    public String findTotalCount(MaDocLibCommonDTO maDocLibCommonDTO, User user);

}