package dream.mgr.usrcd.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrcd.dao.MaCdUsrListDAO;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;

/**
 * 사용자코드관리
 * @author 
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maCdUsrListDAOTarget"
 * @spring.txbn id="maCdUsrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaCdUsrListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaCdUsrListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCommonDTO
     * @return 
     */
    public List findSheet(MaCdUsrCommonDTO maCdUsrCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT ''                                   seqNo,        ");
        query.append("       ''                                   isDelCheck,   ");
        query.append("       comp_no                              compNo,       ");
        query.append("       cdusrm_id                            cdUsrmId,     ");
        query.append("       dir_type                             dirType,      ");
        query.append("       description                          description,  ");
        query.append("       remark,             	                            ");
        query.append("       SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isUseDesc ");
        query.append("FROM   TACDUSRM  x                                        ");
    	query.append("WHERE  1=1												");// 회사코드
    	query.append(this.getWhere(maCdUsrCommonDTO, user));
//        query.append("ORDER BY dir_type                                         ");

    	query.getOrderByQuery("x.dir_type", maCdUsrCommonDTO.getOrderBy(), maCdUsrCommonDTO.getDirection());
                
        return getJdbcTemplate().queryForList(query.toString(maCdUsrCommonDTO.getIsLoadMaxCount(), maCdUsrCommonDTO.getFirstRow()));
	}
	
    /**
     * 사용자코드 Master, Detail 모두 삭제
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrGridDTO
     * @return
     */
    public int deleteCdUsr(String compNo, String cdusrm_id)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue = 0;
        
        query.append("DELETE TACDUSRD                           ");
        query.append("WHERE  comp_no   = ?                      ");
        query.append("  AND  cdusrm_id = ?                      ");

        Object[] objects = new Object[] {   
                compNo,
                cdusrm_id
                };
        rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
        
        query = new QueryBuffer();
        query.append("DELETE TACDUSRM                           ");
        query.append("WHERE  comp_no   = ?                      ");
        query.append("  AND  cdusrm_id = ?                      ");
        rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    private String getWhere(MaCdUsrCommonDTO maCdUsrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	if (!"".equals(maCdUsrCommonDTO.getCdUsrmId()))
        {
            query.getAndQuery("x.cdusrm_id", maCdUsrCommonDTO.getCdUsrmId());
            return query.toString();
        }
    	query.getLikeQuery("x.dir_type",    maCdUsrCommonDTO.getFilterDirType());      // 코드유형
    	query.getLikeQuery("x.description", maCdUsrCommonDTO.getFilterDescription());  // 코드유형명

    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaCdUsrCommonDTO maCdUsrCommonDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                     				");
        query.append("       COUNT(1)                               			");
        query.append("FROM   TACDUSRM  x                                        ");
    	query.append("WHERE  1=1												");
    	query.append(this.getWhere(maCdUsrCommonDTO, user));
    	
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}