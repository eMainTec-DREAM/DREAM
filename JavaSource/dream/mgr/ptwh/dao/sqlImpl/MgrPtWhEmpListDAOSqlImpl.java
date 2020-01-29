package dream.mgr.ptwh.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.ptwh.dao.MgrPtWhEmpListDAO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;

/**
 * 부품창고 담당자 - List DAO implements
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrPtWhEmpListDAOTarget"
 * @spring.txbn id="mgrPtWhEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrPtWhEmpListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrPtWhEmpListDAO
{
    public List findPtWhEmpList(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception
    { 
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT        																										");
        query.append("        ''																							AS seqNo		");
        query.append("       ,''																							AS ISDELCHECK	");
        query.append("       ,(SELECT description																							");
        query.append("         FROM   TADEPT																								");
        query.append("         WHERE  comp_no = x.comp_no																					");
        query.append("          AND dept_id = (SELECT dept_id FROM TAEMP WHERE comp_no = x.comp_no AND emp_id = x.emp_id))	AS deptDesc		");
        query.append("       ,(SELECT emp_no                                																");
        query.append("          FROM TAEMP                                																	");
        query.append("         WHERE comp_no = x.comp_no                																	");
        query.append("           AND emp_id = x.emp_id)                                                                   	AS empNo 		");
        query.append("       ,(SELECT emp_name                            																	");
        query.append("          FROM TAEMP                                																	");
        query.append("         WHERE comp_no = x.comp_no                																	");
        query.append("           AND emp_id = x.emp_id)                                                                    	AS empDesc 		");
        query.append("       ,x.REMARK																						AS remark 		");
        query.append("       ,x.WHUSER_ID                  													 				AS whUserId		");
        query.append("       ,x.EMP_ID                                                                                  	AS empId		");
        query.append("       ,x.WCODE_ID                  													  				AS wCodeId		");
        query.append("FROM TAWHUSER x																										");
        query.append("WHERE 1=1																												");
        query.append(this.getWhere(mgrPtWhEmpListDTO, user));
        query.getOrderByQuery("x.WHUSER_ID","x.WHUSER_ID", mgrPtWhEmpListDTO.getOrderBy(), mgrPtWhEmpListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(mgrPtWhEmpListDTO.getIsLoadMaxCount(), mgrPtWhEmpListDTO.getFirstRow()));
    } 

	private String getWhere(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        // 회사
        query.getStringEqualQuery("x.comp_no", user.getCompNo());        
        
        // 창고 담당자 ID
        if(!"".equals(mgrPtWhEmpListDTO.getWhUserId())){
            query.getAndQuery("x.WHUSER_ID", mgrPtWhEmpListDTO.getWhUserId());
            return query.toString();
        }
        
        // 창고 ID
        query.getAndQuery("x.wcode_id", mgrPtWhEmpListDTO.getWcodeId());
        
        return query.toString();
    } 

    public int deletePtWhEmpList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAWHUSER				");
        query.append("WHERE  comp_no 			= ?		");
        query.append(" AND WHUSER_ID 			= ?		");

        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT									");
        query.append("       COUNT(1)							");
        query.append("FROM TAWHUSER x							");
        query.append("WHERE 1=1									");
        query.append(this.getWhere(mgrPtWhEmpListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
}