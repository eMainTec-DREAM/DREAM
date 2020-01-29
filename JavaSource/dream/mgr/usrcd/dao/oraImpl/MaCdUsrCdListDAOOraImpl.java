package dream.mgr.usrcd.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrcd.dao.MaCdUsrCdListDAO;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;

/**
 * 사용자코드관리 상세 목록
 * @author 
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maCdUsrCdListDAOTarget"
 * @spring.txbn id="maCdUsrCdListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaCdUsrCdListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaCdUsrCdListDAO
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
        
        query.append(" SELECT                                                                                               ");
        query.append("        cdusrd_id                                                                     AS id           ");
        query.append("      , ''                                                                            AS seqNo        ");
        query.append("      , ''                                                                            AS isDelCheck   ");
        query.append("      , comp_no                                                                       AS compNo       ");
        query.append("      , cdusrd_id                                                                     AS cdUsrdId     ");
        query.append("      , cdusrm_id                                                                     AS cdUsrmId     ");
        query.append("      , p_cdusrd_id                                                                   AS pCdUsrdId    ");
        query.append("      , SFAIDTODESC(x.p_cdusrd_id, x.dir_type, 'USR', x.comp_no)                      AS pcdUsrdDesc  ");         
        query.append("      , cdusrd_no                                                                     AS cdUsrdNo     ");
        query.append("      , description                                                                   AS description  ");
        query.append("      , remark                                                                        AS remark       ");
        query.append("      , ord_no                                                                        AS ordNo        ");
        query.append("      , mng_cd                                                                        AS mngCd        ");
        query.append("      , SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"')  AS isUseDesc    ");
        query.append("      , LEVEL                                                                         AS LVL          ");
        query.append("      , MIN(LEVEL) OVER(ORDER BY x.ord_no)                                            AS MINLVL       ");
        query.append("   FROM TACDUSRD x                                                                                    ");
        query.append("  WHERE 1 = 1                                                                                         ");
        query.append(this.getWhere(maCdUsrCommonDTO));
        query.append("  START WITH x.p_cdusrd_id = '0'                                                                      ");
        query.append("CONNECT BY PRIOR x.cdusrd_id = x.p_cdusrd_id                                                          ");
        query.append("  ORDER BY x.ord_no                                                                            ");

        return getJdbcTemplate().queryForList(query.toString());
	}
	
    /**
     * 선택된 하위 데이터 삭제 
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdGridDTO
     * @return
     */
    public int deleteCdUsrCdList(String compNo, String cdUsrdId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TACDUSRD                           ");
        query.append("WHERE  comp_no   = ?                      ");
        query.append("  AND  cdusrd_id = ?                      ");                   
        
        Object[] objects = new Object[] {   
                compNo,
                cdUsrdId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    private String getWhere(MaCdUsrCommonDTO maCdUsrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.comp_no", maCdUsrCommonDTO.getCompNo());
    	query.getAndQuery("x.cdusrm_id", maCdUsrCommonDTO.getCdUsrmId());
    	if (!"".equals(maCdUsrCommonDTO.getCdUsrdId()))
        {
            query.getAndQuery("x.cdusrd_id", maCdUsrCommonDTO.getCdUsrdId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaCdUsrCommonDTO maCdUsrCommonDTO, User user)
	{
		QueryBuffer query = new QueryBuffer();		

        query.append("SELECT                                     	");
        query.append("       COUNT(1)                               ");
        query.append("FROM   TACDUSRD x                             ");
        query.append("WHERE  1=1                                    ");
        query.append(this.getWhere(maCdUsrCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}