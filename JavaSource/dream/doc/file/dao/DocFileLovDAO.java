package dream.doc.file.dao;

import java.util.List;

import common.bean.User;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * 첨부파일 LOV- 목록 dao
 * @author  jung7126
 * @version $Id: DocFileLovDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface DocFileLovDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: DocFileLovDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocLibCommonDTO
     * @return List
     */
    public List findList(MaDocLibCommonDTO maDocLibCommonDTO, User user);
}