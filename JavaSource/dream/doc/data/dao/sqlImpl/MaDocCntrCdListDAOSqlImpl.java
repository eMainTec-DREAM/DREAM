package dream.doc.data.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.doc.data.dao.MaDocCntrCdListDAO;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.dto.MaDocCntrCdListDTO;

/**
 * 일반자료실 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maDocCntrCdListDAOTarget"
 * @spring.txbn id="maDocCntrCdListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDocCntrCdListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaDocCntrCdListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdCommonDTO
     * @return List
     */
    public List findList(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User loginUser)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  		");
        query.append("SELECT ''										 seqNo,     				");
        query.append("       ''                                     isDelCheck, 				");
        query.append("       x.comp_no                              compNo,     				");
        query.append("       x.doccntr_id                           docCntrId,  				");
        query.append("       x.doccntr_no                           docCntrNo,  				");
        query.append("       x.description                          description,				");
        query.getDate("x.reg_date", "regDate,");
        query.append("      (SELECT full_desc FROM TAEQCTG                      				");
        query.append("       WHERE  comp_no  = x.comp_no                        				");
        query.append("        AND   eqctg_id = x.eqctg_id )         eqCtgDesc,  				");
        query.append("       dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)      deptDesc,	");
        query.append("       dbo.SFAIDTODESC(x.user_id, '', 'EMP', x.comp_no)       userName  	");
        query.append("FROM   TADOCCNTR x                                       					");
        query.append("WHERE  1 = 1                                              				");
        query.append(this.getWhere(maDocCntrCdCommonDTO, loginUser));
//        query.append("  AND  doccntr_type is null								");
//    	query.append("ORDER  BY doccntr_no                                      ");
        query.getOrderByQuery("x.doccntr_id","x.doccntr_no desc", maDocCntrCdCommonDTO.getOrderBy(), maDocCntrCdCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maDocCntrCdCommonDTO.getIsLoadMaxCount(), maDocCntrCdCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User loginUser)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        if (!"".equals(maDocCntrCdCommonDTO.getDocCntrId()))
        {
            query.getAndQuery("x.doccntr_id", maDocCntrCdCommonDTO.getDocCntrId());
            return query.toString();
        }
        query.getLikeQuery("x.description", maDocCntrCdCommonDTO.getFilterDescription());
        query.getAndDateQuery("x.reg_date", maDocCntrCdCommonDTO.getFilterFromDate(), maDocCntrCdCommonDTO.getFilterToDate());
        query.getLikeQuery("x.doccntr_no", maDocCntrCdCommonDTO.getFilterDocCntrNo());
        query.getAndQuery("x.doccntr_type", maDocCntrCdCommonDTO.getDocCntrType());
        
        // 설비종류
        query.getEqCtgLevelQuery("x.eqctg_id", maDocCntrCdCommonDTO.getFilterEqCtgId(),maDocCntrCdCommonDTO.getFilterEqCtgDesc(), maDocCntrCdCommonDTO.getFilterCompNo());
        
        // 부서
        query.getDeptLevelQuery("x.dept_id", maDocCntrCdCommonDTO.getFilterDeptId(), maDocCntrCdCommonDTO.getFilterDeptDesc(), maDocCntrCdCommonDTO.getFilterCompNo());
        
        // 등록자 
        if(!"".equals(maDocCntrCdCommonDTO.getFilterUserId()))
        {
            query.getAndQuery("x.user_id", maDocCntrCdCommonDTO.getFilterUserId());
        }
        else if(!"".equals(maDocCntrCdCommonDTO.getFilterUserName()))
        {
            query.append("AND x.user_id IN (SELECT emp_id FROM TAEMP            ");
            query.append("                  WHERE  comp_no = x.comp_no          ");
            query.getLikeQuery("emp_name", maDocCntrCdCommonDTO.getFilterUserName());
            query.append("                 )                                    ");
        }
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
                maDocCntrCdCommonDTO.getFilterPlantId(), maDocCntrCdCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdListDTO
     * @param loginUser
     * @return
     */
    public int deleteDocCntr(MaDocCntrCdListDTO maDocCntrCdListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TADOCCNTR                                          ");
        query.append("WHERE  comp_no    = ?                                     ");
        query.append("  AND  doccntr_id = ?                                     ");      
        
        Object[] objects = new Object[] {   
        		loginUser.getCompNo(),
        		maDocCntrCdListDTO.getDocCntrId()
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * 자재 첨부파일 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdListDTO
     * @param loginUser
     * @return
     */
    public int deleteObjDoc(MaDocCntrCdListDTO maDocCntrCdListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAOBJDOC                                           ");
        query.append("WHERE  comp_no     = ?                                    ");
        query.append("  AND  object_id   = ?                                    ");      
        
        Object[] objects = new Object[] {   
        		loginUser.getCompNo(),
        		maDocCntrCdListDTO.getDocCntrId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public String findTotalCount(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User user)
	{ 
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                    ");
        query.append("      count(1)            ");
        query.append("FROM   TADOCCNTR x                                        ");
        query.append("WHERE  1 = 1                                              ");
        query.append(this.getWhere(maDocCntrCdCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
}