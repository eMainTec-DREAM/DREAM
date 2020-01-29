package dream.tool.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.tool.list.dao.MaPttMstrListDAO;
import dream.tool.list.dto.MaPttMstrCommonDTO;
import dream.tool.list.dto.MaPttMstrListDTO;

/**
 * 보전자재분류(마스터) - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maPttMstrListDAOTarget"
 * @spring.txbn id="maPttMstrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttMstrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPttMstrListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrCommonDTO
     * @return List
     */
    public List findList(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT ''                                     seqNo,      ");
        query.append("       ''                                     isDelCheck, ");
        query.append("       x.comp_no                              compNo,     ");
        query.append("       x.part_id                              partId,     ");
        query.append("       x.part_no                              partNo,     ");
        query.append("       x.description + (case when x.pt_size is null then '' else ',' + x.pt_size end )  + (case when x.vendor_code is null then '' else ',' + x.vendor_code end)         partNameSize, ");
        query.append("       x.maker                                maker,      ");
        query.append("       x.model                                model,      ");
        query.append("       dbo.SFACODE_TO_DESC(x.plf_type, 'PLF_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"')  plfTypeDesc,");
        query.append("       x.seller                               seller,     ");
        query.append("       x.lead_time                            leadTime,   ");
        query.append("       dbo.SFACODE_TO_DESC(x.mro_type, 'MRO_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"')  mroType,");
        query.append("       x.usage                                            ");
        query.append("FROM   TAPARTS x                                          ");
    	query.append("WHERE  x.part_categ='TOOL'							    ");
        query.append(this.getWhere(maPttMstrCommonDTO,loginUser));
        query.getOrderByQuery("x.part_id","x.part_id DESC", maPttMstrCommonDTO.getOrderBy(), maPttMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPttMstrCommonDTO.getIsLoadMaxCount(), maPttMstrCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPttMstrCommonDTO maPttMstrCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if (!"".equals(maPttMstrCommonDTO.getPartId()))
        {
            query.getAndQuery("x.part_id", maPttMstrCommonDTO.getPartId());
            return query.toString();
        }
        query.getStringEqualQuery("x.IS_DELETED", "N");
        query.getStringEqualQuery("x.part_categ", "TOOL");
        // 품명/규격
        query.getLikeQuery("x.full_desc", maPttMstrCommonDTO.getFilterPartNameSize());
        query.getLikeQuery("x.part_no", maPttMstrCommonDTO.getFilterPartNo());
        query.getLikeQuery("x.model", maPttMstrCommonDTO.getFilterModel());
        query.getLikeQuery("x.maker", maPttMstrCommonDTO.getFilterMaker());
        query.getLikeQuery("x.usage", maPttMstrCommonDTO.getFilterUsage());
        query.getLikeQuery("x.vendor_code", maPttMstrCommonDTO.getFilterVendorPtCode());
        
        // 내/외자 
        query.getSysCdQuery("x.plf_type", maPttMstrCommonDTO.getFilterPlfType(), maPttMstrCommonDTO.getFilterPlfTypeDesc(), "PLF_TYPE", maPttMstrCommonDTO.getFilterCompNo(),user.getLangId());
        // 수리/소모 구분
        query.getSysCdQuery("x.mro_type", maPttMstrCommonDTO.getFilterMroType(), maPttMstrCommonDTO.getFilterMroTypeDesc(), "MRO_TYPE", maPttMstrCommonDTO.getFilterCompNo(),user.getLangId());
        
        // 사용여부 
        query.getSysCdQuery("x.is_use", maPttMstrCommonDTO.getFilterIsUse(), maPttMstrCommonDTO.getFilterIsUseDesc(), "IS_USE", maPttMstrCommonDTO.getFilterCompNo(),user.getLangId());
        
        // 내부품번여부 
        query.getSysCdQuery("x.is_inpart", maPttMstrCommonDTO.getFilterIsInPart(), maPttMstrCommonDTO.getFilterIsInPartDesc(), "IS_INPART", maPttMstrCommonDTO.getFilterCompNo(),user.getLangId());
        
        // 자재사용부서 
        if(!"".equals(maPttMstrCommonDTO.getFilterDeptId())||!"".equals(maPttMstrCommonDTO.getFilterDeptDesc()))
        {
            query.append("AND x.part_id IN (SELECT part_id FROM TAPTUSEDDEPT a  ");
            query.append("                  WHERE  comp_no = x.comp_no          ");
            query.getDeptLevelQuery("dept_id", maPttMstrCommonDTO.getFilterDeptId(), maPttMstrCommonDTO.getFilterDeptDesc(), maPttMstrCommonDTO.getFilterCompNo());
            query.append("                  )  ");
        }
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return
     */
    public int updateDeletePartsFlag(MaPttMstrListDTO maPttMstrListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPARTS SET            ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DESCRIPTION = '[Delete] '+DESCRIPTION     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE  comp_no  = ?           ");
        query.append("  AND  part_id  = ?           ");      
        
        Object[] objects = new Object[] {  
        		loginUser.getEmpId()
        		,DateUtil.getDate()
                ,loginUser.getCompNo()
                ,maPttMstrListDTO.getPartId()
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public String findTotalCount(MaPttMstrCommonDTO maPttMstrCommonDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPARTS x                                          ");
    	query.append("WHERE  x.part_categ='TOOL'							    ");
        query.append(this.getWhere(maPttMstrCommonDTO, user));
		
		List resultList = getJdbcTemplate().queryForList(query.toString());
		return QuerySqlBuffer.listToString(resultList);
	
	}
    
}