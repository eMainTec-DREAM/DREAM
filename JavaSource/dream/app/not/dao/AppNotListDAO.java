package dream.app.not.dao;

import java.util.List;

import common.spring.BaseJdbcDaoSupportIntf;
import dream.app.not.dto.AppNotCommonDTO;
import dream.app.not.dto.AppNotDTO;

/**
 * 예방점검 - 목록
 * @author  javaworker
 * @version $Id: AppNotListDAO.java,v 1.1 2013/08/30 09:14:41 javaworker Exp $
 * @since   1.0
 */
public interface AppNotListDAO extends BaseJdbcDaoSupportIntf
{
    public List findAppNotList(AppNotCommonDTO appNotCommonDTO);
    

    
    public int findAppNotListCount(AppNotCommonDTO appNotCommonDTO);

    /**
     * 통보문서확인
     * @author  javaworker
     * @version $Id: AppNotListDAO.java,v 1.1 2013/08/30 09:14:41 javaworker Exp $
     * @since   1.0
     * 
     * @param appNotDTO
     */
    public void confirmAppNot(AppNotDTO appNotDTO);
}