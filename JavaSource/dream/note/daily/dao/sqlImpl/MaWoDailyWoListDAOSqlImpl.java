package dream.note.daily.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.note.daily.dao.MaWoDailyWoListDAO;
import dream.note.daily.dto.MaWoDailyDetailDTO;

/**
 * 일일작업확인 - 작업 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoDailyWoListDAOTarget"
 * @spring.txbn id="maWoDailyWoListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoDailyWoListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoDailyWoListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDailyDetailDTO
     * @return List
     */
    public List findList(MaWoDailyDetailDTO maWoDailyDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("WITH wodaylist AS (																");
        query.append("SELECT 																			");
        query.append("        a.comp_no																	");
        query.append("      , a.start_fdate																");
        query.append("      , a.start_ftime																");
        query.append("      , a.start_edate																");
        query.append("      , a.start_etime																");
        query.append("FROM TAWODAYLIST a																");
        query.append("WHERE a.comp_no = '"+user.getCompNo()+"'											");
        query.append("AND a.wodaylist_id = '"+maWoDailyDetailDTO.getWoDayListId()+"' 					");
        query.append("  )  																				");
        query.append("SELECT																			");
        query.append("		''															seqNo,			");
        query.append("		x.wkor_id													wkOrId,			");
        query.append("		x.wo_no 													woNo,			");
        query.append("		x.description 												wkOrDesc,		");
        query.getDate("x.wkor_date", "startDate,");
		query.append("        b.item_no                                                 itemNo,    		");
		query.append("        b.description 						                    AS equipDesc,	");
        query.append("       (SELECT aa.full_desc                                	     				");
        query.append("        FROM TAEQLOC aa                               	     					");
        query.append("        WHERE aa.comp_no = y.comp_no                                        		");
        query.append("         AND aa.eqloc_id = b.equip_id )                          	eqLocDesc,		");
        query.append("		(SELECT description															");
        query.append("		 FROM TADEPT																");
        query.append("		 WHERE comp_no = x.comp_no													");
        query.append("			AND dept_id = x.dept_id) 								deptDesc,		");
        query.append("		dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"') 			woTypeDesc,		");
        query.append("		dbo.SFACODE_TO_DESC(x.pm_type,x.wo_type+'_TYPE','SYS','','"+user.getLangId()+"')	pmTypeDesc,		");
        query.append("		dbo.SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"')		woStatusDesc,	");
        query.append("		x.work_time 												workTime,		");
		query.append("      (SELECT emp_name                                                      		");
        query.append("       FROM TAEMP                                                            		");
        query.append("       WHERE comp_no = x.comp_no                                              	");
        query.append("        AND emp_id = x.emp_id)                                    empDesc,    	");
        query.append("      (SELECT c.emp_name                                            				");
        query.append("       FROM TAEMP c                            									");
        query.append("       WHERE y.comp_no = c.comp_no                                        		");
        query.append("        AND b.sub_mng_id = c.emp_id)                            	AS subMng,		");
        query.append("      (SELECT c.description                                        				");
        query.append("       FROM TAEQCTG c                            									");
        query.append("       WHERE y.comp_no = c.comp_no                                        		");
        query.append("        AND b.eqctg_id = c.eqctg_id)                         		AS eqCtgDesc,	");
        query.append("      (SELECT ISNULL(y.full_desc,y.description) 									");
        query.append("         FROM TAEQASMB y 															");
        query.append("        WHERE y.comp_no = x.comp_no 												");
        query.append("          AND y.eqasmb_id = x.eqasmb_id)       					AS eqAsmbDesc,  ");
        query.append("      x.perform                                                   perform,    	");
        query.append("		(select param1 FROM tACDSYSD where list_type=x.wo_type+'_TYPE' AND cdsysd_no=x.pm_type)	param	");
        query.append("    	, x.wo_type     											woType			");
        query.append("    	, x.pm_type     											pmType			");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                         ");
        query.append("ON x.comp_no = y.comp_no                                                          ");
        query.append(" AND x.wkor_id = y.wkor_id  														");
        query.append("		INNER JOIN TAEQUIPMENT b                              						");
        query.append("      ON y.comp_no = b.comp_no                                					");
        query.append("       AND y.equip_id = b.equip_id												");
        query.append("LEFT OUTER JOIN wodaylist z ON y.comp_no = z.comp_no              				");
        query.append("WHERE 1=1																			");
        query.append(this.getWhere(maWoDailyDetailDTO));
        query.getOrderByQuery("x.wkor_id","x.wo_no", maWoDailyDetailDTO.getOrderBy(), maWoDailyDetailDTO.getDirection());
        
//        return getJdbcTemplate().queryForList(query.toString(),object);
        return getJdbcTemplate().queryForList(query.toString(maWoDailyDetailDTO.getIsLoadMaxCount(), maWoDailyDetailDTO.getFirstRow()));

    }
    
    private String getWhere(MaWoDailyDetailDTO maWoDailyDetailDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        query.getAndQuery("x.comp_no", maWoDailyDetailDTO.getCompNo());
        query.getAndQuery("x.is_deleted", "N");

    	if (!"".equals(maWoDailyDetailDTO.getWkorId()))
        {
            query.getAndQuery("x.wkor_id", maWoDailyDetailDTO.getWkorId());
            return query.toString();
        }
    	
//        query.getAndQuery("x.wkor_date", CommonUtil.dateToData(maWoDailyDetailDTO.getWoDate()));
//        String startFdateTime = ("".equals(maWoDailyDetailDTO.getStartFdate())?"000000":maWoDailyDetailDTO.getStartFdate().replaceAll("-", ""))
//                +("".equals(maWoDailyDetailDTO.getStartFtime())?"0000":maWoDailyDetailDTO.getStartFtime().replaceAll(":", ""));
//        String startEdateTime = ("".equals(maWoDailyDetailDTO.getStartEdate())?"000000":maWoDailyDetailDTO.getStartEdate().replaceAll("-", ""))
//                +("".equals(maWoDailyDetailDTO.getStartEtime())?"0000":maWoDailyDetailDTO.getStartEtime().replaceAll(":", ""));
//        query.getAndDateQuery("ISNULL(x.start_date,'000000')+ISNULL(x.start_time,'0000')", startFdateTime, startEdateTime);

    	query.append("  AND  ISNULL(x.start_date,'000000')+ISNULL(x.start_time,'0000') >= ISNULL(z.start_fdate,'000000')+ISNULL(z.start_ftime,'0000')     ");
    	query.append("  AND  ISNULL(x.start_date,'000000')+ISNULL(x.start_time,'0000') <= ISNULL(z.start_edate,'000000')+ISNULL(z.start_etime,'0000')		");
    	
        query.getAndQuery("x.dept_id", maWoDailyDetailDTO.getWoDeptId());
        query.getAndQuery("x.plant", maWoDailyDetailDTO.getPlant());
        query.getAndQuery("x.wkctr_id", maWoDailyDetailDTO.getWkCtrId());
        query.getAndQuery("y.equip_id", maWoDailyDetailDTO.getEquipId());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("WITH wodaylist AS (															");
        query.append("SELECT 																		");
        query.append("        a.comp_no																");
        query.append("      , a.start_fdate															");
        query.append("      , a.start_ftime															");
        query.append("      , a.start_edate															");
        query.append("      , a.start_etime															");
        query.append("FROM TAWODAYLIST a															");
        query.append("WHERE a.comp_no = '"+user.getCompNo()+"'										");
        query.append("AND a.wodaylist_id = '"+maWoDailyDetailDTO.getWoDayListId()+"' 				");
        query.append("  )  																			");
        query.append("SELECT																		");
        query.append("       COUNT(1)           													");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                     ");
        query.append("ON x.comp_no = y.comp_no                                                      ");
        query.append(" AND x.wkor_id = y.wkor_id  													");
        query.append("		INNER JOIN TAEQUIPMENT b                              					");
        query.append("      ON y.comp_no = b.comp_no                                				");
        query.append("       AND y.equip_id = b.equip_id											");
        query.append("LEFT OUTER JOIN wodaylist z ON y.comp_no = z.comp_no              			");
        query.append("WHERE 1=1																		");
        query.append(this.getWhere(maWoDailyDetailDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
}