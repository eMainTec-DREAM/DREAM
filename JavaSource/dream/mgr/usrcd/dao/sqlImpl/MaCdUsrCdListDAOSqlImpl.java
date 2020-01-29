package dream.mgr.usrcd.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class MaCdUsrCdListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaCdUsrCdListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                                                            ");
        query.append(" SELECT                                                                                                       ");
        query.append("        x.cdusrd_id                                                                           AS ID           ");
        query.append("      , ''                                                                                    AS SEQNO        ");
        query.append("      , ''                                                                                    AS ISDELCHECK   ");
        query.append("      , x.comp_no                                                                             AS COMPNO       ");
        query.append("      , x.cdusrd_id                                                                           AS CDUSRDID     ");
        query.append("      , x.cdusrm_id                                                                           AS CDUSRMID     ");
        query.append("      , x.p_cdusrd_id                                                                         AS PCDUSRDID    ");
        query.append("      , dbo.SFAIDTODESC(x.p_cdusrd_id, x.dir_type, 'USR', x.comp_no)                          AS PCDUSRDDESC  ");
        query.append("      , x.cdusrd_no                                                                           AS CDUSRDNO     ");
        query.append("      , x.description                                                                         AS DESCRIPTION  ");
        query.append("      , x.remark                                                                              AS REMARK       ");
        query.append("      , x.ord_no                                                                              AS ORDNO        ");
        query.append("      , x.mng_cd                                                                              AS MNGCD        ");
        query.append("      , dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'\"+user.getLangId()+\"')    AS ISUSEDESC    ");
        query.append("      , y.lvl                                                                                 AS LVL          ");
        query.append("      , MIN(y.lvl) OVER()                                                                     AS MINLVL       ");
        query.append("   FROM TACDUSRD x ,(SELECT * FROM dbo.SFACDUSRD_ALL('"+user.getCompNo()+"','0')) y                           ");
        query.append("  WHERE x.cdusrd_id = y.cdusrd_id                                                                             ");
        query.append(this.getWhere(maCdUsrCommonDTO));
        query.append("  ORDER BY y.lvl, x.ord_no                                                                                    ");
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TACDUSRD                           ");
        query.append("WHERE  comp_no   = ?                      ");
        query.append("  AND  cdusrd_id = ?                      ");                   
        
        Object[] objects = new Object[] {   
                compNo,
                cdUsrdId
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    private String getWhere(MaCdUsrCommonDTO maCdUsrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                     	");
        query.append("       COUNT(1)                               ");
        query.append("FROM   TACDUSRD x                             ");
        query.append("WHERE  1=1                                    ");
        query.append(this.getWhere(maCdUsrCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}