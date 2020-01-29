package dream.work.rpt.mabmeqlist.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.mabmeqlist.dao.MaBmEqListDAO;
import dream.work.rpt.mabmeqlist.dto.MaBmEqListDTO;

/**
 * 설비별고장분석DAO
 * @author  kim21017
 * @version $Id: MaBmEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maBmEqListDAOTarget"
 * @spring.txbn id="maBmEqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBmEqListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaBmEqListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaBmEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmEqListDTO
     * @return List
     */
    public List findBmEqList(MaBmEqListDTO maBmEqListDTO, User user)
    {
        String lang = maBmEqListDTO.getUserLang();
        
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT *										");
        query.append("FROM											");
        query.append("(SELECT	deptDesc AS DEPTDESC				");
        query.append("          ,deptId AS DEPTID                   ");
        query.append("          ,eqLocDesc AS eqLocDesc             ");
        query.append("          ,eqLocId AS EQLOCID                 ");
        query.append("          ,eqCtg AS EQCTGDESC                 ");
        query.append("          ,eqCtgId AS EQCTGID                 ");
        query.append("          ,equipDesc AS EQDESC                ");
        query.append("          ,bmCtg AS BMCTGDESC                 ");
        query.append("          ,bmCtgId AS BMCTGID                 ");
        query.append("			,COUNT(equipId) AS BMCNT			");
        query.append("			,ROUND(SUM(bmTime)/60,2) AS BMTIME	");
        query.append("			,caCd AS CACD						");
        query.append("			,reCd AS RECDRESULT					");
        query.append("			,MAX(equipNo) AS EQUIPNO			");
        query.append(" FROM											");
        query.append("	(SELECT										");
        query.append("		(SELECT description FROM TADEPT WHERE comp_no = x.comp_no AND dept_id = x.dept_id) AS deptDesc				");
        query.append("		        ,x.dept_id AS deptId                                                                				");
        query.append("				,(SELECT full_desc FROM TAEQLOC WHERE comp_no = x.comp_no AND eqloc_id = y.eqloc_id) AS eqLocDesc	");
        query.append("				,y.eqloc_id AS eqLocId                                                                        		");
        query.append("				,(SELECT description FROM TAEQCTG WHERE comp_no = x.comp_no AND eqctg_id = y.eqctg_id) AS eqCtg		");
        query.append("				,y.eqctg_id AS eqCtgId                                                                        		");
        query.append("				,y.equip_id AS equipId																				");
        query.append("				,y.description AS equipDesc																			");
        query.append("				,(SELECT description FROM TAEQASMB WHERE eqasmb_id=													");
        query.append("						(SELECT eqasmb_id FROM TAWOFAIL WHERE comp_no = x.comp_no AND wkor_id=x.wkor_id)			");
        query.append("						AND comp_no = x.comp_no) AS bmCtg															");
        query.append("				,(SELECT eqasmb_id FROM TAWOFAIL WHERE comp_no = x.comp_no AND wkor_id=x.wkor_id) AS bmCtgId		");
        query.append("				,y.eqdn_work_time AS bmTime																				");
        query.append("				,(SELECT 																							");
        query.append("       			(select b.key_name																				");
        query.append("            			from talang b																				");
        query.append("            			where  b.lang = '"+lang+"'																	");
        query.append("            			and aa.key_type = b.key_type																");
        query.append("            			and aa.key_no = b.key_no)	description														");
        query.append("						FROM TAFAILURE aa WHERE  aa.comp_no = x.comp_no AND aa.fail_type = 'CA' AND aa.failure_id=	");
        query.append("						(SELECT ca_cd FROM TAWOFAIL WHERE comp_no = x.comp_no AND wkor_id=x.wkor_id)) AS caCd		");
        query.append("				,(SELECT 																							");
        query.append("       			(select b.key_name																				");
        query.append("            			from talang b																				");
        query.append("            			where  b.lang = '"+lang+"'																	");
        query.append("            			and aa.key_type = b.key_type																");
        query.append("            			and aa.key_no = b.key_no)	description														");
        query.append("						FROM TAFAILURE aa WHERE  aa.comp_no = x.comp_no AND aa.fail_type = 'RE' AND aa.failure_id=	");
        query.append("						(SELECT re_cd FROM TAWOFAIL WHERE comp_no = x.comp_no AND wkor_id=x.wkor_id)) AS reCd		");
        query.append("				 	  , equipNo												");
        query.append("			FROM TAWORKORDER x,												");
        query.append("		(SELECT b.eqloc_id eqloc_id											");
        query.append("				,b.eqctg_id eqctg_id										");
        query.append("				,b.comp_no comp_no											");
        query.append("				,a.wkor_id wkor_id											");
        query.append("				,b.equip_id equip_id										");
        query.append("				,b.main_mng_id main_mng_id									");
        query.append("				,b.sub_mng_id sub_mng_id									");
        query.append("				,b.is_law_eq is_law_eq										");
        query.append("				,b.description description									");
        query.append("				,b.item_no equipNo											");
        query.append("              ,c.eqdn_work_time eqdn_work_time                            ");
        query.append("		FROM TAWOEQUIP a, TAEQUIPMENT b, TAWOFAIL c							");
        query.append("		WHERE a.comp_no = b.comp_no 										");
        query.append("		AND a.equip_id = b.equip_id											");
        query.append("		AND a.comp_no = '"+maBmEqListDTO.getCompNo()+"' 					");
        query.append("      AND c.comp_no = a.comp_no                                           ");
        query.append("      AND c.wkor_id = a.wkor_id ) y                                       ");
        query.append("			WHERE x.wkor_id = y.wkor_id										");
        query.append("			  AND x.comp_no = y.comp_no										");
        query.append("            and x.wo_type='BM'                							");
        query.append("            and x.wo_status = 'C'             							");

        query.append(this.getWhere(maBmEqListDTO, user));
        query.append(")																			");
        query.append("GROUP BY  deptDesc, deptId ,eqLocDesc, eqLocId ,eqCtg, eqCtgId, equipId  ,equipDesc ,bmCtg, bmCtgId, caCd ,reCd		");
        query.append("ORDER BY deptDesc															");
        query.append(")																			");
        query.append("UNION ALL																	");
        query.append("SELECT	NVL(deptDesc,(SELECT key_name									");
        query.append("					FROM TALANG												");
        query.append("					WHERE lang='"+lang+"'									");
        query.append("					  AND key_no='total2')) AS deptDesc						");
        query.append("			,deptId															");
        query.append("			,eqLocDesc														");
        query.append("			,eqLocId														");
        query.append("			,eqCtg															");
        query.append("			,eqCtgId														");
        query.append("			,equipId														");
        query.append("			,equipDesc														");
        query.append("			,bmCtg															");
        query.append("			,bmCtgId														");
        query.append("			,bmTime															");
        query.append("			,caCd															");
        query.append("			,reCd															");
        query.append("			,equipNo														");
        query.append("FROM ( 																	");
        query.append("SELECT	MAX((SELECT key_name											");
        query.append("					FROM TALANG												");
        query.append("					WHERE lang='"+lang+"'									");
        query.append("					  AND key_no='total2')) AS deptDesc						");
        query.append("			,NULL	AS deptId												");
        query.append("			,NULL	AS eqLocDesc											");
        query.append("			,NULL	AS eqLocId												");
        query.append("			,NULL	AS eqCtg												");
        query.append("			,NULL	AS eqCtgId												");
        query.append("			,NULL	AS equipId												");
        query.append("			,NULL	AS equipDesc											");
        query.append("			,NULL	AS bmCtg												");
        query.append("			,count(x.wkor_id) AS bmCtgId									");
        query.append("			,ROUND(SUM(y.eqdn_work_time)/60,2) AS bmTime							");
        query.append("			,NULL	AS caCd													");
        query.append("			,NULL	AS reCd													");
        query.append("			,NULL	AS equipNo												");
        query.append("FROM TAWORKORDER x,														");
        query.append("		(SELECT b.eqloc_id eqloc_id											");
        query.append("				,b.eqctg_id eqctg_id										");
        query.append("				,b.comp_no comp_no											");
        query.append("				,a.wkor_id wkor_id											");
        query.append("				,b.equip_id equip_id										");
        query.append("				,b.main_mng_id main_mng_id									");
        query.append("				,b.sub_mng_id sub_mng_id									");
        query.append("				,b.is_law_eq is_law_eq										");
        query.append("				,b.description description									");
        query.append("				,b.item_no equipNo											");
        query.append("              ,c.eqdn_work_time eqdn_work_time                            ");
        query.append("		FROM TAWOEQUIP a, TAEQUIPMENT b, TAWOFAIL c							");
        query.append("		WHERE a.comp_no = b.comp_no 										");
        query.append("		AND a.equip_id = b.equip_id											");
        query.append("		AND a.comp_no = '"+maBmEqListDTO.getCompNo()+"' 					");
        query.append("      AND c.comp_no = a.comp_no                                           ");
        query.append("      AND c.wkor_id = a.wkor_id ) y                                       ");
        query.append("WHERE x.wkor_id = y.wkor_id												");
        query.append("	  AND x.comp_no = y.comp_no												");
        query.append("    and x.wo_type='BM'													");
        query.append("    and x.wo_status = 'C'													");
        query.append(this.getWhere(maBmEqListDTO, user));
        query.append("	)																		");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaBmEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmEqListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaBmEqListDTO maBmEqListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("substr(x.start_date,0,4)", maBmEqListDTO.getFilterYyyy().substring(0, 4));
        query.getDeptLevelQuery("x.dept_id", maBmEqListDTO.getFilterDeptId(),maBmEqListDTO.getFilterDeptDesc(), maBmEqListDTO.getCompNo());
      //위치
        query.getEqLocLevelQuery("y.eqloc_id", maBmEqListDTO.getFilterEqLocId(), maBmEqListDTO.getFilterEqLocDesc(), maBmEqListDTO.getCompNo());
        //종류
        query.getEqCtgLevelQuery("y.eqctg_id", maBmEqListDTO.getFilterEqCtgId(), maBmEqListDTO.getFilterEqCtgDesc(), maBmEqListDTO.getCompNo());
      //관리자(정)
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.main_mng_id AND aa.comp_no='"+maBmEqListDTO.getCompNo()+"')", 
        		maBmEqListDTO.getFilterMainMngId(), maBmEqListDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("y.sub_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.sub_mng_id AND aa.comp_no='"+maBmEqListDTO.getCompNo()+"')", 
        		maBmEqListDTO.getFilterSubMngId(), maBmEqListDTO.getFilterSubMngName());
      //법정설비
        query.getLikeQuery("y.is_law_eq", maBmEqListDTO.getFilterIsLawEq());
        //설비
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.main_mng_id AND aa.comp_no='"+maBmEqListDTO.getCompNo()+"')", 
        		maBmEqListDTO.getFilterMainMngId(), maBmEqListDTO.getFilterMainMngName());
        //설비
    	query.getCodeLikeQuery("y.equip_id", 
    			"y.description", maBmEqListDTO.getFilterEquipId(), maBmEqListDTO.getFilterEquipDesc());
    	
    	//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
                maBmEqListDTO.getFilterPlantId(), maBmEqListDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
}