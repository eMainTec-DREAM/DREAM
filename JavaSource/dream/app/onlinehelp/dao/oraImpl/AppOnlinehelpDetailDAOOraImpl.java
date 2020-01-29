package dream.app.onlinehelp.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.app.onlinehelp.dao.AppOnlinehelpDetailDAO;
import dream.app.onlinehelp.dto.AppOnlinehelpCommonDTO;
import dream.app.onlinehelp.dto.AppOnlinehelpDetailDTO;

/**
 * 도움말 상세 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="appOnlinehelpDetailDAOTarget"
 * @spring.txbn id="appOnlinehelpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppOnlinehelpDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AppOnlinehelpDetailDAO
{
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param appOnlinehelpCommonDTO
     * @param user 
     * @return
     */
    public AppOnlinehelpDetailDTO findDetail(AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("    a.onlinehelp_id                                                                         onlineHelpId       ");
        query.append("    ,a.description                                                                          title              ");
        query.append("    ,a.file_name                                                                            fileName      ");
        query.append("    ,(SELECT description FROM TAPAGE WHERE file_name=a.file_name)   pageDesc      ");
        query.append("    ,a.CONTENTS                                                                          CONTENTS     ");
        query.append("FROM TAONLINEHELP a       ");
        query.append("WHERE 1=1     ");
        query.getAndQuery("a.file_name", appOnlinehelpCommonDTO.getPageId());
//        query.getAndQuery("a.comp_no", user.getCompNo());

        AppOnlinehelpDetailDTO appOnlinehelpDetailDTO = 
                (AppOnlinehelpDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new AppOnlinehelpDetailDTO()));
        
        return appOnlinehelpDetailDTO;
    }
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param appOnlinehelpDetailDTO
     * @param appOnlinehelpCommonDTO
     * @return
     */
    public int updateDetail(AppOnlinehelpDetailDTO appOnlinehelpDetailDTO, AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAONLINEHELP SET      ");
        query.append("  description     = ?,    ");
        query.append("  file_name       = ?,    ");
        query.append("  comp_no       = ?,    ");
        query.append("  contents        = ?,    ");
        query.append("  upd_date       = TO_CHAR(SYSDATE,'yyyymmdd'),   ");
        query.append("  emp_id          = (SELECT emp_id FROM TAUSER WHERE comp_no = ? AND user_id = ?),    ");
        query.append("  emp_no         = (SELECT emp_no FROM TAEMP WHERE emp_id=(SELECT emp_id FROM TAUSER WHERE comp_no = ? AND user_id = ?)),    ");
        query.append("  emp_name     = (SELECT emp_name FROM TAEMP WHERE emp_id=(SELECT emp_id FROM TAUSER WHERE comp_no = ? AND user_id = ?))    ");
        query.append("WHERE onlinehelp_id = ?       ");
        
        Object[] objects = new Object[] {
                appOnlinehelpDetailDTO.getTitle(),
                appOnlinehelpCommonDTO.getPageId(),
                user.getCompNo(),
                appOnlinehelpDetailDTO.getContents(),
                user.getCompNo(),
                user.getUserId(),
                user.getCompNo(),
                user.getUserId(),
                user.getCompNo(),
                user.getUserId(),
                appOnlinehelpDetailDTO.getOnlineHelpId()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param appOnlinehelpDetailDTO
     * @param appOnlinehelpCommonDTO
     * @param user 
     * @return
     */
    public int insertDetail(AppOnlinehelpDetailDTO appOnlinehelpDetailDTO, AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAONLINEHELP                 ");
        query.append("  (onlinehelp_id,     description,        ");
        query.append("   file_name,         comp_no,        ");
        query.append("   contents,          upd_date,       ");
        query.append("   emp_id,            emp_no,          ");
        query.append("   emp_name                             ");
        query.append("  )   VALUES                          ");
        query.append("  (?,             ?,                  ");
        query.append("   ?,             ?,                  ");
        query.append("   ?,             TO_CHAR(SYSDATE,'yyyymmdd'),");
        query.append("  (SELECT emp_id FROM TAUSER       ");
        query.append("                        WHERE comp_no = ?     ");
        query.append("                        AND user_id = ?      ");
        query.append("  ),        ");
        query.append("  (SELECT emp_no FROM TAEMP      ");
        query.append("  WHERE emp_id=(SELECT emp_id FROM TAUSER       ");
        query.append("                        WHERE comp_no = ?     ");
        query.append("                        AND user_id = ?      ");
        query.append("  )),        ");
        query.append("  (SELECT emp_name FROM TAEMP      ");
        query.append("  WHERE emp_id=(SELECT emp_id FROM TAUSER       ");
        query.append("                        WHERE comp_no = ?     ");
        query.append("                        AND user_id = ?      ");
        query.append("  ))        ");
        query.append("  )                                   ");
        
        Object[] objects = new Object[] {
                appOnlinehelpDetailDTO.getOnlineHelpId(),
                appOnlinehelpDetailDTO.getTitle(),
                appOnlinehelpCommonDTO.getPageId(),
                user.getCompNo(),
                appOnlinehelpDetailDTO.getContents(),
                user.getCompNo(),
                user.getUserId(),
                user.getCompNo(),
                user.getUserId(),
                user.getCompNo(),
                user.getUserId()
        };
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
}