package mobile.dream.work.pm.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import mobile.dream.work.pm.list.dao.WorkPmWorkListDAO;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.form.WorkPmWorkListForm;

/**
 * 계획작업 - 목록 dao
 * @author  jung7126
 * @version $Id: WorkPmWorkListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="workPmWorkListDAOTarget"
 * @spring.txbn id="workPmWorkListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmWorkListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmWorkListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: WorkPmWorkListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmWorkCommonDTO
     * @return List
     */
    public List findList(WorkPmWorkCommonDTO workPmWorkCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = workPmWorkCommonDTO.getCompNo();

        query.append("WITH eqipInfo AS (                                                            		");
        query.append("    SELECT a.equip_id,                                                        		");
        query.append("           a.wkor_id,                                                         		");
        query.append("           b.item_no,                                                         		");
        query.append("           a.comp_no,                                                         		");
        query.append("           b.sub_mng_id,                                                      		");
        query.append("           b.eqctg_id,                                                        		");
        query.append("           b.old_eq_no,                                                       		");
        query.append("           b.description ,                                                    		");
        query.append("           b.eqctg_type                                                       		");
        query.append("     FROM  TAWOEQUIP a, TAEQUIPMENT b                                         		");
        query.append("     WHERE a.comp_no = b.comp_no                                              		");
        query.append("       AND a.equip_id = b.equip_id   )                                        		");
        query.append("SELECT                                                                        		");
        query.append("        ''                                                            seqNo,        		");
        query.append("        ''                                                            isDelCheck,    		");
        query.append("      x.wkor_id                                                  		ID,         		");
        query.append("        x.wkor_id                                                     wkOrId,        		");
        query.append("        x.wo_no                                                     	woNo,        		");
        query.append("        x.description                                                 wkOrDesc,    		");
        query.getDate("x.wkor_date", "startDate,");
        query.append("        DECODE(z.eqctg_type,'MD','('||z.old_eq_no||')'||z.description, z.description) AS equipDesc,      		");
        query.append("        z.item_no AS equipNo,            		");
        query.append("        (SELECT description                                                        		");
        query.append("           FROM TADEPT                                                            		");
        query.append("          WHERE comp_no = x.comp_no                                                		");
        query.append("            AND dept_id = x.dept_id)                                 deptDesc,    		");
        query.append("         (SELECT description                                                   	 		");
        query.append("          FROM TAWKCTR                                                            		");
        query.append("          WHERE comp_no = x.comp_no                                                		");
        query.append("          AND wkctr_id = x.wkctr_id)                                 wkCtrDesc,    		");
        query.append("        SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+user.getLangId()+"')             shiftDesc,    		");
        query.append("        SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"')                 woTypeDesc,    		");
        query.append("        SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+user.getLangId()+"')         pmTypeDesc,    		");
        query.append("        x.work_time                                                 workTime,    			");
        query.append("        TO_CHAR(TO_DATE(x.start_time,'hh24mi'),'hh24:mi')            startTime,    		");
        query.append("        TO_CHAR(TO_DATE(x.end_time,'hh24mi'),'hh24:mi')                endTime,    		");
        query.append("        (SELECT a.lndn_start_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no    			");
        query.append("            AND a.wkor_id = x.wkor_id) prodStartTime,                            			");
        query.append("        (SELECT a.lndn_end_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no        		");
        query.append("            AND a.wkor_id = x.wkor_id) prodEndTime,                                		");
        query.append("        (SELECT a.lndn_work_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no    			");
        query.append("            AND a.wkor_id = x.wkor_id) lndnTime,                                			");
        query.append("        SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"')            woStatusDesc,    		");
        query.append("        x.wo_status                                                woStatus,        		");
        query.append("        (SELECT emp_name                                                        			");
        query.append("           FROM TAEMP                                                            			");
        query.append("          WHERE comp_no = x.comp_no                                                		");
        query.append("            AND emp_id = x.emp_id)                                     empDesc,    		");
        query.append("        (SELECT c.emp_name                                								");
        query.append("            FROM TAEMP c                                          						");
        query.append("            WHERE y.comp_no = c.comp_no                                       			");
        query.append("                AND z.sub_mng_id = c.emp_id )                 		subMng,    			");
        query.append("        (SELECT c.description                             								");
        query.append("            FROM TAEQCTG c                                        						");
        query.append("            WHERE y.comp_no = c.comp_no                                                  	");
        query.append("                AND z.eqctg_id = c.eqctg_id )             AS eqCtgDesc,      				");
        query.append("        SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+user.getLangId()+"') selfVendorTypeDesc,        		");
        query.append("      SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)           vendorDesc,    			");
        query.append("        x.perform                                                     perform,    		");
        query.append("      x.wo_type                                                    woType,        		");
        query.append("      x.pm_type                                                    pmType,        		");
        query.append("      x.wo_gen_type                                                woGenType,    			");
        query.append("      (SELECT param1 FROM TACDSYSD WHERE list_Type= x.wo_type||'_TYPE' AND cdsysd_no=x.pm_type) param		");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                                 ");
        query.append("ON x.comp_no = y.comp_no AND x.wkor_id = y.wkor_id                 						");
        query.append("		INNER JOIN TAEQUIPMENT z                        									");
        query.append("      ON y.comp_no = z.comp_no AND y.equip_id = z.equip_id                           		");
        query.append("WHERE 1=1                                                                                 ");
        query.append("  AND  x.comp_no = '"+compNo+"'												");
        query.append("  AND  x.wo_type = 'PMW'	         											");
        query.append(this.getWhere(workPmWorkCommonDTO,user));
        query.getOrderByQuery("4 DESC", workPmWorkCommonDTO.getOrderBy(), workPmWorkCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPmWorkCommonDTO.getIsLoadMaxCount(), workPmWorkCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  jung7126
     * @version $Id: WorkPmWorkListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmWorkCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkPmWorkCommonDTO workPmWorkCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        String[] colArr = {"x.wo_no","x.description","DECODE(y.eqctg_type,'MD','('||y.old_eq_no||')'||y.description, y.description)"};
        query.getLikeQuery(colArr, workPmWorkCommonDTO.getSearchText());
        
        //부서
        query.getDeptLevelQuery("x.dept_id", workPmWorkCommonDTO.getDeptId(), workPmWorkCommonDTO.getDeptDesc(), user.getCompNo());
        //작업그룹
        query.getWkCtrLevelQuery("x.wkctr_id", workPmWorkCommonDTO.getWkCtrId(), workPmWorkCommonDTO.getWkCtrDesc(), user.getCompNo());
        //작업일자
        query.getAndDateQuery("wkor_date", workPmWorkCommonDTO.getWoDateFrom(), workPmWorkCommonDTO.getWoDateTo());;
        //위치
        if(!"".equals(workPmWorkCommonDTO.getEqLocId())||!"".equals(workPmWorkCommonDTO.getEqLocDesc())){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  						");
        	query.append("					FROM TAWOEQUIP a, TAEQUIPMENT b			");
        	query.append("					WHERE a.comp_no = b.comp_no				");
        	query.append("					AND   a.equip_id = b.equip_id			");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getEqLocLevelQuery("b.eqloc_id", workPmWorkCommonDTO.getEqLocId(), workPmWorkCommonDTO.getEqLocDesc(), user.getCompNo());
            query.append("					)										");
        }
        //설비유형
        if(!"".equals(workPmWorkCommonDTO.getEqCtgType())||!"".equals(workPmWorkCommonDTO.getEqCtgTypeDesc())){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  						");
        	query.append("					FROM TAWOEQUIP a, TAEQUIPMENT b			");
        	query.append("					WHERE a.comp_no = b.comp_no				");
        	query.append("					AND   a.equip_id = b.equip_id			");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getSysCdQuery("b.eqctg_type", workPmWorkCommonDTO.getEqCtgType(), workPmWorkCommonDTO.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
            query.append("					)										");
        }
        
        return query.toString();
    }

	@Override
	public String findTotalCount(WorkPmWorkListForm workPmWorkListForm, User user) {
		
		QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        query.append("WITH eqipInfo AS (                                                            ");
        query.append("    SELECT a.equip_id,                                                        ");
        query.append("           a.wkor_id,                                                         ");
        query.append("           b.item_no,                                                         ");
        query.append("           a.comp_no,                                                         ");
        query.append("           b.sub_mng_id,                                                      ");
        query.append("           b.eqctg_id,                                                        ");
        query.append("           b.old_eq_no,                                                       ");
        query.append("           b.description ,                                                    ");
        query.append("           b.eqctg_type                                                       ");
        query.append("     FROM  TAWOEQUIP a, TAEQUIPMENT b                                         ");
        query.append("     WHERE a.comp_no = b.comp_no                                              ");
        query.append("       AND a.equip_id = b.equip_id   )                                        ");
        query.append("SELECT                                                                        ");
        query.append("      COUNT(1)                                                                ");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                     ");
        query.append("ON x.comp_no = y.comp_no AND x.wkor_id = y.wkor_id                 			");
        query.append("		INNER JOIN TAEQUIPMENT z                        						");
        query.append("      ON y.comp_no = z.comp_no AND y.equip_id = z.equip_id                    ");
        query.append("WHERE 1=1                                                                     ");
        query.append("  AND  x.comp_no = '"+compNo+"'                                               ");
        query.append("  AND  x.wo_type = 'PMW'	         											");
        query.append(this.getWhere(workPmWorkListForm.getWorkPmWorkCommonDTO(), user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}