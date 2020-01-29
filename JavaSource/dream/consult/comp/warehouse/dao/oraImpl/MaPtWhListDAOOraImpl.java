package dream.consult.comp.warehouse.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.warehouse.dao.MaPtWhListDAO;
import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;

/**
 * 부품창고 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtWhListDAOTarget"
 * @spring.txbn id="maPtWhListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtWhListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtWhListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhCommonDTO
     * @return List
     */
    public List findPtWhList(MaPtWhCommonDTO maPtWhCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtWhCommonDTO.getCompNo();
        
        query.append("SELECT						");
        query.append("         '' seqNo				");
        query.append("        ,'' isDelCheck		");
        query.append("        ,x.comp_no as compNo	");
        query.append("        ,(select aa.description from tacomp aa where x.comp_no = aa.comp_no) as compDesc	");
        query.append("        ,x.wcode				");
        query.append("        ,x.wname				");
        query.append("        ,x.remark				");
        query.append("        ,x.is_use isUse			");
        query.append("        ,x.wcode_id wcodeId      ");
        query.append("FROM TAWAREHOUSE x				");
        query.append("WHERE 1 = 1    				");
        query.append(this.getWhere(maPtWhCommonDTO));
        query.getOrderByQuery("x.wcode_id", maPtWhCommonDTO.getOrderBy(), maPtWhCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtWhCommonDTO.getIsLoadMaxCount(), maPtWhCommonDTO.getFirstRow()));    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePtWh(String wcodeId,String compNo,  User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAWAREHOUSE			          ");
    	query.append("WHERE  wcode_id       = '"+wcodeId+"'		  ");
    	query.append("    and comp_no       = '"+compNo+"'		  ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtWhCommonDTO maPtWhCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(maPtWhCommonDTO.getWcodeId()) && !"".equals(maPtWhCommonDTO.getCompNo()))
        {
            query.getAndQuery("x.wcode_id", maPtWhCommonDTO.getWcodeId());
            query.getAndQuery("x.comp_no", maPtWhCommonDTO.getCompNo());
            return query.toString();
        }
        
        query.getCodeLikeQuery("x.wcode_id", "x.wname", maPtWhCommonDTO.getFilterWcodeId(), maPtWhCommonDTO.getFilterWname());
        query.getAndQuery("x.comp_no", maPtWhCommonDTO.getFilterCompNo());
        
        return query.toString();
    }

	public String findTotalCount(MaPtWhCommonDTO maPtWhCommonDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM   TAWAREHOUSE x	");
        query.append("WHERE  1=1            ");
        query.append(this.getWhere(maPtWhCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}