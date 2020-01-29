package dream.part.rep.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.rep.dao.MaPtRepListDAO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * 부품수리 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtRepListDAOTarget"
 * @spring.txbn id="maPtRepListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtRepListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtRepListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @return List
     */
    public List findList(MaPtRepCommonDTO maPtRepCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtRepCommonDTO.getCompNo();
        
        query.append("SELECT ''                                             seqNo,          ");
        query.append("       ''                                             isDelCheck,     ");
        query.append("       x.comp_no                                      compNo,         ");
        query.append("       x.ptrepairlist_id                              ptRepairListId, ");
        query.append("       x.ptrepairlist_no                              ptRepairListNo, ");
        query.append("       SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)  deptDesc,       "); 
        query.getDate("x.repair_date", "repairDate,");
        query.getDate("x.reg_date", "regDate,");
        query.append("       SFACODE_TO_DESC(x.ptrepairlist_status, 'REPAIR_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') repairStatusDesc,    ");
        query.append("      (SELECT description FROM TAVENDOR                   ");                                  
        query.append("       WHERE  comp_no   = x.comp_no                       ");
        query.append("         AND  vendor_id = x.vendor_id)                vendorDesc,     "); 
        query.append("       y.part_no                                      partNo,         ");
        query.append("       y.description||', '||y.pt_size                 partNameSize,   ");
        query.append("       NVL(x.repair_qty, 0)                           repairQty,  	"); 
        query.append("       NVL(x.unit_price, 0)                           unitPrice,  	"); 
        query.append("       NVL(x.tot_price, 0)                            totPrice,   	"); 
        query.append("       SFAIDTODESC(x.inspector, '', 'EMP', x.comp_no) inspectorName   ");  
        query.append("       ,x.plant			 							plant			");
        query.append("       ,(SELECT a.description          								");
        query.append("          FROM TAPLANT a                   							");
        query.append("          WHERE a.comp_no = x.comp_no      							");
        query.append("          AND a.plant = x.plant            							");
        query.append("         )                    						plantDesc   	");
        query.append("		 , y.description								partdesc		");
        query.append("		 , y.model 										ptmodel			");
        query.append("		 , y.pt_size 									ptsize			");
        query.append("FROM   TAPTREPAIRLIST x, TAPARTS y                       			 	");
        query.append("WHERE x.comp_no = y.comp_no(+)                            			");
        query.append("  AND x.part_id = y.part_id(+)                            			");
        query.append("  AND x.comp_no = '"+compNo+"'                            			");
        query.append(this.getWhere(maPtRepCommonDTO, user));
        //query.append("ORDER BY x.ptrepairlist_id DESC                           ");
        query.getOrderByQuery("x.ptrepairlist_id DESC ", maPtRepCommonDTO.getOrderBy(), maPtRepCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maPtRepCommonDTO.getIsLoadMaxCount(), maPtRepCommonDTO.getFirstRow()));
    }
    
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
    public int deleteList(String compNo, String ptRepairListId)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPTREPAIRLIST		              ");
    	query.append("WHERE  comp_no          = '"+compNo+"'		  ");
    	query.append("  AND  ptrepairlist_id  = '"+ptRepairListId+"'  ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("DELETE FROM TAPTRPRAPPLIST		              ");
    	query.append("WHERE  comp_no          = '"+compNo+"'		  ");
    	query.append("  AND  ptrepairlist_id  = '"+ptRepairListId+"'  ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtRepCommonDTO maPtRepCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(maPtRepCommonDTO.getPtRepairListId()))
        {
            query.getAndQuery("x.ptrepairlist_id", maPtRepCommonDTO.getPtRepairListId());
            return query.toString();
        }
        
      //상태
        query.getSysCdQuery("x.ptrepairlist_status", maPtRepCommonDTO.getPtRepStatus(), maPtRepCommonDTO.getPtRepStatusDesc(), "REPAIR_STATUS", maPtRepCommonDTO.getCompNo(), user.getLangId());
        // 담당부서
        query.getDeptLevelQuery("x.dept_id", maPtRepCommonDTO.getFilterDeptId(),maPtRepCommonDTO.getFilterDeptDesc(), maPtRepCommonDTO.getCompNo());
             
        //수리일자
        String startDate = maPtRepCommonDTO.getFilterStartDate();
        String endDate = maPtRepCommonDTO.getFilterEndDate();
        query.getAndDateQuery("x.repair_date", startDate, endDate);
        query.getAndDateQuery("x.request_date", maPtRepCommonDTO.getFilterReqStartDate(), maPtRepCommonDTO.getFilterReqEndDate());
        //등록일자
        query.getAndDateQuery("x.reg_date", maPtRepCommonDTO.getFilterRegStartDate(), maPtRepCommonDTO.getFilterRegEndDate());
        
        // 검수자
        if(!"".equals(maPtRepCommonDTO.getFilterInspector()))
        {
            query.getAndQuery("x.inspector", maPtRepCommonDTO.getFilterInspector());
        }
        else if(!"".equals(maPtRepCommonDTO.getFilterInspectorName()))
        {
            query.append(" AND  x.inspector IN (SELECT emp_id FROM TAEMP        ");
            query.append("                      WHERE  comp_no = x.comp_no      ");
            query.getLikeQuery("emp_name", maPtRepCommonDTO.getFilterInspectorName());
            query.append("					   )						        ");
        }
        
        // 품명/규격
        query.getLikeQuery("y.full_desc", maPtRepCommonDTO.getFilterPartNameSize());
        //상태
        query.getAndQuery("x.ptrepairlist_status", maPtRepCommonDTO.getPtRepStatus());
        
        //설비
        if(!"".equals(maPtRepCommonDTO.getFilterEquipId()))
        {
            query.append(" AND y.part_id IN(SELECT a.part_id FROM TAEQPART a       ");
            query.append("                            WHERE a.comp_no=y.comp_no       ");
            query.getAndQuery("a.equip_id", maPtRepCommonDTO.getFilterEquipId());
            query.append("                            )     ");
        }
        else if(!"".equals(maPtRepCommonDTO.getFilterEquipDesc()))
        {
            query.append(" AND y.part_id IN(SELECT DISTINCT a.part_id FROM TAEQPART a       ");
            query.append("                            WHERE a.comp_no=y.comp_no         ");
            query.append("                            AND a.equip_id IN(SELECT aa.equip_id FROM TAEQUIPMENT aa      ");
            query.append("                                                    WHERE aa.comp_no=a.comp_no        ");
            query.getLikeQuery("aa.description", maPtRepCommonDTO.getFilterEquipDesc());
            query.append("                                                    )     ");
            query.append("                            )     ");
        }
      
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no=x.comp_no AND a.plant=x.plant )", 
        		maPtRepCommonDTO.getFilterPlantId(), maPtRepCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(MaPtRepCommonDTO maPtRepCommonDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        query.append("SELECT          						");
        query.append("       COUNT(1)      					");
        query.append("FROM   TAPTREPAIRLIST x, TAPARTS y	");
        query.append("WHERE x.comp_no = y.comp_no(+)       	");
        query.append("  AND x.part_id = y.part_id(+)       	");
        query.append("  AND x.comp_no = '"+compNo+"'       	");
        query.append(this.getWhere(maPtRepCommonDTO, user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}