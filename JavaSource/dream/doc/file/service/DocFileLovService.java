package dream.doc.file.service;

import java.util.List;

import common.bean.User;
import dream.doc.file.dto.MaDocLibCommonDTO;


/**
 * 첨부파일 - 목록 service
 * @author  jung7126
 * @version $Id: DocFileLovService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface DocFileLovService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: DocFileLovService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maDocLibCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaDocLibCommonDTO maDocLibCommonDTO, User user);    
}
