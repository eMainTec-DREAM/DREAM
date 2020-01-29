package dream.asset.std.ctctr.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.std.ctctr.dao.AssetStdCtctrListDAO;
import dream.asset.std.ctctr.dto.AssetStdCtctrCommonDTO;

/**
 * 회계자산 - 목록 dao
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="assetStdCtctrListDAOTarget"
 * @spring.txbn id="assetStdCtctrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetStdCtctrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetStdCtctrListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdCtctrCommonDTO
     * @return List
     */
    public List findCtctrList(AssetStdCtctrCommonDTO assetStdCtctrCommonDTO, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT        								");
        query.append("    ''                          ISDELCHECK    ");
        query.append("    ,''                         SEQNO       	");
        query.append("    ,x.ctctr_id                 CTCTRID       ");
        query.append("    ,x.ctctr_no                 CTCTRNO       ");
        query.append("    ,x.description              DESCRIPTION	");
        query.append("    ,x.is_use                   ISUSE      	");
        query.append("    ,x.remark                   REMARK      	");
        query.append("    ,(SELECT a.description FROM TADEPT a WHERE a.comp_no=x.comp_no AND a.dept_id=x.dept_id) deptDesc ");
        query.append("FROM TACTCTR x        						");
        query.append("WHERE  1=1                                    ");
        query.append(this.getWhere(assetStdCtctrCommonDTO, user));
        query.getOrderByQuery("x.ctctr_id", "x.ctctr_no", assetStdCtctrCommonDTO.getOrderBy(), assetStdCtctrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetStdCtctrCommonDTO.getIsLoadMaxCount(), assetStdCtctrCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdCtctrCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AssetStdCtctrCommonDTO assetStdCtctrCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if(!("".equals(assetStdCtctrCommonDTO.getCtctrId())||assetStdCtctrCommonDTO.getCtctrId()==null))
        {
            query.getAndQuery("x.ctctr_id", assetStdCtctrCommonDTO.getCtctrId());
            return query.toString();
        }
        query.getLikeQuery("x.ctctr_no", assetStdCtctrCommonDTO.getFilterCtctrNo());
        query.getLikeQuery("x.description", assetStdCtctrCommonDTO.getFilterDescription());
        
        // 사용여부 
        query.getSysCdQuery("x.is_use", assetStdCtctrCommonDTO.getFilterIsUse(), assetStdCtctrCommonDTO.getFilterIsUse(), "IS_USE", user.getCompNo(), user.getLangId());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		assetStdCtctrCommonDTO.getFilterPlantId(), assetStdCtctrCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param ctctrId
     * @return
     */
    public int deleteCtctr(String ctctrId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TACTCTR          ");
        query.append("WHERE  comp_no  = ?    ");
        query.append("  AND  ctctr_id  = ?    ");      
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,ctctrId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(AssetStdCtctrCommonDTO assetStdCtctrCommonDTO,User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TACTCTR x                                      ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(assetStdCtctrCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}