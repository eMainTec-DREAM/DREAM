package dream.note.daily.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.note.daily.dao.MaWoDailyPmiListDAO;
import dream.note.daily.dto.MaWoDailyDetailDTO;

/**
 * 일일작업확인 - 작업 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoDailyPmiListDAOTarget"
 * @spring.txbn id="maWoDailyPmiListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoDailyPmiListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoDailyPmiListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDailyCommonDTO
     * @return List
     */
    public List findList(MaWoDailyDetailDTO maWoDailyDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                   	");
        query.append("        ''                                                seqNo          	");
        query.append("        ,''                                               isDelCheck     	");
        query.append("        ,a.pminslist_id 									pmInsListId		");
        query.append("        ,a.pminssched_id   								pmInsSchedId	");
        query.append("        ,(SELECT b.pm_no FROM TAPMLST b                                  	");
        query.append("            WHERE b.pm_id = a.pm_id                                      	");
        query.append("            AND b.comp_no = a.comp_no)                    pmNo           	");
        query.append("        ,a.description                                    pmDesc         	");
        query.append("        ,                                                                	");
        query.getDate("a.wkor_date", "wkorDate");
        query.append("        ,(SELECT b.item_no FROM TAEQUIPMENT b                            	");
        query.append("           WHERE b.equip_id = a.equip_id                                 	");
        query.append("             AND b.comp_no = a.comp_no)                   equipNo		   	");
        query.append("        ,(SELECT b.description FROM TAEQUIPMENT b                        	");
        query.append("           WHERE b.equip_id = a.equip_id                                 	");
        query.append("             AND b.comp_no = a.comp_no)                   equipDesc      	");
        query.append("        ,(SELECT b.description FROM TADEPT b                             	");
        query.append("            WHERE b.dept_id = a.dept_id                                  	");
        query.append("            AND b.comp_no = a.comp_no)                    deptDesc       	");
        query.append("        ,(SELECT b.description FROM TAWKCTR b							   	");
        query.append("           WHERE b.wkctr_id = a.wkctr_id 								   	");
        query.append("             AND b.comp_no = a.comp_no )                  wkCtr		   	");
        query.append("        ,(SELECT b.emp_name FROM TAEMP b                                 	");
        query.append("           WHERE b.emp_id = a.emp_id                                     	");
        query.append("             AND b.comp_no = a.comp_no)                   empDesc		   	");
        query.append("        , a.start_date || ' ' || a.start_time     		pmiFromTime	   	");
        query.append("        , a.end_date || ' ' || a.end_time                 pmiToTime		");
        query.append("        , a.work_time                   					woTimeMin		");
        query.append("        , a.remark    									remark 		 	");
        query.append("FROM TAPMINSLIST a            											");
        query.append("WHERE 1=1                                         						");
        query.append(this.getWhere(maWoDailyDetailDTO));
        query.getOrderByQuery("1", maWoDailyDetailDTO.getOrderBy(), maWoDailyDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoDailyDetailDTO.getIsLoadMaxCount(), maWoDailyDetailDTO.getFirstRow()));

    }
    
    private String getWhere(MaWoDailyDetailDTO maWoDailyDetailDTO)
    {        
        QueryBuffer query = new QueryBuffer();
      
        query.getAndQuery("a.comp_no", maWoDailyDetailDTO.getCompNo());
        
    	if (!"".equals(maWoDailyDetailDTO.getPminslistId()))
        {
            query.getAndQuery("a.pminslist_id", maWoDailyDetailDTO.getPminslistId());
            return query.toString();
        }
        
//        query.getAndQuery("a.wkor_date", CommonUtil.dateToData(maWoDailyDetailDTO.getWoDate()));
        String startFdateTime = ("".equals(maWoDailyDetailDTO.getStartFdate())?"000000":maWoDailyDetailDTO.getStartFdate().replaceAll("-", ""))
                +("".equals(maWoDailyDetailDTO.getStartFtime())?"0000":maWoDailyDetailDTO.getStartFtime().replaceAll(":", ""));
        String startEdateTime = ("".equals(maWoDailyDetailDTO.getStartEdate())?"000000":maWoDailyDetailDTO.getStartEdate().replaceAll("-", ""))
                +("".equals(maWoDailyDetailDTO.getStartEtime())?"0000":maWoDailyDetailDTO.getStartEtime().replaceAll(":", ""));
        query.getAndDateQuery("NVL(a.start_date,'000000')||NVL(a.start_time,'0000')", startFdateTime, startEdateTime);
        query.getAndQuery("a.dept_id", maWoDailyDetailDTO.getWoDeptId());
        query.getAndQuery("a.plant", maWoDailyDetailDTO.getPlant());
        query.getAndQuery("a.wkctr_id", maWoDailyDetailDTO.getWkCtrId());
        query.getAndQuery("a.equip_id", maWoDailyDetailDTO.getEquipId());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception {
		
		QueryBuffer query = new QueryBuffer();

        query.append("SELECT					");
        query.append("       COUNT(1)           ");
        query.append("FROM TAPMINSLIST a        ");
        query.append("WHERE 1=1					");
        query.append(this.getWhere(maWoDailyDetailDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}