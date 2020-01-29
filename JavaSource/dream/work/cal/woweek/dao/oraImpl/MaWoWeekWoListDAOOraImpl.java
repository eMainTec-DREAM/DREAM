package dream.work.cal.woweek.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.cal.woweek.dao.MaWoWeekWoListDAO;
import dream.work.cal.woweek.dto.MaWoWeekWoCommonDTO;

/**
 * 주간작업일정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaWoWeekWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoWeekWoListDAOTarget"
 * @spring.txbn id="maWoWeekWoListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoWeekWoListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoWeekWoListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoWeekWoCommonDTO
     * @return List
     */
    public List findSchedList(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user)
    {
    	String yyyymmdd    = maWoWeekWoCommonDTO.getYyyymmdd();
        String compNo      = user.getCompNo();
        String deptId      = maWoWeekWoCommonDTO.getDeptId();
        String deptDesc    = maWoWeekWoCommonDTO.getDeptDesc();
    	String type        = maWoWeekWoCommonDTO.getSchedType();
        String eqLocId     = maWoWeekWoCommonDTO.getEqLocId();
        String eqLocDesc   = maWoWeekWoCommonDTO.getEqLocDesc();
        String eqCtgId     = maWoWeekWoCommonDTO.getEqCtgId();
        String eqCtgDesc   = maWoWeekWoCommonDTO.getEqCtgDesc();
        String mainMngId   = maWoWeekWoCommonDTO.getMainMngId();
        String mainMngName = maWoWeekWoCommonDTO.getMainMngName();
        String subMngId    = maWoWeekWoCommonDTO.getSubMngId();
        String subMngName  = maWoWeekWoCommonDTO.getSubMngName();
        String isLawEq     = maWoWeekWoCommonDTO.getIsLawEq();
        String plfTypeId   = maWoWeekWoCommonDTO.getPlfTypeId();
        String plfTypeDesc = maWoWeekWoCommonDTO.getPlfTypeDesc();
        String woTypeId    = maWoWeekWoCommonDTO.getWoTypeId();
        String woTypeDesc  = maWoWeekWoCommonDTO.getWoTypeDesc();
        String pmTypeId    = maWoWeekWoCommonDTO.getPmTypeId();
        String pmTypeDesc  = maWoWeekWoCommonDTO.getPmTypeDesc();
        String wkCtrId     = maWoWeekWoCommonDTO.getWkCtrId();
        String wkCtrDesc   = maWoWeekWoCommonDTO.getWkCtrDesc();
        String eqCtgTypeId = maWoWeekWoCommonDTO.getEqCtgTypeId();
        String eqCtgTypeDesc= maWoWeekWoCommonDTO.getEqCtgTypeDesc();
        String equipId     = maWoWeekWoCommonDTO.getEquipId();
        String equipDesc   = maWoWeekWoCommonDTO.getEquipDesc();
        String shiftId     = maWoWeekWoCommonDTO.getShiftId();
        String shiftDesc   = maWoWeekWoCommonDTO.getShiftDesc();
        String clickedWoType = maWoWeekWoCommonDTO.getClickedWoType();
        String plantId     = maWoWeekWoCommonDTO.getPlantId();
        String plantDesc   = maWoWeekWoCommonDTO.getPlantDesc();
    	if("T".equals(type)){
    		type = "";
    	}
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("WITH eqipInfo AS (                                                            		");
        query.append("     SELECT  		");
        query.append("     a.equip_id,                                                              		");
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
        query.append("        ''                                                          seqNo,        	");
        query.append("        ''                                                          isDelCheck,    	");
        query.append("        x.wkor_id                                                   wkOrId,        	");
        query.append("        x.wo_no                                                     woNo,        		");
        query.append("        x.description                                               wkOrDesc,    		");
        query.append("      x.wkor_date                                                   startDate,  		");
        query.append("      x.start_date||x.start_time                                    startDatetime,	");
        query.append("      x.end_date||x.end_time                                        endDatetime,		");
        query.append("      (SELECT c.full_desc                                								");
        query.append("          FROM TAEQLOC c                                     							");
        query.append("          WHERE y.comp_no = c.comp_no                                         		");
        query.append("              AND z.eqloc_id = c.eqloc_id  )                   								  AS eqLocDesc,   		");
        query.append("        TO_CHAR(DECODE(z.eqctg_type,'MD','('||z.old_eq_no||')'||z.description, z.description))  AS equipDesc,  		");
        query.append("        z.item_no                                                                               AS equipNo,      		");
        query.append("        (SELECT description                                                        		");
        query.append("           FROM TADEPT                                                            		");
        query.append("          WHERE comp_no = x.comp_no                                                		");
        query.append("            AND dept_id = x.dept_id)                                 deptDesc,    		");
        query.append("         (SELECT description                                                    			");
        query.append("          FROM TAWKCTR                                                            		");
        query.append("          WHERE comp_no = x.comp_no                                                		");
        query.append("          AND wkctr_id = x.wkctr_id)                                 	wkCtrDesc,    		");
        query.append("        SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+user.getLangId()+"')      shiftDesc,    		");
        query.append("        SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"')            woTypeDesc,    		");
        query.append("        SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+user.getLangId()+"')   pmTypeDesc,    		");
        query.append("        x.work_time                                                 	workTime,    		");
        query.append("        TO_CHAR(TO_DATE(x.start_time,'hh24mi'),'hh24:mi')             startTime,    		");
        query.append("        TO_CHAR(TO_DATE(x.end_time,'hh24mi'),'hh24:mi')               endTime,    		");
        query.append("        (SELECT a.lndn_start_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no  	  		");
        query.append("            AND a.wkor_id = x.wkor_id) prodStartTime,                            			");
        query.append("        (SELECT a.lndn_end_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no        		");
        query.append("            AND a.wkor_id = x.wkor_id) prodEndTime,                                		");
        query.append("        SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"')        woStatusDesc,  		");
        query.append("        x.wo_status                                                	woStatus,      		");
        query.append("        (SELECT emp_name                                                        			");
        query.append("           FROM TAEMP                                                            			");
        query.append("          WHERE comp_no = x.comp_no                                                		");
        query.append("            AND emp_id = x.emp_id)                                     empDesc,    		");
        query.append("        (SELECT c.emp_name                                    							");
        query.append("            FROM TAEMP c                            										");
        query.append("            WHERE y.comp_no = c.comp_no                                        		");
        query.append("                AND z.sub_mng_id = c.emp_id ) AS subMng,                        		");
        query.append("        (SELECT c.description                             							");
        query.append("            FROM TAEQCTG c                                        					");
        query.append("            WHERE y.comp_no = c.comp_no                                       		");
        query.append("                ANd z.eqctg_id = c.eqctg_id   )             AS eqCtgDesc,      		");
        query.append("        x.perform                                                     perform,    	");
        query.append("      x.wo_type                                                    woType,        	");
        query.append("      x.pm_type                                                   pmType,        		");
        query.append("      x.wo_gen_type                                                woGenType,    		");
        query.append("      (SELECT param1 FROM TACDSYSD WHERE list_Type= x.wo_type||'_TYPE' AND cdsysd_no=x.pm_type) param,		");
        query.append("      (SELECT b.pm_no                                                         		");
        query.append("       FROM TAPMSCHED a INNER JOIN TAPMLST b                                  		");
        query.append("       ON a.comp_no=b.comp_no AND a.pm_id=b.pm_id                             		");
        query.append("       WHERE a.comp_no=x.comp_no AND a.wkor_id=x.wkor_id) pmNo                		");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                             ");
        query.append("ON x.comp_no = y.comp_no                                                              ");
        query.append(" AND x.wkor_id = y.wkor_id                                         					");
        query.append("		INNER JOIN TAEQUIPMENT z                                              			");
        query.append("      ON y.comp_no = z.comp_no                                                		");
        query.append("       AND y.equip_id = z.equip_id													");
        query.append("WHERE 1=1  		");

        if("1".equals(maWoWeekWoCommonDTO.getIsBd())){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id FROM TAWOPOINT a WHERE a.comp_no=x.comp_no AND a.pm_point_rep_status='BD') ");
        }
        
        query.append(this.getWhere(yyyymmdd,type,compNo,deptId,deptDesc, eqLocId, eqLocDesc
        		,eqCtgId, eqCtgDesc, mainMngId, mainMngName, subMngId, subMngName
        		,plfTypeId,plfTypeDesc, isLawEq,woTypeId,woTypeDesc,pmTypeId,pmTypeDesc,equipId,equipDesc,maWoWeekWoCommonDTO,clickedWoType
        		,wkCtrId,wkCtrDesc,eqCtgTypeId,eqCtgTypeDesc,user,shiftId,shiftDesc,plantId,plantDesc));

        query.getOrderByQuery("x.wkor_date, x.description", maWoWeekWoCommonDTO.getOrderBy(), maWoWeekWoCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoWeekWoCommonDTO.getIsLoadMaxCount(), maWoWeekWoCommonDTO.getFirstRow()));
        
    }
    
    
    public String findTotalCount(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user)
    {
    	String yyyymmdd    = maWoWeekWoCommonDTO.getYyyymmdd();
        String compNo      = user.getCompNo();
        String deptId      = maWoWeekWoCommonDTO.getDeptId();
        String deptDesc    = maWoWeekWoCommonDTO.getDeptDesc();
    	String type        = maWoWeekWoCommonDTO.getSchedType();
        String eqLocId     = maWoWeekWoCommonDTO.getEqLocId();
        String eqLocDesc   = maWoWeekWoCommonDTO.getEqLocDesc();
        String eqCtgId     = maWoWeekWoCommonDTO.getEqCtgId();
        String eqCtgDesc   = maWoWeekWoCommonDTO.getEqCtgDesc();
        String mainMngId   = maWoWeekWoCommonDTO.getMainMngId();
        String mainMngName = maWoWeekWoCommonDTO.getMainMngName();
        String subMngId    = maWoWeekWoCommonDTO.getSubMngId();
        String subMngName  = maWoWeekWoCommonDTO.getSubMngName();
        String isLawEq     = maWoWeekWoCommonDTO.getIsLawEq();
        String plfTypeId   = maWoWeekWoCommonDTO.getPlfTypeId();
        String plfTypeDesc = maWoWeekWoCommonDTO.getPlfTypeDesc();
        String woTypeId    = maWoWeekWoCommonDTO.getWoTypeId();
        String woTypeDesc  = maWoWeekWoCommonDTO.getWoTypeDesc();
        String pmTypeId    = maWoWeekWoCommonDTO.getPmTypeId();
        String pmTypeDesc  = maWoWeekWoCommonDTO.getPmTypeDesc();
        String wkCtrId     = maWoWeekWoCommonDTO.getWkCtrId();
        String wkCtrDesc   = maWoWeekWoCommonDTO.getWkCtrDesc();
        String eqCtgTypeId = maWoWeekWoCommonDTO.getEqCtgTypeId();
        String eqCtgTypeDesc= maWoWeekWoCommonDTO.getEqCtgTypeDesc();
        String equipId     = maWoWeekWoCommonDTO.getEquipId();
        String equipDesc   = maWoWeekWoCommonDTO.getEquipDesc();
        String shiftId     = maWoWeekWoCommonDTO.getShiftId();
        String shiftDesc   = maWoWeekWoCommonDTO.getShiftDesc();
        String clickedWoType = maWoWeekWoCommonDTO.getClickedWoType();
        String plantId     = maWoWeekWoCommonDTO.getPlantId();
        String plantDesc   = maWoWeekWoCommonDTO.getPlantDesc();
    	if("T".equals(type)){
    		type = "";
    	}
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																		");
        query.append("		COUNT(1)                                                            	");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                     ");
        query.append("ON x.comp_no = y.comp_no                                                      ");
        query.append(" AND x.wkor_id = y.wkor_id                                         			");
        query.append("		INNER JOIN TAEQUIPMENT z                                              	");
        query.append("      ON y.comp_no = z.comp_no                                                ");
        query.append("       AND y.equip_id = z.equip_id											");
        query.append("WHERE 1=1  																	");

        if("1".equals(maWoWeekWoCommonDTO.getIsBd())){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id FROM TAWOPOINT a WHERE a.comp_no=x.comp_no AND a.pm_point_rep_status='BD') ");
        }
        query.append(this.getWhere(yyyymmdd,type,compNo,deptId,deptDesc, eqLocId, eqLocDesc
        		,eqCtgId, eqCtgDesc, mainMngId, mainMngName, subMngId, subMngName
        		,plfTypeId,plfTypeDesc, isLawEq,woTypeId,woTypeDesc,pmTypeId,pmTypeDesc,equipId,equipDesc,maWoWeekWoCommonDTO,clickedWoType
        		,wkCtrId,wkCtrDesc,eqCtgTypeId,eqCtgTypeDesc,user,shiftId,shiftDesc,plantId,plantDesc));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    
    
    /**
     * find Schedule
     * @author  kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoWeekWoCommonDTO
     * @return List
     */
    public String[][] findSchedule(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
//        String yyyymm      = maWoWeekWoCommonDTO.getFilterYyyymm().substring(0, 6);
        String startDate   = maWoWeekWoCommonDTO.getStartWkorDate();
        String endDate     = maWoWeekWoCommonDTO.getEndWkorDate();
        String compNo      = user.getCompNo();
        String deptId      = maWoWeekWoCommonDTO.getFilterDeptId();
        String deptDesc    = maWoWeekWoCommonDTO.getFilterDeptDesc();
        String eqLocId     = maWoWeekWoCommonDTO.getFilterEqLocId();
        String eqLocDesc   = maWoWeekWoCommonDTO.getFilterEqLocDesc();
        String eqCtgId     = maWoWeekWoCommonDTO.getFilterEqCtgId();
        String eqCtgDesc   = maWoWeekWoCommonDTO.getFilterEqCtgDesc();
        String mainMngId   = maWoWeekWoCommonDTO.getFilterMainMngId();
        String mainMngName = maWoWeekWoCommonDTO.getFilterMainMngName();
        String subMngId    = maWoWeekWoCommonDTO.getFilterSubMngId();
        String subMngName  = maWoWeekWoCommonDTO.getFilterSubMngName();
        String isLawEq     = maWoWeekWoCommonDTO.getFilterIsLawEq();
        String plfTypeId   = maWoWeekWoCommonDTO.getFilterPlfTypeId();
        String plfTypeDesc = maWoWeekWoCommonDTO.getFilterPlfTypeDesc();
        String woTypeId    = maWoWeekWoCommonDTO.getFilterWoTypeId();
        String woTypeDesc  = maWoWeekWoCommonDTO.getFilterWoTypeDesc();
        String pmTypeId    = maWoWeekWoCommonDTO.getFilterPmTypeId();
        String pmTypeDesc  = maWoWeekWoCommonDTO.getFilterPmTypeDesc();
        String wkCtrId     = maWoWeekWoCommonDTO.getFilterWkCtrId();
        String wkCtrDesc   = maWoWeekWoCommonDTO.getFilterWkCtrDesc();
        String eqCtgTypeId = maWoWeekWoCommonDTO.getFilterEqCtgTypeId();
        String eqCtgTypeDesc= maWoWeekWoCommonDTO.getFilterEqCtgTypeDesc();
        String equipId     = maWoWeekWoCommonDTO.getFilterEquipId();
        String equipDesc   = maWoWeekWoCommonDTO.getFilterEquipDesc();
        String shiftId     = maWoWeekWoCommonDTO.getFilterShiftId();
        String shiftDesc   = maWoWeekWoCommonDTO.getFilterShiftDesc();
        String plantId     = maWoWeekWoCommonDTO.getFilterPlantId();
        String plantDesc   = maWoWeekWoCommonDTO.getFilterPlantDesc();
        String total   = "T";
        String completed = "C";
        String incomplete = "P";

        query.append("SELECT x.tday 								day							");
        query.append("		 ,CEIL((TO_NUMBER(SUBSTRB(x.tday, -2, 2)) + 7						");
        query.append("		 - to_number(TO_CHAR(TO_DATE(x.tday,'YYYYMMDD'),'D')))/7) week		");
        query.append("		 ,x.dow 									dow						");
        query.append("		 ,SUM(DECODE(y.woStatus,'"+total+"',1,0)) 		totalt				");
        query.append("		 ,SUM(DECODE(y.woStatus,'"+completed+"',1,0)) 	totalc				");
        query.append("		 ,SUM(CASE WHEN y.woStatus IN ('P','PPWDA','PPWRA','PPWOA','PRW','PRWDA','PRWRA','PRWOA') THEN 1 ELSE 0 END) 	totalp	");
//        query.append("		 ,SUM(DECODE(y.woStatus,'"+incomplete+"',1,0)) 	totalp				");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+total+"BM"+"',1,0)) 		bmt	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+completed+"BM"+"',1,0)) 	bmc	");
        query.append("		 ,SUM(CASE WHEN y.woStatus||y.woType IN ('PBM','PPWDABM','PPWRABM','PPWOABM','PRWBM','PRWDABM','PRWRABM','PRWOABM') THEN 1 ELSE 0 END) 	bmp	");
//        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+incomplete+"BM"+"',1,0)) 	bmp	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+total+"PM"+"',1,0)) 		pmt	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+completed+"PM"+"',1,0)) 	pmc	");
        query.append("		 ,SUM(CASE WHEN y.woStatus||y.woType IN ('PPM','PPWDAPM','PPWRAPM','PPWOAPM','PRWPM','PRWDAPM','PRWRAPM','PRWOAPM') THEN 1 ELSE 0 END) 	pmp	");
//        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+incomplete+"PM"+"',1,0)) 	pmp	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+total+"CM"+"',1,0)) 		cmt	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+completed+"CM"+"',1,0)) 	cmc	");
        query.append("		 ,SUM(CASE WHEN y.woStatus||y.woType IN ('PCM','PPWDACM','PPWRACM','PPWOACM','PRWCM','PRWDACM','PRWRACM','PRWOACM') THEN 1 ELSE 0 END) 	cmp	");
//        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+incomplete+"CM"+"',1,0)) 	cmp	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+total+"IV"+"',1,0)) 		ivt	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+completed+"IV"+"',1,0)) 	ivc	");
        query.append("		 ,SUM(CASE WHEN y.woStatus||y.woType IN ('PIV','PPWDAIV','PPWRAIV','PPWOAIV','PRWIV','PRWDAIV','PRWRAIV','PRWOAIV') THEN 1 ELSE 0 END) 	ivp	");
//        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+incomplete+"IV"+"',1,0)) 	ivp	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+total+"TR"+"',1,0)) 		trt	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+completed+"TR"+"',1,0)) 	trc	");
        query.append("		 ,SUM(CASE WHEN y.woStatus||y.woType IN ('PTR','PPWDATR','PPWRATR','PPWOATR','PRWTR','PRWDATR','PRWRATR','PRWOATR') THEN 1 ELSE 0 END) 	trp	");
//        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+incomplete+"TR"+"',1,0)) 	trp	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+total+"TI"+"',1,0)) 		tit	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+completed+"TI"+"',1,0)) 	tic	");
        query.append("		 ,SUM(CASE WHEN y.woStatus||y.woType IN ('PTI','PPWDATI','PPWRATI','PPWOATI','PRWTI','PRWDATI','PRWRATI','PRWOATI') THEN 1 ELSE 0 END) 	tip	");
//        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+incomplete+"TI"+"',1,0)) 	tip	");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+total+"PMC"+"',1,0)) 		pmct");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+completed+"PMC"+"',1,0)) 	pmcc");
        query.append("		 ,SUM(CASE WHEN y.woStatus||y.woType IN ('PPMC','PPWDAPMC','PPWRAPMC','PPWOAPMC','PRWPMC','PRWDAPMC','PRWRAPMC','PRWOAPMC') THEN 1 ELSE 0 END) pmcp ");
//        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+incomplete+"PMC"+"',1,0)) 	pmcp");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+total+"PMW"+"',1,0)) 		pmwt");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+completed+"PMW"+"',1,0)) 	pmwc");
        query.append("		 ,SUM(CASE WHEN y.woStatus||y.woType IN ('PPMW','PPWDAPMW','PPWRAPMW','PPWOAPMW','PRWPMW','PRWDAPMW','PRWRAPMW','PRWOAPMW') THEN 1 ELSE 0 END) pmwp ");
//        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+incomplete+"PMW"+"',1,0)) 	pmwp");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+total+"PMI"+"',1,0)) 		pmit");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+completed+"PMI"+"',1,0)) 		");
        
//        query.append("		  ||','||(SELECT NVL(SUM(COUNT(DISTINCT a.wkor_id)),0)				");
//        query.append("				  FROM TAWORKORDER a, TAWOPOINT b							");
//        query.append("				  WHERE a.comp_no = b.comp_no								");
//        query.append("				  AND a.wkor_id = b.wkor_id									");
//        query.append("				  AND a.wkor_date= x.tday									");
//        query.append("				  AND b.pm_point_rep_status='BD'							");
//        query.append("				  AND a.wo_status='C'										");
//        query.append("				  AND a.wo_type='PMI'										");
//        query.append("				  AND a.pm_type='INS'										");
//        query.append("				  AND a.wkor_id IN (SELECT x.wkor_id FROM TAWORKORDER x		");
//        query.append("				  WHERE 1=1 												");
//        query.append(this.getWhere("","",compNo,deptId,deptDesc, eqLocId, eqLocDesc
//        		,eqCtgId, eqCtgDesc, mainMngId, mainMngName, subMngId, subMngName
//        		,plfTypeId,plfTypeDesc, isLawEq,woTypeId,woTypeDesc,pmTypeId,pmTypeDesc,equipId,equipDesc,maWoWeekWoCommonDTO,"",wkCtrId,wkCtrDesc,eqCtgTypeId,eqCtgTypeDesc, user
//        		,shiftId, shiftDesc));
//        query.append("				  )														 	");
//        query.append("				  GROUP BY a.wkor_id)										");
        
        query.append("		pmic																");
        query.append("		 ,SUM(CASE WHEN y.woStatus||y.woType IN ('PPMI','PPWDAPMI','PPWRAPMI','PPWOAPMI','PRWPMI','PRWDAPMI','PRWRAPMI','PRWOAPMI') THEN 1 ELSE 0 END) pmip ");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+incomplete+"PMI"+"',1,0)) 	pmip");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+total+"PMP"+"',1,0)) 		pmpt");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+completed+"PMP"+"',1,0)) 	pmpc");
        query.append("		 ,SUM(CASE WHEN y.woStatus||y.woType IN ('PPMP','PPWDAPMP','PPWRAPMP','PPWOAPMP','PRWPMP','PRWDAPMP','PRWRAPMP','PRWOAPMI') THEN 1 ELSE 0 END) pmpp ");
        query.append("		 ,SUM(DECODE(y.woStatus||y.woType,'"+incomplete+"PMP"+"',1,0)) 	pmpp");
        query.append(" FROM TADAY x, (															");
        query.append("					SELECT x.wkor_date				startDate,				");
        query.append("							'"+total+"'				woStatus,				");
        query.append("							x.wo_type				woType					");
        query.append("					FROM TAWORKORDER x										");
        query.append("					WHERE 1=1												");
        query.append(this.getWhere("","",compNo,deptId,deptDesc, eqLocId, eqLocDesc
        		,eqCtgId, eqCtgDesc, mainMngId, mainMngName, subMngId, subMngName
        		,plfTypeId,plfTypeDesc, isLawEq,woTypeId,woTypeDesc,pmTypeId,pmTypeDesc,equipId,equipDesc,maWoWeekWoCommonDTO,"",wkCtrId,wkCtrDesc,eqCtgTypeId,eqCtgTypeDesc, user
        		,shiftId, shiftDesc,plantId,plantDesc));
        query.append("AND wo_type IN (SELECT cdsysd_no FROM tacdsysd WHERE list_type='WO_TYPE' AND is_use='Y')       ");
        query.append("					UNION ALL												");
        query.append("					SELECT x.wkor_date				startDate,				");
        query.append("							'"+completed+"'			woStatus,				");
        query.append("							x.wo_type				woType					");
        query.append("					FROM TAWORKORDER x										");
        query.append("					WHERE 1=1												");
        query.append(this.getWhere("",completed,compNo,deptId,deptDesc, eqLocId, eqLocDesc
        		,eqCtgId, eqCtgDesc, mainMngId, mainMngName, subMngId, subMngName
        		,plfTypeId,plfTypeDesc, isLawEq,woTypeId,woTypeDesc,pmTypeId,pmTypeDesc,equipId,equipDesc,maWoWeekWoCommonDTO,"",wkCtrId,wkCtrDesc,eqCtgTypeId,eqCtgTypeDesc, user
        		,shiftId, shiftDesc,plantId,plantDesc));
        query.append("AND wo_type IN (SELECT cdsysd_no FROM tacdsysd WHERE list_type='WO_TYPE' AND is_use='Y')       ");
        query.append("					UNION ALL												");
        query.append("					SELECT x.wkor_date				startDate,				");
        query.append("							'"+incomplete+"'		woStatus,				");
        query.append("							x.wo_type				woType					");
        query.append("					FROM TAWORKORDER x										");
        query.append("					WHERE 1=1												");
        query.append(this.getWhere("",incomplete,compNo,deptId,deptDesc, eqLocId, eqLocDesc
        		,eqCtgId, eqCtgDesc, mainMngId, mainMngName, subMngId, subMngName
        		,plfTypeId,plfTypeDesc, isLawEq,woTypeId,woTypeDesc,pmTypeId,pmTypeDesc,equipId,equipDesc,maWoWeekWoCommonDTO,"",wkCtrId,wkCtrDesc,eqCtgTypeId,eqCtgTypeDesc, user
        		,shiftId, shiftDesc,plantId,plantDesc));
        query.append("AND wo_type IN (SELECT cdsysd_no FROM tacdsysd WHERE list_type='WO_TYPE' AND is_use='Y')       ");
        query.append("				) y															");
        query.append("WHERE x.tday = y.startDate(+)												");//wkor_date
        query.getAndDateQuery("x.tday", startDate, endDate);
        query.append("GROUP BY x.tday, x.week,x.dow												");
        query.append("ORDER BY x.tday															");
        
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.toStringArray(resultList);
    }
    public String[][] findWoType(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT cdsysd_no cdsysd_no,			");
        query.append("       nvl((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+loginUser.getLangId()+"'		");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       description,										");
    	query.append("		is_use isUse					");
    	query.append("	FROM TACDSYSD x						");
    	query.append("	WHERE 1=1							");
    	query.getAndQuery("list_type", "WO_TYPE");
    	
    	List resultList = getJdbcTemplate().queryForList(query.toString());
    	return QueryBuffer.toStringArray(resultList);
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param day
     * @param status
     * @param compNo
     * @param deptId
     * @param deptDesc
     * @return
     * @throws Exception
     */
    private String getWhere(String day, String status, String compNo, String deptId, String deptDesc,
    		String eqLocId, String eqLocDesc, String eqCtgId, String eqCtgDesc, String mainMngId, String mainMngName,
    		String subMngId, String subMngName,String plfTypeId, String plfTypeDesc, String isLawEq,String woTypeId, String woTypeDesc
    		,String pmTypeId, String pmTypeDesc,String equipId, String equipDesc,MaWoWeekWoCommonDTO maWoWeekWoCommonDTO,String clickedWoType
    		,String wkCtrId, String wkCtrDesc,String eqCtgTypeId, String eqCtgTypeDesc, User user, String shiftId, String shiftDesc
    		,String plantId, String plantDesc)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(maWoWeekWoCommonDTO.getCreatedWkorId()))
        {
            query.getAndQuery("x.wkor_id", maWoWeekWoCommonDTO.getCreatedWkorId());
            return query.toString();
        }
        
        //날짜 전체 검색(리스트조회 사용)
        if (day.length()==8) {
        	query.append("  AND x.wkor_date = '"+day+"'						");
		}
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
    	//법정설비여부
    	if(!"".equals(isLawEq)){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  					");
        	query.append("					FROM TAWOEQUIP a, TAEQUIPMENT b		");
        	query.append("					WHERE a.comp_no = b.comp_no			");
        	query.append("					AND a.equip_id = b.equip_id			");
        	query.getStringEqualQuery("a.comp_no", compNo);
            query.getLikeQuery("b.is_law_eq", isLawEq);
        	query.append("				)										");
        }
    	//상태
    	if("P".equals(status)){
    		query.append("AND x.wo_status IN ('P','PPWDA','PPWRA','PPWOA','PRW','PRWDA','PRWRA','PRWOA')	");
    	}else{
    		query.getAndQuery("x.wo_status",status);
    	}
    	//부서
    	query.getDeptLevelQuery("x.dept_id", deptId, deptDesc, compNo);
    	//위치
    	if(!"".equals(eqLocId)||!"".equals(eqLocDesc)){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  					");
        	query.append("					FROM TAWOEQUIP a, TAEQUIPMENT b		");
        	query.append("					WHERE a.comp_no = b.comp_no			");
        	query.append("					AND a.equip_id = b.equip_id			");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getEqLocLevelQuery("b.eqloc_id", eqLocId, eqLocDesc, compNo);
        	query.append("				)										");
        }
    	//종류
    	if(!"".equals(eqCtgId)||!"".equals(eqCtgDesc)){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  					");
        	query.append("					FROM TAWOEQUIP a, TAEQUIPMENT b		");
        	query.append("					WHERE a.comp_no = b.comp_no			");
        	query.append("					AND a.equip_id = b.equip_id			");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getEqCtgLevelQuery("b.eqctg_id", eqCtgId, eqCtgDesc, compNo); 
        	query.append("				)										");
        }
    	//담당자(정)
    	if(!"".equals(mainMngId)||!"".equals(mainMngName)){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  					");
        	query.append("					FROM TAWOEQUIP a, TAEQUIPMENT b		");
        	query.append("					WHERE a.comp_no = b.comp_no			");
        	query.append("					AND a.equip_id = b.equip_id			");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getCodeLikeQuery("b.main_mng_id", 
        			"(SELECT emp_name FROM TAEMP WHERE emp_id = b.main_mng_id AND comp_no = '"+compNo+"')", mainMngId, mainMngName);
        	query.append("				)										");
        }
    	//담당자(부)
    	if(!"".equals(subMngId)||!"".equals(subMngName)){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  					");
        	query.append("					FROM TAWOEQUIP a, TAEQUIPMENT b		");
        	query.append("					WHERE a.comp_no = b.comp_no			");
        	query.append("					AND a.equip_id = b.equip_id			");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getCodeLikeQuery("b.sub_mng_id", 
        			"(SELECT emp_name FROM TAEMP WHERE emp_id = b.sub_mng_id AND comp_no = '"+compNo+"')", subMngId, subMngName);
        	query.append("				)										");
        }
    	//설비
    	if(!"".equals(equipId)||!"".equals(equipDesc)){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  					");
        	query.append("					FROM TAWOEQUIP a, TAEQUIPMENT b		");
        	query.append("					WHERE a.comp_no = b.comp_no			");
        	query.append("					AND a.equip_id = b.equip_id			");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getCodeLikeQuery("b.equip_id", 
        			"b.description||b.item_no", equipId, equipDesc);
        	query.append("				)										");
        }
    	//내/외자 구분
    	if(!"".equals(plfTypeId)||!"".equals(plfTypeDesc)){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  					");
        	query.append("					FROM TAWOEQUIP a, TAEQUIPMENT b		");
        	query.append("					WHERE a.comp_no = b.comp_no			");
        	query.append("					AND a.equip_id = b.equip_id			");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getSysCdQuery("b.plf_type", plfTypeId, plfTypeDesc, "PLF_TYPE", compNo, user.getLangId());
        	query.append("				)										");
        }
        //설비유형
        if(!"".equals(eqCtgTypeId)||!"".equals(eqCtgTypeDesc)){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  					");
        	query.append("					FROM TAWOEQUIP a, TAEQUIPMENT b		");
        	query.append("					WHERE a.comp_no = b.comp_no			");
        	query.append("					AND a.equip_id = b.equip_id			");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getSysCdQuery("b.eqctg_type", eqCtgTypeId, eqCtgTypeDesc, "EQCTG_TYPE", compNo, user.getLangId());
        	query.append("				)										");
        }
    	//작업형태
    	query.getSysCdQuery("x.pm_type", pmTypeId, pmTypeDesc, "x.wo_type||'_TYPE'", compNo, user.getLangId());
        //작업종류
      	query.getSysCdQuery("x.wo_type", woTypeId, woTypeDesc, "WO_TYPE", compNo, user.getLangId());
      	query.getSysCdQuery("x.shift_type", shiftId, shiftDesc, "SHIFT_TYPE", compNo, user.getLangId());
      	query.getAndQuery("x.wo_type", clickedWoType);
      	//작업그룹
      	query.getWkCtrLevelQuery("x.wkctr_id", wkCtrId, wkCtrDesc, compNo);
      	
      //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
                plantId, plantDesc);
        
        //최신버전의 설비의 작업만 보여줌.
        query.append("AND NOT EXISTS (SELECT a.wkor_id  							");
    	query.append("						FROM TAWOEQUIP a, TAEQUIPMENT b			");
    	query.append("						WHERE a.comp_no = b.comp_no				");
    	query.append("						AND   a.equip_id = b.equip_id			");
    	query.append("                      AND a.wkor_id = x.wkor_id				");
    	query.getStringEqualQuery("a.comp_no", user.getCompNo());
    	query.getAndQuery("b.is_last_version", "N");
        query.append("						)										");
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int updateDeleteTagSched(String id, User user)
    {
    	int rtnValue  = 0;
    	
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAWORKORDER  SET       ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE COMP_NO = ?             ");
    	query.append("  AND WKOR_ID =      ?       ");
    	
    	Object[] objects = new Object[]{
    			 user.getEmpId()
    			,DateUtil.getDateTime()
        		,user.getCompNo()
        		,id
        };
    	rtnValue = this.getJdbcTemplate().update(query.toString(),objects);
        
    	return rtnValue;
        
    }
    public int create4wp(String wkorId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("begin                                                          ");
    	query.append("SP_WOMAKE_4WP_BYONE(                                          ");
    	query.append("                  '"+user.getCompNo()+"'          ");
    	query.append("                 ,"+wkorId+"         ");
    	query.append("                 );                                            ");
    	query.append("end;                                                           ");
    	
    	this.getJdbcTemplate().execute(query.toString());
    	
    	return 0;
    }
    
    /**
     * 리포트 바디
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List findReportWoList(String id, MaWoWeekWoCommonDTO maWoWeekWoCommonDTO ) {

		QueryBuffer query = new QueryBuffer();
		String lang = maWoWeekWoCommonDTO.getUserLang();

		query.append("SELECT																	");
        query.append("		x.wo_no woNo														");
        query.append("		,(SELECT description												");
        query.append("			FROM TADEPT														");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND dept_id = x.dept_id) deptDesc								");
        query.append("		,(SELECT emp_name													");
        query.append("			FROM TAEMP														");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND emp_id = x.emp_id) empdesc								");
        query.append("		,TO_CHAR(TO_DATE(x.start_date,'yyyymmdd'),'yyyy-mm-dd')||' '||		");
        query.append("			TO_CHAR(TO_DATE(x.start_time,'HH24MISS'),'HH24:MI')||' ~ '||	");
        query.append("			TO_CHAR(TO_DATE(x.end_date,'yyyymmdd'),'yyyy-mm-dd')||' '||		");
        query.append("			TO_CHAR(TO_DATE(x.end_time,'HH24MISS'),'HH24:MI') woDate		");
        query.append("		,x.work_time workTime												");
        query.append("		,x.description woDesc												");
        query.append("		,z.description										AS eqDesc		");
        query.append("		,(SELECT full_desc													");
        query.append("			FROM TAEQLOC													");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("				AND eqloc_id = x.eqloc_id	) 				AS eqLocDesc	");
        query.append("		,SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+lang+"') woTypeDesc				");
        query.append("		,SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+lang+"') pmTypeDesc	");
        query.append("		,(SELECT (SELECT (select b.key_name									");
        query.append("            			from talang b										");
        query.append("            			where  b.lang = '"+lang+"'							");
        query.append("            			and aa.key_type = b.key_type						");
        query.append("            			and aa.key_no = b.key_no)	description				");
        query.append("					FROM TAFAILURE aa										");
        query.append("					WHERE aa.comp_no = x.comp_no							");
        query.append("					  AND aa.failure_id = ca_cd 							");
        query.append("					  AND aa.fail_type='CA')								");
        query.append("			FROM TAWOFAIL													");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND wkor_id = x.wkor_id) caCdDesc								");
        query.append("		,(SELECT (SELECT (select b.key_name									");
        query.append("            			from talang b										");
        query.append("            			where  b.lang = '"+lang+"'							");
        query.append("            			and aa.key_type = b.key_type						");
        query.append("            			and aa.key_no = b.key_no)	description				");
        query.append("					FROM TAFAILURE aa										");
        query.append("					WHERE aa.comp_no = x.comp_no							");
        query.append("					  AND aa.failure_id = re_cd								");
        query.append("					  AND aa.fail_type='RE')								");
        query.append("			FROM TAWOFAIL													");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND wkor_id = x.wkor_id) reCdDesc								");
        query.append("		,x.perform perform,													");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woResultReport' AND key_type='LABEL') woResultReport,		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='number' AND key_type='LABEL') num,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woDate' AND key_type='LABEL') workDate,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woEquip' AND key_type='LABEL') woEquip,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipNo' AND key_type='LABEL') equipNo,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='costTime' AND key_type='LABEL') costTime,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkPoint' AND key_type='LABEL') checkPoint,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woName' AND key_type='LABEL') woName,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipName' AND key_type='LABEL') equipName,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqLocName' AND key_type='LABEL') equipLoc,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woType' AND key_type='LABEL') woType,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmType' AND key_type='LABEL') pmType,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='action' AND key_type='LABEL') action,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woRemark' AND key_type='LABEL') woRemark,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woCraft' AND key_type='LABEL') workCraft,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='empNumber' AND key_type='LABEL') empNumber,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='name' AND key_type='LABEL') empName,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='costTime' AND key_type='LABEL') costTime2,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark' AND key_type='LABEL') remark,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='insertPart' AND key_type='LABEL') insertPart,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNo' AND key_type='LABEL') partNumber,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNameSize' AND key_type='LABEL') partNameSize,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='useQty' AND key_type='LABEL') useQty,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='seqNo' AND key_type='LABEL') seqNo,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='asmbInspect' AND key_type='LABEL') asmbInspect,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkMethod' AND key_type='LABEL') checkMethod,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='fitBasis' AND key_type='LABEL') fitBasis,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkType' AND key_type='LABEL') checkType,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmPointRsltStatus' AND key_type='LABEL') pmPointRsltStatus,	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='dept' AND key_type='LABEL') dept,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='manager' AND key_type='LABEL') manager,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='reason' AND key_type='LABEL') reason,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='startDate' AND key_type='LABEL') startDate,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='endDate' AND key_type='LABEL') endDate,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='endDate' AND key_type='LABEL') endDate,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkValUom' AND key_type='LABEL') checkValUom,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmPoint' AND key_type='LABEL') pmPoint,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='resultVal' AND key_type='LABEL') resultVal					");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                             					");
        query.append("ON x.comp_no = y.comp_no                                                              					");
        query.append(" AND x.wkor_id = y.wkor_id                                         										");
        query.append("		INNER JOIN TAEQUIPMENT z                                              								");
        query.append("      ON y.comp_no = z.comp_no                                                							");
        query.append("       AND y.equip_id = z.equip_id																		");
        query.append("WHERE 1=1  																								");
        query.append(" AND x.comp_no = '"+maWoWeekWoCommonDTO.getCompNo()+"'					");
        query.append(" AND x.wkor_id = "+id+"													");

		return getJdbcTemplate().queryForList(query.toString());
	}

    /**
     * 리포트 작업자
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List findReportWoCraftList(String id, MaWoWeekWoCommonDTO maWoWeekWoCommonDTO ) {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT																		");
        query.append("		(SELECT emp_no															");
        query.append("			FROM TAEMP															");
        query.append("			WHERE comp_no = x.comp_no											");
        query.append("			  AND emp_id = x.emp_id) craftEmpNo									");
        query.append("		,(SELECT emp_name														");
        query.append("			FROM TAEMP															");
        query.append("			WHERE comp_no = x.comp_no											");
        query.append("			  AND emp_id = x.emp_id) craftEmpDesc								");
        query.append("		,TO_CHAR(TO_DATE(x.start_date,'yyyymmdd'),'yyyy-mm-dd')||' '||			");
        query.append("			TO_CHAR(TO_DATE(x.start_time,'HH24MISS'),'HH24:MI') craftStartDate	");
        query.append("		,TO_CHAR(TO_DATE(x.end_date,'yyyymmdd'),'yyyy-mm-dd')||' '||			");
        query.append("			TO_CHAR(TO_DATE(x.end_time,'HH24MISS'),'HH24:MI') craftEndDate		");
        query.append("		,TO_CHAR(x.work_time) craftWorkTime										");
        query.append("		,x.remark craftRemark													");
        query.append("FROM TAWOCRAFT x																");
        query.append("WHERE x.comp_no = '"+maWoWeekWoCommonDTO.getCompNo()+"'						");
        query.append("  AND x.wkor_id = "+id+"														");
        query.append("UNION ALL																		");
        query.append("SELECT '','','','','','' FROM DUAL											");

		return getJdbcTemplate().queryForList(query.toString());
	}

    /**
     * 리포트 투입부품
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List findReportWoPartList(String id, MaWoWeekWoCommonDTO maWoWeekWoCommonDTO ) {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT														");
        query.append("		y.part_no partNo										");
        query.append("		,y.description||', '||y.pt_size partDesc				");
        query.append("		,TO_CHAR(x.use_qty) partUseQty							");
        query.append("FROM TAWOPARTS x, TAPARTS y									");
        query.append("WHERE x.comp_no = y.comp_no									");
        query.append("  AND x.part_id = y.part_id									");
        query.append("  AND x.comp_no = '"+maWoWeekWoCommonDTO.getCompNo()+"'	");
        query.append("  AND x.wkor_id = "+id+"										");
        query.append("UNION ALL														");
        query.append("SELECT '','','' FROM DUAL										");

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * 리포트 검사항목
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List findReportWoPointList(String id, MaWoWeekWoCommonDTO maWoWeekWoCommonDTO ) {

		QueryBuffer query = new QueryBuffer();

		String lang = maWoWeekWoCommonDTO.getUserLang();
		query.append("SELECT TO_CHAR(y.step_num) AS POSEQNO,											");
        query.append("		(SELECT description															");
        query.append("		  FROM TAEQASMB																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND eqasmb_id = y.eqasmb_id)||'/'||										");
        query.append("		y.check_point AS POCHECKPOINT,												");
        query.append("		y.check_method||'/'||y.fit_basis AS POFITBASIS,								");
        query.append("		SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+lang+"') AS POCHECKTYPE,	");
        query.append("		y.check_min||'/'||y.check_basis_val||'/'||y.check_max||'('||y.uom||')' AS POUOM,");
        query.append("		DECODE(z.wo_status,'C',														");
        query.append("		SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+lang+"') ,	");
        query.append("		'')||'' AS POSTATUS,															");
        query.append("		DECODE(z.wo_status,'C', x.result_value ,'')||'' AS POVALUE						");
        query.append("FROM TAWOPOINT x, TAPMPOINT y, TAWORKORDER z										");
        query.append("WHERE x.comp_no = y.comp_no														");
        query.append("AND x.comp_no = z.comp_no															");
        query.append("AND x.wkor_id = z.wkor_id															");
        query.append("	AND x.pm_point_id = y.pm_point_id												");
        query.getAndQuery("x.wkor_id", id);
        query.getAndQuery("x.comp_no", maWoWeekWoCommonDTO.getCompNo());
        query.append("UNION ALL																			");
        query.append("SELECT '','','','','','','' FROM DUAL												");

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * 리포트 작업설비항목
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List findReportWoEqList(String id, MaWoWeekWoCommonDTO maWoWeekWoCommonDTO ) {

		QueryBuffer query = new QueryBuffer();

		query.append("SELECT (SELECT item_no 								");
        query.append("			FROM TAEQUIPMENT 							");
        query.append("			WHERE comp_no = x.comp_no					");
        query.append("			AND   equip_id = x.equip_id )  itemNumber	");
        query.append("		,(SELECT description 							");
        query.append("			FROM TAEQUIPMENT 							");
        query.append("			WHERE comp_no = x.comp_no					");
        query.append("			AND   equip_id = x.equip_id )  itemDesc		");
        query.append("FROM TAWOEQUIP x										");
        query.append("WHERE 1=1												");
        query.getAndQuery("x.wkor_id", id);
        query.getAndQuery("x.comp_no", maWoWeekWoCommonDTO.getCompNo());

		return getJdbcTemplate().queryForList(query.toString());
	}
}